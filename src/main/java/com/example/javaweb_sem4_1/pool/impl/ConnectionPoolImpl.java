package com.example.javaweb_sem4_1.pool.impl;

import com.example.javaweb_sem4_1.pool.ConnectionPool;
import com.example.javaweb_sem4_1.exception.DaoException;
import com.example.javaweb_sem4_1.security.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolImpl.class.getName());
    private static final int MAX_POOL_SIZE = 8;
    private static ConnectionPoolImpl instance;
    private BlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
    private BlockingQueue<Connection> usedConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            logger.error("Could not register database driver", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConnectionPoolImpl() {
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            try {
                Connection connection = createConnection();
                freeConnections.add(connection);
            } catch (SQLException e) {
                logger.error("Could not create database connection", e);
                throw new RuntimeException(e);
            }
        }
    }

    private Connection createConnection() throws SQLException {
        String url = DatabaseConfig.DB_URL;
        Properties prop = new Properties();
        prop.put("user", DatabaseConfig.DB_USER);
        prop.put("password", DatabaseConfig.DB_PASSWORD);
        return DriverManager.getConnection(url, prop);
    }

    public static ConnectionPoolImpl getInstance() {
        if (instance == null) {
            synchronized (ConnectionPoolImpl.class) {
                if (instance == null) {
                    instance = new ConnectionPoolImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws InterruptedException {
        Connection connection = freeConnections.take();
        usedConnections.put(connection);
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) throws InterruptedException, SQLException {
        if (connection != null) {
            usedConnections.remove(connection);
            if (!connection.isClosed() && connection.isValid(2)) {
                freeConnections.put(connection);
            } else {
                closeConnection(connection);
            }
        }
    }

    @Override
    public void destroyPool() {
        for (Connection connection : usedConnections) {
            closeConnection(connection);
        }
        for (Connection connection : freeConnections) {
            closeConnection(connection);
        }
        deregisterDrivers();
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Could not close database connection", e);
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Could not deregister driver", e);
            }
        });
    }
}
