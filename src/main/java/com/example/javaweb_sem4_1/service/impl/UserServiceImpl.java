package com.example.javaweb_sem4_1.service.impl;

import com.example.javaweb_sem4_1.dao.UserDao;
import com.example.javaweb_sem4_1.dao.impl.UserDaoImpl;
import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.exception.ServiceException;
import com.example.javaweb_sem4_1.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
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
            throw new ServiceException("Error while authenticating user", e);
        }
        return match;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        System.out.println("UserServiceImpl: начало получения всех пользователей.");
        try {
            List<User> users = userDao.findAll();
            System.out.println("UserServiceImpl: получено пользователей: " + users.size());
            return users;
        } catch (DaoException e) {
            System.out.println("UserServiceImpl: ошибка при получении списка пользователей из DAO.");
            e.printStackTrace();
            throw new ServiceException("Error while retrieving all users", e);
        }
    }

}