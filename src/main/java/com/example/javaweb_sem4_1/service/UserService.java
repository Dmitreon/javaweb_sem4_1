package com.example.javaweb_sem4_1.service;

import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.ServiceException;

import java.util.List;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
    void createUser(User user) throws ServiceException;
}
