package com.example.javaweb_sem4_1.service;

import com.example.javaweb_sem4_1.entity.Image;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import jakarta.servlet.http.Part;

import java.util.List;

public interface UserService {
    User authenticate(String login, String password) throws ServiceException;
    List<User> retrieveAllUsers() throws ServiceException;
    void createUser(User user) throws ServiceException;
    boolean usernameExists(String username) throws ServiceException;
    boolean emailExists(String email) throws ServiceException;
    User findUserById(int id) throws ServiceException;
    void changePassword(User user, String currentPassword, String newPassword, String confirmPassword) throws ServiceException, DaoException;
    boolean deleteUser(int id) throws ServiceException;
    boolean addPredefinedImage(Part filePart) throws ServiceException;
    List<Image> getAvailableImages() throws ServiceException;
    void setUserPredefinedImage(int userId, int imageId) throws ServiceException;
    Image getPredefinedImageById(int imageId) throws ServiceException;
    int verification(String userEmail) throws ServiceException;
}
