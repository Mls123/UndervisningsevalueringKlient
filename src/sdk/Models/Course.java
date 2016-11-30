package sdk.Models;

/**
 * Dette er et DTO af course med dets attributter - Denne klasse bruges til og gemme objekter
 */
public class Course {
    private String code;
    private String displaytext;
    private int course_id;
    private String id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplaytext() {
        return displaytext;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDisplaytext(String displaytext) {
        this.displaytext = displaytext;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
