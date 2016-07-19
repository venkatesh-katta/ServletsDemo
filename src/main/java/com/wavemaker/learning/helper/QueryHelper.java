package com.wavemaker.learning.helper;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.wavemaker.learning.Utils.ConnectionBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by venkateswarluk on 18/7/16.
 */
public class QueryHelper {

    private Statement statement;

    private Connection connection;

    public Connection getConnection() {
        Connection connection = ConnectionBuilder.getInstance().getConnection();
        return connection;
    }

    public ResultSet query(String query) throws SQLException {
        statement = (Statement) getConnection().createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }

}
