package com.wavemaker.learning.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by venkateswarluk on 15/7/16.
 */
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("auth-cookie")) {
                Cookie cookie = new Cookie(cookies[i].getName(), null);
                cookie.setMaxAge(0);
                cookie.setPath(cookies[i].getPath());
                response.addCookie(cookie);
                out.print("Sucessfully LOgout!!");
            }
        }

        out.print("<b>Please User First Login</b>");
        request.getRequestDispatcher("login.html").include(request, response);


    }
}
