package com.example.javaweb_sem4_1.service.impl;

import com.example.javaweb_sem4_1.dao.UserDao;
import com.example.javaweb_sem4_1.dao.impl.UserDaoImpl;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        boolean match;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Error while authenticating user", e);
            throw new ServiceException("Error while authenticating user", e);
        }
        return match;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        logger.log(Level.INFO, "Beginning to retrieve all users.");
        try {
            List<User> users = userDao.findAll();
            logger.log(Level.INFO, "Number of users retrieved: {0}", users.size());
            return users;
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Error retrieving user list from DAO.", e);
            throw new ServiceException("Error while retrieving all users", e);
        }
    }

    @Override
    public void createUser(User user) throws ServiceException {
        if (user.getUsername() == null || user.getPassword() == null) {
            logger.log(Level.WARNING, "Username or password cannot be null.");
            throw new ServiceException("Username or password cannot be null.");
        }
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        try {
            boolean isInserted = userDao.insert(user);
            if (!isInserted) {
                logger.log(Level.SEVERE, "User creation failed: No rows affected.");
                throw new ServiceException("User creation failed: No rows affected.");
            }
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Error while creating user", e);
            throw new ServiceException("Error while creating user", e);
        }
    }
}
