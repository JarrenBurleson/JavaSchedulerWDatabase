
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

//TODO Need to add CourseEntry class and pass that to addcourse rather than individual variables. Also create StudentEntry class and continue creating StudentQueries class
//Really just create all entry classes first ie CourseEntry, StudentEntry and ScheduleEntry
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudentList;
    private static PreparedStatement getStudent;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student) throws Exception
    {
        
        connection = DBConnection.getConnection();
        
        try
        {
            addStudent = connection.prepareStatement("insert into app.students (studentid,firstname,lastname) values (?,?,?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            throw new Exception("This student already exists.");
        }
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getStudentList = connection.prepareStatement("select * from app.students");
            resultSet = getStudentList.executeQuery();
            
            while(resultSet.next())
            {
                StudentEntry currentStudent = new StudentEntry(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3));
                students.add(currentStudent);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
    
    public static StudentEntry getStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        StudentEntry student = null;
        
        try
        {
            getStudent = connection.prepareStatement("select * from app.students where studentID=(?)");
            getStudent.setString(1,studentID);
            resultSet = getStudent.executeQuery();

            student = new StudentEntry(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3));
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return student;
    }
    
    
    //TODO add functionality to dropStudent
    public static void dropStudent()
    {
        
    }
}
