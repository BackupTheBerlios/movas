/*
 * InitVideo.java
 *
 * Created on 29. Dezember 2003, 12:52
 */

package movas.Init;


import javax.media.rtp.event.*;
import com.sun.media.ui.*;

import java.awt.*;
import java.net.*;
import javax.media.*;
import javax.media.control.*;
import javax.media.datasink.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.rtp.*;
import javax.media.rtp.rtcp.*;
import javax.swing.*;
import java.util.*;
/**
 *
 * @author  MS
 */
public class InitVideo extends javax.swing.JPanel implements ReceiveStreamListener, ControllerListener{
    Player player = null;
    DataSource mediafile = null;
    int titelleiste;
    DataSink filewriter = null;
    Processor processor = null;
    boolean configured = false;
    boolean realized = false;
    SessionManager mgr=null;
    SendStream rtpstream=null;
    SendStream rtpstream2=null;
    DataSource source = null;   
    Vector playerlist = new Vector();
    private String address = null;
    private int DestPort=0;
    
    static DataSource dsource=null; 
    
    boolean terminatedbyClose = false;
    static boolean firststream = false;
    
    
    
    private static boolean			debugDeviceList = false;   
    private static String			defaultVideoDeviceName = null;
    private static String			defaultAudioDeviceName = null;   
    private static CaptureDeviceInfo            captureVideoDevice = null;
    private static CaptureDeviceInfo            captureAudioDevice = null;
    private static Structure                    struct=null;
    private static DeviceInfo                   DI=null;
    
   

    
    /** Creates new form Video */
    public InitVideo(int direction,String address,int DestPort) {
        initComponents();
        this.address=address;
        this.DestPort=DestPort;
        switch(direction){
            case movas.GUI.video.EMPFANG: InitReceive();break;
            case movas.GUI.video.VERSAND: InitSend();break;
            default: System.err.print("No Video Direction defined!");break;
        }
    }
    
    private void InitSend(){
    try{
        struct=new InitFileHandler().read();
        }catch(Exception e){e.printStackTrace();}
        DI=new DeviceInfo();   
        getDevices();
        this.setLayout(new BorderLayout());
        
                MediaLocator videoMediaLocator = captureVideoDevice.getLocator();
                DataSource videoDataSource = null;
                try
		{
                        videoDataSource = javax.media.Manager.createCloneableDataSource(javax.media.Manager.createDataSource(videoMediaLocator));
		}
		catch (Exception ie) { }
               
                
                FormatControl formatControls[] = null;
		formatControls = ((CaptureDevice) videoDataSource).getFormatControls();
		for (int x = 0; x < formatControls.length; x++)
		{
			if (formatControls[x] == null)
				continue;
                        
                        formatControls[x].setFormat(DI.getFormatByName(captureVideoDevice,struct.VideoFormat));
		}
                
                MediaLocator audioMediaLocator = captureAudioDevice.getLocator();
                DataSource audioDataSource = null;
                try
		{
			audioDataSource = javax.media.Manager.createDataSource(audioMediaLocator);
		}
		catch (Exception ie) { }
		formatControls = ((CaptureDevice) audioDataSource).getFormatControls();
                for (int x = 0; x < formatControls.length; x++)
		{
			if (formatControls[x] == null)
				continue;
                
                    formatControls[x].setFormat(DI.getFormatByName(captureAudioDevice,struct.AudioFormat));
                }
                // merge the two data sources
		// --------------------------
		DataSource mixedDataSource = null;
		try
		{
			DataSource dArray[] = new DataSource[2];
			dArray[0] = ((SourceCloneable)videoDataSource).createClone();
			dArray[1] = audioDataSource;
			mixedDataSource = javax.media.Manager.createMergingDataSource(dArray);
		}
		catch (Exception ise) {  }

                
                
                
                try {
                processor = Manager.createProcessor(mixedDataSource);
                } catch (Exception exe) {}
                processor.addControllerListener(this);
                processor.configure();
                while (!configured) {try{this.wait(100);}catch(Exception e){}}
                processor.setContentDescriptor(new ContentDescriptor( ContentDescriptor.MIXED));
                processor.getTrackControls()[0].setFormat(new VideoFormat(VideoFormat.H263_RTP));
                processor.getTrackControls()[1].setFormat(new AudioFormat(AudioFormat.GSM_RTP));
                processor.realize();
                
                while (!realized) {try{this.wait(100);}catch(Exception e){}}
		
                DataSource source = javax.media.Manager.createCloneableDataSource(processor.getDataOutput());
                DataSource source1 = ((SourceCloneable)source).createClone();
                DataSource source2 = ((SourceCloneable)source).createClone();
               // String URL = "rtp://192.168.0.3:22224/video/1";
                //MediaLocator dest = new MediaLocator(URL);
  // create a datasink to do the file writing & open the sink to
  // make sure we can write to it.
 /* DataSink rtpsender = null;
  try {
      rtpsender = Manager.createDataSink(source, dest);
      rtpsender.open();
  } catch (Exception e) {}*/
  SessionManager rtpsm=null;
  SessionManager rtpsm2=null;
  try{
  
        rtpsm = this.createManager(      address,
                                         struct.KomPort+2,
                                         128,
                                         false,
                                         true); 
        rtpsm2 = this.createManager(     address,
                                         struct.KomPort,
                                         128,
                                         false,
                                         true); 
  }catch(Exception e){e.printStackTrace();}
         // The session manager then needs to be initialized and started:
         // rtpsm.initSession(...); 
         // rtpsm.startSession(...); 
 
         try {
             rtpstream = rtpsm.createSendStream(processor.getDataOutput(),0);
             rtpstream2 = rtpsm2.createSendStream(processor.getDataOutput(),1);

         } catch (Exception e) {
             e.printStackTrace();
         } 
  
  
  
  
                try{
                processor.start();
                rtpstream.start();
                rtpstream2.start();
                //rtpsender.start();
                }catch(Exception e){}
        
                
                
                try {
           mediafile = videoDataSource;
            player = Manager.createPlayer(mediafile);
            player.addControllerListener(this);
        } catch (Exception e) {}

        repaint();
        titelleiste=this.getHeight(); 
        player.start();     
    }
    
