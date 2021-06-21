package services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class DBExecutor {
    private static final Connection dbConnection;
    private static Statement stmt;

    static {
        dbConnection = DBConnection.getConnection();
    }


    @SneakyThrows
    public static void execute(String query) {
        createStatement();
        try {
            stmt.execute(query);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new SQLException(e.getMessage());
        }
    }

    @SneakyThrows
    public static ResultSet executeQuery(String query) {
        createStatement();
        try {
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new SQLException(e.getMessage());
        }
    }

    @SneakyThrows
    private static void createStatement() {
        try {
            stmt = dbConnection.createStatement();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new SQLException(e.getMessage());
        }
    }
}