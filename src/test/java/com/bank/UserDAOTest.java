package com.bank;

import com.bank.account.User;
import com.bank.dao.UserDAO;
import com.bank.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;


public class UserDAOTest {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(conn);

            // Register a new user
            User newUser = new User("testuser", "test123");
            boolean isRegistered = userDAO.registerUser(newUser);
            System.out.println("User Registration: " + isRegistered);

            // Authenticate user
            User authenticatedUser = userDAO.authenticateUser("testuser", "test123");
            System.out.println(authenticatedUser != null ? "Authentication Successful" : "Authentication failed");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}