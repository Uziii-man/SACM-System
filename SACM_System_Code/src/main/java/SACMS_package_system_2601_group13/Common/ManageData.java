package SACMS_package_system_2601_group13.Common;

import java.sql.*;
import java.util.ArrayList;

public class ManageData {
    DatabaseManager databaseManager = new DatabaseManager();
    // Method to insert data to database
    public void insertData(ArrayList<Object> details1DArrayList, String queryStatement){

        // Connecting to the database via DatabaseManager Class
        try (Connection connection = DatabaseManager.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement)) {
            // Assuming the order of details in the ArrayList corresponds to the column order
            for (int i = 0; i < details1DArrayList.size(); i++) {
                preparedStatement.setString(i + 1, String.valueOf((Object) details1DArrayList.get(i)));
            }
            // Execute the insert statement
            preparedStatement.executeUpdate();

            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            // Displaying the error message in a error alert box
            databaseManager.alertFunctionBox("Data Insertion", "Error in Adding Data",
                    "Please check the database connectivity and try again.");

            System.err.println("Error adding staff information: " + e.getMessage());
        } finally {
            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();
        }
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

            // Process the result set into 2D ArrayList
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


    // To delete and update data from the database from a given query statement
    public void modifyData(String queryStatement){
        // Connecting to the database via DatabaseManager Class
        try (Connection connection = DatabaseManager.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(queryStatement)) {
            // Execute the insert statement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating staff information: " + e.getMessage());
        } finally {
            // Closing the database connection via DatabaseManager Class
            DatabaseManager.closeConnection();
        }
    }

//    public void modifyData(ArrayList<Object> userDetailsArrayList, String deleteQueryStatement) throws SQLException {
//
//        // Connecting to the database via DatabaseManager Class
//        Connection connection = DatabaseManager.connect();
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQueryStatement)) {
//            // Assuming the order of details in the ArrayList corresponds to the column order
//            for (int i = 0; i < userDetailsArrayList.size(); i++) {
//                preparedStatement.setString(i + 1, String.valueOf((Object) userDetailsArrayList.get(i)));
//            }
//            // Execute the insert statement
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Staff information deleted successfully.");
//            } else {
//                System.out.println("Failed to delete staff information.");
//            }
//            // Closing the database connection via DatabaseManager Class
//            DatabaseManager.closeConnection();
//        } catch (SQLException e) {
//            System.err.println("Error deleting staff information: " + e.getMessage());
//        }
//    }

}
