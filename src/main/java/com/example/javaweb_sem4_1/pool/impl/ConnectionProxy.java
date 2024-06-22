package com.example.javaweb_sem4_1.pool.impl;

import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProxy implements ConnectionPool {
    private final ConnectionPool realConnectionPool;

    public ConnectionProxy() {
        this.realConnectionPool = ConnectionPoolImpl.getInstance();
    }

    @Override
    public Connection getConnection() throws DaoException {
        try {
            return realConnectionPool.getConnection();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DaoException("Thread was interrupted", e);
        }
    }

    @Override
    public void releaseConnection(Connection connection) throws DaoException {
        try {
            realConnectionPool.releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            throw new DaoException("Failed to release connection", e);
        }
    }

    @Override
    public void destroyPool() {
        realConnectionPool.destroyPool();
    }
}
