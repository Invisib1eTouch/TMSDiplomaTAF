package services;

import core.PropertyReader;
import org.testng.log4testng.Logger;

import java.sql.*;

public class JdbcService {
    private static final Logger logger = Logger.getLogger(JdbcService.class);

    private static final String DB_URL = PropertyReader.getDbUrl();
    private static final String DB_USERNAME = PropertyReader.getDbUser();
    private static final String DB_PSW = PropertyReader.getDbPassword();

    private Connection connection = null;
    private Statement statement = null;

    public JdbcService() {
        logger.info("Setup connection to Postgres DB...");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Postgres JDBC Driver is not found.");
            logger.error(e.getMessage());
            return;
        }

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PSW);
        } catch (SQLException e) {
            logger.error("Connection is failed.");
            logger.error(e.getMessage());
        }

        if (connection != null) {
            logger.info("You're successfully connected to the DB.");
        } else {
            logger.error("Failed to connect to DB.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        try {
            if (statement == null) {
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            logger.error("Unable to create a statement.");
            logger.error(e.getMessage());
        }
        return statement;
    }

    public void executeSQL(String sql) {
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(sql);
            logger.info("SQL: " + sql + " has been executed.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultSet;
    }

    public void closeStatement() {
        try {
            statement.close();
            logger.info("Statement has been closed.");
        } catch (SQLException e) {
            logger.error("Could not close the statement.");
            logger.error(e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            logger.info("Connection has been closed.");
        } catch (SQLException e) {
            logger.error("Could not close the connection.");
            logger.error(e.getMessage());
        }
    }
}
