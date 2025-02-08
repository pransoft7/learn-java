package com.bank.account;

import org.mindrot.jbcrypt.BCrypt;
import java.time.LocalDateTime;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private LocalDateTime createdAt;

    // Constructor - fetching user from DB
    public User(int userId, String username, String passwordHash) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Constructor for inserting new user
    public User(String username, String password) {
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.createdAt = LocalDateTime.now();
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(5));
    }

    public boolean checkPassword(String enteredPassword) {
        return BCrypt.checkpw(enteredPassword, this.passwordHash);
    }

    // Getters
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters (except passwordHash, which is managed through hashing)
    public void setUsername(String username) { this.username = username; }

    // Update password securely
    public void setPassword(String newPassword) {
        this.passwordHash = hashPassword(newPassword);
    }
}
