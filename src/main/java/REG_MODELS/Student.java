
package REG_MODELS;

public class Student {
    private String std_id;
    private String std_name;
    private String std_bday;
    private String std_address;
    private String std_contactNumber;
    private String std_parentEmail;
    private String std_class;

    public Student(String std_id, String std_name, String std_bday, String std_address, String std_contactNumber, String std_parentEmail, String std_class) {
        this.std_id = std_id;
        this.std_name = std_name;
        this.std_bday = std_bday;
        this.std_address = std_address;
        this.std_contactNumber = std_contactNumber;
        this.std_parentEmail = std_parentEmail;
        this.std_class = std_class;
    }

    public String getStd_id() {
        return std_id;
    }

    public String getStd_name() {
        return std_name;
    }

    public String getStd_bday() {
        return std_bday;
    }

    public String getStd_address() {
        return std_address;
    }

    public String getStd_contactNumber() {
        return std_contactNumber;
    }

    public String getStd_parentEmail() {
        return std_parentEmail;
    }

    public String getStd_class() {
        return std_class;
    }
    
    
    
    
    
    
    
    
    
}
