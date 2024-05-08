package org.example.lab9_servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

@WebServlet(name = "ServletTask1", value = "/ServletTask1")
public class ServletTask1 extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String protocol=request.getProtocol();
        String ipAdr=request.getRemoteAddr();
        String clientName=request.getRemoteHost();
        String method=request.getMethod();
        String url=request.getRequestURL().toString();

        String queryString = request.getQueryString();

        PrintWriter out = response.getWriter();
        out.println("Time now :"+ LocalTime.now()+"<br/>");
        out.println("Protocol: " + protocol+"<br/>");
        out.println("IP Address: " + ipAdr+"<br/>");
        out.println("Client Name: " + clientName+"<br/>");
        out.println("Method: " + method+"<br/>");
        out.println("URL: " + url+"<br/>");
        out.println("Parametrs: " + queryString+"<br/>" );
    }

    public void destroy() {
    }
}