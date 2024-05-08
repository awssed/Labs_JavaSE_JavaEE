package DAO;

import java.sql.SQLException;
import java.util.List;

public interface IQuerry {
    public List<String> getTeacherHasLesson(String day,String room) throws SQLException;
    public List<String> getTeacherHasNoLesson(String day) throws SQLException;
    public List<String> findDaysWithGivenClassCount(int count) throws SQLException;
    public List<String> findDaysWithGivenClassRoomCount(int count) throws SQLException;
    public boolean firstLessonToEnd(String day) throws SQLException;
    public boolean addTeacher(String name,int numberStudents);

}
