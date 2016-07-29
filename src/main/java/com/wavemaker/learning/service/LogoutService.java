package com.wavemaker.learning.service;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by venkateswarluk on 21/7/16.
 */
public class LogoutService implements IService<Object> {

    @Override
    public Object doAction(List<String> params, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("auth-cookie")) {
                Cookie cookie = new Cookie(cookies[i].getName(), null);
                cookie.setMaxAge(0);
                cookie.setPath(cookies[i].getPath());
                response.addCookie(cookie);

                out.print("<center><b>Sucessfully Logout!!</b></center>");
            }
        }
        out.print("<center><b>Please Login First</b></center>");
        out.print("<html lang=en>");
        out.print("<head>");
        out.print("<meta charset=UTF-8>");
        out.print("</head>");
        out.print("<body>");
        out.print("<center>");
        out.print("<form action=login method=post>");
        out.print("<h1>Login Form</h1>");
        out.print("<table>");
        out.print("<tr>");
        out.print("<td>User Name :</td> <td><input type=text name=name></td><br>");
        out.print("</tr>");
        out.print("<tr>");
        out.print("<td>Password :</td> <td><input type=password name=password></td><br>");
        out.print("</tr>");
        out.print("<tr>");
        out.print("<td><input type=submit value=Submit></td>");
        out.print("</tr>");
        out.print("</table>");
        out.print("</form>");
        out.print("</center>");
        out.print("</body>");
        out.print(" </html>");
        return null;
    }


}
