package main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfiguration {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/FeeCollectionSystem";
    private static final String DB_USER = "ns";
    private static final String DB_PASSWORD = "";

    public static Connection getDBConnection(){

        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;

    }

}
