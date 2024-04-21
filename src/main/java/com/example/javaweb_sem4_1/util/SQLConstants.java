package com.example.javaweb_sem4_1.util;

public final class SQLConstants {
    public static final String INSERT_USER = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
    public static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE username = ?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private SQLConstants() {
        throw new AssertionError("Instantiating utility class.");
    }
}
