package org.example.lab9_servlet;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

@WebFilter(urlPatterns = "/ServletTask2")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = servletRequest.getParameter("action");
        String login = servletRequest.getParameter("userLogin");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            if ("login".equals(action)) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false", "root", "qazwsx2005")) {
                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT * " +
                                    "FROM Users " +
                                    "WHERE Users.username = ?;"
                    );
                    statement.setString(1,login);
                    ResultSet result = statement.executeQuery();
                    if (!result.next()) {
                        // Redirect to the "Reg.jsp" page
                        ((HttpServletResponse)servletResponse).sendRedirect("Reg.jsp");
                        return; // Stop the filter chain
                    }
                    filterChain.doFilter(servletRequest, servletResponse);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
