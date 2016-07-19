package com.wavemaker.learning.servlets;

import com.wavemaker.learning.models.User;
import com.wavemaker.learning.base64.EncodeandDecode;
import com.wavemaker.learning.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by venkateswarluk on 15/7/16.
 */
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = userService.loadUser(name);
        if (password.equals(user.getPassword())) {
            out.print("You are successfully logged in!");
            out.print("<br>Welcome, " + name);

            addCookie(request, response, name);
            response.sendRedirect("index.jsp");
        } else {
            out.print("<b>sorry, username or password is incorrect!!!!<b>");
            request.getRequestDispatcher("login.html").include(request, response);
        }

        out.close();

    }

    private void addCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        String encodedName = EncodeandDecode.base64Encode(name);
        Cookie cookie = new Cookie("auth-cookie", encodedName);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }
}
