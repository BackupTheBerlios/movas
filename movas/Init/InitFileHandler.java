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

import java.io.*;
public class InitFileHandler {
    
    /** Creates a new instance of InitFileHandler */
    public InitFileHandler() {
       
    }
    
    public void write(movas.GUI.OptionsDialog options) throws java.lang.Exception{
        FileOutputStream fos = new FileOutputStream("./setup.cfg");
        ObjectOutputStream f = new ObjectOutputStream(fos);
        Structure struct    = new Structure();
        struct.KomPort      = options.getKommunikationsPort();
        struct.VideoDevice  =(String)options.getVideoDevice();
        struct.AudioDevice  =(String)options.getAudioDevice();
        struct.AudioFormat  =(String)options.getAudioFormat();
        struct.VideoFormat  =(String)options.getVideoFormat();
        struct.AudioCodec   =(String)options.getAudioCodec();
        struct.VideoCodec   =(String)options.getVideoCodec();
        f.writeObject((Object)struct);
        f.close();
        fos.close();
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

