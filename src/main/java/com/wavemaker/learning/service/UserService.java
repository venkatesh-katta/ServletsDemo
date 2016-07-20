package com.wavemaker.learning.service;

import com.wavemaker.learning.helper.QueryHelper;
import com.wavemaker.learning.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by venkateswarluk on 19/7/16.
 */
public class UserService {

    private QueryHelper queryHelper = new QueryHelper();

    public User loadUser(String userName){
        try {
            ResultSet resultSet = queryHelper.executeQuery("select * from Login where username='"+userName+"'");
            if (!resultSet.next()) {
                throw new RuntimeException("User does not exist in database");
            }
            User user = new User();
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }catch (SQLException sqle){
            throw new RuntimeException(sqle);
        }


    }
}
