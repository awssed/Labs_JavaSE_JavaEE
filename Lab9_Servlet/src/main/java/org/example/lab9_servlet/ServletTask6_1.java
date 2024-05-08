package org.example.lab9_servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

@WebServlet(name = "ServletTask6_1", value = "/ServletTask6_1")
public class ServletTask6_1 extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/ServletTask6_2");
        // Получение параметров из первого запроса
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");

        // Установка параметров как атрибутов запроса
        request.setAttribute("param1", param1);
        request.setAttribute("param2", param2);

        // Переадресация во второй сервлет
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
            }

    public void destroy() {
    }
}
