
package REG_UIZ;

public class MASSAGE_BOX extends javax.swing.JFrame {
   
    
    public MASSAGE_BOX() {
        initComponents();
    }
    
    public MASSAGE_BOX(String massage) {
        initComponents();
        lblmassage.setText(massage);
    }
  
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        closebtn = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblmassage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Confirmbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Confrim BOX");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(216, 103, 155));
        jPanel2.setForeground(new java.awt.Color(255, 255, 204));
        jPanel2.setToolTipText("");
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closebtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Titles_common_icons\\dispose_boxuionly.png")); // NOI18N
        closebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closebtnActionPerformed(evt);
            }
        });
        jPanel2.add(closebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 14, 15));

        Title.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setText("Massage box");
        jPanel2.add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 190, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 15));

        jPanel3.setBackground(new java.awt.Color(238, 227, 232));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblmassage.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(lblmassage, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 460, 120));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel1.setText("Close");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 50, 20));

        Confirmbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid70x25.png")); // NOI18N
        Confirmbtn.setPreferredSize(new java.awt.Dimension(70, 25));
        Confirmbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover70x25.png")); // NOI18N
        Confirmbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmbtnActionPerformed(evt);
            }
        });
        jPanel3.add(Confirmbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 600, 180));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebtnActionPerformed
        dispose();
        
    }//GEN-LAST:event_closebtnActionPerformed

    private void ConfirmbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmbtnActionPerformed
       dispose();
    }//GEN-LAST:event_ConfirmbtnActionPerformed

  
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MASSAGE_BOX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MASSAGE_BOX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MASSAGE_BOX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MASSAGE_BOX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MASSAGE_BOX().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirmbtn;
    private javax.swing.JLabel Title;
    private javax.swing.JButton closebtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblmassage;
    // End of variables declaration//GEN-END:variables
}
