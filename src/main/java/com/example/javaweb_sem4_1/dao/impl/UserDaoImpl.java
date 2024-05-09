package com.example.javaweb_sem4_1.dao.impl;

import com.example.javaweb_sem4_1.dao.BaseDao;
import com.example.javaweb_sem4_1.dao.UserDao;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.pool.ConnectionPool;
import com.example.javaweb_sem4_1.util.SqlQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";

    private static final UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error inserting user into database", e);
        } finally {
            if (connection != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(connection);
                } catch (InterruptedException e) {
                    throw new DaoException("Failed to release connection", e);
                }
            }
        }
    }

    @Override
    public boolean delete(int userId) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_USER);
            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error deleting user from database", e);
        } finally {
            if (connection != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(connection);
                } catch (InterruptedException e) {
                    throw new DaoException("Failed to release connection", e);
                }
            }
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        Connection connection = null;
        List<User> users = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(COLUMN_ID));
                user.setUsername(resultSet.getString(COLUMN_USERNAME));
                user.setPassword(resultSet.getString(COLUMN_PASSWORD));
                user.setEmail(resultSet.getString(COLUMN_EMAIL));
                users.add(user);
            }
            return users;
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error retrieving user list from database", e);
        } finally {
            if (connection != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(connection);
                } catch (InterruptedException e) {
                    throw new DaoException("Failed to release connection", e);
                }
            }
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error updating user in the database", e);
        } finally {
            if (connection != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(connection);
                } catch (InterruptedException e) {
                    throw new DaoException("Failed to release connection", e);
                }
            }
        }
    }

    public User findBy(String field, String value) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format(SqlQuery.SELECT_USER_BY_FIELD, field));
            if ("id".equals(field)) {
                statement.setInt(1, Integer.parseInt(value));
            } else {
                statement.setString(1, value);
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(COLUMN_ID));
                user.setUsername(resultSet.getString(COLUMN_USERNAME));
                user.setPassword(resultSet.getString(COLUMN_PASSWORD));
                user.setEmail(resultSet.getString(COLUMN_EMAIL));
                return user;
            }
            return null;
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error finding user by " + field, e);
        } finally {
            if (connection != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(connection);
                } catch (InterruptedException e) {
                    throw new DaoException("Failed to release connection", e);
                }
            }
        }
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
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            try {
                statement.setString(1, fieldValue);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            } finally {
                if (statement != null) {
                    statement.close();
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error checking field existence", e);
        } finally {
            if (connection != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(connection);
                } catch (InterruptedException e) {
                    throw new DaoException("Failed to release connection", e);
                }
            }
        }
        return false;
    }
}
