package services;

import core.PropertyReader;

import java.sql.*;

public class DBConnection {
    private static Connection connection = null;

    static {
        String url = PropertyReader.getDbUrl();
        String user = PropertyReader.getDbUser();
        String psw = PropertyReader.getDbPassword();

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, psw);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
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
