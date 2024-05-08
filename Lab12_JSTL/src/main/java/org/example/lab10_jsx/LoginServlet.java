package org.example.lab10_jsx;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.lab10_jsx.classes.DAO;
import org.example.lab10_jsx.classes.User;

import java.io.IOException;
import java.sql.*;

@WebServlet(name ="LoginServlet", value="/Login")
public class LoginServlet extends HttpServlet {

    public void init()
    {
    }
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//    {
//
//    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url="jdbc:mysql://localhost:3306/Lab10?autoReconnect=true&useSSL=false";
        String user="root";
        String passwordBD="qazwsx2005";
        Connection cn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            cn= DriverManager.getConnection(url,user,passwordBD);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        String login=request.getParameter("login");
        String password=request.getParameter("password");
        if(login.isEmpty()||password.isEmpty())
        {
            request.setAttribute("ErrorMessage","Пустое поле");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        try {
            PreparedStatement st= cn.prepareStatement("SELECT * from users where login=(?) and password=SHA2(?, 256)");
            st.setString(1,login);
            st.setString(2,password);
            ResultSet rs=st.executeQuery();
            if(!rs.next())
            {
                request.setAttribute("ErrorMessage","Неверный логин или пароль");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
            User userObj=new User(login,password);
            HttpSession session=request.getSession();
            session.setAttribute("User",userObj);
            request.setAttribute("name",login);

            request.getRequestDispatcher("/welcome.jsp").forward(request,response);
            cn.close();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
