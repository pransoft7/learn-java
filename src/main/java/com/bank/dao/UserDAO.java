package com.bank.dao;

import java.sql.*;
import com.bank.account.User;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert new user
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password_hash, created_at) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setTimestamp( 3, Timestamp.valueOf(user.getCreatedAt()) );
            
            int affectedRows = stmt.executeUpdate();
            // Check CODE STYLE here !!!
            if (affectedRows > 0) {
                System.out.println("User registered successfully!");
            }
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Authenticate existing user
    public User authenticateUser(String username, String enteredPassword) {
        String sql = "SELECT user_id, password_hash FROM users WHERE username = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String storedHash = rs.getString("password_hash");

                User user = new User(userId, username, storedHash);

                if (user.checkPassword(enteredPassword)) {
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
