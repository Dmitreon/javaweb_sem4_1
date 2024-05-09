package com.example.javaweb_sem4_1.dao;

import com.example.javaweb_sem4_1.entity.AbstractEntity;
import com.example.javaweb_sem4_1.exception.DaoException;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;
    public abstract boolean update(T t) throws DaoException;
}
