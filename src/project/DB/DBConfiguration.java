package project.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfiguration {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pao";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    private static Connection databaseConnection;

    private DBConfiguration() {

    }

    public static Connection getDatabaseConnection() {
        try {
            if (databaseConnection == null || databaseConnection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }

        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return databaseConnection;
    }


}
