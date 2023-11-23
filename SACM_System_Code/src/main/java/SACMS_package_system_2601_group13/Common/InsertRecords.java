package SACMS_package_system_2601_group13.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertRecords {
    // Method to add staff details
    public InsertRecords(String StaffID, String FirstName, String LastName, String Email, String Password)
            throws SQLException {

        // Connecting to the database via DatabaseManager Class
        Connection connection = DatabaseManager.connect();

        String insertQuery = "INSERT INTO club_advisor (StaffID, FirstName, LastName, Email, Password) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, StaffID);
            preparedStatement.setString(2, FirstName);
            preparedStatement.setString(3, LastName);
            preparedStatement.setString(4, Email);
            preparedStatement.setString(5, Password);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Staff information added successfully.");
            } else {
                System.out.println("Failed to add staff information.");
            }
            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();

        } catch (SQLException e) {
            System.err.println("Error adding staff information: " + e.getMessage());
        }
    }

    public InsertRecords(String StudentID, String FirstName, String LastName, String Grade, String Email, String Password)
            throws SQLException {

        // Connecting to the database via DatabaseManager Class
        Connection connection = DatabaseManager.connect();

        String insertQuery = "INSERT INTO student (StudentID, FirstName, LastName, Grade, Email, Password) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, StudentID);
            preparedStatement.setString(2, FirstName);
            preparedStatement.setString(3, LastName);
            preparedStatement.setString(4, Grade);
            preparedStatement.setString(5, Email);
            preparedStatement.setString(6, Password);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Staff information added successfully.");
            } else {
                System.out.println("Failed to add staff information.");
            }
            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();

        } catch (SQLException e) {
            System.err.println("Error adding student information: " + e.getMessage());
        }
    }
}
