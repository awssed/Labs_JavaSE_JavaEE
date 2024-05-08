package org.example.lab8_firstwebapp;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "<div style=\"text-align: center; background: linear-gradient(45deg, #6a11cb, #2575fc);\">\n" +
                "  <h2 style=\"color: white\">SAFONOV EUGENE POIT_4-2</h2>\n" +
                "  <h3 style=\"color: white\">Faculty of Information Technology</h3>\n" +
                "  <h3 style=\"color: white\">Information Technology Software</h3>\n" +
                "</div>";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(message);
        out.println("</body></html>");
    }

    public void destroy() {
    }
}