package com.wavemaker.learning.helper;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.wavemaker.learning.Utils.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by venkateswarluk on 18/7/16.
 */
public class QueryHelper {

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = (Statement) getConnection().createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    private Connection getConnection() {
        Connection connection = ConnectionUtil.getInstance().getConnection();
        return connection;
    }

}
