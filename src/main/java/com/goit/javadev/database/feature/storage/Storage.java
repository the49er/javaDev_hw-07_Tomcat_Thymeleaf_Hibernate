package com.goit.javadev.database.feature.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Storage {
    private static final Storage INSTANCE = new Storage();
    private Connection connection;
    String pathToDbInitFile;

    private Storage() {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")){
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("dbUrl");
            String connectionUser = properties.getProperty("dbUserTest");
            String connectionUserPassword = properties.getProperty("dbUserTest_pass");
            String dbDriver = properties.getProperty("dbDriver");
            this.pathToDbInitFile = properties.getProperty("pathToDbInitFile");

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

    private String[] getSqlArr() {
        String sqlString = "";
        try {
            sqlString = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(pathToDbInitFile))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlString.split("\n");

    }

    public int executeUpdate(String sql) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(sql);
            return 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public int executeUpdates() {
        if (getSqlArr().length > 0) {
            for (String sql : getSqlArr()) {
                executeUpdate(sql);
            }
            return 1;
        }
        return -1;
    }
}



