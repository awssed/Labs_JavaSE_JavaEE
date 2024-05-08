package org.example.lab10_jsx;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab10_jsx.classes.DAO;
import org.example.lab10_jsx.classes.User;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "RegisterServlet",value = "/Register")
public class RegisterServlet extends HttpServlet {

    public void init()
    {
    }
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
        String repPassword=request.getParameter("repPassword");

        if(!password.equals(repPassword)) {
            request.setAttribute("ErrorMessage", "Пароль не совпадает");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        if(login.length()!=0 && password.length()!=0 && repPassword.length()!=0 && cn!=null) {
            try {
                PreparedStatement st = cn.prepareStatement("SELECT * from users where login=(?)");
                st.setString(1,login);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    request.setAttribute("ErrorMessage", "Пользователь с таким логином уже сущестует");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {
                    st=null;
                    st = cn.prepareStatement("INSERT INTO users (login,password) values((?),SHA2(?, 256))");
                    st.setString(1, login);
                    st.setString(2, password);
                    st.executeUpdate();
                    cn.close();
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();

    }
}
