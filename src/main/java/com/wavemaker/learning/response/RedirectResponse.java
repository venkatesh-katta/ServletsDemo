package com.wavemaker.learning.response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by venkateswarluk on 25/7/16.
 */
public class RedirectResponse implements IResponse<String>{
    @Override
    public void viewRender(HttpServletRequest request,HttpServletResponse response, String url) throws IOException,ServletException {
        PrintWriter out = response.getWriter();

        out.print("<html>");
        out.print("<body>");
        out.print("<center>");
        out.print("<h2>GetAllProductsDetails</h2>");
        out.print("<table>");
        out.print("<form action=productslist method=get>");

        out.print("<tr>");
        out.print("<td>Product Details:</td> <td><input type=submit value=GetAllProductsDetails /></td><br>");
        out.print("</tr>");
        out.print("</form>");
        out.print("<form action=logout method=get>");
        out.print("<td>Product Details:</td> <td><input type=submit value=LOGOUT!! /></td><br>");
        out.print("</form>");
        out.print("</table>");
        out.print("</center>");
        out.print("</body>");
        out.print("</html>");

    }
}
