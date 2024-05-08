package org.example.lab9_servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletTask7_1", value = "/ServletTask7_1")
public class ServletTask7_1 extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Формируем GET-запрос к Сервлет-два
        response.setContentType("text/html;charset=UTF-8");
        String param1 = "value1";
        String param2 = "value2";
        String url = "http://localhost:8080/ServletTask7_2?param1=" + param1 + "&param2=" + param2;

        // Отправляем GET-запрос
        java.net.HttpURLConnection connection = (java.net.HttpURLConnection) new java.net.URL(url).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        StringBuilder response1 = new StringBuilder();
        try (java.io.BufferedReader in = new java.io.BufferedReader(
                new java.io.InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
        }

        // Выводим ответ пользователю
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Ответ от Сервлет-два:</h1>");
        out.println(response1.toString());
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
