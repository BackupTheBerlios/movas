/*
 * chat.java
 *
 * Created on 6. November 2003, 17:04
 */

package movas.GUI;

/**
 *
 * @author  2fast
 */
public class chat extends javax.swing.JFrame {
    
    /** Creates new form chat */
    public chat() {
        initComponents();
        init();
        this.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2)-(this.getWidth()/2),
        (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2)-(this.getHeight()/2));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextFieldChat = new javax.swing.JTextField();

        setTitle("Chat");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(jTextPane1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jSplitPane1.setTopComponent(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(20, 36));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Statistik");
        jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Ausblenden");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformed(evt);
            }
        });

        jPanel3.add(jButton1, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jTextFieldChat.setText("jTextField1");
        jTextFieldChat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldChatKeyTyped(evt);
            }
        });

        jPanel2.add(jTextFieldChat, java.awt.BorderLayout.CENTER);

        jSplitPane1.setBottomComponent(jPanel2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActionPerformed
        String cmd = evt.getActionCommand();
        if(cmd == "Ausblenden") this.setVisible(false);
    }//GEN-LAST:event_ActionPerformed

    private void jTextFieldChatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldChatKeyTyped
        if(evt.getKeyChar()== '\n'){
            /*sendeoperation hier implementieren*/
            System.out.println("abgesendet");
            jTextFieldChat.setText("");
        }
    }//GEN-LAST:event_jTextFieldChatKeyTyped
    
    void init(){
        jSplitPane1.setResizeWeight(1);
    }
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        this.setVisible(false);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new chat().show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextFieldChat;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
    
}
