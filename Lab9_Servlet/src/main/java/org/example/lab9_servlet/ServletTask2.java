package org.example.lab9_servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet(name = "ServletTask2", value = "/ServletTask2")
public class ServletTask2 extends HttpServlet {

    public void init() {
        // Initialization code goes here
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //параметры
        String action = request.getParameter("action");
        String login = request.getParameter("userLogin");
        String userPassword = request.getParameter("userPassword");
        String lastTime="";
        String userType="";
        int visitCount=1;
        //куки
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie:cookies)
        {
            if(cookie.getName().equals("lastVisit"+login))
            {
                lastTime=cookie.getValue();
            }
            if (cookie.getName().equals("visitCount"+login))
            {
                visitCount= Integer.parseInt(cookie.getValue());
            }
            if(cookie.getName().equals("userType"+login))
            {
                userType=cookie.getValue();
            }
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            if ("login".equals(action)) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false", "root", "qazwsx2005")) {
                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT Roles.role_name " +
                                    "FROM Users " +
                                    "JOIN Roles ON Users.role_id = Roles.id " +
                                    "WHERE Users.username = ? AND Users.password = SHA2(?, 256);"
                    );

                    statement.setString(1, login);
                    statement.setString(2, userPassword);

                    ResultSet result = statement.executeQuery();
                    if (result.next()) {
                        LocalTime time = LocalTime.now();
                        LocalDate date = LocalDate.now();
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Результат отправки формы входа</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Добро пожаловать!</h1>");
                        out.println("<p>Ваш логин: " + login + "</p>");
                        out.println("<p>Ваш пароль: " + userPassword + "</p>");
                        out.println("<p>Ваша роль: " + result.getString("role_name") + "</p>");
                        out.println("<p>Текущее время: " + time + "</p>");
                        out.println("<p>Текущее дата: " + date + "</p>");
                        out.println("<h3>Cookies</h3");
                        out.println("<p>Число посещений: "+visitCount+"</p>");
                        out.println("<p>Время последнего посещения: "+lastTime+"</p>");
                        out.println("<p>Роль: "+userType+"</p>");
                        out.println("</body>");
                        out.println("</html>");

                        //Cookie

                        //последнее время посещения
                        Cookie newVisitTimeCookie=new Cookie("lastVisit"+login,LocalTime.now().toString());
                        newVisitTimeCookie.setMaxAge(150 * 60 * 60);

                        //количесвто посещений
                        Cookie newVisitCountCookie=new Cookie("visitCount"+login,String.valueOf(visitCount++));
                        newVisitCountCookie.setMaxAge(150*60*60);

                        //тип пользователя
                        Cookie newUserTypeCookie=new Cookie("userType"+login,result.getString("role_name"));
                        newUserTypeCookie.setMaxAge(150*60*60);

                        //добавить куки в ответ
                        response.addCookie(newVisitTimeCookie);
                        response.addCookie(newVisitCountCookie);
                        response.addCookie(newUserTypeCookie);

                    }
                    else {
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Результат отправки формы входа</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Неверный пароль!!</h1>");
                        out.println("<a href = 'Auth.jsp'>Вернуться обратно</a>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
            }
            if("register".equals(action))
            {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false", "root", "qazwsx2005")) {
                    PreparedStatement statement = connection.prepareStatement(
                            "INSERT INTO Users (username, password,role_id)"+
                                "VALUES (?, SHA2(?, 256),1)"
                    );

                    statement.setString(1, login);
                    statement.setString(2, userPassword);

                    int rowsAffected=statement.executeUpdate();
                    if(rowsAffected>0)
                    {
                        out.println("<h1>Пользователь успешно прошёл регистрацию!</h1>");
                        out.println("<a href = 'Auth.jsp'>Вернуться обратно</a>");
                    }
                    else
                    {
                        out.println("<h1>Пользователь c таким логином уже существует!!</h1>");
                        out.println("<a href = 'Reg.jsp'>Вернуться обратно</a>");
                    }
                }
            }
        } catch (Exception e) {

            out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void destroy() {
        // Cleanup code goes here
    }
}