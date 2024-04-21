package com.example.javaweb_sem4_1.dao.impl;

import com.example.javaweb_sem4_1.dao.BaseDao;
import com.example.javaweb_sem4_1.dao.UserDao;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.pool.ConnectionPool;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.javaweb_sem4_1.util.SQLConstants.*;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
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
        } catch (SQLException e) {
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
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving user list from database", e);
        }
        return users;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String passwordFromDb = resultSet.getString("password");
                    return BCrypt.checkpw(password, passwordFromDb);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error authenticating user", e);
        }
        return false;
    }
}
