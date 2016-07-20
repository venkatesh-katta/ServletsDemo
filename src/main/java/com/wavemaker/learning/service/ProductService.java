package com.wavemaker.learning.service;

import com.wavemaker.learning.helper.QueryHelper;
import com.wavemaker.learning.models.Products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkateswarluk on 19/7/16.
 */
public class ProductService {

    QueryHelper queryHelper = new QueryHelper();
    List<Products> list = new ArrayList<Products>();
    public List<Products> loadProducts(){
        try {
            ResultSet resultSet = queryHelper.executeQuery("select * from Products");
            while (resultSet.next()) {
                Products products = new Products();
                products.setProductID(resultSet.getInt("ProductID"));
                products.setProductName(resultSet.getString("ProductName"));
                list.add(products);
            }
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
        return list;
    }
}
