/*
 * options.java
 *
 * Created on 1. November 2003, 10:54
 */
package GUI;
/**
 *
 * @author  MS
 */
public class OptionsDialog extends javax.swing.JFrame {
    
    /** Creates new form options */
    public OptionsDialog() {
        initComponents();
        this.setTitle("movas - Optionen");
        this.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2)-(this.getWidth()/2),
        (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2)-(this.getHeight()/2));
        
        
        
        ComboAudioDevice.addItem("JavaSound");
        ComboAudioDevice.addItem("DirectSound");
        ComboVideoDevice.addItem("WDM Capture Driver");
        ComboVideoFormat.addItem("176x144");
        ComboVideoFormat.addItem("352x288");
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        ComboAudioDevice = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        ComboAudioFormat = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        ComboVideoDevice = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        ComboVideoFormat = new javax.swing.JComboBox();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        ComboAudioCodec = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        ComboVideoCodec = new javax.swing.JComboBox();
        jPanel20 = new javax.swing.JPanel();
        TextFieldPort = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton_OK = new javax.swing.JButton();
        jButton_Apply = new javax.swing.JButton();
        jButton_Abort = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();

        jPanel1.setLayout(new java.awt.GridLayout(2, 1, 10, 20));

        jPanel2.setLayout(new java.awt.GridLayout(4, 1, 10, 0));

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Audio Einstellungen"));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("  AudioDevice");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        ComboAudioDevice.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.add(ComboAudioDevice, java.awt.BorderLayout.NORTH);

        jPanel2.add(jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("  Format");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel3, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6);

        jPanel7.setLayout(new java.awt.BorderLayout());

        ComboAudioFormat.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.add(ComboAudioFormat, java.awt.BorderLayout.NORTH);

        jPanel2.add(jPanel7);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(4, 1, 10, 0));

        jPanel3.setBorder(new javax.swing.border.TitledBorder("Video Einstellungen"));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("  VideoDevice");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        ComboVideoDevice.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.add(ComboVideoDevice, java.awt.BorderLayout.NORTH);

        jPanel3.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("  Format");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel4, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel10);

        jPanel11.setLayout(new java.awt.BorderLayout());

        ComboVideoFormat.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.add(ComboVideoFormat, java.awt.BorderLayout.NORTH);

        jPanel3.add(jPanel11);

        jPanel1.add(jPanel3);

        jTabbedPane1.addTab("Devices", jPanel1);

        jPanel16.setLayout(new java.awt.GridLayout(3, 1));

        jPanel18.setLayout(new java.awt.BorderLayout());

        jPanel18.setBorder(new javax.swing.border.TitledBorder("AudioCodec"));
        ComboAudioCodec.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.add(ComboAudioCodec, java.awt.BorderLayout.NORTH);

        jPanel16.add(jPanel18);

        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel19.setBorder(new javax.swing.border.TitledBorder("VideoCodec"));
        ComboVideoCodec.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.add(ComboVideoCodec, java.awt.BorderLayout.NORTH);

        jPanel16.add(jPanel19);

        jPanel20.setLayout(new java.awt.BorderLayout());

        jPanel20.setBorder(new javax.swing.border.TitledBorder("Kommunikationsport"));
        TextFieldPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldPort.setText("7340");
        jPanel20.add(TextFieldPort, java.awt.BorderLayout.NORTH);

        jPanel16.add(jPanel20);

        jTabbedPane1.addTab("Einstellungen", jPanel16);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel17.add(jPanel13);

        jPanel17.add(jPanel15);

        jButton_OK.setMnemonic('o');
        jButton_OK.setText("OK");
        jButton_OK.setPreferredSize(new java.awt.Dimension(107, 26));
        jButton_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OKActionPerformed(evt);
            }
        });

        jPanel17.add(jButton_OK);

        jButton_Apply.setMnemonic('b');
        jButton_Apply.setText("\u00dcbernehmen");
        jButton_Apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ApplyActionPerformed(evt);
            }
        });

        jPanel17.add(jButton_Apply);

        jButton_Abort.setMnemonic('a');
        jButton_Abort.setText("Abbrechen");
        jButton_Abort.setPreferredSize(new java.awt.Dimension(107, 26));
        jButton_Abort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AbortActionPerformed(evt);
            }
        });

        jPanel17.add(jButton_Abort);

        jPanel17.add(jPanel12);

        jPanel17.add(jPanel14);

        getContentPane().add(jPanel17, java.awt.BorderLayout.SOUTH);

        pack();
    }//GEN-END:initComponents

    private void jButton_AbortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AbortActionPerformed
        // Add your handling code here:
        this.hide();
    }//GEN-LAST:event_jButton_AbortActionPerformed

    private void jButton_ApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ApplyActionPerformed
        // Add your handling code here:
        //System.exit(0);
        CallDialog CD = new CallDialog();
        CD.show();
    }//GEN-LAST:event_jButton_ApplyActionPerformed

    private void jButton_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OKActionPerformed
        // Add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton_OKActionPerformed
   
    /** Die VideoComboBox mit Inhalt f�llen
     * @param videoDevices Ein Vector der die verf�gbaren Video Devices enthaelt
     * @param selected Das aktuell ausgew�hlte Video Device
     */    
    public void setVideoDevices(java.util.Vector videoDevices, Object selected) {
        java.util.Enumeration enum = videoDevices.elements();
        while (enum.hasMoreElements()) {
            ComboVideoDevice.addItem(enum.nextElement());
        }
        ComboVideoDevice.setSelectedItem(selected);
    }
    
    public void setVideoFormats(java.util.Vector videoFormats, Object selected) {
       java.util.Enumeration enum = videoFormats.elements();
        while (enum.hasMoreElements()) {
            ComboVideoFormat.addItem(enum.nextElement());
        }
        ComboVideoFormat.setSelectedItem(selected); 
    }
    
    /** Die VideoComboBox mit Inhalt f�llen
     * @param audioDevices Ein Vector der die verf�gbaren Audio Devices enthaelt
     * @param selected Das aktuell ausgew�hlte Audio Device
     */    
    public void setAudioDevices(java.util.Vector audioDevices, Object selected) {
        java.util.Enumeration enum = audioDevices.elements();
        while (enum.hasMoreElements()) {
            ComboAudioDevice.addItem(enum.nextElement());
        }
        ComboAudioDevice.setSelectedItem(selected);
    }
    
    public void setAudioFormats(java.util.Vector audioFormats, Object selected) {
        java.util.Enumeration enum = audioFormats.elements();
        while (enum.hasMoreElements()) {
            ComboAudioFormat.addItem(enum.nextElement());
        }
        ComboAudioFormat.setSelectedItem(selected); 
    }
    
    public void setAudioCodecs() {}
    
    public void setVideoCodecs() {}
    
    /** Das Port Edit Textfeld mit dem Korrekten Port belegen
     * @param port Der momentan eingestellte Kommunikationsport
     */    
    public void setKommunikationsPort(int port) {
        TextFieldPort.setText(""+port);
    }
    
    /** Liefert das gewaehlte Video Device zur�ck
     * @return das aktuell gewaehlte Video Device als Object
     */    
    public Object getVideoDevice() {
        return ComboVideoDevice.getSelectedItem();
    }
    
    public void getVideoFormat() {}
    
    /** Liefert das gewaehlte Audio Device zur�ck
     * @return das aktuell gewaehlte Video Device als Object
     */    
    public Object getAudioDevice() {
        return ComboAudioDevice.getSelectedItem();
    }
    
    public void getAudioFormat() {}
    public void getAudioCodec() {}
    public void getVideoCodec() {}
    
    /** Liefert den in dem Optionsmen� eingestellten Kommunikationsport zur�ck
     * @return der Kommunikationsport
     */    
    public int getKommunikationsPort() {
        return Integer.parseInt(TextFieldPort.getText());
    }
    
    
    
      /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        new OptionsDialog().show();
        
        
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboAudioCodec;
    private javax.swing.JComboBox ComboAudioDevice;
    private javax.swing.JComboBox ComboAudioFormat;
    private javax.swing.JComboBox ComboVideoCodec;
    private javax.swing.JComboBox ComboVideoDevice;
    private javax.swing.JComboBox ComboVideoFormat;
    private javax.swing.JTextField TextFieldPort;
    private javax.swing.JButton jButton_Abort;
    private javax.swing.JButton jButton_Apply;
    private javax.swing.JButton jButton_OK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
}