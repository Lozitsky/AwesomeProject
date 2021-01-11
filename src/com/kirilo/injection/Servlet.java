package com.kirilo.injection;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet", "/"}, loadOnStartup = 1)
public class Servlet extends HttpServlet {
    @EJB
    private HelloBean bean;

    @Inject
    private HelloPojo pojo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final Writer writer = response.getWriter();
        writer.append("<html>");
        writer.append("<body>");
        writer.append("<h1>Hello simple WebServlet!</h1>");
        writer.append("<h1>Hello " + bean.getNameType() + "!!</h1>");
        writer.append("<h1>Hello " + pojo.getNameType() + "!!</h1>");
        writer.append("</body>");
        writer.append("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
