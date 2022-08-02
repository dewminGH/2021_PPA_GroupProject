
package REG_MODELS;

/**
 *
 * @author ASUS
 */
public class clz {
    private String teach_id;
    private String teach_name;
    private String subject;
    private String grade;

    public clz(String teach_id, String teach_name, String subject, String grade) {
        this.teach_id = teach_id;
        this.teach_name = teach_name;
        this.subject = subject;
        this.grade = grade;
    }

    public String getTeach_id() {
        return teach_id;
    }

    public String getTeach_name() {
        return teach_name;
    }

    public String getSubject() {
        return subject;
    }

    public String getGrade() {
        return grade;
    }
    
    
}
