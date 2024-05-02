package com.example.javaweb_sem4_1.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());
    private static final int MAX_POOL_SIZE = 8;
    private static ConnectionPool instance;
    private BlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
    private BlockingQueue<Connection> usedConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Could not register database driver", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConnectionPool() {
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            try {
                Connection connection = createConnection();
                freeConnections.add(connection);
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Could not create database connection", e);
                throw new RuntimeException(e);
            }
        }
    }

    private Connection createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/java_web";
        Properties prop = new Properties();
        prop.put("user", "postgres");
        prop.put("password", "user123");
        return DriverManager.getConnection(url, prop);
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        Connection connection = freeConnections.take();
        usedConnections.put(connection);
        return connection;
    }

    public void releaseConnection(Connection connection) throws InterruptedException {
        usedConnections.remove(connection);
        freeConnections.put(connection);
    }

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
            logger.log(Level.SEVERE, "Could not close database connection", e);
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Could not deregister driver", e);
            }
        });
    }
}