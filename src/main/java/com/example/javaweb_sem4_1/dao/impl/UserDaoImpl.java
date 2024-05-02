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

import static com.example.javaweb_sem4_1.util.SqlQuery.*;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        boolean inserted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            int rowsAffected = statement.executeUpdate();
            inserted = rowsAffected > 0;
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error inserting user into database", e);
        }
        return inserted;
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException("delete unsupported");
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(COLUMN_ID));
                user.setUsername(resultSet.getString(COLUMN_USERNAME));
                user.setPassword(resultSet.getString(COLUMN_PASSWORD));
                user.setEmail(resultSet.getString(COLUMN_EMAIL));
                users.add(user);
            }
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error retrieving user list from database", e);
        }
        return users;
    }

    @Override
    public boolean update(User user) throws DaoException {
        String query = UPDATE_USER;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error updating user in the database", e);
        }
    }

    public User findBy(String field, String value) throws DaoException {
        User user = null;
        String query = String.format(SqlQuery.SELECT_USER_BY_FIELD, field);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            if (field.equals("id")) {
                int intValue = Integer.parseInt(value);
                statement.setInt(1, intValue);
            } else {
                statement.setString(1, value);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(COLUMN_ID));
                    user.setUsername(resultSet.getString(COLUMN_USERNAME));
                    user.setPassword(resultSet.getString(COLUMN_PASSWORD));
                    user.setEmail(resultSet.getString(COLUMN_EMAIL));
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new DaoException("Error finding user by " + field, e);
        }
        return user;
    }

}
