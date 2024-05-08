package DAO;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class    DAO implements IConnection,IQuerry {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public DAO(String bundle) {
        ResourceBundle resources=ResourceBundle.getBundle(bundle);
        this.url=resources.getString("db.url");
        this.user=resources.getString("db.user");
        this.password=resources.getString("db.password");
    }

    @Override
    public Boolean getConnection() throws SQLException {
        this.connection=DriverManager.getConnection(url,user,password);
        return true;
    }

    @Override
    public void CloseConnection() throws SQLException {
        connection.close();
    }

    @Override
    public List<String> getTeacherHasLesson(String day,String room) throws SQLException {
        ArrayList<String> result=new ArrayList<>() ;
        String querry="SELECT *\n" +
                "from teachers INNER JOIN subjects on\n" +
                "teachers.teacher_id=subjects.teacher_id\n" +
                "WHERE subjects.class_day = (?)\n"+
                "AND subjects.classroom=(?)";
        PreparedStatement ps= this.connection.prepareStatement(querry);
        ps.setString(1, day);
        ps.setString(2, room);
        ResultSet rs= ps.executeQuery();
        while(rs.next())
        {
            result.add(rs.getString("teacher_name"));
        }
        return result;
    }

    @Override
    public List<String> getTeacherHasNoLesson(String day) throws SQLException {
        ArrayList<String> result=new ArrayList<>();
        String querry="SELECT *\n" +
                "FROM teachers\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT *\n" +
                "    FROM subjects\n" +
                "    WHERE teachers.teacher_id = subjects.teacher_id\n" +
                "    AND subjects.class_day = (?)\n" +
                ");";
        PreparedStatement ps= this.connection.prepareStatement(querry);
        ps.setString(1, day);
        ResultSet rs= ps.executeQuery();
        while(rs.next())
        {
            result.add(rs.getString("teacher_name"));
        }
        return result;
    }

    @Override
    public List<String> findDaysWithGivenClassCount(int count) throws SQLException {
        ArrayList<String> result=new ArrayList<>();
        String querry="SELECT subjects.class_day\n" +
                "from subjects\n" +
                "GROUP BY subjects.class_day\n" +
                "HAVING COUNT(*)=(?)";
        PreparedStatement ps= this.connection.prepareStatement(querry);
        ps.setInt(1, count);
        ResultSet rs= ps.executeQuery();
        while(rs.next())
        {
            result.add(rs.getString("subjects.class_day"));
        }
        return result;
    }

    @Override
    public List<String> findDaysWithGivenClassRoomCount(int count) throws SQLException {
        ArrayList<String> result=new ArrayList<>();
        String querry="SELECT class_day\n" +
                "FROM Subjects\n" +
                "GROUP BY class_day\n" +
                "HAVING COUNT(DISTINCT classroom) = (?);";
        PreparedStatement ps= this.connection.prepareStatement(querry);
        ps.setInt(1, count);
        ResultSet rs= ps.executeQuery();
        while(rs.next())
        {
            result.add(rs.getString("subjects.class_day"));
        }
        return result;
    }

    @Override
    public boolean firstLessonToEnd(String day) throws SQLException {
        //--------Getting first lesson------------
        int firstSubjectId=-1;
        String querryForFirst="SELECT * \n" +
                "FROM subjects\n" +
                "where subjects.class_day=(?)\n" +
                "order by class_time\n" +
                "limit 1";
        PreparedStatement ps=this.connection.prepareStatement(querryForFirst);
        ps.setString(1,day);
        ResultSet result=ps.executeQuery();
        while(result.next())
        {
            firstSubjectId=result.getInt("subject_id");
        }
        if(firstSubjectId==-1)
            return false;
        //----------Getting last lesson----------
        LocalTime lastSubjectTime = null;
        String querryForLast="SELECT * \n" +
                "FROM subjects\n" +
                "where subjects.class_day=(?)\n" +
                "order by class_time DESC\n" +
                "limit 1";
        PreparedStatement ps2=connection.prepareStatement(querryForLast);
        ps2.setString(1,day);
        result=ps2.executeQuery();
        while (result.next())
        {
            lastSubjectTime=result.getTime("class_time").toLocalTime();
        }
        String updateQuerry="UPDATE subjects SET class_time = (?) WHERE subject_id = (?)";
        PreparedStatement ps3=connection.prepareStatement(updateQuerry);
        lastSubjectTime=lastSubjectTime.plusHours(1);
        ps3.setTime(1,Time.valueOf(lastSubjectTime));//Plus 1 hour and cast to SQl time
        ps3.setInt(2,firstSubjectId);
        ps3.executeUpdate();
        return true;
    }

    @Override
    public boolean addTeacher(String name, int numberStudents) {
        try {
            this.connection.setAutoCommit(false);
            String querryInsert="INSERT INTO Teachers (teacher_name, num_students)\n" +
                    "VALUES\n" +
                    "  (?,?)";
            PreparedStatement ps=connection.prepareStatement(querryInsert);
            ps.setString(1,name);
            ps.setInt(2,numberStudents);
            ps.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        }
        catch (Exception e)
        {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return false;
    }
}
