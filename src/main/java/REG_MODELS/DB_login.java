
package REG_MODELS;


public class DB_login {
    private final String url= "jdbc:mysql://localhost:3306/ppa_mysql";
    private final String user="root";
    private final String password="721812";

    public DB_login() {
    }

   

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