    private void InitReceive(){
        SessionManager rtpsm = this.createManager(address,DestPort,128,true,false); 
         SessionManager rtpsm2 = this.createManager(address,DestPort+2,128,true,false); 
    }
    
    
    private SessionManager createManager(String address,
                                         int port,
                                         int ttl,
                                         boolean listener,
                                         boolean sendlistener)
     {
         mgr = (SessionManager)new com.sun.media.rtp.RTPSessionMgr();
         
         if (mgr == null) return null;
 
        //mgr.addFormat(new VideoFormat(VideoFormat.H263_RTP),10);
        //mgr.addFormat(new AudioFormat(AudioFormat.GSM_RTP),18);
 
         if (listener) mgr.addReceiveStreamListener(this);
        // if (sendlistener) new RTPSendStreamWindow(mgr);
         
         // ask session mgr to generate the local participant's CNAME
         String cname = mgr.generateCNAME();
         String username = null;
 
         try {
             username = System.getProperty("user.name");
         } catch (SecurityException e){
             username = "jmf-user";
         }
         
         // create our local Session Address
         SessionAddress localaddr = new SessionAddress();
         
         try{
             InetAddress destaddr =  InetAddress.getByName(address);
 
             SessionAddress sessaddr = new SessionAddress(destaddr,
                                                          port,
                                                          destaddr,
                                                          port + 1);
             SourceDescription[] userdesclist= new SourceDescription[]
             {
                 new SourceDescription(SourceDescription
                                       .SOURCE_DESC_EMAIL,
                                       "jmf-user@sun.com",

                                       1,
                                       false),
 
                 new SourceDescription(SourceDescription
                                       .SOURCE_DESC_CNAME,
                                       cname,
                                       1,
                                       false),
 
                 new SourceDescription(SourceDescription
                                       .SOURCE_DESC_TOOL,
                                       "JMF RTP Player v2.0",
                                       1,
                                       false)
             };
 
             mgr.initSession(localaddr,
                             userdesclist,
                             0.05,
                             0.25);
             
             
             
             
             mgr.startSession(sessaddr,ttl,null);
         } catch (Exception e) {
             System.err.println(e.getMessage());
             return null;
         }
         
         return mgr;
     }
    
    
    public void start() { 

    }
    public void stop(){
        try {
            processor.stop();
            player.stop();
            player.close();
            processor.close();
           // filewriter.close();
            mgr.closeSession("RTP Session Terminated");
            this.closeManager();
            mgr = null;
        } catch (Exception ex) {}
    }
    public void destroy() {
        this.removeAll();
        System.exit(0);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
        

        this.setLayout(new java.awt.BorderLayout());


    }
    
   
    
