/*
 * CallDialog.java
 *
 * Created on 5. November 2003, 16:39
 */

package movas.GUI;

/**
 *
 * @author  MS
 */
public class CallDialog extends javax.swing.JFrame {
    
    /** Creates new form CallDialog */
    public CallDialog() {
        initComponents();
        this.setCursor(java.awt.Cursor.HAND_CURSOR);
        this.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2)-(this.getWidth()/2),
        (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2)-(this.getHeight()/2));
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButtonAnrufen = new javax.swing.JButton();
        jButtonAbbrechen = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridLayout(3, 1, 10, 10));

        setTitle("Anrufen");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        getContentPane().add(jPanel1);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jLabel1.setText("Adresse:");
        jPanel2.add(jLabel1);

        jTextField1.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel2.add(jTextField1);

        jLabel2.setText("Port:");
        jPanel2.add(jLabel2);

        jTextField2.setText("22222");
        jTextField2.setToolTipText("den Port eingeben, auf dem der Partner arbeitet");
        jTextField2.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel2.add(jTextField2);

        getContentPane().add(jPanel2);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        jButtonAnrufen.setMnemonic('A');
        jButtonAnrufen.setText("OK");
        jButtonAnrufen.setToolTipText("Verbindung aufbauen");
        jButtonAnrufen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbbrechenActionPerformed(evt);
            }
        });

        jPanel4.add(jButtonAnrufen);

        jButtonAbbrechen.setMnemonic('b');
        jButtonAbbrechen.setText("Abbrechen");
        jButtonAbbrechen.setToolTipText("Abbrechen");
        jButtonAbbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbbrechenActionPerformed(evt);
            }
        });

        jPanel4.add(jButtonAbbrechen);

        getContentPane().add(jPanel4);

        pack();
    }//GEN-END:initComponents

    private void jButtonAbbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbbrechenActionPerformed
        // Add your handling code here:
        this.hide();
    }//GEN-LAST:event_jButtonAbbrechenActionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        this.hide();
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new CallDialog().show();
    }
    
    public String getAddress(){
        return jTextField1.getText();
    }
    
    public int getPort(){
        return Integer.parseInt(jTextField2.getText());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbbrechen;
    private javax.swing.JButton jButtonAnrufen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    
}
