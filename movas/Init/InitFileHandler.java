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
import movas.Init.*;
import movas.GUI.*;
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
        f.writeObject(struct);
        f.close();
        fos.close();
    }
    
    public void read(movas.GUI.OptionsDialog options) throws java.lang.Exception{
        FileInputStream fos = new FileInputStream("./setup.cfg");
        ObjectInputStream f = new ObjectInputStream(fos);
        Structure struct = new Structure();
        struct=((Structure)f.readObject());
        f.close();
        fos.close();
        options.setKommunikationsPort(struct.KomPort);
        options.setSelectedAudioDevice(struct.AudioDevice);
        options.setSelectedVideoDevice(struct.VideoDevice);
        options.setSelectedAudioFormat(struct.AudioFormat);
        options.setSelectedVideoFormat(struct.VideoFormat);
        options.setSelectedAudioCodec(struct.AudioCodec);
        options.setSelectedVideoCodec(struct.VideoCodec);
        //System.out.println(struct.AudioDevice);
    }
    
    public Structure read() throws java.lang.Exception{
        FileInputStream fos = new FileInputStream("./setup.cfg");
        ObjectInputStream f = new ObjectInputStream(fos);
        Structure struct = new Structure();
        struct=((Structure)f.readObject());
        f.close();
        fos.close();
        return struct;
    }


    
}

