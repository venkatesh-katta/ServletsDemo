package com.wavemaker.learning.servlets;


import com.wavemaker.learning.alogorithm.CookieHandler;
import com.wavemaker.learning.models.Products;
import com.wavemaker.learning.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by venkateswarluk on 14/7/16.
 */
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] cookie = request.getCookies();
        String name = CookieHandler.getAuthCookie(cookie);
        if (name != null && !name.isEmpty()) {
            out.print("<b>Welcome to Profile</b>");
            out.print("<br>Welcome, " + name);
            List<Products> productsList = productService.loadProducts();
            out.print("<table border='1' width='100%'");
            out.print("<tr><th>ProductID</th><th>ProductName</th></tr>");
            for (Products e : productsList) {
                out.print("<tr><td>" + e.getProductID() +
                        "</td><td>" + e.getProductName() +
                        "</td><td><a href='productsDetails?id=" + e.getProductID() + "'>ProductDetailInformation</a></td> ");
            }
            out.print("</table>");
        }
        out.print("<b><center>Please login first</b></center>");
        request.getRequestDispatcher("login.html").include(request, response);
        out.close();
    }
}
