package com.example.javaweb_sem4_1.dao.impl;

import com.example.javaweb_sem4_1.dao.BaseDao;
import com.example.javaweb_sem4_1.dao.UserDao;
import com.example.javaweb_sem4_1.entity.Image;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.pool.ConnectionPool;
import com.example.javaweb_sem4_1.util.SqlQuery;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PREDEFINED_IMAGE_ID = "predefined_image_id";
    private static final String COLUMN_ROLE = "role"; // Добавлено поле для роли

    private static final UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                if (user.getPredefinedImageId() == null) {
                    statement.setNull(4, Types.INTEGER);
                } else {
                    statement.setInt(4, user.getPredefinedImageId());
                }
                statement.setString(5, user.getRole()); // Установка роли
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public boolean delete(int userId) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_USER)) {
                statement.setInt(1, userId);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<User> findAll() throws DaoException {
        return executeWithConnection(connection -> {
            List<User> users = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_USERS);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt(COLUMN_ID));
                    user.setUsername(resultSet.getString(COLUMN_USERNAME));
                    user.setPassword(resultSet.getString(COLUMN_PASSWORD));
                    user.setEmail(resultSet.getString(COLUMN_EMAIL));
                    int predefinedImageId = resultSet.getInt(COLUMN_PREDEFINED_IMAGE_ID);
                    if (!resultSet.wasNull()) {
                        user.setPredefinedImageId(predefinedImageId);
                    }
                    user.setRole(resultSet.getString(COLUMN_ROLE));
                    users.add(user);
                }
                return users;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public boolean update(User user) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                if (user.getPredefinedImageId() == null) {
                    statement.setNull(4, Types.INTEGER);
                } else {
                    statement.setInt(4, user.getPredefinedImageId());
                }
                statement.setString(5, user.getRole());
                statement.setInt(6, user.getId());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public User findBy(String field, String value) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(String.format(SqlQuery.SELECT_USER_BY_FIELD, field))) {
                if ("id".equals(field)) {
                    statement.setInt(1, Integer.parseInt(value));
                } else {
                    statement.setString(1, value);
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getInt(COLUMN_ID));
                        user.setUsername(resultSet.getString(COLUMN_USERNAME));
                        user.setPassword(resultSet.getString(COLUMN_PASSWORD));
                        user.setEmail(resultSet.getString(COLUMN_EMAIL));
                        int predefinedImageId = resultSet.getInt(COLUMN_PREDEFINED_IMAGE_ID);
                        if (!resultSet.wasNull()) {
                            user.setPredefinedImageId(predefinedImageId);
                        }
                        user.setRole(resultSet.getString(COLUMN_ROLE));
                        return user;
                    }
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public boolean usernameExists(String username) throws DaoException {
        return fieldExists(SqlQuery.CHECK_USERNAME_EXISTS, username);
    }

    @Override
    public boolean emailExists(String email) throws DaoException {
        return fieldExists(SqlQuery.CHECK_EMAIL_EXISTS, email);
    }

    private boolean fieldExists(String query, String fieldValue) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, fieldValue);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
                return false;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public boolean updateProfileImage(int userId, InputStream imageStream) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PROFILE_IMAGE)) {
                if (imageStream != null) {
                    statement.setBinaryStream(1, imageStream);
                } else {
                    statement.setNull(1, java.sql.Types.BINARY);
                }
                statement.setInt(2, userId);
                int result = statement.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public InputStream getProfileImage(int userId) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_PROFILE_IMAGE)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getBinaryStream("profile_image");
                    }
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<Image> getAvailableImages() throws DaoException {
        return executeWithConnection(connection -> {
            List<Image> images = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_AVAILABLE_IMAGES);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    images.add(new Image(resultSet.getInt("id"), resultSet.getBytes("image_data")));
                }
                return images;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public boolean addPredefinedImage(InputStream imageStream) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_PREDEFINED_IMAGE)) {
                if (imageStream != null) {
                    statement.setBinaryStream(1, imageStream);
                } else {
                    statement.setNull(1, java.sql.Types.BINARY);
                }
                int result = statement.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public boolean updatePredefinedImageId(int userId, int imageId) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PREDEFINED_IMAGE_ID)) {
                statement.setInt(1, imageId);
                statement.setInt(2, userId);
                int result = statement.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Image getPredefinedImageById(int imageId) throws DaoException {
        return executeWithConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_PREDEFINED_IMAGE_BY_ID)) {
                statement.setInt(1, imageId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Image(resultSet.getInt("id"), resultSet.getBytes("image_data"));
                    }
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private <R> R executeWithConnection(Function<Connection, R> function) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return function.apply(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DaoException("Thread was interrupted", e);
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
    }
}
