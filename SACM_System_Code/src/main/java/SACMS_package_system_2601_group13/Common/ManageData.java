package SACMS_package_system_2601_group13.Common;

import java.sql.*;
import java.util.ArrayList;

public class ManageData {
    // Method to insert data to database
    public void insertData(ArrayList<Object> userDetailsArrayList, String insertQueryStatement) throws SQLException {

        // Connecting to the database via DatabaseManager Class
        Connection connection = DatabaseManager.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement)) {
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
        } finally {
            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();
        }
    }

    // Method to get data in a 2D ArrayList from the database
    public ArrayList<ArrayList<Object>> get2DArrayData(String getQueryStatement) {
        // 2D ArrayList to store the data
        ArrayList<ArrayList<Object>> table2DRecords = new ArrayList<>();

        try (Connection connection = DatabaseManager.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getQueryStatement)) {

            // Get column count
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Process the result set
            while (resultSet.next()) {
                ArrayList<Object> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                table2DRecords.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();
        }

        return table2DRecords;
    }

    // Method to get details in a 1D ArrayList from the database
    public ArrayList<Object> get1DArrayData(String getQueryStatement) {
        // 1D ArrayList to store the data
        ArrayList<Object> table1DRecords = new ArrayList<>();
        // Connecting to the database via DatabaseManager Class
        try (Connection connection = DatabaseManager.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getQueryStatement)) {

            // Get column count
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Process the result set
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    table1DRecords.add(resultSet.getObject(i));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();
        }
        return table1DRecords;
    }


    public void updateData(ArrayList<Object> userDetailsArrayList, String updateQueryStatement) throws SQLException {

        // Connecting to the database via DatabaseManager Class
        Connection connection = DatabaseManager.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQueryStatement)) {
            // Assuming the order of details in the ArrayList corresponds to the column order
            for (int i = 0; i < userDetailsArrayList.size(); i++) {
                preparedStatement.setString(i + 1, String.valueOf((Object) userDetailsArrayList.get(i)));
            }
            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Staff information updated successfully.");
            } else {
                System.out.println("Failed to update staff information.");
            }
            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            System.err.println("Error updating staff information: " + e.getMessage());
        }
    }

    public void deleteData(ArrayList<Object> userDetailsArrayList, String deleteQueryStatement) throws SQLException {

        // Connecting to the database via DatabaseManager Class
        Connection connection = DatabaseManager.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQueryStatement)) {
            // Assuming the order of details in the ArrayList corresponds to the column order
            for (int i = 0; i < userDetailsArrayList.size(); i++) {
                preparedStatement.setString(i + 1, String.valueOf((Object) userDetailsArrayList.get(i)));
            }
            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Staff information deleted successfully.");
            } else {
                System.out.println("Failed to delete staff information.");
            }
            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            System.err.println("Error deleting staff information: " + e.getMessage());
        }
    }

}
