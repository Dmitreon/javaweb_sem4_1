package com.example.javaweb_sem4_1.util;

public final class SqlQuery {
    public static final String INSERT_USER = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
    public static final String SELECT_ALL_USERS = "SELECT id, username, password, email FROM users ORDER BY id";
    public static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";
    public static final String SELECT_USER_BY_FIELD = "SELECT id, username, password, email FROM users WHERE %s = ?";

    private SqlQuery() {
        throw new AssertionError("Instantiating utility class.");
    }
}
