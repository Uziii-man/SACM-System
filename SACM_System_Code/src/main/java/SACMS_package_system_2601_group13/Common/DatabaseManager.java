package SACMS_package_system_2601_group13.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/sacm_system";
    private static final String username = "uzman";
    private static final String password = "1234";

    // Establish the database connection
    public static Connection connect() {
        try {
            // If connection is null or closed, reopen it
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            System.err.println("Error establishing/reopening database connection: " + e.getMessage());
        }
        return connection;
    }


    // Close the database connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            } finally {
                connection = null; // Reset the connection reference
            }
        }
    }
}
