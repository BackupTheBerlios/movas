/*
 * DeviceInfo.java
 *
 * Created on 3. Dezember 2003, 12:01
 */
package movas.Init;

import javax.media.*;
import javax.media.format.*;
import java.util.*;
/**
 *
 * @author  schulzma
 */
public class DeviceInfo {
    
    private java.util.Vector deviceListVector = null;
    
    /** Creates a new instance of DeviceInfo */
    public DeviceInfo() {
        deviceListVector = CaptureDeviceManager.getDeviceList(null);
    }
    

     public java.util.Vector [] getDevices() {   
        java.util.Vector [] deviceNames = new java.util.Vector[2];
        deviceNames[0] = new Vector();
        deviceNames[1] = new Vector();
        for (int x = 0; x < deviceListVector.size(); x++)
            {
                // get all device name
                CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) deviceListVector.elementAt(x);
                String deviceInfoText = deviceInfo.getName();

                Format deviceFormat[] = deviceInfo.getFormats();

                if (deviceFormat[0] instanceof VideoFormat) {
                    //System.out.println("Video: "+deviceInfo.getName());
                    deviceNames[0].add(deviceInfo.getName());
                }
                // serach for audio device
                if (deviceFormat[0] instanceof AudioFormat) {
                    //System.out.println("Audio: "+deviceInfo.getName());
                    deviceNames[1].add(deviceInfo.getName());
                }
            }
        return deviceNames;
     }
    
     
     
     public javax.media.CaptureDeviceInfo getVideoDevice(String _VideoDeviceName) {
         CaptureDeviceInfo captureVideoDevice = null;

         for (int x = 0; x < deviceListVector.size(); x++)
	 {
            // display device name
            CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) deviceListVector.elementAt(x);
            String deviceInfoText = deviceInfo.getName();
            if (deviceInfo.getName().indexOf(_VideoDeviceName) >= 0)
                captureVideoDevice = deviceInfo;            
         } // for (int x = 0; x < deviceListVector.size(); x++)
         return captureVideoDevice;
     }
     
     public javax.media.CaptureDeviceInfo getAudioDevice(String _AudioDeviceName) {
         CaptureDeviceInfo captureVideoDevice = null;

         for (int x = 0; x < deviceListVector.size(); x++)
	 {
            // display device name
            CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) deviceListVector.elementAt(x);
            String deviceInfoText = deviceInfo.getName();
            if (deviceInfo.getName().indexOf(_AudioDeviceName) >= 0)
                captureVideoDevice = deviceInfo;   
         } // for (int x = 0; x < deviceListVector.size(); x++)
         return captureVideoDevice;
     }
    
     public java.util.Vector getSupportedFormats(CaptureDeviceInfo _device) {
         Format deviceFormat[] = _device.getFormats();
         java.util.Vector formats = new java.util.Vector();
         for (int y = 0; y < deviceFormat.length; y++)
         {
            formats.add(deviceFormat[y].toString());
            //System.out.println(deviceFormat[y].toString());
         }
         return formats;
     }
     
     /** Die Methode liefert für ein bestimmtes Device das Format anhand des übergebenen
      * Namen zurück.
      * @param device Das Gerät, für welches ein Format ermittelt werden soll
      * @param  Der Name des Formates
      * @return Format
      */     
     public javax.media.Format getFormatByName(CaptureDeviceInfo device, String format){
         javax.media.Format formats[]=device.getFormats();
         for (int y = 0; y < formats.length; y++){
            if(formats[y].toString().equals(format)){
                System.out.println("TRUETRUERAMTRUESTEN");
                return formats[y];
                
            }
         }
         return null;
     }
     
/*     
      public static void main(String args[]) {
        
        new DeviceInfo().getDevices();
        new DeviceInfo().getSupportedFormats(new DeviceInfo().getAudioDevice("JavaSound audio capture"));
   
    }
*/   
}
