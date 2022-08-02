
package REG_MODELS;


public class Teacher {
    
    private String teach_id;
    private String teach_name;
    private String teach_address;
    private String teach_email;
    private String teach_contatNumber;
    private String teach_subject;

    public Teacher(String teach_id, String teach_name, String teach_address, String teach_email, String teach_contatNumber, String teach_subject) {
        this.teach_id = teach_id;
        this.teach_name = teach_name;
        this.teach_address = teach_address;
        this.teach_email = teach_email;
        this.teach_contatNumber = teach_contatNumber;
        this.teach_subject = teach_subject;
    }

    public String getTeach_id() {
        return teach_id;
    }

    public String getTeach_name() {
        return teach_name;
    }

    public String getTeach_address() {
        return teach_address;
    }

    public String getTeach_email() {
        return teach_email;
    }

    public String getTeach_contatNumber() {
        return teach_contatNumber;
    }

    public String getTeach_subject() {
        return teach_subject;
    }
    
    
    
}
