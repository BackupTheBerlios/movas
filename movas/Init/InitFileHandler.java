/*
 * InitFileHandler.java
 *
 * Created on 3. Dezember 2003, 16:19
 */

package movas.Init;

/**
 *
 * @author  2fast
 */
public class InitFileHandler {
    
    /** Creates a new instance of InitFileHandler */
    public InitFileHandler() {
       
    }
    
    public void write(movas.GUI.OptionsDialog options){
        Structure struct = new Structure();
        struct.KomPort = options.getKommunikationsPort();
        struct.VideoDevice=(String)options.getVideoDevice();
        struct.AudioDevice=(String)options.getAudioDevice();
        struct.AudioFormat=options.getAudioFormat();
        struct.VideoFormat=options.getVideoFormat();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
    
    private class Structure implements java.io.Serializable{
        private int KomPort;
        private String VideoDevice;
        private String AudioDevice;
        private String VideoFormat;
        private String AudioFormat;
        private String VideoCodec;
        private String AudioCodec;
    }

}

