package com.wavemaker.learning.service;

import com.wavemaker.learning.helper.QueryHelper;
import com.wavemaker.learning.models.Products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkateswarluk on 20/7/16.
 */
public class ProductDetailsService {
    QueryHelper queryHelper = new QueryHelper();
    List<Products> productsList = new ArrayList<Products>();
    public List<Products> loadProductDetails(String productId){
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
