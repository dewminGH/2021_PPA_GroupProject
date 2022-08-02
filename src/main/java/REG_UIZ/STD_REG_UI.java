
package REG_UIZ;

import REG_MODELS.DB_login;
import REG_MODELS.DBcon;
import REG_MODELS.Student;
import REG_MODELS.Teacher;
import REG_MODELS.clz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class STD_REG_UI extends javax.swing.JFrame {
    Student std;
    Teacher teach;
    clz cls;
    DB_login data = new DB_login();
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement Insertps = null;
    
    Connection con;
   
///counstuctor
    public STD_REG_UI(String login){
        initComponents();
        //nayon
        setLocationRelativeTo(null);
        txtstd_ID.setEditable(false);
        txt_name.setEditable(false);
        txt_sub.setEditable(false);
        
       //
        ImageIcon icon = new ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Titles_common_icons\\title_bar_icon.png");
        this.setIconImage(icon.getImage());  
        CLASS_REG_PANNEL.setVisible(false);
        SETTING_PANNEL.setVisible(false);
        REPORT_PANNEL.setVisible(false);
        ACCOUNTMANAGE_PANNEL.setVisible(false);
        ATTENDANCE_PANNEL.setVisible(false);
        ATTENDANCE_PANNEL_fk.setVisible(false);
        PAY_PANNEL.setVisible(false);
        jLabel52.setVisible(false);
        jLabel65.setVisible(false);
        combobox_load();
        
        if(login.equalsIgnoreCase("regular"))
        {
            //report 
            reportbtn.setVisible(false);
            jLabel50.setVisible(false);
            jLabel51.setVisible(false);
            jLabel52.setVisible(true);
            jLabel52.setText("Reports Unavailable");
            
            //use acct
            accountManageBtn.setVisible(false);
            jLabel53.setVisible(false);
            jLabel54.setVisible(false);
            jLabel65.setVisible(true);
            jLabel65.setText("Account Unavailable");
        }
        
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public STD_REG_UI() {
          initComponents();
      
        
    }
  
    //stdCombo_plus combo box
    public void combobox_load()
    {
       String sql = "SELECT  DISTINCT subject FROM class "; 
       ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            Statement st = con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
               stdCombo_plus.addItem(rs.getString("subject")); 
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //manage pannel functions
     public void showTableData() {
        try {
            conn = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            String sql = "SELECT * FROM admin";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }
     
    public void clear(){
        id.setText("");
        name.setText("");
        mail.setText("");
        password.setText("");
        address.setText("");
        no.setText("");
        com.setText("");
        
    }
    //class
    public void classClean(){
        teach_id_clz.setText("");
        teach_name_clz.setText("");
        subject_clz.setText("");
        lblgrade.setText("Select the Grade");
    } 
    public void classRegister()
    {
        CLASS_REG_PANNEL.setVisible(true);
        teach_id_clz.setText(teach.getTeach_id());
        teach_name_clz.setText(teach.getTeach_name());
        lblgrade.setText("Select the Grade");
    }
   //Student 
    public void std_fields_clear(){ //std page ----->with id
        std_id.setText("");
        std_name.setText("");
        std_bday.setText("");
        std_address.setText("");
        std_contactNumber.setText("");
        std_parentEmail.setText("");
        std_class.setText("");
    }
    public void std_fields_clear2(){ //std page----> ID Field not clr
        std_name.setText("");
        std_bday.setText("");
        std_address.setText("");
        std_contactNumber.setText("");
        std_parentEmail.setText("");
        std_class.setText("");
    }
    
   //Teacher
    public void teach_fields_clear(){ //std page ----->with id
        teach_id.setText("");
        teach_name.setText("");
        teach_address.setText("");
        teach_contactNumber.setText("");
        teach_mail.setText("");
        teach_subject.setText("");
    }
    public void teach_fields_clear2(){ //std page ----->ID Field not clr
        teach_name.setText("");
        teach_address.setText("");
        teach_contactNumber.setText("");
        teach_mail.setText("");
        teach_subject.setText("");
    }
    
    //himasha function mail
      public void SendMailHandler() {
        String parenetEmail = txtParentEmail.getText();
        String FromEmail = "ppaproject2021@gmail.com"; // Your email
        String FromEmailPassword = "12345abc#"; //Your email password
        String Subjects = "payment";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FromEmail, FromEmailPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(parenetEmail));
            message.setSubject(Subjects);
            message.setText("payment succefully");
            Transport.send(message);
            JOptionPane.showMessageDialog(rootPane, "Email -Send");
        } catch (Exception ex) {
            System.out.println("" + ex);
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        basepanel = new javax.swing.JPanel();
        menupanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        studentbtn = new javax.swing.JButton();
        teacherbtn = new javax.swing.JButton();
        attendancebtn = new javax.swing.JButton();
        paymentbtn = new javax.swing.JButton();
        settingbtn = new javax.swing.JButton();
        logoutbtn = new javax.swing.JButton();
        sizefix_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbl4 = new javax.swing.JLabel();
        ACCOUNTMANAGE_PANNEL = new javax.swing.JPanel();
        clz_stage4 = new javax.swing.JPanel();
        clz_stage5 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        javax.swing.JPanel normaluser = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        password = new javax.swing.JPasswordField();
        jLabel60 = new javax.swing.JLabel();
        type = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        no = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        jLabel63 = new javax.swing.JLabel();
        com = new javax.swing.JPasswordField();
        jLabel64 = new javax.swing.JLabel();
        REPORT_PANNEL = new javax.swing.JPanel();
        report_stage1 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        atd_repbtn = new javax.swing.JButton();
        std_repbtn = new javax.swing.JButton();
        User_login = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        CLASS_REG_PANNEL = new javax.swing.JPanel();
        clz_stage1 = new javax.swing.JPanel();
        clz_stage2 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblgrade = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        teach_id_clz = new javax.swing.JTextField();
        teach_name_clz = new javax.swing.JTextField();
        subject_clz = new javax.swing.JTextField();
        clz_stage3 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        clz_backbtn = new javax.swing.JButton();
        clz_viewbtn = new javax.swing.JButton();
        clz_removebtn = new javax.swing.JButton();
        clz_addbtn = new javax.swing.JButton();
        clz_plus = new javax.swing.JPanel();
        imgplus = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        Combo_plus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        datatbl = new javax.swing.JTable();
        STUDENT_REG_PANNEL = new javax.swing.JPanel();
        std_stage1 = new javax.swing.JPanel();
        std_stage2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        std_id = new javax.swing.JTextField();
        std_address = new javax.swing.JTextField();
        std_name = new javax.swing.JTextField();
        std_contactNumber = new javax.swing.JTextField();
        std_bday = new javax.swing.JTextField();
        std_parentEmail = new javax.swing.JTextField();
        std_class = new javax.swing.JTextField();
        std_stage3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        std_updatebtn = new javax.swing.JButton();
        std_removebtn = new javax.swing.JButton();
        std_addbtn = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        std_plus = new javax.swing.JPanel();
        imgplus1 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        stdCombo_plus = new javax.swing.JComboBox<>();
        TEACHER_REG_PANNEL = new javax.swing.JPanel();
        teach_stage1 = new javax.swing.JPanel();
        teach_stage2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        teach_id = new javax.swing.JTextField();
        teach_address = new javax.swing.JTextField();
        teach_name = new javax.swing.JTextField();
        teach_contactNumber = new javax.swing.JTextField();
        teach_mail = new javax.swing.JTextField();
        teach_subject = new javax.swing.JTextField();
        teach_stage3 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        teach_updatebtn = new javax.swing.JButton();
        teach_removebtn = new javax.swing.JButton();
        teach_addbtn = new javax.swing.JButton();
        SETTING_PANNEL = new javax.swing.JPanel();
        setting_stage2 = new javax.swing.JPanel();
        report = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        reportbtn = new javax.swing.JButton();
        account_manage = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        accountManageBtn = new javax.swing.JButton();
        about = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        contact_us = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        ATTENDANCE_PANNEL = new javax.swing.JPanel();
        atn_stage1 = new javax.swing.JPanel();
        atn_stage2 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        atn_stage3 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        text = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        atntb = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel78 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        ATTENDANCE_PANNEL_fk = new javax.swing.JPanel();
        atn_stage4 = new javax.swing.JPanel();
        atn_stage5 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        atd_up_pannel = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        txt_sh = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        atntb2 = new javax.swing.JTable();
        jLabel73 = new javax.swing.JLabel();
        txtstd_ID = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txt_sub = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        cmb_mo = new javax.swing.JComboBox<>();
        cmb_wk = new javax.swing.JComboBox<>();
        com_p = new javax.swing.JComboBox<>();
        btn_up = new javax.swing.JButton();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        PAY_PANNEL = new javax.swing.JPanel();
        clz_stage6 = new javax.swing.JPanel();
        clz_stage7 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        Student_pay = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        txtStudentID = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        txtStudentName = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        txtParentEmail = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        txtClassFee = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        txtPaidDate = new javax.swing.JTextField();
        jBSearch = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jBAdd = new javax.swing.JButton();
        jBUpdate = new javax.swing.JButton();
        jBDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Victory");

        basepanel.setBackground(new java.awt.Color(255, 255, 255));
        basepanel.setMinimumSize(new java.awt.Dimension(0, 0));
        basepanel.setPreferredSize(new java.awt.Dimension(0, 0));
        basepanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menupanel.setBackground(new java.awt.Color(209, 94, 157));
        menupanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\std_reg.png")); // NOI18N
        menupanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 40, 40));

        jLabel20.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\std_att.png")); // NOI18N
        menupanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 40, 40));

        jLabel21.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\std_pay.png")); // NOI18N
        menupanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 40, 40));

        jLabel22.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\setting.png")); // NOI18N
        menupanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 40, 40));

        jLabel23.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\logout.png")); // NOI18N
        menupanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 40, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("                    STUDENT REGISTER");
        menupanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 270, 66));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("                    TEACHER REGISTER");
        menupanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 260, 66));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("                     ATTENDANCE");
        menupanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 260, 66));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("                     PAYMENT");
        menupanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 260, 66));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("                     SETTINGS");
        menupanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 240, 66));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("                     LOG OUT");
        menupanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 260, 66));

        jLabel5.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\teach_reg.png")); // NOI18N
        menupanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 40, 40));

        studentbtn.setBackground(new java.awt.Color(233, 128, 158));
        studentbtn.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        studentbtn.setForeground(new java.awt.Color(255, 255, 255));
        studentbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_solid.png")); // NOI18N
        studentbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_hover.png")); // NOI18N
        studentbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentbtnActionPerformed(evt);
            }
        });
        menupanel.add(studentbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 260, 66));

        teacherbtn.setBackground(new java.awt.Color(233, 128, 158));
        teacherbtn.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        teacherbtn.setForeground(new java.awt.Color(255, 255, 255));
        teacherbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_solid.png")); // NOI18N
        teacherbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_hover.png")); // NOI18N
        teacherbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherbtnActionPerformed(evt);
            }
        });
        menupanel.add(teacherbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 260, 66));

        attendancebtn.setBackground(new java.awt.Color(0, 204, 204));
        attendancebtn.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        attendancebtn.setForeground(new java.awt.Color(255, 255, 255));
        attendancebtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_solid.png")); // NOI18N
        attendancebtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_hover.png")); // NOI18N
        attendancebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendancebtnActionPerformed(evt);
            }
        });
        menupanel.add(attendancebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 260, 66));

        paymentbtn.setBackground(new java.awt.Color(255, 255, 102));
        paymentbtn.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        paymentbtn.setForeground(new java.awt.Color(255, 255, 255));
        paymentbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_solid.png")); // NOI18N
        paymentbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_hover.png")); // NOI18N
        paymentbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentbtnActionPerformed(evt);
            }
        });
        menupanel.add(paymentbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 260, 66));

        settingbtn.setBackground(new java.awt.Color(0, 204, 204));
        settingbtn.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        settingbtn.setForeground(new java.awt.Color(255, 255, 255));
        settingbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_solidFix.jpg")); // NOI18N
        settingbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_hoverFix.jpg")); // NOI18N
        settingbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingbtnActionPerformed(evt);
            }
        });
        menupanel.add(settingbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 260, 66));

        logoutbtn.setBackground(new java.awt.Color(0, 204, 204));
        logoutbtn.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        logoutbtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_solid.png")); // NOI18N
        logoutbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Menu_icons\\Menubtn_hover.png")); // NOI18N
        logoutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbtnActionPerformed(evt);
            }
        });
        menupanel.add(logoutbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 260, 66));

        sizefix_panel.setBackground(new java.awt.Color(209, 94, 157));

        javax.swing.GroupLayout sizefix_panelLayout = new javax.swing.GroupLayout(sizefix_panel);
        sizefix_panel.setLayout(sizefix_panelLayout);
        sizefix_panelLayout.setHorizontalGroup(
            sizefix_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        sizefix_panelLayout.setVerticalGroup(
            sizefix_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        menupanel.add(sizefix_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 557, 260, 10));

        jPanel1.setBackground(new java.awt.Color(209, 94, 157));

        lbl4.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\logo_icons\\vic_logo.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(lbl4)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        menupanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 220, 180));

        basepanel.add(menupanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 580));

        ACCOUNTMANAGE_PANNEL.setBackground(new java.awt.Color(255, 255, 255));

        clz_stage4.setBackground(new java.awt.Color(255, 255, 255));
        clz_stage4.setPreferredSize(new java.awt.Dimension(743, 535));

        clz_stage5.setBackground(new java.awt.Color(252, 158, 158));

        jLabel55.setBackground(new java.awt.Color(252, 158, 158));
        jLabel55.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setText(" Account Management");

        javax.swing.GroupLayout clz_stage5Layout = new javax.swing.GroupLayout(clz_stage5);
        clz_stage5.setLayout(clz_stage5Layout);
        clz_stage5Layout.setHorizontalGroup(
            clz_stage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(clz_stage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(clz_stage5Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(112, Short.MAX_VALUE)))
        );
        clz_stage5Layout.setVerticalGroup(
            clz_stage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
            .addGroup(clz_stage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(clz_stage5Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        normaluser.setBackground(new java.awt.Color(255, 255, 255));
        normaluser.setPreferredSize(new java.awt.Dimension(743, 535));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel56.setText("ID");

        jLabel57.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel57.setText("Name");

        jLabel58.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel58.setText("E-mail");

        jLabel59.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel59.setText("PASSWORD");

        id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        id.setPreferredSize(new java.awt.Dimension(7, 25));

        name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        name.setPreferredSize(new java.awt.Dimension(7, 25));

        mail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mail.setPreferredSize(new java.awt.Dimension(7, 25));

        jButton2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton2.setText("CREATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton4.setText("REMOVE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        password.setPreferredSize(new java.awt.Dimension(7, 25));

        jLabel60.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel60.setText("TYPE");

        type.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN ACCOUNT", "NORMAL ACCOUNT" }));

        jLabel61.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel61.setText("Mobile  Number");

        no.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        no.setPreferredSize(new java.awt.Dimension(7, 25));

        jLabel62.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel62.setText("ADDRESS");

        address.setColumns(20);
        address.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        address.setRows(5);
        jScrollPane3.setViewportView(address);

        jLabel63.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel63.setText("COMFIRM PASSWORD");

        com.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        com.setPreferredSize(new java.awt.Dimension(7, 25));

        jLabel64.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel64.setText("Account Privacy");

        javax.swing.GroupLayout normaluserLayout = new javax.swing.GroupLayout(normaluser);
        normaluser.setLayout(normaluserLayout);
        normaluserLayout.setHorizontalGroup(
            normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(normaluserLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(normaluserLayout.createSequentialGroup()
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(normaluserLayout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, normaluserLayout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(com, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, normaluserLayout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(99, 99, 99)
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(normaluserLayout.createSequentialGroup()
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, normaluserLayout.createSequentialGroup()
                                .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel57))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(mail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, normaluserLayout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addGap(18, 18, 18)
                                .addComponent(no, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        normaluserLayout.setVerticalGroup(
            normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(normaluserLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(normaluserLayout.createSequentialGroup()
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58)
                            .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(normaluserLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel62))
                            .addGroup(normaluserLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, normaluserLayout.createSequentialGroup()
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63)
                            .addComponent(com, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(normaluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, normaluserLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout clz_stage4Layout = new javax.swing.GroupLayout(clz_stage4);
        clz_stage4.setLayout(clz_stage4Layout);
        clz_stage4Layout.setHorizontalGroup(
            clz_stage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clz_stage4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(clz_stage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(normaluser, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clz_stage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        clz_stage4Layout.setVerticalGroup(
            clz_stage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clz_stage4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(clz_stage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(normaluser, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout ACCOUNTMANAGE_PANNELLayout = new javax.swing.GroupLayout(ACCOUNTMANAGE_PANNEL);
        ACCOUNTMANAGE_PANNEL.setLayout(ACCOUNTMANAGE_PANNELLayout);
        ACCOUNTMANAGE_PANNELLayout.setHorizontalGroup(
            ACCOUNTMANAGE_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACCOUNTMANAGE_PANNELLayout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(clz_stage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ACCOUNTMANAGE_PANNELLayout.setVerticalGroup(
            ACCOUNTMANAGE_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACCOUNTMANAGE_PANNELLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(clz_stage4, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        basepanel.add(ACCOUNTMANAGE_PANNEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 1010, 578));

        REPORT_PANNEL.setBackground(new java.awt.Color(209, 94, 157));

        report_stage1.setBackground(new java.awt.Color(209, 94, 157));
        report_stage1.setPreferredSize(new java.awt.Dimension(743, 535));
        report_stage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Attendence  Register Report");
        report_stage1.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 420, 30));

        jLabel85.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Student  Register Report");
        report_stage1.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 420, 40));

        jLabel87.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("User  Register Report");
        report_stage1.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 420, 30));

        atd_repbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\reportz\\solidCL.png")); // NOI18N
        atd_repbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\reportz\\hoverCL.png")); // NOI18N
        atd_repbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atd_repbtnActionPerformed(evt);
            }
        });
        report_stage1.add(atd_repbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 751, 56));

        std_repbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\reportz\\solidCL.png")); // NOI18N
        std_repbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\reportz\\hoverCL.png")); // NOI18N
        std_repbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_repbtnActionPerformed(evt);
            }
        });
        report_stage1.add(std_repbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 122, 751, 56));

        User_login.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\reportz\\solidCL.png")); // NOI18N
        User_login.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\reportz\\hoverCL.png")); // NOI18N
        User_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                User_loginActionPerformed(evt);
            }
        });
        report_stage1.add(User_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 184, 751, 56));

        jLabel88.setFont(new java.awt.Font("Arial", 1, 42)); // NOI18N
        jLabel88.setText("Reports");
        report_stage1.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 360, 50));

        javax.swing.GroupLayout REPORT_PANNELLayout = new javax.swing.GroupLayout(REPORT_PANNEL);
        REPORT_PANNEL.setLayout(REPORT_PANNELLayout);
        REPORT_PANNELLayout.setHorizontalGroup(
            REPORT_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(REPORT_PANNELLayout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(report_stage1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        REPORT_PANNELLayout.setVerticalGroup(
            REPORT_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(report_stage1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );

        basepanel.add(REPORT_PANNEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 1010, 580));

        CLASS_REG_PANNEL.setBackground(new java.awt.Color(255, 255, 255));

        clz_stage1.setBackground(new java.awt.Color(255, 255, 255));
        clz_stage1.setPreferredSize(new java.awt.Dimension(743, 535));

        clz_stage2.setBackground(new java.awt.Color(252, 158, 158));

        jLabel34.setBackground(new java.awt.Color(252, 158, 158));
        jLabel34.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("       CLASS REGISTER");

        javax.swing.GroupLayout clz_stage2Layout = new javax.swing.GroupLayout(clz_stage2);
        clz_stage2.setLayout(clz_stage2Layout);
        clz_stage2Layout.setHorizontalGroup(
            clz_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(clz_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(clz_stage2Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(112, Short.MAX_VALUE)))
        );
        clz_stage2Layout.setVerticalGroup(
            clz_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
            .addGroup(clz_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(clz_stage2Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel35.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel35.setText("Grade");

        jLabel36.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel36.setText("Subject");

        lblgrade.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblgrade.setText("Select the Grade");

        jLabel39.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel39.setText("Teacher Name");

        jLabel40.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel40.setText("Teacher ID");

        teach_id_clz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teach_id_clzActionPerformed(evt);
            }
        });

        subject_clz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_clzActionPerformed(evt);
            }
        });

        clz_stage3.setBackground(new java.awt.Color(255, 255, 255));
        clz_stage3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel44.setText("Back");
        clz_stage3.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 60, -1));

        jLabel41.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel41.setText("Remove");
        clz_stage3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, -1));

        jLabel42.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel42.setText("View");
        clz_stage3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 60, -1));

        jLabel43.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel43.setText("Register");
        clz_stage3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 60, -1));

        clz_backbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        clz_backbtn.setText("UPDATE");
        clz_backbtn.setPreferredSize(new java.awt.Dimension(100, 30));
        clz_backbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        clz_backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clz_backbtnActionPerformed(evt);
            }
        });
        clz_stage3.add(clz_backbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 85, -1));

        clz_viewbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        clz_viewbtn.setText("UPDATE");
        clz_viewbtn.setPreferredSize(new java.awt.Dimension(100, 30));
        clz_viewbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        clz_viewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clz_viewbtnActionPerformed(evt);
            }
        });
        clz_stage3.add(clz_viewbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 11, 85, -1));

        clz_removebtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        clz_removebtn.setText("REMOVE");
        clz_removebtn.setPreferredSize(new java.awt.Dimension(100, 30));
        clz_removebtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        clz_removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clz_removebtnActionPerformed(evt);
            }
        });
        clz_stage3.add(clz_removebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 11, 85, -1));

        clz_addbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        clz_addbtn.setText("ADD");
        clz_addbtn.setPreferredSize(new java.awt.Dimension(100, 30));
        clz_addbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        clz_addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clz_addbtnActionPerformed(evt);
            }
        });
        clz_stage3.add(clz_addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 11, 85, -1));

        clz_plus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgplus.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Class_pane\\plus.png")); // NOI18N
        clz_plus.add(imgplus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -2, 40, 40));

        jLabel45.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Class_pane\\pluslbl.png")); // NOI18N
        clz_plus.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 42));

        Combo_plus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "7", "8", "9", "10", "11", "12", "13" }));
        Combo_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_plusActionPerformed(evt);
            }
        });
        clz_plus.add(Combo_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 50, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        datatbl.setBackground(new java.awt.Color(254, 205, 205));
        datatbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher ID", "Teacher Name", "Subject", "Grade"
            }
        ));
        datatbl.setGridColor(new java.awt.Color(255, 255, 255));
        datatbl.setSelectionBackground(new java.awt.Color(253, 194, 194));
        jScrollPane1.setViewportView(datatbl);

        javax.swing.GroupLayout clz_stage1Layout = new javax.swing.GroupLayout(clz_stage1);
        clz_stage1.setLayout(clz_stage1Layout);
        clz_stage1Layout.setHorizontalGroup(
            clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clz_stage1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clz_stage1Layout.createSequentialGroup()
                        .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clz_stage1Layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(clz_stage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(clz_stage1Layout.createSequentialGroup()
                                .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(teach_id_clz, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(teach_name_clz, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(clz_stage1Layout.createSequentialGroup()
                                        .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblgrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(subject_clz, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                        .addGap(9, 9, 9)
                                        .addComponent(clz_plus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(clz_stage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        clz_stage1Layout.setVerticalGroup(
            clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clz_stage1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(clz_stage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clz_stage1Layout.createSequentialGroup()
                        .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teach_id_clz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teach_name_clz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subject_clz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clz_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblgrade, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(clz_plus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clz_stage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CLASS_REG_PANNELLayout = new javax.swing.GroupLayout(CLASS_REG_PANNEL);
        CLASS_REG_PANNEL.setLayout(CLASS_REG_PANNELLayout);
        CLASS_REG_PANNELLayout.setHorizontalGroup(
            CLASS_REG_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CLASS_REG_PANNELLayout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(clz_stage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CLASS_REG_PANNELLayout.setVerticalGroup(
            CLASS_REG_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CLASS_REG_PANNELLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(clz_stage1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        basepanel.add(CLASS_REG_PANNEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 1010, 578));

        STUDENT_REG_PANNEL.setBackground(new java.awt.Color(255, 255, 255));

        std_stage1.setBackground(new java.awt.Color(255, 255, 255));
        std_stage1.setPreferredSize(new java.awt.Dimension(743, 535));

        std_stage2.setBackground(new java.awt.Color(252, 158, 158));

        jLabel6.setBackground(new java.awt.Color(252, 158, 158));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("       STUDENT REGISTER");

        javax.swing.GroupLayout std_stage2Layout = new javax.swing.GroupLayout(std_stage2);
        std_stage2.setLayout(std_stage2Layout);
        std_stage2Layout.setHorizontalGroup(
            std_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(std_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(std_stage2Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(112, Short.MAX_VALUE)))
        );
        std_stage2Layout.setVerticalGroup(
            std_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
            .addGroup(std_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(std_stage2Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setText("Address");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel9.setText("Grade");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel10.setText("Class / Discription");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel11.setText("Contact Number");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel12.setText("Student Name");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel13.setText("Student ID");

        std_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_idActionPerformed(evt);
            }
        });

        std_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_addressActionPerformed(evt);
            }
        });

        std_contactNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_contactNumberActionPerformed(evt);
            }
        });

        std_bday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_bdayActionPerformed(evt);
            }
        });

        std_parentEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_parentEmailActionPerformed(evt);
            }
        });

        std_class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_classActionPerformed(evt);
            }
        });

        std_stage3.setBackground(new java.awt.Color(255, 255, 255));
        std_stage3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel16.setText("Remove");
        std_stage3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel15.setText("Update");
        std_stage3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 60, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel14.setText("Register");
        std_stage3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 60, -1));

        std_updatebtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        std_updatebtn.setText("UPDATE");
        std_updatebtn.setPreferredSize(new java.awt.Dimension(100, 30));
        std_updatebtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        std_updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_updatebtnActionPerformed(evt);
            }
        });
        std_stage3.add(std_updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 11, 85, -1));

        std_removebtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        std_removebtn.setText("REMOVE");
        std_removebtn.setPreferredSize(new java.awt.Dimension(100, 30));
        std_removebtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        std_removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_removebtnActionPerformed(evt);
            }
        });
        std_stage3.add(std_removebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 11, 85, -1));

        std_addbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        std_addbtn.setText("ADD");
        std_addbtn.setPreferredSize(new java.awt.Dimension(100, 30));
        std_addbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        std_addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_addbtnActionPerformed(evt);
            }
        });
        std_stage3.add(std_addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 11, 85, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel24.setText("Parent Email");

        std_plus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgplus1.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Class_pane\\plus.png")); // NOI18N
        std_plus.add(imgplus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jLabel46.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Class_pane\\pluslbl.png")); // NOI18N
        std_plus.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        stdCombo_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdCombo_plusActionPerformed(evt);
            }
        });
        std_plus.add(stdCombo_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, -1));

        javax.swing.GroupLayout std_stage1Layout = new javax.swing.GroupLayout(std_stage1);
        std_stage1.setLayout(std_stage1Layout);
        std_stage1Layout.setHorizontalGroup(
            std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(std_stage1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(std_stage1Layout.createSequentialGroup()
                        .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(std_address, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(std_name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(std_stage1Layout.createSequentialGroup()
                                .addComponent(std_parentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(std_stage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(std_contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(std_stage1Layout.createSequentialGroup()
                                .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(std_stage1Layout.createSequentialGroup()
                                        .addComponent(std_bday, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(265, 265, 265))
                                    .addGroup(std_stage1Layout.createSequentialGroup()
                                        .addComponent(std_id, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)))
                                .addComponent(std_class, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(std_plus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(std_stage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        std_stage1Layout.setVerticalGroup(
            std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(std_stage1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(std_stage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(std_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(std_class, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(std_plus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(std_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(std_bday, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(std_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(std_contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(std_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(std_stage1Layout.createSequentialGroup()
                        .addComponent(std_parentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(std_stage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout STUDENT_REG_PANNELLayout = new javax.swing.GroupLayout(STUDENT_REG_PANNEL);
        STUDENT_REG_PANNEL.setLayout(STUDENT_REG_PANNELLayout);
        STUDENT_REG_PANNELLayout.setHorizontalGroup(
            STUDENT_REG_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(STUDENT_REG_PANNELLayout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(std_stage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        STUDENT_REG_PANNELLayout.setVerticalGroup(
            STUDENT_REG_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(STUDENT_REG_PANNELLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(std_stage1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        basepanel.add(STUDENT_REG_PANNEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 1010, 578));

        TEACHER_REG_PANNEL.setBackground(new java.awt.Color(255, 255, 255));

        teach_stage1.setBackground(new java.awt.Color(255, 255, 255));
        teach_stage1.setPreferredSize(new java.awt.Dimension(743, 535));

        teach_stage2.setBackground(new java.awt.Color(252, 158, 158));

        jLabel7.setBackground(new java.awt.Color(252, 158, 158));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("       TEACHER REGISTER");

        javax.swing.GroupLayout teach_stage2Layout = new javax.swing.GroupLayout(teach_stage2);
        teach_stage2.setLayout(teach_stage2Layout);
        teach_stage2Layout.setHorizontalGroup(
            teach_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(teach_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(teach_stage2Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(112, Short.MAX_VALUE)))
        );
        teach_stage2Layout.setVerticalGroup(
            teach_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
            .addGroup(teach_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(teach_stage2Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel25.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel25.setText("Address");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel26.setText("E-Mail");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel27.setText("Subjects Teaching");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel28.setText("Contact Number");

        jLabel29.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel29.setText("Teacher Name");

        jLabel30.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel30.setText("Teacher ID");

        teach_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teach_idActionPerformed(evt);
            }
        });

        teach_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teach_addressActionPerformed(evt);
            }
        });

        teach_contactNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teach_contactNumberActionPerformed(evt);
            }
        });

        teach_mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teach_mailActionPerformed(evt);
            }
        });

        teach_subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teach_subjectActionPerformed(evt);
            }
        });

        teach_stage3.setBackground(new java.awt.Color(255, 255, 255));
        teach_stage3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel31.setText("Remove");
        teach_stage3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, -1));

        jLabel32.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel32.setText("Update");
        teach_stage3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 60, -1));

        jLabel33.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel33.setText("Register");
        teach_stage3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 60, -1));

        teach_updatebtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        teach_updatebtn.setText("UPDATE");
        teach_updatebtn.setPreferredSize(new java.awt.Dimension(100, 30));
        teach_updatebtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        teach_updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teach_updatebtnActionPerformed(evt);
            }
        });
        teach_stage3.add(teach_updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 11, 85, -1));

        teach_removebtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        teach_removebtn.setText("REMOVE");
        teach_removebtn.setPreferredSize(new java.awt.Dimension(100, 30));
        teach_removebtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        teach_removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teach_removebtnActionPerformed(evt);
            }
        });
        teach_stage3.add(teach_removebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 11, 85, -1));

        teach_addbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        teach_addbtn.setText("ADD");
        teach_addbtn.setPreferredSize(new java.awt.Dimension(100, 30));
        teach_addbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        teach_addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teach_addbtnActionPerformed(evt);
            }
        });
        teach_stage3.add(teach_addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 11, 85, -1));

        javax.swing.GroupLayout teach_stage1Layout = new javax.swing.GroupLayout(teach_stage1);
        teach_stage1.setLayout(teach_stage1Layout);
        teach_stage1Layout.setHorizontalGroup(
            teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teach_stage1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(teach_stage1Layout.createSequentialGroup()
                        .addGroup(teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teach_address, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teach_name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(teach_stage1Layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(teach_stage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(teach_contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(teach_stage1Layout.createSequentialGroup()
                                .addGroup(teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(teach_stage1Layout.createSequentialGroup()
                                        .addComponent(teach_mail, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(265, 265, 265))
                                    .addGroup(teach_stage1Layout.createSequentialGroup()
                                        .addComponent(teach_id, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)))
                                .addComponent(teach_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(teach_stage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        teach_stage1Layout.setVerticalGroup(
            teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teach_stage1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(teach_stage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teach_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teach_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teach_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teach_mail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teach_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(teach_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teach_contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addComponent(teach_stage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout TEACHER_REG_PANNELLayout = new javax.swing.GroupLayout(TEACHER_REG_PANNEL);
        TEACHER_REG_PANNEL.setLayout(TEACHER_REG_PANNELLayout);
        TEACHER_REG_PANNELLayout.setHorizontalGroup(
            TEACHER_REG_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TEACHER_REG_PANNELLayout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(teach_stage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TEACHER_REG_PANNELLayout.setVerticalGroup(
            TEACHER_REG_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TEACHER_REG_PANNELLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(teach_stage1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        basepanel.add(TEACHER_REG_PANNEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 1010, 578));

        SETTING_PANNEL.setBackground(new java.awt.Color(209, 94, 157));

        setting_stage2.setBackground(new java.awt.Color(209, 94, 157));
        setting_stage2.setPreferredSize(new java.awt.Dimension(743, 535));

        report.setBackground(new java.awt.Color(209, 94, 157));
        report.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("   ");
        report.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 240, 70));

        jLabel50.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\report_icon.png")); // NOI18N
        report.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 90, 90));

        jLabel51.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("          Reports");
        report.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 240, 70));

        reportbtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\solidCL.png")); // NOI18N
        reportbtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\hoverCL.png")); // NOI18N
        reportbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportbtnActionPerformed(evt);
            }
        });
        report.add(reportbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 263, 266));

        account_manage.setBackground(new java.awt.Color(209, 94, 157));
        account_manage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel65.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setToolTipText("");
        account_manage.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 240, 70));

        jLabel53.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\accountManage_icon.png")); // NOI18N
        account_manage.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 90, 90));

        jLabel54.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("    Account Manage");
        account_manage.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 240, 70));

        accountManageBtn.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\solidCL.png")); // NOI18N
        accountManageBtn.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\hoverCL.png")); // NOI18N
        accountManageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountManageBtnActionPerformed(evt);
            }
        });
        account_manage.add(accountManageBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 261, 264));

        about.setBackground(new java.awt.Color(209, 94, 157));
        about.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel102.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("      Contact Us");
        about.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 240, 70));

        jLabel101.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\contact_us.png")); // NOI18N
        about.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 90, 90));

        jButton8.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\solidCL.png")); // NOI18N
        jButton8.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\hoverCL.png")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        about.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 0, 260, 264));

        contact_us.setBackground(new java.awt.Color(209, 94, 157));
        contact_us.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel104.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 255, 255));
        jLabel104.setText("            About ");
        contact_us.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 240, 70));

        jLabel103.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\about.png")); // NOI18N
        contact_us.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 90, 90));

        jButton9.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\solidCL.png")); // NOI18N
        jButton9.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\setting\\hoverCL.png")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        contact_us.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 259, 264));

        javax.swing.GroupLayout setting_stage2Layout = new javax.swing.GroupLayout(setting_stage2);
        setting_stage2.setLayout(setting_stage2Layout);
        setting_stage2Layout.setHorizontalGroup(
            setting_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setting_stage2Layout.createSequentialGroup()
                .addGroup(setting_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(report, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(account_manage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(setting_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contact_us, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(about, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        setting_stage2Layout.setVerticalGroup(
            setting_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setting_stage2Layout.createSequentialGroup()
                .addGroup(setting_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(report, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(about, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(setting_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(account_manage, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(contact_us, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SETTING_PANNELLayout = new javax.swing.GroupLayout(SETTING_PANNEL);
        SETTING_PANNEL.setLayout(SETTING_PANNELLayout);
        SETTING_PANNELLayout.setHorizontalGroup(
            SETTING_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SETTING_PANNELLayout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addComponent(setting_stage2, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        SETTING_PANNELLayout.setVerticalGroup(
            SETTING_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SETTING_PANNELLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(setting_stage2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
        );

        basepanel.add(SETTING_PANNEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 0, 760, 580));

        ATTENDANCE_PANNEL.setBackground(new java.awt.Color(255, 255, 255));

        atn_stage1.setBackground(new java.awt.Color(255, 255, 255));
        atn_stage1.setPreferredSize(new java.awt.Dimension(743, 535));

        atn_stage2.setBackground(new java.awt.Color(252, 158, 158));

        jLabel66.setBackground(new java.awt.Color(252, 158, 158));
        jLabel66.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("       ATTENDANCE");

        javax.swing.GroupLayout atn_stage2Layout = new javax.swing.GroupLayout(atn_stage2);
        atn_stage2.setLayout(atn_stage2Layout);
        atn_stage2Layout.setHorizontalGroup(
            atn_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(atn_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(atn_stage2Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(112, Short.MAX_VALUE)))
        );
        atn_stage2Layout.setVerticalGroup(
            atn_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
            .addGroup(atn_stage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(atn_stage2Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        atn_stage3.setBackground(new java.awt.Color(255, 255, 255));
        atn_stage3.setMaximumSize(new java.awt.Dimension(743, 549));
        atn_stage3.setMinimumSize(new java.awt.Dimension(0, 0));
        atn_stage3.setName(""); // NOI18N
        atn_stage3.setPreferredSize(new java.awt.Dimension(743, 549));
        atn_stage3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel68.setText("Insert");
        atn_stage3.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 60, 30));

        jLabel69.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel69.setText("Update");
        atn_stage3.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 60, 30));

        jLabel67.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel67.setText("  Add");
        atn_stage3.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 60, 30));

        text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textActionPerformed(evt);
            }
        });
        text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textKeyReleased(evt);
            }
        });
        atn_stage3.add(text, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 29, 117, 27));

        jLabel76.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel76.setText("Search Subject");
        atn_stage3.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 24, 110, 34));

        atntb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Student Name", "Subject", "Month", "Week 1", "Week 2", "Week 3", "Week 4"
            }
        ));
        atntb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atntbMouseClicked(evt);
            }
        });
        atntb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                atntbKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(atntb);

        atn_stage3.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 76, 580, 140));

        jButton1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        jButton1.setText("Insert");
        jButton1.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        atn_stage3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 110, 30));

        jButton5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        jButton5.setText("Update");
        jButton5.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        atn_stage3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 110, 30));

        jComboBox2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "week 1", "week 2", "week 3", "week 4" }));
        atn_stage3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 100, 27));

        jButton6.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        jButton6.setMaximumSize(new java.awt.Dimension(130, 30));
        jButton6.setMinimumSize(new java.awt.Dimension(130, 30));
        jButton6.setPreferredSize(new java.awt.Dimension(130, 30));
        jButton6.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        atn_stage3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 110, 30));

        jLabel77.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel77.setText("Attendance");
        atn_stage3.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 80, 30));

        jComboBox3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        atn_stage3.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 100, 26));

        jLabel78.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel78.setText("Student ID");
        atn_stage3.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, 24));

        jTextField2.setFocusTraversalPolicyProvider(true);
        atn_stage3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 100, 24));

        jLabel79.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel79.setText("Week");
        atn_stage3.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 40, 30));
        atn_stage3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 100, 25));

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Present", "Absent" }));
        atn_stage3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 84, 27));

        jLabel80.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel80.setText("Month");
        atn_stage3.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 56, -1));

        jLabel81.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel81.setText("Name");
        atn_stage3.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 40, 30));

        javax.swing.GroupLayout atn_stage1Layout = new javax.swing.GroupLayout(atn_stage1);
        atn_stage1.setLayout(atn_stage1Layout);
        atn_stage1Layout.setHorizontalGroup(
            atn_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atn_stage1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(atn_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(atn_stage3, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atn_stage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        atn_stage1Layout.setVerticalGroup(
            atn_stage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atn_stage1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(atn_stage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(atn_stage3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout ATTENDANCE_PANNELLayout = new javax.swing.GroupLayout(ATTENDANCE_PANNEL);
        ATTENDANCE_PANNEL.setLayout(ATTENDANCE_PANNELLayout);
        ATTENDANCE_PANNELLayout.setHorizontalGroup(
            ATTENDANCE_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ATTENDANCE_PANNELLayout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(atn_stage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ATTENDANCE_PANNELLayout.setVerticalGroup(
            ATTENDANCE_PANNELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ATTENDANCE_PANNELLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(atn_stage1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        basepanel.add(ATTENDANCE_PANNEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 1010, 578));

        ATTENDANCE_PANNEL_fk.setBackground(new java.awt.Color(255, 255, 255));

        atn_stage4.setBackground(new java.awt.Color(255, 255, 255));
        atn_stage4.setPreferredSize(new java.awt.Dimension(743, 535));

        atn_stage5.setBackground(new java.awt.Color(252, 158, 158));

        jLabel70.setBackground(new java.awt.Color(252, 158, 158));
        jLabel70.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setText("       ATTENDANCE");

        javax.swing.GroupLayout atn_stage5Layout = new javax.swing.GroupLayout(atn_stage5);
        atn_stage5.setLayout(atn_stage5Layout);
        atn_stage5Layout.setHorizontalGroup(
            atn_stage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(atn_stage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(atn_stage5Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(112, Short.MAX_VALUE)))
        );
        atn_stage5Layout.setVerticalGroup(
            atn_stage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
            .addGroup(atn_stage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(atn_stage5Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        atd_up_pannel.setBackground(new java.awt.Color(255, 255, 255));
        atd_up_pannel.setMaximumSize(new java.awt.Dimension(743, 549));
        atd_up_pannel.setMinimumSize(new java.awt.Dimension(0, 0));
        atd_up_pannel.setName(""); // NOI18N
        atd_up_pannel.setPreferredSize(new java.awt.Dimension(743, 549));
        atd_up_pannel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel71.setText("Update");
        atd_up_pannel.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 354, 70, 20));

        txt_sh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_shKeyReleased(evt);
            }
        });
        atd_up_pannel.add(txt_sh, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 20, 110, 28));

        jLabel72.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel72.setText("Search");
        atd_up_pannel.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 19, 78, 30));

        atntb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Student Name", "Subject", "month", "Week 1", "Week 2", "Week 3", "Week 4"
            }
        ));
        atntb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atntb2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(atntb2);

        atd_up_pannel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 550, 130));

        jLabel73.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel73.setText("Student ID");
        atd_up_pannel.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 100, -1));
        atd_up_pannel.add(txtstd_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 118, -1));

        jLabel74.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel74.setText("Attendance");
        atd_up_pannel.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 90, 20));

        txt_sub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_subActionPerformed(evt);
            }
        });
        atd_up_pannel.add(txt_sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 120, -1));

        jLabel75.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel75.setText("Week");
        atd_up_pannel.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 50, 20));
        atd_up_pannel.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 120, -1));

        cmb_mo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December" }));
        atd_up_pannel.add(cmb_mo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 120, -1));

        cmb_wk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "week1", "week2", "week3", "week4" }));
        atd_up_pannel.add(cmb_wk, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 120, -1));

        com_p.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Present", "Absent" }));
        atd_up_pannel.add(com_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, 120, 20));

        btn_up.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btn_up.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        btn_up.setMaximumSize(new java.awt.Dimension(130, 30));
        btn_up.setMinimumSize(new java.awt.Dimension(130, 30));
        btn_up.setPreferredSize(new java.awt.Dimension(130, 30));
        btn_up.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        btn_up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_upMouseClicked(evt);
            }
        });
        btn_up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_upActionPerformed(evt);
            }
        });
        atd_up_pannel.add(btn_up, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 120, 30));

        jLabel82.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel82.setText("Subject");
        atd_up_pannel.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 70, 20));

        jLabel83.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel83.setText("Month");
        atd_up_pannel.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 106, -1));

        jLabel84.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel84.setText("Name");
        atd_up_pannel.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 60, 20));

        javax.swing.GroupLayout atn_stage4Layout = new javax.swing.GroupLayout(atn_stage4);
        atn_stage4.setLayout(atn_stage4Layout);
        atn_stage4Layout.setHorizontalGroup(
            atn_stage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atn_stage4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(atn_stage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(atd_up_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atn_stage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        atn_stage4Layout.setVerticalGroup(
            atn_stage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atn_stage4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(atn_stage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atd_up_pannel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ATTENDANCE_PANNEL_fkLayout = new javax.swing.GroupLayout(ATTENDANCE_PANNEL_fk);
        ATTENDANCE_PANNEL_fk.setLayout(ATTENDANCE_PANNEL_fkLayout);
        ATTENDANCE_PANNEL_fkLayout.setHorizontalGroup(
            ATTENDANCE_PANNEL_fkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ATTENDANCE_PANNEL_fkLayout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(atn_stage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ATTENDANCE_PANNEL_fkLayout.setVerticalGroup(
            ATTENDANCE_PANNEL_fkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ATTENDANCE_PANNEL_fkLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(atn_stage4, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        basepanel.add(ATTENDANCE_PANNEL_fk, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 1010, 578));

        PAY_PANNEL.setBackground(new java.awt.Color(255, 255, 255));
        PAY_PANNEL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clz_stage6.setBackground(new java.awt.Color(255, 255, 255));
        clz_stage6.setPreferredSize(new java.awt.Dimension(743, 535));

        clz_stage7.setBackground(new java.awt.Color(252, 158, 158));

        jLabel89.setBackground(new java.awt.Color(252, 158, 158));
        jLabel89.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(51, 51, 51));
        jLabel89.setText("PAYMENTS");

        javax.swing.GroupLayout clz_stage7Layout = new javax.swing.GroupLayout(clz_stage7);
        clz_stage7.setLayout(clz_stage7Layout);
        clz_stage7Layout.setHorizontalGroup(
            clz_stage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(clz_stage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(clz_stage7Layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(112, Short.MAX_VALUE)))
        );
        clz_stage7Layout.setVerticalGroup(
            clz_stage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
            .addGroup(clz_stage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(clz_stage7Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Student_pay.setBackground(new java.awt.Color(255, 255, 255));
        Student_pay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel100.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel100.setText("Search");
        Student_pay.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 90, 20));

        jLabel99.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel99.setText("Insert");
        Student_pay.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, 90, 20));

        jLabel98.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel98.setText("Clear");
        Student_pay.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 90, 20));

        jLabel97.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel97.setText("Update");
        Student_pay.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 90, 20));

        jLabel96.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel96.setText("Delete");
        Student_pay.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 344, 90, 20));

        jLabel90.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel90.setText("Student ID");
        Student_pay.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 80, -1));

        txtStudentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudentIDActionPerformed(evt);
            }
        });
        Student_pay.add(txtStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 150, 30));

        jLabel91.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel91.setText("Student Name");
        Student_pay.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 90, -1));
        Student_pay.add(txtStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 150, 30));

        jLabel92.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel92.setText("Parent Email");
        Student_pay.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, -1));
        Student_pay.add(txtParentEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 150, 30));

        jLabel93.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel93.setText("Subject");
        Student_pay.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 70, -1));
        Student_pay.add(txtSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 150, 30));

        jLabel94.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel94.setText("Class Fee");
        Student_pay.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 70, -1));
        Student_pay.add(txtClassFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 150, 30));

        jLabel95.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel95.setText("Paid Date");
        Student_pay.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 70, 20));

        txtPaidDate.setForeground(new java.awt.Color(153, 153, 153));
        txtPaidDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPaidDateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPaidDateFocusLost(evt);
            }
        });
        txtPaidDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaidDateActionPerformed(evt);
            }
        });
        Student_pay.add(txtPaidDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 150, 30));

        jBSearch.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jBSearch.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        jBSearch.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        jBSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSearchActionPerformed(evt);
            }
        });
        Student_pay.add(jBSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 130, 30));

        jButton7.setFont(new java.awt.Font("Arial", 1, 8)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        jButton7.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        Student_pay.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 130, 30));

        jBAdd.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jBAdd.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        jBAdd.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        jBAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAddActionPerformed(evt);
            }
        });
        Student_pay.add(jBAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 130, 30));

        jBUpdate.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jBUpdate.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        jBUpdate.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        jBUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBUpdateActionPerformed(evt);
            }
        });
        Student_pay.add(jBUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 130, 30));

        jBDelete.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jBDelete.setIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_solid.png")); // NOI18N
        jBDelete.setRolloverIcon(new javax.swing.ImageIcon("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\IMG_coustomMade\\Stdpane_Icons\\bnt_hover.png")); // NOI18N
        jBDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeleteActionPerformed(evt);
            }
        });
        Student_pay.add(jBDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 130, 30));

        javax.swing.GroupLayout clz_stage6Layout = new javax.swing.GroupLayout(clz_stage6);
        clz_stage6.setLayout(clz_stage6Layout);
        clz_stage6Layout.setHorizontalGroup(
            clz_stage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clz_stage6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(clz_stage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clz_stage7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Student_pay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        clz_stage6Layout.setVerticalGroup(
            clz_stage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clz_stage6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(clz_stage7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Student_pay, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
        );

        PAY_PANNEL.add(clz_stage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 32, -1, -1));

        basepanel.add(PAY_PANNEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 1010, 578));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(basepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(basepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void std_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_idActionPerformed
        
        
    }//GEN-LAST:event_std_idActionPerformed

    private void std_bdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_bdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_bdayActionPerformed

    private void std_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_addressActionPerformed

    private void std_contactNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_contactNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_contactNumberActionPerformed

    private void std_addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_addbtnActionPerformed
 
      String id = std_id.getText();
      String name = std_name.getText();
      String bday = std_bday.getText();
      String address =std_address.getText();
      String contactNumber =std_contactNumber.getText(); 
      String parentEmail = std_parentEmail.getText();
      String clz= std_class.getText();
      
      if(id.equalsIgnoreCase("")){ std_id.setText("ID field Empty");}
      else{
      if(contactNumber.length()==10){
      std = new Student(id, name, bday, address, contactNumber, parentEmail, clz);
      std_fields_clear();
      
      STD_ADD_CONFIRM_UI std_ui01 =new STD_ADD_CONFIRM_UI(std);
      std_ui01.setVisible(true);
      }
      else{std_fields_clear(); std_contactNumber.setText("Format Wrong");}
 
      }  
    }//GEN-LAST:event_std_addbtnActionPerformed

    private void std_updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_updatebtnActionPerformed
        
        if(std_contactNumber.getText().length()==10){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            String sql="UPDATE student SET  std_name=? , std_bday=? , std_address=? , std_contactNumber=? , std_parentEmail=? , std_class=?  WHERE std_id=?";
            
            PreparedStatement pst=(PreparedStatement)con.prepareStatement(sql);
            
            pst.setString(1, std_name.getText());
            pst.setString(2, std_bday.getText());
            pst.setString(3, std_address.getText());
            pst.setString(4, std_contactNumber.getText());
            pst.setString(5, std_parentEmail.getText());
            pst.setString(6, std_class.getText());
            pst.setString(7, std_id.getText());
            
            int row =pst.executeUpdate();
            if(row>0)
            {
               std_id.setText("UPDATED") ;
            }else{std_id.setText("ID NOT EXIST");}
            std_fields_clear2();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            std_contactNumber.setText("Format Wrong");
        }
    }//GEN-LAST:event_std_updatebtnActionPerformed

    private void std_removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_removebtnActionPerformed
        String sql="DELETE FROM student WHERE std_id='" +  std_id.getText()+"'";
        
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection(data.getUrl(),data.getUser(), data.getPassword());
            Statement st = con.createStatement();
            int row =st.executeUpdate(sql);
            if(row >0)
            {
                std_id.setText("REMOVED");
            }
            else{std_id.setText("ID NOT EXIST");}
            std_fields_clear2();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_std_removebtnActionPerformed

    private void logoutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbtnActionPerformed
       dispose();
       Login ui = new Login();
       ui.setVisible(true);
      
    }//GEN-LAST:event_logoutbtnActionPerformed

    private void settingbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingbtnActionPerformed
      STUDENT_REG_PANNEL.setVisible(false);
      TEACHER_REG_PANNEL.setVisible(false);
      CLASS_REG_PANNEL.setVisible(false);
      SETTING_PANNEL.setVisible(true);
      REPORT_PANNEL.setVisible(false);
      ATTENDANCE_PANNEL.setVisible(false);
      ACCOUNTMANAGE_PANNEL.setVisible(false);
      ATTENDANCE_PANNEL_fk.setVisible(false);
      PAY_PANNEL.setVisible(false);
      
    }//GEN-LAST:event_settingbtnActionPerformed

    private void paymentbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentbtnActionPerformed
      STUDENT_REG_PANNEL.setVisible(false);
      TEACHER_REG_PANNEL.setVisible(false);
      CLASS_REG_PANNEL.setVisible(false);
      SETTING_PANNEL.setVisible(false);
      REPORT_PANNEL.setVisible(false);
      ATTENDANCE_PANNEL.setVisible(false);
      ACCOUNTMANAGE_PANNEL.setVisible(false);
      ATTENDANCE_PANNEL_fk.setVisible(false);
      PAY_PANNEL.setVisible(true);
    }//GEN-LAST:event_paymentbtnActionPerformed

    private void attendancebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendancebtnActionPerformed
     ATTENDANCE_PANNEL_fk.setVisible(false);
     REPORT_PANNEL.setVisible(false);
     STUDENT_REG_PANNEL.setVisible(false);
     TEACHER_REG_PANNEL.setVisible(false);
     CLASS_REG_PANNEL.setVisible(false);
     SETTING_PANNEL.setVisible(false);
     ACCOUNTMANAGE_PANNEL.setVisible(false);
     ATTENDANCE_PANNEL.setVisible(true);
     PAY_PANNEL.setVisible(false);
    }//GEN-LAST:event_attendancebtnActionPerformed

    private void teacherbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherbtnActionPerformed
       STUDENT_REG_PANNEL.setVisible(false);
       TEACHER_REG_PANNEL.setVisible(true);
       CLASS_REG_PANNEL.setVisible(false);
       SETTING_PANNEL.setVisible(false);
       REPORT_PANNEL.setVisible(false);
       ACCOUNTMANAGE_PANNEL.setVisible(false);
       ATTENDANCE_PANNEL.setVisible(false);
       ATTENDANCE_PANNEL_fk.setVisible(false);
       PAY_PANNEL.setVisible(false);
       
    }//GEN-LAST:event_teacherbtnActionPerformed

    private void studentbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentbtnActionPerformed
       CLASS_REG_PANNEL.setVisible(false);
       STUDENT_REG_PANNEL.setVisible(true);
       SETTING_PANNEL.setVisible(false);
       REPORT_PANNEL.setVisible(false);
       ACCOUNTMANAGE_PANNEL.setVisible(false);
       ATTENDANCE_PANNEL.setVisible(false);
       ATTENDANCE_PANNEL_fk.setVisible(false);
       PAY_PANNEL.setVisible(false);

    }//GEN-LAST:event_studentbtnActionPerformed

    private void teach_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teach_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teach_idActionPerformed

    private void teach_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teach_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teach_addressActionPerformed

    private void teach_contactNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teach_contactNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teach_contactNumberActionPerformed

    private void teach_mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teach_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teach_mailActionPerformed

    private void teach_subjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teach_subjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teach_subjectActionPerformed

    private void teach_updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teach_updatebtnActionPerformed
        if(teach_contactNumber.getText().length()!=10){teach_contactNumber.setText("Format Wrong");}
        else{
        try {
            String sql="UPDATE teacher SET teach_name=?,teach_address=?,teach_contactNumber=?,teach_mail=?,teach_subject=? WHERE teach_id=?";
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setString(1, teach_name.getText() );
            pst.setString(2, teach_address.getText());
            pst.setString(3, teach_contactNumber.getText());
            pst.setString(4, teach_mail.getText());
            pst.setString(5, teach_subject.getText());
            pst.setString(6, teach_id.getText());
            
            int row = pst.executeUpdate();
            if(row>0)
            {teach_id.setText("UPDATED") ;}
            else{teach_id.setText("ID NOT EXIST");}
            teach_fields_clear2();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_teach_updatebtnActionPerformed

    private void teach_removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teach_removebtnActionPerformed
        try {
            String sql="DELETE FROM teacher WHERE teach_id='"+teach_id.getText()+"'";
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            Statement st = con.createStatement();
            int row = st.executeUpdate(sql);
            
            if(row>0)
            {
                teach_id.setText("REMOVED");
            }
            else{teach_id.setText("ID NOT EXIST");}
            teach_fields_clear2();
            
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_teach_removebtnActionPerformed

    private void teach_addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teach_addbtnActionPerformed
      
      
      String id = teach_id.getText();
      String name = teach_name.getText();
      String address = teach_address.getText();
      String email =teach_mail.getText();
      String contactNumber =teach_contactNumber.getText();
      String subject= teach_subject.getText();
      if(id.equals(""))
      {
         teach_id.setText("ID field Empty");
      }
      else{
          if(contactNumber.length()==10){
      teach = new Teacher(id, name, address, email, contactNumber, subject);
      teach_fields_clear();
      classRegister();
      TEACH_ADD_CONFIRM_UI teach_ui = new TEACH_ADD_CONFIRM_UI(teach);
      teach_ui.setVisible(true);
      TEACHER_REG_PANNEL.setVisible(false);}
          else{teach_contactNumber.setText("Format Wrong");}
    }
    }//GEN-LAST:event_teach_addbtnActionPerformed

    private void std_parentEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_parentEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_parentEmailActionPerformed

    private void clz_addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clz_addbtnActionPerformed
      String CLZteach_id =teach_id_clz.getText();
      String CLZteach_name =teach_name_clz.getText();
      String CLZsubject =subject_clz.getText();
      String ClZgrade = lblgrade.getText();
      if(ClZgrade.equals("Select the Grade")){lblgrade.setText("Fill all the fields");}   else{
      if(CLZsubject.equalsIgnoreCase("") || ClZgrade.equals("Fill all the fields") ){
        {lblgrade.setText("Fill all the fields");}
      }else{
          try {
              cls = new clz(CLZteach_id, CLZteach_name, CLZsubject, ClZgrade);
              String sql = "INSERT INTO class (teach_id, teach_name, subject, grade) VALUES (?,?,?,?)";
      
              Class.forName("com.mysql.jdbc.Driver");
              Connection con = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
              PreparedStatement pst= (PreparedStatement) con.prepareStatement(sql);
              pst.setString(1, CLZteach_id);
              pst.setString(2, CLZteach_name);
              pst.setString(3, CLZsubject);
              pst.setInt(4, Integer.parseInt(ClZgrade));
              
              pst.executeUpdate();
              con.close();
              MASSAGE_BOX msg_UI = new MASSAGE_BOX("CLASS REGISTERED");
              msg_UI.setVisible(true);
              subject_clz.setText("");
              lblgrade.setText("Select the Grade");
          } catch (SQLException ex) {
              Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
          }
      
      
      }}
    }//GEN-LAST:event_clz_addbtnActionPerformed

    private void clz_removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clz_removebtnActionPerformed
        
        if( (teach_id_clz.getText().equalsIgnoreCase("") || subject_clz.getText().equalsIgnoreCase("")) || ((lblgrade.getText().equalsIgnoreCase("Fill all the fields") || lblgrade.getText().equalsIgnoreCase("Fill all the fields"))) ){
            
            lblgrade.setText("Fill all the fields");
        }else{
  
        String sql = "DELETE FROM class WHERE teach_id=?  AND subject=? AND  grade=?"; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, teach_id_clz.getText());
            pst.setString(2, subject_clz.getText());
            pst.setInt(3, Integer.parseInt(lblgrade.getText()));
            int row = pst.executeUpdate();
            String massage;
            if(row>0)
            {
                massage="CLASS REMOVED";
            }else{massage="CLASS NOT REGISTERED";}
            con.close();
            MASSAGE_BOX ui = new MASSAGE_BOX(massage);
            ui.setVisible(true);
            subject_clz.setText("");
            lblgrade.setText("Select the Grade");
        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
        
    }//GEN-LAST:event_clz_removebtnActionPerformed

    private void clz_viewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clz_viewbtnActionPerformed
      ResultSet rst=null;
      String sql_3 ="SELECT * FROM class WHERE teach_id =? AND subject=? AND grade =?";//ok
      String sql_2 ="SELECT * FROM class WHERE  subject=? AND grade =?";//ok
      String sql_1 ="SELECT * FROM class WHERE teach_id =?";//ok
      
      
        try {
            Connection con = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement pst;
            if(teach_id_clz.getText().equalsIgnoreCase("")){
                
            if(lblgrade.getText().equalsIgnoreCase("Select the Grade") || lblgrade.getText().equalsIgnoreCase("Fill all the fields") )  //chk grade is selected if not string --->.db.tbl (int) column mix with string
            {
                lblgrade.setText("Fill all the fields");
            }
            else{
            pst = (PreparedStatement) con.prepareStatement(sql_2);
            pst.setString(1, subject_clz.getText());
            pst.setInt(2, Integer.parseInt(lblgrade.getText()));
            rst = pst.executeQuery();
            }
            }
            
      else 
          { 
              if(subject_clz.getText().equalsIgnoreCase(""))
              {pst = (PreparedStatement) con.prepareStatement(sql_1);
              pst.setString(1, teach_id_clz.getText());
              rst = pst.executeQuery();
              }
              else
              {
                if(lblgrade.getText().equalsIgnoreCase("Select the Grade") || lblgrade.getText().equalsIgnoreCase("Fill all the fields") )  //chk grade is selected if not string --->.db.tbl (int) column mix with string
            {
                lblgrade.setText("Fill all the fields");
            }    else{
                  pst = (PreparedStatement) con.prepareStatement(sql_3);
                  pst.setString(1, teach_id_clz.getText());
                  pst.setString(2, subject_clz.getText());
                  pst.setInt(3, Integer.parseInt(lblgrade.getText()));
                  rst = pst.executeQuery();
                  }
              
              }
          }
            //table
           DefaultTableModel tbl = (DefaultTableModel) datatbl.getModel();
             //table clean
            int rowcounter = tbl.getRowCount();
           if(rowcounter!=0){
           for(int i=rowcounter ; i>0 ; i-- )
            { 
               int remove_index=i-1;
               tbl.removeRow(remove_index);
         
            }}
            
            //table data add
            while(rst.next()){
               int size= 0;
              tbl.insertRow(size,new Object[] { rst.getString("teach_id"),rst.getString("teach_name"),rst.getString("subject"), rst.getString("grade") });
              size++;
             }

        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      
    }//GEN-LAST:event_clz_viewbtnActionPerformed

    private void clz_backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clz_backbtnActionPerformed
        classClean();
        CLASS_REG_PANNEL.setVisible(false);
        TEACHER_REG_PANNEL.setVisible(true);
        ATTENDANCE_PANNEL.setVisible(false);
        PAY_PANNEL.setVisible(false);
    }//GEN-LAST:event_clz_backbtnActionPerformed

    private void subject_clzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_clzActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_clzActionPerformed

    private void teach_id_clzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teach_id_clzActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teach_id_clzActionPerformed

    private void Combo_plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_plusActionPerformed
    lblgrade.setText(Combo_plus.getSelectedItem().toString());     
    }//GEN-LAST:event_Combo_plusActionPerformed

    private void stdCombo_plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdCombo_plusActionPerformed
    std_class.setText(std_class.getText()+stdCombo_plus.getSelectedItem().toString()+",");
    }//GEN-LAST:event_stdCombo_plusActionPerformed

    private void std_classActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_classActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_classActionPerformed

    private void reportbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportbtnActionPerformed
     REPORT_PANNEL.setVisible(true);
     STUDENT_REG_PANNEL.setVisible(false);
     TEACHER_REG_PANNEL.setVisible(false);
     CLASS_REG_PANNEL.setVisible(false);
     SETTING_PANNEL.setVisible(false);
     ACCOUNTMANAGE_PANNEL.setVisible(false);
     ATTENDANCE_PANNEL.setVisible(false);
     PAY_PANNEL.setVisible(false);
    }//GEN-LAST:event_reportbtnActionPerformed

    private void std_repbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_repbtnActionPerformed
       
        
        try {
            Connection con = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            Class.forName("com.mysql.jdbc.Driver");
            
            String query = "select std_id,std_name,std_parentEmail,std_bday from student";
            JasperDesign jasperDesign = JRXmlLoader.load("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\Report\\Registation_rep.jrxml");
            
            JRDesignQuery jdesign = new JRDesignQuery();
            jdesign.setText(query);
            
            jasperDesign.setQuery(jdesign);
            
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
            
            JasperViewer.viewReport(jasperPrint,false);
            
            
            
            
            con.close();
            ////////////////
        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_std_repbtnActionPerformed

    private void User_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_User_loginActionPerformed
     try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            JasperDesign jdesign = JRXmlLoader.load("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\Report\\report2.jrxml");
            String query = "select * from admin";
            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);
            jdesign.setQuery(updateQuery);
            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, conn);
            JasperViewer.viewReport(jprint,false);

        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);;
        } catch (JRException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
 
                                           
    
    }//GEN-LAST:event_User_loginActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        id.setText(model.getValueAt(selectedRow, 0).toString());
        name.setText(model.getValueAt(selectedRow, 3).toString());
        mail.setText(model.getValueAt(selectedRow, 1).toString());
        no.setText(model.getValueAt(selectedRow, 5).toString());
        address.setText(model.getValueAt(selectedRow, 6).toString());
        password.setText(model.getValueAt(selectedRow, 2).toString());
        com.setText(model.getValueAt(selectedRow, 7).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        try {

            String query = "DELETE FROM `admin` WHERE id=?";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());

            pst = conn.prepareStatement(query);
            pst.setString(1, id.getText());

            int row = pst.executeUpdate();
            if (row > 0) {
                /* JOptionPane.showMessageDialog(null," removed");*/
                JOptionPane.showMessageDialog(null, "removed", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                /*JOptionPane.showMessageDialog(null,"Invalid ID Number  ");*/
                JOptionPane.showMessageDialog(null, "Invalid ID Number", "Error", JOptionPane.ERROR_MESSAGE);
            }

            clear();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

        showTableData();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        if (no.getText().length() != 10) {
            JOptionPane.showMessageDialog(null, "Please enter a correct contact number", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", mail.getText()))) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    /*String query="UPDATE admin SET mail=?,password=?,name=? ,no=? ,address=? type=?,comfirm=? WHERE id=?" ;*/
                    String query = "UPDATE `admin` SET `mail`=?,`password`=?,`name`=?,`type`=?,`no`=?,`address`=?,`comfirm`=? WHERE id=?";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
                    pst = conn.prepareStatement(query);

                    pst.setString(1, mail.getText());
                    pst.setString(2, password.getText());
                    pst.setString(3, name.getText());
                    pst.setString(4, no.getText());
                    String value = type.getSelectedItem().toString();
                    pst.setString(5, value);
                    pst.setString(8, id.getText());
                    pst.setString(7, com.getText());
                    pst.setString(6, address.getText());

                    int row = pst.executeUpdate();
                    if (row > 0) {
                        /*JOptionPane.showMessageDialog(null," Update");*/
                        JOptionPane.showMessageDialog(null, "UPDATE ", "Error", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        /*  JOptionPane.showMessageDialog(null,"Invalid ID Number  ");*/
                        JOptionPane.showMessageDialog(null, "Invalid ID Number", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    clear();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        showTableData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        if (no.getText().length() != 10) {
            JOptionPane.showMessageDialog(null, "Please enter a correct contact number", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", mail.getText()))) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                try {
                    String query = " INSERT INTO `admin`(`id`, `mail`, `password`, `name`, `type`,`no`,`address`) VALUES (?,?,?,?,?,?,?)";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
                    pst = conn.prepareStatement(query);
                    pst.setString(1, id.getText());
                    pst.setString(2, mail.getText());
                    pst.setString(3, password.getText());
                    pst.setString(4, name.getText());
                    String value = type.getSelectedItem().toString();
                    pst.setString(5, value);
                    pst.setString(6, no.getText());
                    pst.setString(7, address.getText());
                    if (id.equals("")) {
                        JOptionPane.showMessageDialog(null, "Id cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (password.getText().equals(com.getText())) {

                            pst.executeUpdate();

                            JOptionPane.showMessageDialog(null, "succefully", "Error", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "not matching password", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                    }

                    /*JOptionPane.showMessageDialog(null,"succefully");*/
                    clear();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                }
            }
        }

        showTableData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void accountManageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountManageBtnActionPerformed
     REPORT_PANNEL.setVisible(false);
     STUDENT_REG_PANNEL.setVisible(false);
     TEACHER_REG_PANNEL.setVisible(false);
     CLASS_REG_PANNEL.setVisible(false);
     SETTING_PANNEL.setVisible(false);
     ACCOUNTMANAGE_PANNEL.setVisible(true);
     ATTENDANCE_PANNEL_fk.setVisible(false);
    }//GEN-LAST:event_accountManageBtnActionPerformed

    private void textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textActionPerformed

    private void textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textKeyReleased
        String serachText = text.getText();
        String subject = "";
        String grade = "";

        if (!serachText.isBlank() && serachText != null) {
            String serachVal[] = serachText.split(",");

            if(serachVal.length > 1) {
                subject = serachVal[0];
                grade = serachVal[1];
            } else if (serachVal.length == 1 ){
                subject = serachVal[0];
            }
        }

        DefaultTableModel tbl = (DefaultTableModel) atntb.getModel();

        int r = atntb.getRowCount();
        while(r-- > 0){
            tbl.removeRow(r);
        }

        try {
            ResultSet rs =  DBcon.getData(subject,grade);

            while(rs.next()){
                java.util.Vector v = new java.util.Vector();

                v.add(rs.getString("std_id"));
                v.add(rs.getString("std_name"));
                v.add(rs.getString("std_class"));
                System.out.println(rs.getString("std_id"));
                tbl.addRow(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error");
        }
    }//GEN-LAST:event_textKeyReleased

    private void atntbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atntbMouseClicked

        DefaultTableModel tblmodel = (DefaultTableModel) atntb.getModel();

        String tblID = tblmodel.getValueAt(atntb.getSelectedRow(),0).toString();
        String tblName = tblmodel.getValueAt(atntb.getSelectedRow(),1).toString();
        //         Object month = tblmodel.getValueAt(jTable1.getSelectedRow(),3);
        //         String str = (String) month;

        jTextField2.setText(tblID);
        jTextField3.setText(tblName);
        //         jComboBox3.setSelectedItem(month);

    }//GEN-LAST:event_atntbMouseClicked

    private void atntbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_atntbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_atntbKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        DefaultTableModel tblmodel = (DefaultTableModel) atntb.getModel();
        String studentID, studentname, subject, month, week1, week2, week3, week4;

        try {
            String url = "jdbc:mysql://localhost:3306/ppa_mysql";
            Class.forName("com.mysql.cj.jdbc.Driver");
            //st = con.createStatement();
            Connection con = DriverManager.getConnection(url, "root", "721812");

            if (atntb.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Please select one to insert");
            } else {
                int i = atntb.getSelectedRow();
                studentID = tblmodel.getValueAt(i, 0).toString();
                studentname = tblmodel.getValueAt(i, 1).toString();
                subject = tblmodel.getValueAt(i, 2).toString();

                if (tblmodel.getValueAt(i, 3) == null) {
                    month = "";
                    JOptionPane.showMessageDialog(this, "Month can not be null for student with Id " + studentID);
                } else {
                    month = tblmodel.getValueAt(i, 3).toString();

                    if (tblmodel.getValueAt(i, 4) == null) {
                        week1 = "N/A";
                    } else {
                        week1 = tblmodel.getValueAt(i, 4).toString();
                    }

                    if (tblmodel.getValueAt(i, 5) == null) {
                        week2 = "N/A";
                    } else {
                        week2 = tblmodel.getValueAt(i, 5).toString();;
                    }

                    if (tblmodel.getValueAt(i, 6) == null) {
                        week3 = "N/A";
                    } else {
                        week3 = tblmodel.getValueAt(i, 6).toString();;
                    }

                    if (tblmodel.getValueAt(i, 7) == null) {
                        week4 = "N/A";
                    } else {
                        week4 = tblmodel.getValueAt(i, 7).toString();;
                    }

                    if (hasRecord(con, studentID, month)) {
                        JOptionPane.showMessageDialog(null, "A record for student " + studentID + " for month " + month + " has been already inserted. Update instead.");
                    } else {
                        String query = "insert into attendance values(?,?,?,?,?,?,?,?)";

                        PreparedStatement prpstmt = con.prepareStatement(query);
                        prpstmt.setString(1, studentID);
                        prpstmt.setString(2, studentname);
                        prpstmt.setString(3, subject);
                        prpstmt.setString(4, month);
                        prpstmt.setString(5, week1);
                        prpstmt.setString(6, week2);
                        prpstmt.setString(7, week3);
                        prpstmt.setString(8, week4);

                        prpstmt.execute();
                        JOptionPane.showMessageDialog(this, "Data for student " + studentID + " inserted successfuly");
                    }
                }
            }
            con.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        DefaultTableModel tblmodel = (DefaultTableModel) atntb.getModel();

        if(atntb.getSelectedRowCount()== 1){

            String selectedvalue3 = jComboBox3.getSelectedItem().toString();
            String selectedvalue1 = jComboBox1.getSelectedItem().toString();
            String selectedvalue2 = jComboBox2.getSelectedItem().toString();

            tblmodel.setValueAt(selectedvalue3,atntb.getSelectedRow(),3);

            if(selectedvalue2 == "week 1"){
                tblmodel.setValueAt(selectedvalue1,atntb.getSelectedRow(),4);
            }
            else if(selectedvalue2 == "week 2"){
                tblmodel.setValueAt(selectedvalue1,atntb.getSelectedRow(),5);
            }
            else if(selectedvalue2 == "week 3"){
                tblmodel.setValueAt(selectedvalue1,atntb.getSelectedRow(),6);
            }
            else{
                tblmodel.setValueAt(selectedvalue1,atntb.getSelectedRow(),7);
            }

        } else{}

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void txt_shKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_shKeyReleased

        String serachText = txt_sh.getText();
        String subject = "";
        String grade = "";

        if (!serachText.isBlank() && serachText != null) {
            String serachVal[] = serachText.split(",");

            if(serachVal.length > 1) {
                subject = serachVal[0];
                grade = serachVal[1];
            } else if (serachVal.length == 1 ){
                subject = serachVal[0];
            }
        }

        DefaultTableModel tbl = (DefaultTableModel) atntb2.getModel();

        int r = atntb2.getRowCount();
        while(r-- > 0){
            tbl.removeRow(r);
        }

        try {
            ResultSet rs =  DBcon.getAttendanceData(subject,grade);

            while(rs.next()){
                java.util.Vector v = new java.util.Vector();

                v.add(rs.getString("studentID"));
                v.add(rs.getString("studentname"));
                v.add(rs.getString("subject"));
                v.add(rs.getString("month"));
                v.add(rs.getString("week1"));
                v.add(rs.getString("week2"));
                v.add(rs.getString("week3"));
                v.add(rs.getString("week4"));

                tbl.addRow(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error");
        }
    }//GEN-LAST:event_txt_shKeyReleased

    private void atntb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atntb2MouseClicked
        DefaultTableModel tblmodel = (DefaultTableModel) atntb2.getModel();

        String tblID = tblmodel.getValueAt(atntb2.getSelectedRow(),0).toString();
        String tblName = tblmodel.getValueAt(atntb2.getSelectedRow(),1).toString();
        String tblSubName = tblmodel.getValueAt(atntb2.getSelectedRow(),2).toString();
        Object month = tblmodel.getValueAt(atntb2.getSelectedRow(),3);
        //         String str = (String) month;

        txtstd_ID.setText(tblID);
        txt_name.setText(tblName);
        txt_sub.setText(tblSubName);
        cmb_mo.setSelectedItem(month);
    }//GEN-LAST:event_atntb2MouseClicked

    private void txt_subActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_subActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_subActionPerformed

    private void btn_upMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_upMouseClicked

    }//GEN-LAST:event_btn_upMouseClicked

    private void btn_upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_upActionPerformed
        DefaultTableModel tblmodel = (DefaultTableModel) atntb2.getModel();
        String studentID, studentname, subject, month, week, atndnce;

        try {
            String url = "jdbc:mysql://localhost:3306/ppa_mysql";
            Class.forName("com.mysql.cj.jdbc.Driver");
            //st = con.createStatement();
            Connection con = DriverManager.getConnection(url, "root", "721812");

            studentID = txtstd_ID.getText();
            atndnce = com_p.getSelectedItem().toString();
            month = cmb_mo.getSelectedItem().toString();
            week = cmb_wk.getSelectedItem().toString();

            String query = "update attendance set month = ?,"+ week +"=? where studentID = ? ";

            PreparedStatement prpstmt = con.prepareStatement(query);
            prpstmt.setString(1, month);
            prpstmt.setString(2, atndnce);
            prpstmt.setString(3, studentID);

            prpstmt.execute();
            JOptionPane.showMessageDialog(this, "Data for student " + studentID + " updated successfuly");

            con.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_upActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        STUDENT_REG_PANNEL.setVisible(false);
        TEACHER_REG_PANNEL.setVisible(false);
        CLASS_REG_PANNEL.setVisible(false);
        SETTING_PANNEL.setVisible(false);
        REPORT_PANNEL.setVisible(false);
        ATTENDANCE_PANNEL.setVisible(false);
        ACCOUNTMANAGE_PANNEL.setVisible(false);
        ATTENDANCE_PANNEL_fk.setVisible(true);
        PAY_PANNEL.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void atd_repbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atd_repbtnActionPerformed
       
    try {
            Connection con = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
            Class.forName("com.mysql.jdbc.Driver");

            String query = "select studentID,studentname,subject,week1,week2,week3,week4 from attendance where month='Octomber'";
            JasperDesign jasperDesign = JRXmlLoader.load("E:\\ppa_netb\\PPA_Dewmin\\src\\main\\java\\Report\\attendance.jrxml");

            JRDesignQuery jdesign = new JRDesignQuery();
            jdesign.setText(query);

            jasperDesign.setQuery(jdesign);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);

            JasperViewer.viewReport(jasperPrint, false);
            ////////////////
        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_atd_repbtnActionPerformed

    private void txtStudentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentIDActionPerformed

    private void txtPaidDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPaidDateFocusGained

    }//GEN-LAST:event_txtPaidDateFocusGained

    private void txtPaidDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPaidDateFocusLost

    }//GEN-LAST:event_txtPaidDateFocusLost

    private void txtPaidDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaidDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaidDateActionPerformed

    private void jBSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSearchActionPerformed
        try {
            // TODO add your handling code here:
            Insertps = con.prepareStatement("SELECT `student id`, `student name`, `parent email`, `subject`, `class fee`, `paid date` FROM `student_payment_t` WHERE `student id`=? ");
            String studentId = txtStudentID.getText();
            Insertps.setString(1, studentId);
            ResultSet rs = Insertps.executeQuery();
            if (rs.next() == false) {
                JOptionPane.showMessageDialog(this, "Sorry Records Not Found, Please Check The Student ID");
                txtStudentName.setText("");
                txtParentEmail.setText("");
                txtSubject.setText("");
                txtClassFee.setText("");
                txtPaidDate.setText("");
                txtStudentID.requestFocus();

            } else {

                txtStudentName.setText(rs.getString("student name"));
                txtParentEmail.setText(rs.getString("parent email"));
                txtSubject.setText(rs.getString("subject"));
                txtClassFee.setText(rs.getString("class fee"));
                txtPaidDate.setText(rs.getString("paid date"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jBSearchActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        txtStudentName.setText("");
        txtParentEmail.setText("");
        txtSubject.setText("");
        txtClassFee.setText("");
        txtPaidDate.setText("");
        txtStudentID.requestFocus();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jBAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAddActionPerformed
        String studentId = txtStudentID.getText();
        String studentName = txtStudentName.getText();
        String parenetEmail = txtParentEmail.getText();
        String subject = txtSubject.getText();
        String classFee = txtClassFee.getText();
        String paidDate = txtPaidDate.getText();

        if (!studentId.isEmpty()) {
            if (!studentName.isEmpty()) {
                if (!subject.isEmpty()) {
                    if (!classFee.isEmpty()) {
                        int result;
                        String sql = "INSERT INTO `student_payment_t`(`student id`, `student name`, `parent email`, `subject`, `class fee`, `paid date`) VALUES (?,?,?,?,?,?)";
                        try {
                            Insertps = con.prepareStatement(sql);
                            Insertps.setString(1, studentId);
                            Insertps.setString(2, studentName);
                            Insertps.setString(3, parenetEmail);
                            Insertps.setString(4, subject);
                            Insertps.setString(5, classFee);
                            Insertps.setString(6, paidDate);

                            result = Insertps.executeUpdate();
                            if (result == 1) {
                                JOptionPane.showMessageDialog(null, "Insert Successfull", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                                this.SendMailHandler();
                            } else {
                                JOptionPane.showMessageDialog(null, "Insert Error", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Please Insert class Fee ", "Class Fee", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please Insert Subject ", "Subject", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Insert Student Name ", "Student Name", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Insert Student ID", "Student ID", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBAddActionPerformed

    private void jBUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBUpdateActionPerformed

        String studentId = txtStudentID.getText();
        String studentName = txtStudentName.getText();
        String parenetEmail = txtParentEmail.getText();
        String subject = txtSubject.getText();
        String classFee = txtClassFee.getText();
        String paidDate = txtPaidDate.getText();

        if (!studentId.isEmpty()) {
            if (!studentName.isEmpty()) {
                if (!subject.isEmpty()) {
                    if (!classFee.isEmpty()) {
                        int result;
                        String sql = "UPDATE `student_payment_t` SET `student name`=?,`parent email`=?,`subject`=?,`class fee`=?,`paid date`=? WHERE `student id`=?";
                        try {
                            Insertps = con.prepareStatement(sql);

                            Insertps.setString(1, studentName);
                            Insertps.setString(2, parenetEmail);
                            Insertps.setString(3, subject);
                            Insertps.setString(4, classFee);
                            Insertps.setString(5, paidDate);
                            Insertps.setString(6, studentId);

                            result = Insertps.executeUpdate();
                            if (result == 1) {
                                JOptionPane.showMessageDialog(null, "Records Updated", "Sucess", JOptionPane.INFORMATION_MESSAGE);

                                txtStudentName.setText("");
                                txtParentEmail.setText("");
                                txtSubject.setText("");
                                txtClassFee.setText("");
                                txtPaidDate.setText("");
                                txtStudentID.requestFocus();
                            } else {
                                JOptionPane.showMessageDialog(null, "Records Not Updated", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Please Insert class Fee ", "Class Fee", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please Insert Subject ", "Subject", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Insert Student Name ", "Student Name", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Insert Student ID", "Student ID", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jBUpdateActionPerformed

    private void jBDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeleteActionPerformed

        String studentId = txtStudentID.getText();
        int result;
        String sql = "DELETE FROM `student_payment_t` WHERE `student id`=?";
        try {
            Insertps = con.prepareStatement(sql);
            Insertps.setString(1, studentId);

            result = Insertps.executeUpdate();
            if (result == 1) {
                JOptionPane.showMessageDialog(null, "Records Deleted", "Sucess", JOptionPane.INFORMATION_MESSAGE);

                txtStudentName.setText("");
                txtParentEmail.setText("");
                txtSubject.setText("");
                txtClassFee.setText("");
                txtPaidDate.setText("");
                txtStudentID.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Records Not Deleted", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(STD_REG_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBDeleteActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String massage = "Version 1.0 BETA";
        MASSAGE_BOX msg = new MASSAGE_BOX(massage);
        msg.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String massage = "Aggrasive_BOTZ@gmail.com";
        MASSAGE_BOX msg = new MASSAGE_BOX(massage);
        msg.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed
    private boolean hasRecord(Connection con, String studentID, String month) throws SQLException {
        
        boolean hasRecord = false;
        
        String selectQuery = "select * from attendance where studentID = ? and month = ?";
        PreparedStatement st = con.prepareStatement(selectQuery);

        st.setString(1, studentID);
        st.setString(2, month);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            hasRecord = true;
        }
        
        return hasRecord;
    }
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(STD_REG_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(STD_REG_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(STD_REG_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(STD_REG_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new STD_REG_UI().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ACCOUNTMANAGE_PANNEL;
    private javax.swing.JPanel ATTENDANCE_PANNEL;
    private javax.swing.JPanel ATTENDANCE_PANNEL_fk;
    private javax.swing.JPanel CLASS_REG_PANNEL;
    private javax.swing.JComboBox<String> Combo_plus;
    private javax.swing.JPanel PAY_PANNEL;
    private javax.swing.JPanel REPORT_PANNEL;
    private javax.swing.JPanel SETTING_PANNEL;
    private javax.swing.JPanel STUDENT_REG_PANNEL;
    private javax.swing.JPanel Student_pay;
    private javax.swing.JPanel TEACHER_REG_PANNEL;
    private javax.swing.JButton User_login;
    private javax.swing.JPanel about;
    private javax.swing.JButton accountManageBtn;
    private javax.swing.JPanel account_manage;
    private javax.swing.JTextArea address;
    private javax.swing.JButton atd_repbtn;
    private javax.swing.JPanel atd_up_pannel;
    private javax.swing.JPanel atn_stage1;
    private javax.swing.JPanel atn_stage2;
    private javax.swing.JPanel atn_stage3;
    private javax.swing.JPanel atn_stage4;
    private javax.swing.JPanel atn_stage5;
    private javax.swing.JTable atntb;
    private javax.swing.JTable atntb2;
    private javax.swing.JButton attendancebtn;
    private javax.swing.JPanel basepanel;
    private javax.swing.JButton btn_up;
    private javax.swing.JButton clz_addbtn;
    private javax.swing.JButton clz_backbtn;
    private javax.swing.JPanel clz_plus;
    private javax.swing.JButton clz_removebtn;
    private javax.swing.JPanel clz_stage1;
    private javax.swing.JPanel clz_stage2;
    private javax.swing.JPanel clz_stage3;
    private javax.swing.JPanel clz_stage4;
    private javax.swing.JPanel clz_stage5;
    private javax.swing.JPanel clz_stage6;
    private javax.swing.JPanel clz_stage7;
    private javax.swing.JButton clz_viewbtn;
    private javax.swing.JComboBox<String> cmb_mo;
    private javax.swing.JComboBox<String> cmb_wk;
    private javax.swing.JPasswordField com;
    private javax.swing.JComboBox<String> com_p;
    private javax.swing.JPanel contact_us;
    private javax.swing.JTable datatbl;
    private javax.swing.JTextField id;
    private javax.swing.JLabel imgplus;
    private javax.swing.JLabel imgplus1;
    private javax.swing.JButton jBAdd;
    private javax.swing.JButton jBDelete;
    private javax.swing.JButton jBSearch;
    private javax.swing.JButton jBUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lblgrade;
    private javax.swing.JButton logoutbtn;
    private javax.swing.JTextField mail;
    private javax.swing.JPanel menupanel;
    private javax.swing.JTextField name;
    private javax.swing.JTextField no;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton paymentbtn;
    private javax.swing.JPanel report;
    private javax.swing.JPanel report_stage1;
    private javax.swing.JButton reportbtn;
    private javax.swing.JPanel setting_stage2;
    private javax.swing.JButton settingbtn;
    private javax.swing.JPanel sizefix_panel;
    private javax.swing.JComboBox<String> stdCombo_plus;
    private javax.swing.JButton std_addbtn;
    private javax.swing.JTextField std_address;
    private javax.swing.JTextField std_bday;
    private javax.swing.JTextField std_class;
    private javax.swing.JTextField std_contactNumber;
    private javax.swing.JTextField std_id;
    private javax.swing.JTextField std_name;
    private javax.swing.JTextField std_parentEmail;
    private javax.swing.JPanel std_plus;
    private javax.swing.JButton std_removebtn;
    private javax.swing.JButton std_repbtn;
    private javax.swing.JPanel std_stage1;
    private javax.swing.JPanel std_stage2;
    private javax.swing.JPanel std_stage3;
    private javax.swing.JButton std_updatebtn;
    private javax.swing.JButton studentbtn;
    private javax.swing.JTextField subject_clz;
    private javax.swing.JButton teach_addbtn;
    private javax.swing.JTextField teach_address;
    private javax.swing.JTextField teach_contactNumber;
    private javax.swing.JTextField teach_id;
    private javax.swing.JTextField teach_id_clz;
    private javax.swing.JTextField teach_mail;
    private javax.swing.JTextField teach_name;
    private javax.swing.JTextField teach_name_clz;
    private javax.swing.JButton teach_removebtn;
    private javax.swing.JPanel teach_stage1;
    private javax.swing.JPanel teach_stage2;
    private javax.swing.JPanel teach_stage3;
    private javax.swing.JTextField teach_subject;
    private javax.swing.JButton teach_updatebtn;
    private javax.swing.JButton teacherbtn;
    private javax.swing.JTextField text;
    private javax.swing.JTextField txtClassFee;
    private javax.swing.JTextField txtPaidDate;
    private javax.swing.JTextField txtParentEmail;
    private javax.swing.JTextField txtStudentID;
    private javax.swing.JTextField txtStudentName;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_sh;
    private javax.swing.JTextField txt_sub;
    private javax.swing.JTextField txtstd_ID;
    private javax.swing.JComboBox<String> type;
    // End of variables declaration//GEN-END:variables
}
