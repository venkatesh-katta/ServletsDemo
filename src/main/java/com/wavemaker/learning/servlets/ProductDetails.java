package com.wavemaker.learning.servlets;

import javax.servlet.http.HttpServlet;

/**
 * Created by venkateswarluk on 15/7/16.
 */
public class ProductDetails extends HttpServlet {

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ProductDetailsService productDetailsService = new ProductDetailsService();
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        String productId = request.getParameter("id");
//        Cookie[] cookie = request.getCookies();
//        String name =  CookieHandler.getAuthCookie(cookie);
//        if (name != null &&!name.isEmpty()) {
//            out.print("<b>Welcome to Profile</b>");
//            out.print("<br>Welcome, " + name);
//            List<Products> list = productDetailsService.loadProductDetails(productId);
//            out.print("<table border='1' width='100%'");
//            out.print("<tr><th>ProductID</th><th>ProductName</th><th>CategoryID</th><th>SupplierID</th><th>QuantityPerUnit</th><th>UnitPrice</th></tr>");
//            for (Products e : list) {
//                out.print("<tr><td>" + e.getProductID() +
//                        "</td><td>" + e.getProductName() +
//                        "</td><td>" + e.getCategoryID() +
//                        "</td><td>" + e.getSupplierID() +
//                        "</td><td>" + e.getQuantityPerUnit() +
//                        "</td><td>" + e.getUnitPrice() +
//                        "</td><td><a href='index.jsp'>HomePage</a></td> ");
//            }
//            out.print("</table>");
//
//        }else {
//            out.print("<center><b>Please login first</b></center>");
//            request.getRequestDispatcher("login.html").include(request, response);
//            out.close();
//        }
//    }


}
