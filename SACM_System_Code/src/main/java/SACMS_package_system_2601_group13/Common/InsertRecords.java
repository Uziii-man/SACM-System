package SACMS_package_system_2601_group13.Common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class InsertRecords {
    // Method to add staff details
    public InsertRecords(ArrayList<String> userDetailsArrayList, String insertQuery)
            throws SQLException {

        // Connecting to the database via DatabaseManager Class
        Connection connection = DatabaseManager.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Assuming the order of details in the ArrayList corresponds to the column order
            for (int i = 0; i < userDetailsArrayList.size(); i++) {
                preparedStatement.setString(i + 1, userDetailsArrayList.get(i));
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

//    public InsertRecords(String StudentID, String FirstName, String LastName, String Grade, String Email, String Password)
//            throws SQLException {
//
//        // Connecting to the database via DatabaseManager Class
//        Connection connection = DatabaseManager.connect();
//
//        String insertQuery = "INSERT INTO student (StudentID, FirstName, LastName, Grade, Email, Password) " +
//                "VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
//            preparedStatement.setString(1, StudentID);
//            preparedStatement.setString(2, FirstName);
//            preparedStatement.setString(3, LastName);
//            preparedStatement.setString(4, Grade);
//            preparedStatement.setString(5, Email);
//            preparedStatement.setString(6, Password);
//
//            // Execute the insert statement
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Staff information added successfully.");
//            } else {
//                System.out.println("Failed to add staff information.");
//            }
//            // Closing the database connection via DatabaseManager Class
//            DatabaseManager.closeConnection();
//
//        } catch (SQLException e) {
//            System.err.println("Error adding student information: " + e.getMessage());
//        }
//    }

    // Method to insert Club Data
    public InsertRecords(String ClubName, String ClubAbbreviation, String ClubDescription) {
        // Connecting to the database via DatabaseManager Class
        Connection connection = DatabaseManager.connect();

        String INSERT_QUERY = "INSERT INTO club (ClubName, ClubAbbreviation, ClubOriginDate, ClubDescription) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            // Set parameters for the PreparedStatement
            preparedStatement.setString(1, ClubName);
            preparedStatement.setString(2, ClubAbbreviation);

            // Get the current date in the local desktop's time zone
            LocalDate currentDate = LocalDate.now();
            // Convert to java.sql.Date for insertion into the database
            Date ClubOriginDate = Date.valueOf(currentDate);
            preparedStatement.setDate(3, ClubOriginDate);

            preparedStatement.setString(4, ClubDescription);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert data to club and advisor relation table
    public InsertRecords(int ClubID, String StaffID){
        // Connecting to the database via DatabaseManager Class
        Connection connection = DatabaseManager.connect();

        String INSERT_QUERY = "INSERT INTO club_and_club_advisor (ClubID, StaffID, JoinDate) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            // Set parameters for the PreparedStatement
            preparedStatement.setInt(1, ClubID);
            preparedStatement.setString(2, StaffID);
            // Get the current date in the local desktop's time zone
            LocalDate currentDate = LocalDate.now();
            // Convert to java.sql.Date for insertion into the database
            Date JoinDate = Date.valueOf(currentDate);
            preparedStatement.setDate(3, JoinDate);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
