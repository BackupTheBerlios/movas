/*
 * InitVideo.java
 *
 * Created on 29. Dezember 2003, 12:52
 */

package movas.Init;

import java.awt.*;
import java.net.*;
import javax.media.*;
import javax.media.control.*;
import javax.media.datasink.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.swing.*;
import java.util.*;
/**
 *
 * @author  MS
 */
public class InitVideo extends javax.swing.JPanel implements ControllerListener{
    Player player = null;
    DataSource mediafile = null;
    int titelleiste;
    DataSink filewriter = null;
    Processor processor = null;
    boolean configured = false;
    boolean realized = false;
        
    private static boolean			debugDeviceList = false;   
    private static String			defaultVideoDeviceName = null;
    private static String			defaultAudioDeviceName = null;   
    private static CaptureDeviceInfo            captureVideoDevice = null;
    private static CaptureDeviceInfo            captureAudioDevice = null;
    private static Structure                    struct=null;
    private static DeviceInfo                   DI=null;
   

    
    /** Creates new form Video */
    public InitVideo() {
        initComponents();
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
                while (!configured) {try{this.wait(1000);}catch(Exception e){}}
                processor.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.MSVIDEO));
                processor.getTrackControls()[0].setFormat(new VideoFormat(VideoFormat.H263));
                processor.getTrackControls()[1].setFormat(new AudioFormat(AudioFormat.GSM_MS));
                processor.realize();
                
                while (!realized) {try{this.wait(1000);}catch(Exception e){}}
		DataSource source = processor.getDataOutput();
                
               MediaLocator dest = new MediaLocator("file:///D:/test2.avi");
  // create a datasink to do the file writing & open the sink to
  // make sure we can write to it.
  DataSink filewriter = null;
  try {
      filewriter = Manager.createDataSink(source, dest);
      filewriter.open();
  } catch (Exception e) {}
               //nur zu Testzwecken                
               /* for (int i=0;i<processor.getSupportedContentDescriptors().length;i++) {
                    System.out.println("FORMAT: "+(processor.getSupportedContentDescriptors())[i].toString());
                }
                for (int i=0;i<processor.getTrackControls().length;i++) {
                    System.out.println("TrackControl: "+(processor.getTrackControls())[i].getFormat().toString());
                }
                
                for (int i=0;i<processor.getTrackControls().length;i++) 
                for(int j=0;j<(processor.getTrackControls())[i].getSupportedFormats().length;j++){
                    System.out.println("SupportedTrackControl: "+(processor.getTrackControls())[i].getSupportedFormats()[j].toString());
                }
                
                Vector plgin = PlugInManager.getPlugInList((processor.getTrackControls())[0].getFormat(),(new VideoFormat(VideoFormat.MPEG)),PlugInManager.CODEC);
                Enumeration plg = plgin.elements();
                while (plg.hasMoreElements()) {
                    System.out.println("PLUGIN.x: "+plg.nextElement().toString());
                }*/ 
  
                try{
                processor.start();
                filewriter.start();
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
    
    public void start() { 

    }
    public void stop(){
        try {
            processor.stop();
            player.stop();
            player.close();
            processor.close();
            filewriter.close();
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
        //VPanel = new javax.swing.JPanel();
        //jPanel1 = new javax.swing.JPanel();
        

        this.setLayout(new java.awt.BorderLayout());

        //this.add(VPanel, java.awt.BorderLayout.CENTER);

        

        //this.add(jPanel1, java.awt.BorderLayout.SOUTH);

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
            this.setSize(player.getVisualComponent().getPreferredSize().width+8,player.getVisualComponent().getPreferredSize().height+player.getControlPanelComponent().getPreferredSize().height+titelleiste);
            //this.setBounds(0,0,320,250);
            validate(); 
            } catch (Exception e) {}
        }
        if (event instanceof ConfigureCompleteEvent) configured = true;
       // System.out.println(event.toString());
    }    
    
    // Variables declaration - do not modify
   // private javax.swing.JPanel VPanel;
    
   // private javax.swing.JPanel jPanel1;
    // End of variables declaration
    
    
}