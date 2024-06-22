/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jarre
 */
public class CourseEntry {
    private String semester;
    private String courseCode;
    private String courseDesc;
    private int seats;
    
    public CourseEntry(String semester, String courseCode, String courseDesc, int seats)
    {
        this.semester = semester;
        this.courseCode = courseCode;
        this.courseDesc = courseDesc;
        this.seats = seats;
    }
    
    public CourseEntry(String semester, String courseCode, String courseDesc, String seats)
    {
        this.semester = semester;
        this.courseCode = courseCode;
        this.courseDesc = courseDesc;
        this.seats = Integer.parseInt(seats);
    }
    
    public String getSemester(){
        return this.semester;
    }
    
    public String getCourseCode(){
        return this.courseCode;
    }
        
    public String getCourseDesc(){
        return this.courseDesc;
    }
    
    public int getSeats(){
        return this.seats;
    }    
}
