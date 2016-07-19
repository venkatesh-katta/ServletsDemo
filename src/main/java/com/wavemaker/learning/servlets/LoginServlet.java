package com.wavemaker.learning.servlets;

import com.wavemaker.learning.Models.User;
import com.wavemaker.learning.base64.EncodeandDecode;
import com.wavemaker.learning.helper.QueryHelper;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkateswarluk on 15/7/16.
 */
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryHelper queryHelper = new QueryHelper();
        ResultSet resultSet = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        List<User> list = new ArrayList<User>();

        try {
            resultSet = queryHelper.query("select * from Login");
            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String dbPassword = null;

        for (User l : list) {
            dbPassword = l.getPassword();
        }
        if (password.equals(dbPassword)) {
            out.print("You are successfully logged in!");
            out.print("<br>Welcome, " + name);

            String encodedName = EncodeandDecode.base64Encode(name);
            Cookie cookie = new Cookie("auth-cookie", encodedName);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
            response.sendRedirect("index.jsp");
        } else {
            out.print("<b>sorry, username or password error!!!!<b>");
            request.getRequestDispatcher("login.html").include(request, response);
        }

        out.close();

    }
}
