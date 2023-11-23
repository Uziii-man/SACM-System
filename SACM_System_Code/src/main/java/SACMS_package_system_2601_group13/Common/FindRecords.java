package SACMS_package_system_2601_group13.Common;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindRecords {
    // checking if the primary key exist in the table
    // Method to check if a primary key exists in the database
    public boolean isPrimaryKeyValid(String primaryKey, String tableName, String columnName, Label labelID,
                                     TextField textFieldID) {
        // Connecting to the database via DatabaseManager Class
        try (Connection connection = DatabaseManager.connect()) {
            System.out.println("in primary method");

            // Query to find the primary key
            String selectQuery = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, primaryKey);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // If there is at least one result, the primary key exists
                    if (resultSet.next()) {
                        // Primary key is not valid
                        labelID.setTextFill(Color.RED);
                        labelID.setText("ID already exists");
                        textFieldID.clear();
                        return false;
                    } else {
                        // Primary key is valid
                        labelID.setTextFill(Color.GREEN);
                        labelID.setText("ID is valid");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Error occurred, treat as if primary key does not exist
            return false;
        } finally {
            // Close the connection
            DatabaseManager.closeConnection();
        }
    }

    // To validate login
    public boolean validateLogin(
            String enteredUsername, String enteredPassword, String tableName,
            String IDName, int passwordColumnNo, Label IDErrorLabel, Label passwordErrorLabel) {

        boolean isValidLogin = false;
        Connection connection = DatabaseManager.connect();

        try {
            if (connection != null && !connection.isClosed()) {
                String selectQuery = "SELECT * FROM " + tableName + " WHERE " + IDName + " = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, enteredUsername);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            // Username exists, check the password
                            String storedPassword = resultSet.getString(passwordColumnNo);

                            if (enteredPassword.equals(storedPassword)) {
                                // Password is correct
                                clearErrorLabels(IDErrorLabel, passwordErrorLabel);
                                isValidLogin = true;
                            } else {
                                // Incorrect password
                                showErrorLabels("Incorrect Password", IDErrorLabel, passwordErrorLabel);
                            }
                        } else {
                            // Username does not exist
                            showErrorLabels("ID does not Exist", IDErrorLabel);
                            clearErrorLabels(passwordErrorLabel);
                        }
                    }
                }
            } else {
                // Handle the case where the connection is null or closed
                showErrorLabels("Database Connection Error", IDErrorLabel, passwordErrorLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            showErrorLabels("Database Error", IDErrorLabel, passwordErrorLabel);
        } finally {
            // Close the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();
        }
        return isValidLogin;
    }

    // modularity is used to have a clean and flexible code
    private static void showErrorLabels(String errorMessage, Label... labels) {
        for (Label label : labels) {
            label.setTextFill(Color.RED);
            label.setText(errorMessage);
        }
    }

    private static void clearErrorLabels(Label... labels) {
        for (Label label : labels) {
            label.setText("");
        }
    }

    // To find Club Advisor and Student Records
    SignIn signIn = new SignIn();
    public String retrieveUserDetails(String tableName, String userIDName,int passwordColumnNo) {
        String userDetails = null;
        String userID = signIn.getUserID();
        String password = signIn.getUserPassword();

        String SELECT_DETAILS_QUERY = "SELECT details FROM " + tableName + " WHERE " + userIDName +
                " = ? AND password = ?";

        try (Connection connection = DatabaseManager.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DETAILS_QUERY)) {

            // Set parameters for the query
            preparedStatement.setString(1, userID);
            preparedStatement.setString(passwordColumnNo, password);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve the details column value
                    userDetails = resultSet.getString("details");
                    System.out.println(userDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDetails;
    }

}