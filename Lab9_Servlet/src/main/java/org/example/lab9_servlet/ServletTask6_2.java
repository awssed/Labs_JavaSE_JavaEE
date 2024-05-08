package org.example.lab9_servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletTask6_2", value = "/ServletTask6_2")
public class ServletTask6_2 extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String p1 = (String) request.getAttribute("param1");
        String p2 = (String) request.getAttribute("param2");

        try {
            writer.println("<h2>Параметр: " + p1 + "</h2>");
            writer.println("<h2>Параметр: " + p2 + "</h2>");
        } finally {
            writer.close();
        }
    }

    public void destroy() {
    }
}
