package com.example.javaweb_sem4_1.dao;

import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.pool.ConnectionPool;
import com.example.javaweb_sem4_1.pool.impl.ConnectionProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class BaseDao<T> {
    private static final Logger logger = LogManager.getLogger(BaseDao.class.getName());
    private final ConnectionPool connectionPool;

    protected BaseDao() {
        this.connectionPool = new ConnectionProxy();
    }

    protected void closeConnection(Connection connection) throws DaoException {
        try {
            connectionPool.releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            logger.error("Failed to release connection", e);
            throw new DaoException("Failed to release connection", e);
        }
    }

    protected <R> R executeWithConnection(Function<Connection, R> function) throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            return function.apply(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DaoException("Thread was interrupted", e);
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
    }
}
