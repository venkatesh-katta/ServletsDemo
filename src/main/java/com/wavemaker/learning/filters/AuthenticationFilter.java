package com.wavemaker.learning.filters;

import com.wavemaker.learning.alogorithm.CookieHandler;
import com.wavemaker.learning.models.User;
import com.wavemaker.learning.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by venkateswarluk on 20/7/16.
 */
public class AuthenticationFilter implements Filter {

    String filterUrl = "login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        UserService userService = new UserService();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = userService.loadUser(name);

        if (filterRequest(httpServletRequest.getRequestURI())) {
            if (user.getPassword().equals(password)) {
                out.print("You are successfully logged in!");
                out.print("<br>Welcome, " + name);
                CookieHandler.addCookie(httpServletRequest, httpServletResponse, name);
                //httpServletResponse.sendRedirect("index.jsp");
                //chain.doFilter(request, response);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                out.print("<b>sorry, username or password is incorrect!!!!<b>");
                request.getRequestDispatcher("login.html").include(request, response);
            }
            chain.doFilter(request, response);
        }
        else {
            Cookie cookie[] = httpServletRequest.getCookies();
            if (CookieHandler.validateCookie(cookie)) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.setContentType("text/html");
                request.getRequestDispatcher("login.html").include(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean filterRequest(String url) {
        return url.contains(filterUrl);
    }
}
