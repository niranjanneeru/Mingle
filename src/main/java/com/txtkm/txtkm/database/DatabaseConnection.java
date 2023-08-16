package com.txtkm.txtkm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private String dbName = "postgres";
    private String protocol = "jdbc:postgresql://";
    private String connectionURL = protocol + "db.fxnnpzeqzdewihdkxvcn.supabase.co/" + dbName;

    private String driver = "org.postgresql.Driver";

    private String username = "postgres";
    private String secret = "TxTKMAdmin@2002";

    private DatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(connectionURL, username, secret);
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            new DatabaseConnection();
        }
        return connection;
    }

    
}
