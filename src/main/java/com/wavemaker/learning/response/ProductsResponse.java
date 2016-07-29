package com.wavemaker.learning.response;

import com.wavemaker.learning.models.Products;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by venkateswarluk on 21/7/16.
 */
public class ProductsResponse implements IResponse<List<Products>> {

    @Override
    public void viewRender(HttpServletRequest request,HttpServletResponse response, List<Products> products) throws IOException,ServletException {
        PrintWriter out = response.getWriter();
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>ProductID</th><th>ProductName</th></tr>");
        for (Products e : products) {
            out.print("<tr><td>" + e.getProductID() +
                    "</td><td>" + e.getProductName() +
                    "</td><td><a href='productDetails?id=" + e.getProductID() + "'>ProductDetailInformation</a></td> ");
        }
        out.print("</table>");

    }
}
