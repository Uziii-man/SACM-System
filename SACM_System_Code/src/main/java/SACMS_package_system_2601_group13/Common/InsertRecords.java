package SACMS_package_system_2601_group13.Common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class InsertRecords {
    // Method to insert data to database
    public InsertRecords(ArrayList<Object> userDetailsArrayList, String insertQuery)
            throws SQLException {

        // Connecting to the database via DatabaseManager Class
        Connection connection = DatabaseManager.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // Assuming the order of details in the ArrayList corresponds to the column order
            for (int i = 0; i < userDetailsArrayList.size(); i++) {
                preparedStatement.setString(i + 1, String.valueOf((Object) userDetailsArrayList.get(i)));
            }
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
}
