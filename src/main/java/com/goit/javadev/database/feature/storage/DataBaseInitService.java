package com.goit.javadev.database.feature.storage;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataBaseInitService {

    public void initDbFlyWay(HibernateUtil hibernateUtil) {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")){
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("dbUrlSchema");
            String connectionUser = properties.getProperty("dbUserTest");
            String connectionUserPassword = properties.getProperty("dbUserTest_pass");

            Flyway flyway = Flyway
                    .configure()
                    .dataSource(connectionUrl,
                                connectionUser,
                                connectionUserPassword)
                    .load();

            flyway.migrate();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (InputStream input = DataBaseInitService.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("dbUrlSchema");
            String connectionUser = properties.getProperty("dbUserTest");
            String connectionUserPassword = properties.getProperty("dbUserTest_pass");
            System.out.println(connectionUrl);
            System.out.println(connectionUser);
            System.out.println(connectionUserPassword);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
