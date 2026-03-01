package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Project: Lab 3 - Java MySQL & MongoDB CRUD
 * Purpose Details: Handles MySQL database connection
 * Course: IST 242
 * Author: David Adeleye
 * Date Developed: 3/1/2026
 * Last Date Changed: 3/1/2026
 * Rev: 1.0
 */
public class MySqlConnectionRepo {

    private static final String URL = "jdbc:mysql://localhost:3306/retail_store";
    private static final String USER = "root";
    private static final String PASSWORD = "IST888IST888";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}