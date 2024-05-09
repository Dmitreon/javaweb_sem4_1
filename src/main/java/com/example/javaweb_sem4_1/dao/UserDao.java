package com.example.javaweb_sem4_1.dao;

import com.example.javaweb_sem4_1.entity.User;
import com.example.javaweb_sem4_1.exception.DaoException;

import java.util.List;

public interface UserDao {
    List<User> findAll() throws DaoException;
    boolean insert(User user) throws DaoException;
    boolean update(User user) throws DaoException;
    User findBy(String field, String value) throws DaoException;
    boolean delete(int userId) throws DaoException;
    boolean usernameExists(String username) throws DaoException;
    boolean emailExists(String email) throws DaoException;
}
