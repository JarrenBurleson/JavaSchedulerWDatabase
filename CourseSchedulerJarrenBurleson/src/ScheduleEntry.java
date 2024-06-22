
import java.util.Calendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jarre
 */
public class ScheduleEntry {

    private String semester;
    private String courseCode;
    private String studentID;
    private String status;
    private java.sql.Timestamp timestamp;
    
    public ScheduleEntry(String semester, String courseCode, String studentID, String status)
    {
        this.semester = semester;
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.status = status;
        this.timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        System.out.println(this.timestamp);
    }
    
    public ScheduleEntry(String semester, String courseCode, String studentID, String status, java.sql.Timestamp timestamp)
    {
        this.semester = semester;
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.status = status;
        this.timestamp = timestamp;
    }
    
    public String getSemester(){
        return this.semester;
    }
    
    public String getCourseCode(){
        return this.courseCode;
    }
        
    public String getStudentID(){
        return this.studentID;
    }
    
    public String getStatus(){
        return this.status;
    }    
    
    public java.sql.Timestamp getTimestamp(){
        return this.timestamp;
    }
    
}
