package com.example.javaweb_sem4_1.util.constant;

public final class SqlQuery {
    public static final String INSERT_USER = "INSERT INTO users (username, password, email, predefined_image_id, role) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_USERS = "SELECT id, username, password, email, predefined_image_id, role FROM users ORDER BY id";
    public static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, email = ?, predefined_image_id = ?, role = ? WHERE id = ?";
    public static final String SELECT_USER_BY_FIELD = "SELECT id, username, password, email, predefined_image_id, role FROM users WHERE %s = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    public static final String CHECK_USERNAME_EXISTS = "SELECT COUNT(*) FROM users WHERE username = ?";
    public static final String CHECK_EMAIL_EXISTS = "SELECT COUNT(*) FROM users WHERE email = ?";
    public static final String INSERT_PREDEFINED_IMAGE = "INSERT INTO predefined_images (image_data) VALUES (?)";
    public static final String UPDATE_PROFILE_IMAGE = "UPDATE users SET profile_image = ? WHERE id = ?";
    public static final String SELECT_PROFILE_IMAGE = "SELECT profile_image FROM users WHERE id = ?";
    public static final String SELECT_AVAILABLE_IMAGES = "SELECT id, image_data FROM predefined_images";
    public static final String UPDATE_PREDEFINED_IMAGE_ID = "UPDATE users SET predefined_image_id = ? WHERE id = ?";
    public static final String SELECT_PREDEFINED_IMAGE_BY_ID = "SELECT id, image_data FROM predefined_images WHERE id = ?";

    private SqlQuery() {
        throw new AssertionError("Instantiating utility class.");
    }
}
