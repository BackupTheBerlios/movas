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
        private static String			defaultVideoDeviceName = "vfw:Microsoft WDM Image Capture (Win32):0";
	private static String			defaultAudioDeviceName = "DirectSoundCapture";   
        private static CaptureDeviceInfo	captureVideoDevice = null;
	private static CaptureDeviceInfo	captureAudioDevice = null;
	

    
    /** Creates new form Video */
    public InitVideo() {
        initComponents();
        getDevices();
        this.setLayout(new BorderLayout());
        
                MediaLocator videoMediaLocator = captureVideoDevice.getLocator();
                DataSource videoDataSource = null;
                //DataSource videoDataSource2 = null;
                try
		{
			//videoDataSource = javax.media.Manager.createDataSource(videoMediaLocator);
                        //videoDataSource2 = javax.media.Manager.createDataSource(videoMediaLocator);
                        videoDataSource = javax.media.Manager.createCloneableDataSource(javax.media.Manager.createDataSource(videoMediaLocator));
		}
		catch (Exception ie) { }
                
                
                
                FormatControl formatControls[] = null;
		formatControls = ((CaptureDevice) videoDataSource).getFormatControls();
		for (int x = 0; x < formatControls.length; x++)
		{
			if (formatControls[x] == null)
				continue;

			Format supportedFormats[] = formatControls[x].getSupportedFormats();
			if (supportedFormats == null)
				continue;

                        for (int i=0;i<supportedFormats.length;i++)
                        System.out.println("++FORMAT -"+i+ "-: "+supportedFormats[i].toString());
                        
                        formatControls[x].setFormat(supportedFormats[18]);
		}

                
                
                
                
                MediaLocator audioMediaLocator = captureAudioDevice.getLocator();
                DataSource audioDataSource = null;
                try
		{
			audioDataSource = javax.media.Manager.createDataSource(audioMediaLocator);
		}
		catch (Exception ie) { }
		formatControls = ((CaptureDevice) audioDataSource).getFormatControls();
                Format supportedFormats[] = formatControls[0].getSupportedFormats();
                formatControls[0].setFormat(supportedFormats[27]);
                
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
                
                while (!configured) {}
                processor.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.MSVIDEO));
               
                processor.getTrackControls()[0].setFormat(new VideoFormat(VideoFormat.H263));
                Format[] VF=processor.getTrackControls()[0].getSupportedFormats();
                for (int i=0; i<VF.length;i++) System.out.println("VF "+i+" : "+VF[i].toString());
                processor.getTrackControls()[1].setFormat(new AudioFormat(AudioFormat.GSM_MS));
                Format[] AF=processor.getTrackControls()[1].getSupportedFormats();
                for (int i=0; i<AF.length;i++) System.out.println("AF "+i+" : "+AF[i].toString());
                processor.realize();
                while (!realized) {}
		DataSource source = processor.getDataOutput();
                
               MediaLocator dest = new MediaLocator("file:///D:/test2.avi");
  // create a datasink to do the file writing & open the sink to
  // make sure we can write to it.
  DataSink filewriter = null;
  try {
      filewriter = Manager.createDataSink(source, dest);
      filewriter.open();
  } catch (Exception e) {}
 
                               
                for (int i=0;i<processor.getSupportedContentDescriptors().length;i++) {
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
                }
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
            processor.close();
            filewriter.close();
        } catch (Exception ex) {}
    }
    public void destroy() {
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
        VPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();

        VPanel.setLayout(new java.awt.BorderLayout());

        this.add(VPanel, java.awt.BorderLayout.CENTER);

        jPanel1.add(jComboBox1);

        this.add(jPanel1, java.awt.BorderLayout.SOUTH);

    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {
        stop();
        destroy();
        System.exit(0);
    }
    
    public void getDevices() {
        java.util.Vector deviceListVector = CaptureDeviceManager.getDeviceList(null);
        for (int x = 0; x < deviceListVector.size(); x++)
		{
			// display device name
			CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) deviceListVector.elementAt(x);
			String deviceInfoText = deviceInfo.getName();
                        
                        
                        //+++++
                        jComboBox1.addItem(deviceInfoText);
                        //+++++
                        
                        
			//System.out.println("device " + x + ": " + deviceInfoText);
                        
                        Format deviceFormat[] = deviceInfo.getFormats();
			for (int y = 0; y < deviceFormat.length; y++)
			{
				// serach for default video device
                            System.out.println("Format: --"+x+"--"+y+"--"+deviceFormat[y].toString());
				if (captureVideoDevice == null)
					if (deviceFormat[7] instanceof VideoFormat)
					if (deviceInfo.getName().indexOf(defaultVideoDeviceName) >= 0)
				{
					captureVideoDevice = deviceInfo;
					//System.out.println(">>> capture video device = " + deviceInfo.getName());
				}

				// search for default video format
				
					
				// serach for default audio device
				if (captureAudioDevice == null)
					if (deviceFormat[y] instanceof AudioFormat)
					if (deviceInfo.getName().indexOf(defaultAudioDeviceName) >= 0)
				{
					captureAudioDevice = deviceInfo;
					//System.out.println(">>> capture audio device = " + deviceInfo.getName());
				}

				// search for default audio format
                      }
                        
        }
    }
    
    /**
     * @param args the command line arguments
     */
    
    public void controllerUpdate(javax.media.ControllerEvent event)  {
        if (event instanceof RealizeCompleteEvent) {
            realized=true;
            try {
            Component comp;
            if ((comp = player.getVisualComponent())!=null) VPanel.add("Center",comp);
            if ((comp = player.getControlPanelComponent())!=null) VPanel.add("South",comp);           
            this.setSize(player.getVisualComponent().getPreferredSize().width+8,player.getVisualComponent().getPreferredSize().height+player.getControlPanelComponent().getPreferredSize().height+titelleiste);
            VPanel.setBounds(0,0,320,250);
            validate(); 
            } catch (Exception e) {}
        }
        if (event instanceof ConfigureCompleteEvent) configured = true;
        System.out.println(event.toString());
    }    
    
    // Variables declaration - do not modify
    private javax.swing.JPanel VPanel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration
    
    
}