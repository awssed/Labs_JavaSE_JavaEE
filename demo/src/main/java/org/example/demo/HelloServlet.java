package org.example.demo;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        Connection con= null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","qazwsx2005");
        Statement st=con.createStatement();
        st.execute("INSERT INTO users (login, password) VALUES 'login','123'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}