package org.spellingb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:h2:~/mydatabase";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connected!");

            createTables();
        }
        return connection;
    }

    private static void createTables() throws SQLException {
        String createUsersTableSQL = """
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(255) NOT NULL,
                password VARCHAR(255) NOT NULL
            );
        """;

        try (PreparedStatement stmt = connection.prepareStatement(createUsersTableSQL)) {
            stmt.execute();
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("The Connection to the database could not be closed");
            }
        }
    }
}
