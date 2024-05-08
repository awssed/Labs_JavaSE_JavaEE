import DAO.DAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.Console;
import java.sql.SQLException;
import java.util.List;

public class Main {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);
    private static DAO dao;
    public static void main(String[] args) throws SQLException {
        try{
            DAO dao=new DAO("db");
            dao.getConnection();
            LOG.info("Succesful connection");
            List<String> res=dao.getTeacherHasLesson("Понедельник","101");
            System.out.println("Преподаватели в 101 адитории в понедельник");
            LOG.info("Преподаватели в 101 адитории в понедельник");
            for(String teacher:res)
            {
                System.out.println(teacher);
                LOG.info(teacher);
            }
            System.out.println("Преподаватели у которыз нету занятия в понедельник");
            LOG.info("Преподаватели у которыз нету занятия в понедельник");
            res=dao.getTeacherHasNoLesson("Понедельник");
            for(String teacher:res)
            {
                System.out.println(teacher);
                LOG.info(teacher);
            }
            System.out.println("Дни в которые есть 3 занятие");
            LOG.info("Дни в которые есть 3 занятие");
            res=dao.findDaysWithGivenClassCount(3);
            for(String day:res)
            {
                System.out.println(day);
                LOG.info(day);
            }
            res=dao.findDaysWithGivenClassRoomCount(2);
            for(String day:res)
            {
                System.out.println(day);
            }
            dao.firstLessonToEnd("Понедельник");
        }
        catch (Exception e)
        {
            LOG.error(e);
        }
        finally {
            dao.CloseConnection();

        }
    }
}