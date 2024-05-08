package org.example.lab10_jsx;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "mainServlet", value = "/mainServlet")
public class mainServlet extends HttpServlet {
    public void init()
    {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        String url="jdbc:mysql://localhost:3306/Lab10?autoReconnect=true&useSSL=false";
        String user="root";
        String passwordBD="qazwsx2005";
        String name=request.getParameter("name");
        Connection cn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            cn= DriverManager.getConnection(url,user,passwordBD);
            PreparedStatement st=cn.prepareStatement("INSERT INTO students (fio,course,groupNum) values ((?),(?),(?))");
            String fio=request.getParameter("fio");
            String course=request.getParameter("course");
            String groupNum=request.getParameter("groupNum");
            st.setString(1,fio);
            st.setString(2,course);
            st.setString(3,groupNum);
            st.executeUpdate();
            cn.close();
            request.setAttribute("name",name);
            request.getRequestDispatcher("/welcome.jsp").forward(request,response);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        String url="jdbc:mysql://localhost:3306/Lab10?autoReconnect=true&useSSL=false";
        String user="root";
        String passwordBD="qazwsx2005";
        String name=request.getParameter("name");
        Connection cn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            cn= DriverManager.getConnection(url,user,passwordBD);
            PreparedStatement st=cn.prepareStatement("DELETE  FROM students WHERE studentId=(?)");
            String id=request.getParameter("id");
            st.setString(1,id);
            st.executeUpdate();
            cn.close();
            request.setAttribute("name",name);
            request.getRequestDispatcher("/welcome.jsp").forward(request,response);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
