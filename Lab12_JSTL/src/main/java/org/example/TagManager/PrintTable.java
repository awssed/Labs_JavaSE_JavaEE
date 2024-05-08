package org.example.TagManager;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.sql.*;

@SuppressWarnings("serial")
public class PrintTable extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("<table><tr>\n" +
                    "        <th>Id</th>\n" +
                    "        <th>ФИО</th>\n" +
                    "        <th>Курс</th>\n" +
                    "        <th>Группа</th>\n" +
                    "    </tr><tbody>");
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
            String query = "SELECT * FROM Students";
            PreparedStatement statement = cn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                out.println("<td>"+resultSet.getString(1)+"</td>");
                out.println("<td>"+resultSet.getString(2)+"</td>");
                out.println("<td>"+resultSet.getString(3)+"</td>");
                out.println("<td>"+resultSet.getString(4)+"</td>");
            }
            out.println("</tbody></table>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return SKIP_BODY;
    }
}
