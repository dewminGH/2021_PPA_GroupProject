
package REG_UIZ;


import REG_MODELS.DB_login;
import REG_MODELS.Teacher;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class TEACH_ADD_CONFIRM_UI extends javax.swing.JFrame {
    
    
    
    
    public TEACH_ADD_CONFIRM_UI() {
        initComponents();
    }
    
    
    public TEACH_ADD_CONFIRM_UI(Teacher teach) {
        initComponents();
    
   
    lblid.setText( teach.getTeach_id());
    lblname.setText(teach.getTeach_name());
    lblnumber.setText(teach.getTeach_contatNumber());
    lbladdress.setText(teach.getTeach_address());
    lblemail.setText(teach.getTeach_email());
    lblsubjects.setText(teach.getTeach_subject());
   
    }
    
    public void box_clean(){
    lblid.setText( "");
    lblname.setText("");
    lblnumber.setText("");
    lbladdress.setText("");
    lblemail.setText("");
    lblsubjects.setText("");
    lblid1.setText( "");
    lblname1.setText("");
    lblnumber1.setText("");
    lbladdress1.setText("");
    lblemail1.setText("");
    lblsubjects1.setText("");      
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        closebtn = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblname = new javax.swing.JLabel();
        lblnumber = new javax.swing.JLabel();
        lbladdress = new javax.swing.JLabel();
        lblid = new javax.swing.JLabel();
        lblsubjects = new javax.swing.JLabel();
        lblemail = new javax.swing.JLabel();
        lblid1 = new javax.swing.JLabel();
        lblname1 = new javax.swing.JLabel();
        lblnumber1 = new javax.swing.JLabel();
        lbladdress1 = new javax.swing.JLabel();
        lblemail1 = new javax.swing.JLabel();
        lblsubjects1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Confirmbtn = new javax.swing.JButton();
        teach_con_massage = new javax.swing.JLabel();

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
        Title.setText(" Teacher register confirm box");
        jPanel2.add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 190, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 15));

        jPanel3.setBackground(new java.awt.Color(238, 227, 232));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel3.add(lblname, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 81, 100, 24));

        lblnumber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel3.add(lblnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 23, 100, 35));

        lbladdress.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel3.add(lbladdress, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 81, 100, 37));

        lblid.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel3.add(lblid, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 23, 100, 30));

        lblsubjects.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblsubjects.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(lblsubjects, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 53, 87, 83));

        lblemail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel3.add(lblemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 129, 130, 30));

        lblid1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblid1.setText("Teacher ID");
        jPanel3.add(lblid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 23, 85, 30));

        lblname1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblname1.setText("Teacher Name");
        jPanel3.add(lblname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 81, 85, 37));

        lblnumber1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblnumber1.setText("Contact Number");
        jPanel3.add(lblnumber1, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 23, -1, 30));

        lbladdress1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbladdress1.setText("Address");
        jPanel3.add(lbladdress1, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 81, 92, 37));

        lblemail1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblemail1.setText("E-mail");
        jPanel3.add(lblemail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 129, 85, 30));

        lblsubjects1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblsubjects1.setText("Subjects");
        lblsubjects1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(lblsubjects1, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 31, -1, 30));

        jPanel5.setBackground(new java.awt.Color(238, 227, 232));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 40));

        jPanel4.setBackground(new java.awt.Color(238, 227, 232));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel1.setText("Confirm");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 50, 20));

        Confirmbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid70x25.png")); // NOI18N
        Confirmbtn.setPreferredSize(new java.awt.Dimension(70, 25));
        Confirmbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover70x25.png")); // NOI18N
        Confirmbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmbtnActionPerformed(evt);
            }
        });
        jPanel4.add(Confirmbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 142, -1, -1));

        teach_con_massage.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(teach_con_massage, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 490, 120));

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
        try {
            DB_login data =new DB_login();
          
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            
            String sql="INSERT INTO teacher (teach_id,teach_name,teach_address,teach_contactNumber,teach_mail,teach_subject) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst=(PreparedStatement)con.prepareStatement(sql);
            
         
            pst.setString(1, lblid.getText());
            pst.setString(2, lblname.getText());
            pst.setString(3, lbladdress.getText());
            pst.setString(4, lblnumber.getText());
            pst.setString(5, lblemail.getText());
            pst.setString(6, lblsubjects.getText());
            
            pst.executeUpdate();
            
         
             con.close();
             box_clean();
            teach_con_massage.setText("Teacher Registered Successfully");
            
        } catch (SQLException ex) {
            Logger.getLogger(TEACH_ADD_CONFIRM_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TEACH_ADD_CONFIRM_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            java.util.logging.Logger.getLogger(TEACH_ADD_CONFIRM_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TEACH_ADD_CONFIRM_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TEACH_ADD_CONFIRM_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TEACH_ADD_CONFIRM_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TEACH_ADD_CONFIRM_UI().setVisible(true);
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbladdress;
    private javax.swing.JLabel lbladdress1;
    private javax.swing.JLabel lblemail;
    private javax.swing.JLabel lblemail1;
    private javax.swing.JLabel lblid;
    private javax.swing.JLabel lblid1;
    private javax.swing.JLabel lblname;
    private javax.swing.JLabel lblname1;
    private javax.swing.JLabel lblnumber;
    private javax.swing.JLabel lblnumber1;
    private javax.swing.JLabel lblsubjects;
    private javax.swing.JLabel lblsubjects1;
    private javax.swing.JLabel teach_con_massage;
    // End of variables declaration//GEN-END:variables
}
