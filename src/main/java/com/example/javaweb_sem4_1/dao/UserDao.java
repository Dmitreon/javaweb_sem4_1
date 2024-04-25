package com.example.javaweb_sem4_1.dao;

import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.DaoException;

import java.util.List;

public interface UserDao {
    boolean authenticate(String login,String password) throws DaoException;
    List<User> findAll() throws DaoException;
    boolean insert(User user) throws DaoException;
}
