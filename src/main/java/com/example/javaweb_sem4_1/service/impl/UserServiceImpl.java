package com.example.javaweb_sem4_1.service.impl;

import com.example.javaweb_sem4_1.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return login.equals(password);//todo
    }
}
