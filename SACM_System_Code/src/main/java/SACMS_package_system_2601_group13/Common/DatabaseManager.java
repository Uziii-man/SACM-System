package SACMS_package_system_2601_group13.Common;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseManager {

    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/sacm_system";
    private static final String username = "uzman";
    private static final String password = "1234";

    // Establish the database connection
    public static Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connection established.");
            } catch (SQLException e) {
                System.err.println("Error establishing database connection: " + e.getMessage());
            }
        }
        return connection;
    }

    // Close the database connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            } finally {
                connection = null; // Reset the connection reference
            }
        }
    }

    // Alert Boxes
    public void alertFunctionBox(String alertTitle, String headerText, String alertText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(headerText);
        alert.setContentText(alertText);
        alert.showAndWait();
    }

    // Alert Boxes to successful sign up
    public void userCreateAlertFunctionBox(String[] userHeader, ArrayList<String> userDetailsArray, String alertTitle, String headerText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(headerText);

        StringBuilder message = new StringBuilder();
        // To show the driver details in each line
        for (int i = 0; i < userHeader.length; i++) {
            message.append(userHeader[i]).append(" : ").append(userDetailsArray.get(i)).append("\n");
        }
        alert.setContentText(message.toString());
        alert.showAndWait();
    }
}
