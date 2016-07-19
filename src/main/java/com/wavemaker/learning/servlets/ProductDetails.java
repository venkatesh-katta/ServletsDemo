package com.wavemaker.learning.servlets;

import com.wavemaker.learning.models.Products;
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
public class ProductDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryHelper queryHelper = new QueryHelper();
        ResultSet resultSet = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String productId = request.getParameter("id");
        List<Products> list = new ArrayList<Products>();
        Cookie[] cookie = request.getCookies();
        String name = null;


        for (int i = 0; i < cookie.length; i++) {
            if (cookie[i].getName().equals("auth-cookie")) {
                name = EncodeandDecode.base64Decode(cookie[i].getValue());
            }
        }
        if (name != null &&!name.isEmpty()) {
            out.print("<b>Welcome to Profile</b>");
            out.print("<br>Welcome, " + name);
            try {
                resultSet = queryHelper.executeQuery("select * from Products where ProductID =" + productId);
                while (resultSet.next()) {
                    Products products = new Products();
                    products.setProductID(resultSet.getInt("ProductID"));
                    products.setProductName(resultSet.getString("ProductName"));
                    products.setCategoryID(resultSet.getInt("CategoryID"));
                    products.setSupplierID(resultSet.getInt("SupplierID"));
                    products.setQuantityPerUnit(resultSet.getString("QuantityPerUnit"));
                    products.setUnitPrice(resultSet.getFloat("UnitPrice"));

                    list.add(products);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.print("<table border='1' width='100%'");
            out.print("<tr><th>ProductID</th><th>ProductName</th><th>CategoryID</th><th>SupplierID</th><th>QuantityPerUnit</th><th>UnitPrice</th></tr>");
            for (Products e : list) {
                out.print("<tr><td>" + e.getProductID() +
                        "</td><td>" + e.getProductName() +
                        "</td><td>" + e.getCategoryID() +
                        "</td><td>" + e.getSupplierID() +
                        "</td><td>" + e.getQuantityPerUnit() +
                        "</td><td>" + e.getUnitPrice() +
                        "</td><td><a href='index.jsp'>HomePage</a></td> ");
            }
            out.print("</table>");

        }else {
            out.print("<b>Please login first</b>");
            request.getRequestDispatcher("login.html").include(request, response);
            out.close();
        }
    }


}
