package com.wavemaker.learning.reader;

import com.wavemaker.learning.models.DBProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by venkateswarluk on 18/7/16.
 */
public class PropertiesReader {

    private Properties properties;

    private InputStream inputStream;

    public PropertiesReader(Properties properties) {
        this.properties = properties;
    }

    public PropertiesReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public DBProperties read()  {
        DBProperties dbProperties = new DBProperties();

        if(inputStream != null) {
            try {
                properties=new Properties();
                properties.load(inputStream);
                System.out.println(properties);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load properties from stream");
            }
        }

        String url = properties.getProperty("url");
        String dbName = properties.getProperty("dbName");
        String driver = properties.getProperty("driver");
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");
        dbProperties.setUrl(url);
        dbProperties.setDbName(dbName);
        dbProperties.setDriverName(driver);
        dbProperties.setUsername(userName);
        dbProperties.setPassword(password);


        return dbProperties;

    }

}
