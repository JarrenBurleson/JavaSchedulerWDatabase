
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
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleList;
    private static PreparedStatement getCount;
    private static ResultSet resultSet;
    
    //Add course to database using ScheduleEntry
    public static void addScheduleEntry(ScheduleEntry entry) throws Exception
    {
        connection = DBConnection.getConnection();
        
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester,coursecode,studentID,status,timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getCourseCode());
            addScheduleEntry.setString(3, entry.getStudentID());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setString(5, entry.getTimestamp().toString());
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            throw new Exception("You have already been scheduled or waitlisted for that class.");
        }
    }    
    

    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = null;
        
        try
        {
            getScheduleList = connection.prepareStatement("select * from app.schedule where studentid = (?) and semester = (?)");
            getScheduleList.setString(1,studentID);
            getScheduleList.setString(2,semester);
            resultSet = getScheduleList.executeQuery();
        
            while(resultSet.next())
            {
                if (schedule == null){
                    schedule = new ArrayList<ScheduleEntry>();
                }
                schedule.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;
    }
    
    public int getScheduledStudentCount(String semester, String courseCode){
        connection = DBConnection.getConnection();
        int count = 0;
        
                try
        {
            getCount = connection.prepareStatement("select count(*) as total from app.schedule where coursecode=(?) and semester = (?)");
            getScheduleList.setString(1,courseCode);
            getScheduleList.setString(2,semester);
            resultSet = getScheduleList.executeQuery();
            
            count = resultSet.getInt("total");
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }
    
    //TODO All below functions are for part two of the final project
    /*
    public ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode){
        
    }
    
    public ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode){
        
    }*/
        
    public static void dropStudentSchedulebyCourse(String semester, String courseCode){
        
    }    
    
    public static void dropSchedulebyCourse(String semester, String courseCode){
        
    }
    
    public static void updateScheduleEntry(String semester, String courseCode){
        
    }
}
