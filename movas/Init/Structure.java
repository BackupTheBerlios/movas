/*
 * Structure.java
 *
 * Created on 4. Dezember 2003, 11:41
 */

package movas.Init;

/**
 *
 * @author  2fast
 */
    public class Structure implements java.io.Serializable{
        
        public Structure(){
            super();
        }
        public int KomPort;
        public String VideoDevice;
        public String AudioDevice;
        public String VideoFormat;
        public String AudioFormat;
        public String VideoCodec;
        public String AudioCodec;
        
    }
