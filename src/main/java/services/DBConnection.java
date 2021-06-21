package services;

import core.PropertyReader;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DBConnection {
    private static Connection connection = null;

    static {
        String url = PropertyReader.getDbUrl();
        String user = PropertyReader.getDbUser();
        String psw = PropertyReader.getDbPassword();

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, psw);
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
