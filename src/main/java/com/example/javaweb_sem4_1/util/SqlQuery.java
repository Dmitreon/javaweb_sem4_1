package com.example.javaweb_sem4_1.util;

public final class SqlQuery {
    public static final String INSERT_USER = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
    public static final String SELECT_ALL_USERS = "SELECT id, username, password, email FROM users ORDER BY id";
    public static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";
    public static final String SELECT_USER_BY_FIELD = "SELECT id, username, password, email FROM users WHERE %s = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    public static final String CHECK_USERNAME_EXISTS = "SELECT COUNT(*) FROM users WHERE username = ?";
    public static final String CHECK_EMAIL_EXISTS = "SELECT COUNT(*) FROM users WHERE email = ?";
    public static final String INSERT_PREDEFINED_IMAGE = "INSERT INTO predefined_images (image_data) VALUES (?)";

    private SqlQuery() {
        throw new AssertionError("Instantiating utility class.");
    }
}
