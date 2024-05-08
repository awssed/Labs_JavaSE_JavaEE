package org.example.TagManager;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

@SuppressWarnings("serial")

public class submit extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            try {
                out.println("<input type=\"submit\" required value=\"Вход\" name=\"input\">\n" +
                        " <input type=\"submit\" required value=\"Регистрация\" name=\"registration\">");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return SKIP_BODY;
        } finally {

        }
    }
}
