package com.example.javaweb_sem4_1.dao;

import com.example.javaweb_sem4_1.entity.AbstractEntity;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDao<T extends AbstractEntity> {
    private static final Logger logger = LogManager.getLogger(BaseDao.class.getName());
    protected void closeConnection(Connection connection) throws DaoException {
        try {
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            logger.error("Failed to release connection", e);
            throw new DaoException("Failed to release connection", e);
        }
    }
}