package org.example.lab10_jsx.classes;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DAO implements IConnection,IQuery {
    private String url;
    private String user;
    private String password;
    private String driver;
    private static Connection connection;

    public DAO() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        url = resourceBundle.getString("db.url");
        user = resourceBundle.getString("db.user");
        password = resourceBundle.getString("db.password");
        driver = resourceBundle.getString("db.driver");
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException("Driver class is missing in classpath", e);
        }
    }

    @Override
    public Boolean getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Statement getStatment() {
        if (connection != null) {
            try {
                return connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public ResultSet ExecuteQuery(String sqlQuery) {
        try {
            this.getConnection();
            Statement s = connection.createStatement();
            return s.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            this.closeConnection();
        }
    }

    public User GetUser(String login, String password) {
        if (connection != null) {
            try {
                PreparedStatement s = connection.prepareStatement(
                        "SELECT * FROM Users" +
                                "WHERE Users.login=(?) and Users.password=SHA2(?, 256),1)"
                );
                ResultSet rs = s.executeQuery();
                if (rs != null)
                    return new User(login, password);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.closeConnection();
            }
        }
        return null;
    }

    public List<Student> GetStudents() {
        if (connection != null) {
            List<Student> students = new ArrayList<Student>();
            try {
                String query = "SELECT * FROM Students";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("studentId");
                    String studentFio = resultSet.getString("fio");
                    int course = resultSet.getInt("course");
                    int groupNum = resultSet.getInt("groupNum");
                    Student student = new Student(studentId, studentFio, course, groupNum);
                    students.add(student);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
            return students;
        }
        return null;
    }

    public boolean AddUser(String login, String password) {
        if (connection != null) {
            String query = "INSERT INTO users (login, password) VALUES (?, SHA2(?, 256))";
            try {
                PreparedStatement st=connection.prepareStatement(query);
                st.setString(1,login);
                st.setString(2,password);
                st.executeQuery();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
