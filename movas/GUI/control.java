/*
 * control.java
 *
 * Created on 5. November 2003, 15:56
 */

package movas.GUI;

/**
 *
 * @author  2fast
 */
import movas.GUI.*;
import movas.Init.*;

public class control extends javax.swing.JFrame {
    private video   empfangsfenster=null;
    private video   sendefenster=null;
    private chat    chatfenster=null;
    private OptionsDialog optionsfenster=null;
    private HilfeFenster hilfefenster=null;
    private CallDialog callDialog = null;
    
    private String address = null;
    private int DestPort = 0;
    /** Creates new form control */
    public control() {
        initComponents();
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2)-(this.getWidth()),
        (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2)-(this.getHeight()));
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kontrollfeld");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(9, 1, 2, 8));

        jPanel1.setPreferredSize(new java.awt.Dimension(100, 180));
        jPanel1.add(jPanel2);

        jButton7.setMnemonic('v');
        jButton7.setText("Verbindung");
        jButton7.setToolTipText("eine direkte Verbindung anw\u00e4hlen");
        jButton7.setActionCommand("direkt");
        jButton7.setBorder(null);
        jButton7.setFocusPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.this.actionPerformed(evt);
            }
        });

        jPanel1.add(jButton7);

        jButton1.setMnemonic('e');
        jButton1.setText("Empfangsfenster");
        jButton1.setToolTipText("Empfangsfenster \u00f6ffnen");
        jButton1.setBorder(null);
        jButton1.setFocusPainted(false);
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.this.actionPerformed(evt);
            }
        });

        jPanel1.add(jButton1);

        jButton2.setMnemonic('s');
        jButton2.setText("Sendefenster");
        jButton2.setToolTipText("Video Sendefenster \u00f6ffnen");
        jButton2.setBorder(null);
        jButton2.setFocusPainted(false);
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.this.actionPerformed(evt);
            }
        });

        jPanel1.add(jButton2);

        jButton3.setMnemonic('c');
        jButton3.setText("Chatfenster");
        jButton3.setToolTipText("Text-Chat Fenster \u00f6ffnen");
        jButton3.setBorder(null);
        jButton3.setFocusPainted(false);
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.this.actionPerformed(evt);
            }
        });

        jPanel1.add(jButton3);

        jButton4.setMnemonic('o');
        jButton4.setText("Optionen");
        jButton4.setToolTipText("Einstellungsfenster \u00f6ffnen");
        jButton4.setBorder(null);
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.this.actionPerformed(evt);
            }
        });

        jPanel1.add(jButton4);

        jButton5.setMnemonic('k');
        jButton5.setText("Kontakte");
        jButton5.setToolTipText("Telefonbuch \u00f6ffnen");
        jButton5.setBorder(null);
        jButton5.setFocusPainted(false);
        jButton5.setEnabled(false);
        jPanel1.add(jButton5);

        jButton6.setMnemonic('h');
        jButton6.setText("Hilfe");
        jButton6.setToolTipText("Hilfe anzeigen");
        jButton6.setBorder(null);
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.this.actionPerformed(evt);
            }
        });

        jPanel1.add(jButton6);

        jButton8.setMnemonic('b');
        jButton8.setText("Beenden");
        jButton8.setToolTipText("Das Programm beenden");
        jButton8.setBorder(null);
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.this.actionPerformed(evt);
            }
        });

        jPanel1.add(jButton8);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void actionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionPerformed
        String cmd = evt.getActionCommand();
        
        if(cmd == "Beenden") {if (sendefenster!=null)sendefenster.kill();System.exit(0);}
        else if(cmd == "direkt") {
            if(callDialog == null) callDialog = new CallDialog();
            callDialog.show();
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
        }
        else if(cmd == "Empfangsfenster") {
            if(empfangsfenster == null) empfangsfenster = new video(video.EMPFANG,callDialog.getAddress(),callDialog.getPort());
            empfangsfenster.setTitle(callDialog.getAddress()+":"+callDialog.getPort());
            empfangsfenster.show();
        }
        else if(cmd == "Sendefenster") {
            this.setCursor(java.awt.Cursor.WAIT_CURSOR);
            try{this.wait(100);}catch(Exception e){}
            if(sendefenster == null) sendefenster = new video(video.VERSAND,callDialog.getAddress(),callDialog.getPort());
            sendefenster.show(); 
            try{this.wait(100);}catch(Exception e){}
            this.setCursor(java.awt.Cursor.DEFAULT_CURSOR);
        }
        else if(cmd == "Chatfenster"){
            if(chatfenster == null) chatfenster = new chat();
            chatfenster.show();
        }
        else if(cmd == "Optionen"){
            if(optionsfenster == null) optionsfenster = new OptionsDialog();
            optionsfenster.show();
        }
        else if(cmd == "Hilfe"){
            if(hilfefenster == null) hilfefenster = new HilfeFenster();
            hilfefenster.show();
        }
    }//GEN-LAST:event_actionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        if (sendefenster!=null)sendefenster.kill();
        //System.gc();
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new control().show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
    
}
