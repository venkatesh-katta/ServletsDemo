package com.wavemaker.learning.Utils;

import com.mysql.jdbc.Connection;
import com.wavemaker.learning.Models.DBProperties;
import com.wavemaker.learning.constants.ProjectConstants;
import com.wavemaker.learning.reader.PropertiesReader;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by venkateswarluk on 14/7/16.
 */
public class ConnectionBuilder {

    InputStream inputStream;

    public static ConnectionBuilder instance;

    private DBProperties dbProperties;


    private ConnectionBuilder(){
        init();
    }

    private void init() {
        String propertiesFileName = ProjectConstants.DBConnectionPropertiesFileName;
        inputStream = getResourceAsStream(propertiesFileName);
        dbProperties = new PropertiesReader(inputStream).read();
        System.out.println(dbProperties.getDriverName());
    }

    private InputStream getResourceAsStream(String propertiesFileName) {
        return getClass().getClassLoader().getResourceAsStream(propertiesFileName);
    }

    public static synchronized ConnectionBuilder getInstance() {
        if (instance == null) {
            instance = new ConnectionBuilder();
        }
        return instance;
    }

    public Connection getConnection()
    {
        try {
            try {
                Class.forName(dbProperties.getDriverName());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Failed to load driver ",e);
            }
            return (Connection) DriverManager.getConnection(dbProperties.getUrl()+dbProperties.getDbName(),dbProperties.getUsername(),dbProperties.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to build connection",e);
        }
    }


}
