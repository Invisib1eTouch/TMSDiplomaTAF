package services;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBExecutor {
    private static final Connection dbConnection;
    private static Statement stmt;

    static {
        dbConnection = DBConnection.getConnection();
    }

    @SneakyThrows
    public static void execute(String query){
        stmt = dbConnection.createStatement();
        stmt.execute(query);
    }

    @SneakyThrows
    public static ResultSet executeQuery(String query){
        stmt = dbConnection.createStatement();
        return stmt.executeQuery(query);
    }
}
