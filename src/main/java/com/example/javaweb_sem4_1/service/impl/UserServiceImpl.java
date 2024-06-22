package com.example.javaweb_sem4_1.service.impl;

import com.example.javaweb_sem4_1.dao.UserDao;
import com.example.javaweb_sem4_1.dao.impl.UserDaoImpl;
import com.example.javaweb_sem4_1.entity.Image;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;
import com.example.javaweb_sem4_1.util.*;
import jakarta.servlet.http.Part;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.InputStream;
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
    public User authenticate(String login, String password) throws ServiceException {
        try {
            if (!FieldValidator.isValidField("username")) {
                throw new ServiceException("Invalid field");
            }
            User user = userDao.findBy("username", login);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            } else {
                throw new ServiceException("Invalid username or password");
            }
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Error while authenticating user", e);
            throw new ServiceException("Error while authenticating user", e);
        }
    }

    @Override
    public List<User> retrieveAllUsers() throws ServiceException {
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
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            throw new ServiceException("Username, password, and email cannot be null.");
        }
        if (!UserValidator.isValidUsername(user.getUsername())) {
            throw new ServiceException("Invalid username.");
        }
        if (!UserValidator.isValidEmail(user.getEmail())) {
            throw new ServiceException("Invalid email.");
        }
        if (!UserValidator.isValidPassword(user.getPassword())) {
            throw new ServiceException("Invalid password.");
        }
        try {
            if (userDao.usernameExists(user.getUsername())) {
                throw new ServiceException("Username already exists.");
            }
            if (userDao.emailExists(user.getEmail())) {
                throw new ServiceException("Email already exists.");
            }
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            user.setRole("user");
            boolean isInserted = userDao.insert(user);
            if (!isInserted) {
                throw new ServiceException("User creation failed: No rows affected.");
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while creating user", e);
        }
    }

    @Override
    public boolean usernameExists(String username) throws ServiceException {
        try {
            return userDao.usernameExists(username);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Error checking if username exists", e);
            throw new ServiceException("Error checking if username exists", e);
        }
    }

    @Override
    public boolean emailExists(String email) throws ServiceException {
        try {
            return userDao.emailExists(email);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Error checking if email exists", e);
            throw new ServiceException("Error checking if email exists", e);
        }
    }


    @Override
    public User findUserById(int id) throws ServiceException {
        try {
            if (!FieldValidator.isValidField("id")) {
                logger.log(Level.SEVERE, "Invalid field");
                throw new ServiceException("Invalid field for user lookup");
            }
            return userDao.findBy("id", Integer.toString(id));
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Error finding user by ID", e);
            throw new ServiceException("Error finding user by ID", e);
        }
    }

    @Override
    public void changePassword(User user, String currentPassword, String newPassword, String confirmPassword) throws ServiceException, DaoException {
        if (newPassword == null || newPassword.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            throw new ServiceException("Password fields cannot be empty.");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new ServiceException("New passwords do not match.");
        }

        if (!UserValidator.isValidPassword(newPassword)) {
            throw new ServiceException("Invalid new password.");
        }

        if (BCrypt.checkpw(newPassword, user.getPassword())) {
            throw new ServiceException("New password cannot be the same as the current password.");
        }

        if (!BCrypt.checkpw(currentPassword, user.getPassword())) {
            throw new ServiceException("Current password is incorrect.");
        }

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        if (!userDao.update(user)) {
            throw new ServiceException("Failed to update password.");
        }
    }

    @Override
    public int verification(String userEmail) throws ServiceException {
        if (!UserValidator.isValidEmail(userEmail)) {
            throw new ServiceException("Invalid email.");
        }
        int verificationCode = CodeGenerator.generate();
        EmailSender emailSender = EmailSender.getInstance();
        try {
            emailSender.sendEmail(userEmail, verificationCode);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while sending verification code", e);
            throw new ServiceException("Error while sending verification code", e);
        }
        return verificationCode;
    }


    @Override
    public boolean deleteUser(int id) throws ServiceException {
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Error deleting user", e);
            throw new ServiceException("Error deleting user", e);
        }
    }

    @Override
    public boolean addPredefinedImage(Part filePart) throws ServiceException {
        String validationError = ImageValidator.validateImage(filePart);
        if (validationError != null) {
            throw new ServiceException(validationError);
        }

        try (InputStream imageStream = filePart.getInputStream()) {
            return userDao.addPredefinedImage(imageStream);
        } catch (DaoException | IOException e) {
            logger.log(Level.SEVERE, "Error while adding predefined image", e);
            throw new ServiceException("Error while adding predefined image", e);
        }
    }

    @Override
    public List<Image> getAvailableImages() throws ServiceException {
        try {
            return userDao.getAvailableImages();
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Error while fetching available images", e);
            throw new ServiceException("Error while fetching available images", e);
        }
    }

    @Override
    public void setUserPredefinedImage(int userId, int imageId) throws ServiceException {
        try {
            if (!userDao.updatePredefinedImageId(userId, imageId)) {
                throw new ServiceException("Failed to update user's predefined image");
            }
        } catch (DaoException e) {
            throw new ServiceException("Error setting predefined image for user", e);
        }
    }

    @Override
    public Image getPredefinedImageById(int imageId) throws ServiceException {
        try {
            return userDao.getPredefinedImageById(imageId);
        } catch (DaoException e) {
            throw new ServiceException("Error retrieving predefined image", e);
        }
    }
}