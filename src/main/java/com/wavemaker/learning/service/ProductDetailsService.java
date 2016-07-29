package com.wavemaker.learning.service;

import com.wavemaker.learning.helper.QueryHelper;
import com.wavemaker.learning.models.Products;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkateswarluk on 20/7/16.
 */
public class ProductDetailsService implements IService<List<Products>> {
    private QueryHelper queryHelper = new QueryHelper();
    @Override
    public List<Products> doAction(List<String> params, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return loadProductDetails(params.get(0));
    }

    private List<Products> loadProductDetails(String productId){
        List<Products> productsList = new ArrayList<>();
        try {
            ResultSet resultSet = queryHelper.executeQuery("select * from Products where ProductID =" + productId);
            while (resultSet.next()) {
                Products products = new Products();
                products.setProductID(resultSet.getInt("ProductID"));
                products.setProductName(resultSet.getString("ProductName"));
                products.setCategoryID(resultSet.getInt("CategoryID"));
                products.setSupplierID(resultSet.getInt("SupplierID"));
                products.setQuantityPerUnit(resultSet.getString("QuantityPerUnit"));
                products.setUnitPrice(resultSet.getFloat("UnitPrice"));
                productsList.add(products);
            }
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
        return productsList;
    }
}
