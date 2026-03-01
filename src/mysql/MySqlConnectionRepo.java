package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionRepo {

    private static final String USER = "lab3user";
    private static final String PASSWORD = "Lab3Pass!123";
    private static final String URL = "jdbc:mysql://localhost:3306/retail_store";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}