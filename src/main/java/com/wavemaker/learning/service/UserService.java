package com.wavemaker.learning.service;

import com.wavemaker.learning.helper.QueryHelper;
import com.wavemaker.learning.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by venkateswarluk on 19/7/16.
 */
public class UserService {

    QueryHelper queryHelper = new QueryHelper();

    public User loadUser(String userName){
        User user = new User();

        try {
            ResultSet resultSet = queryHelper.executeQuery("select * from Login where username='"+userName+"'");
            while (resultSet.next()) {
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle);
        }
        return user;

    }
}
