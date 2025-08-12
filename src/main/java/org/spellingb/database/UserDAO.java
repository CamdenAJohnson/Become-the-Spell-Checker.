package org.spellingb.database;

import org.spellingb.model.User;
import org.spellingb.util.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.executeUpdate();
        }
    }

    public static User login(String username, String passwordPlainText) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");

                    // Hash the incoming password
                    String incomingHash = PasswordHasher.hash(passwordPlainText);

                    // Compare hashes
                    if (storedHash.equals(incomingHash)) {
                        user = new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                storedHash // we only keep the hash in memory, not the plain password
                        );
                    }
                }
            }
        }

        return user; // if null → login failed; otherwise → login successful
    }

    public static User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password")
                    );
                }
            }
        }

        return user;
    }
}
