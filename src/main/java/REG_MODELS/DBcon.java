/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REG_MODELS;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class DBcon {
    public static Statement st;
    public static Connection conn;
    public static PreparedStatement getData;
    public static PreparedStatement getAttnData;
    static{
    
    try{
        String url = "jdbc:mysql://localhost:3306/ppa_mysql";
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url,"root","721812");
        st = conn.createStatement();
        getData = conn.prepareStatement("SELECT std_id,std_name,std_class from student where std_class like ? and std_bday like ?"); 
        getAttnData = conn.prepareStatement("SELECT * from attendance where subject like ? and studentname like ?"); 
    }catch (SQLException ex){
        ex.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(DBcon.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
     public static ResultSet getData(String subject, String grade) throws SQLException{
        getData.setString(1,"%"+subject+"%");
        getData.setString(2,"%"+grade+"%");
        return getData.executeQuery();
    
    }
     
     public static ResultSet getAttendanceData(String subject, String grade) throws SQLException{
        getAttnData.setString(1,"%"+subject+"%");
        getAttnData.setString(2,"%"+grade+"%");
        return getAttnData.executeQuery();
    
    }
      
}