    public void getDevices() {
        
        captureVideoDevice=DI.getVideoDevice(struct.VideoDevice);
        captureAudioDevice=DI.getAudioDevice(struct.AudioDevice);
    }
    
    /**
     * @param args the command line arguments
     */
    
    public void controllerUpdate(javax.media.ControllerEvent event)  {
        if (event instanceof RealizeCompleteEvent) {
            realized=true;
            
            try {
            Component comp;
            if ((comp = player.getVisualComponent())!=null) this.add("Center",comp);
            if ((comp = player.getControlPanelComponent())!=null) this.add("South",comp);           
            //this.setSize(player.getVisualComponent().getPreferredSize().width+8,player.getVisualComponent().getPreferredSize().height+player.getControlPanelComponent().getPreferredSize().height+titelleiste);
            //this.setBounds(0,0,320,250);
            validate(); 
            } catch (Exception e) {}
        }
        if (event instanceof ConfigureCompleteEvent) configured = true;
       // System.out.println(event.toString());
        this.repaint();
    }    
    
     public void update( ReceiveStreamEvent event)
     {
         Player newplayer = null;
         RTPPlayerWindow playerWindow = null;
 
         // find the sourceRTPSM for this event
         SessionManager source = (SessionManager)event.getSource();
 
         // create a new player if a new recvstream is detected
         if (event instanceof NewReceiveStreamEvent)
         {
             String cname = "Java Media Player";
             ReceiveStream stream = null;
             
             try
             {
                 // get a handle over the ReceiveStream
                 stream =((NewReceiveStreamEvent)event)
                         .getReceiveStream();
 
                 Participant part = stream.getParticipant();
 
                 if (part != null) cname = part.getCNAME();
 
                 // get a handle over the ReceiveStream datasource
                 
                
                     dsource= stream.getDataSource();
                    
                 // create a player by passing datasource to the 
                 // Media Manager
                 newplayer = Manager.createPlayer(dsource);
                 System.out.println("created player " + newplayer.toString());
                
                    
                 
             } catch (Exception e) {
                 System.err.println("NewReceiveStreamEvent exception " 
                                    + e.getMessage());
                 e.printStackTrace();
                 return;
             }
 
             if (newplayer == null) return;
 
             playerlist.addElement(newplayer);
             newplayer.addControllerListener(this);
            
             // send this player to player GUI
             //playerWindow = new RTPPlayerWindow( newplayer, cname);
             newplayer.realize();
             
              boolean end=true;
                 while(end){
                    if(newplayer.getState()==javax.media.Player.Realized){
                    newplayer.start();
                    end=false;
                    }
                    try{this.wait(1000);}catch(Exception e){}
                    //System.out.println("newplayer: "+newplayer.getState());
                    
                }
                end=true;
                /*while(end){
                    if(newplayer.getState() == javax.media.Player.Started)end=false;
                    try{this.wait(100);}catch(Exception e){}
                    //System.out.println(newplayer.getState());
                }*/
                
                try{
                    newplayer.getVisualComponent().toString();
               
                    this.removeAll();
                    System.out.println(newplayer.getVisualComponent().toString());
                    this.add(BorderLayout.CENTER,newplayer.getVisualComponent());
                    this.add(BorderLayout.SOUTH,newplayer.getControlPanelComponent());
                    validate();
                    this.repaint();    
                }catch(Exception e){e.printStackTrace();}
         }
     }
     public void closeManager()
     {
         terminatedbyClose = true;
     
         // first close all the players
         for (int i = 0; i < playerlist.size(); i++) {
             ((Player)playerlist.elementAt(i)).close();
         }
         if (mgr != null) {
             mgr.closeSession("RTP Session Terminated");
             mgr = null;
         }
     }
    class RTPPlayerWindow extends PlayerWindow 
     {
         public RTPPlayerWindow( Player player, String title) 
         {
             super(player);
             setTitle(title);
         }
         public void Name(String title){
             setTitle(title);
         }
     }
    // Variables declaration - do not modify
   // private javax.swing.JPanel VPanel;
    
   // private javax.swing.JPanel jPanel1;
    // End of variables declaration
    
    
}