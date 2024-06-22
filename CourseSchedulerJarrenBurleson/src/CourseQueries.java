

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jarre
 */
public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourseList;
    private static PreparedStatement getSeats;
    private static PreparedStatement fixSeats;
    private static ResultSet resultSet;
    
    //Add course to database using CourseEntry
    public static void addCourse(CourseEntry course) throws Exception
    {
        connection = DBConnection.getConnection();
        
        try
        {
            addCourse = connection.prepareStatement("insert into app.courses (semester,coursecode,description,seats) values (?,?,?,?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getCourseDesc());
            addCourse.setString(4, Integer.toString(course.getSeats()));
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            throw new Exception("This course exists already.");
        }
    }
    
    //get ArrayList of all courses for a semester as a CourseEntry type 
    public static ArrayList<CourseEntry> getAllCourses(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> course = new ArrayList<CourseEntry>();
        try
        {
            getCourseList = connection.prepareStatement("select * from app.courses where semester=(?)");
            getCourseList.setString(1,semester);
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
            {
                CourseEntry currentCourse = new CourseEntry(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                course.add(currentCourse);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return course;
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> course = new ArrayList<String>();
        try
        {
            getCourseList = connection.prepareStatement("select coursecode from app.courses where semester=(?)");
            getCourseList.setString(1,semester);
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
            {
                course.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return course;
    } 
    
    public static int getCourseSeats(String semester, String coursecode)
    {
        connection = DBConnection.getConnection();
        int seats = 0;
        try
        {
            getSeats = connection.prepareStatement("select seats from app.courses where semester=(?) and coursecode=(?)");
            getSeats.setString(1,semester);
            getSeats.setString(2,coursecode);
            resultSet = getSeats.executeQuery();
            
            while(resultSet.next())
            {
                seats = resultSet.getInt(1);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return seats;
    }
    
    public static void updateSeats(String semester, String courseCode, int seats){
        connection = DBConnection.getConnection();
        
        try
        {
            fixSeats = connection.prepareStatement("UPDATE app.courses SET seats =(?) where semester =(?) and courseCode=(?)");
            fixSeats.setString(1, Integer.toString(seats));
            fixSeats.setString(2, semester);
            fixSeats.setString(3, courseCode);
            fixSeats.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }        
    }
    
    //TODO for part two add functionality to dropCourse
    public static void dropCourse(String Semester, String courseCode)
    {
        
    }
    
}
