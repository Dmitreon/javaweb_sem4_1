package com.example.javaweb_sem4_1.pool;

import com.example.javaweb_sem4_1.exception.DaoException;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    Connection getConnection() throws DaoException, InterruptedException;
    void releaseConnection(Connection connection) throws DaoException, InterruptedException, SQLException;
    void destroyPool();
}
