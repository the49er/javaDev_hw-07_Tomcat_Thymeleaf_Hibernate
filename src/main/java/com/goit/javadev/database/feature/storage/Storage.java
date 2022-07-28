package com.goit.javadev.database.feature.storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Storage {
    private static final Storage INSTANCE = new Storage();
    private Connection connection;

    private Storage() {
        try (InputStream input = new FileInputStream("C:/Java_GoIt/JavaDev/HomeWorks/06_hw-Tomcat-Thymeleaf/tomcat_thymeleaf/src/main/resources/db.properties")){
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("dbUrl");
            String connectionUser = properties.getProperty("dbUserTest");
            String connectionUserPassword = properties.getProperty("dbUserTest_pass");
            String dbDriver = properties.getProperty("dbDriver");

            Class.forName(dbDriver);
            connection = DriverManager.getConnection(connectionUrl,
                    connectionUser, connectionUserPassword);
            if (connection != null) {
                System.out.println("Successfully connected to MySQL database test");
            }
        } catch (SQLException | ClassNotFoundException | IOException exception) {
            System.out.println("An error occurred while connecting MySQL database");
            exception.printStackTrace();
        }
    }


    public static Storage getInstance(){
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close (){
        try{
            connection.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public int executeUpdate(String sql){
        try (Statement st = connection.createStatement()){
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


}
