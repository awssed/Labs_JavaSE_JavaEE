<%@ page import="org.example.lab10_jsx.classes.DAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.lab10_jsx.classes.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="Styles/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="container">
    <div id="tableDiv">
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>ФИО</th>
                <th>Курс</th>
                <th>Группа</th>
            </tr>
            </thead>
            <tbody>
            <%
                try {
                    String url = "jdbc:mysql://localhost:3306/Lab10?autoReconnect=true&useSSL=false";
                    String user = "root";
                    String passwordBD = "qazwsx2005";
                    Connection cn = null;
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                        cn = DriverManager.getConnection(url, user, passwordBD);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    List<Student> students = new ArrayList<Student>();
                    String query = "SELECT * FROM Students";
                    PreparedStatement statement = cn.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        int studentId = resultSet.getInt("studentId");
                        String studentFio = resultSet.getString("fio");
                        int course = resultSet.getInt("course");
                        int groupNum = resultSet.getInt("groupNum");
                        Student student = new Student(studentId, studentFio, course, groupNum);
                        students.add(student);
                    }
                    for (Student s : students) {
            %>
            <tr>
                <td><%= s.getStudentId() %></td>
                <td><%= s.getStudentFio() %></td>
                <td><%= s.getCourse() %></td>
                <td><%= s.getGroup() %></td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
            </tbody>
        </table>
        <div id="add-remove-div">
            <form id="add-form" method="post" action="mainServlet">
                <h3 style="font-weight: bold">Добавить студента</h3>
                <input type="text" placeholder="Фио" name="fio" autocomplete="off"/>
                <input type="text" placeholder="Курс" name="course" autocomplete="off"/>
                <input type="text" placeholder="Группа" name="groupNumber" autocomplete="off"/>
                <button type="submit">Добавить</button>
            </form>
            <br/><br/>
            <form id="remove-form" method="get" action="mainServlet">
                <h3 style="font-weight: bold">Удалить удалить студента</h3>
                <input type="text" placeholder="ID" name="id" autocomplete="off"/>
                <button type="submit">Удалить</button>
            </form>
        </div>

    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>