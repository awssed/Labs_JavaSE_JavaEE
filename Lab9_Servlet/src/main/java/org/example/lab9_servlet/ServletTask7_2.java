package org.example.lab9_servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletTask7_1", value = "/ServletTask7_1")
public class ServletTask7_2 extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Получаем значения параметров запроса
        response.setContentType("text/html;charset=UTF-8");
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");

        // Генерируем ответ
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Ответ Сервлет-два:</h1>");
        out.println("Параметр 1: " + param1);
        out.println("<br>Параметр 2: " + param2);
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
