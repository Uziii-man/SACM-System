package SACMS_package_system_2601_group13.TableView;

import SACMS_package_system_2601_group13.Common.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableViewController {
    // Method to fetch data from the database
//    public void fetchDataFromDatabase(ArrayList<String> data) {
//        String query = "SELECT ClubID, ClubName, ClubDescription FROM club";
//
//        try (Connection connection = DatabaseManager.connect();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println("in fetchDataFromDatabase");
//
//            while (resultSet.next()) {
//                String clubID = resultSet.getString("ClubID");
//                String clubName = resultSet.getString("ClubName");
//                String clubDescription = resultSet.getString("ClubDescription");
//
//                // Create a string representation and add it to the ArrayList
//                String clubString = String.format("ClubID: %s, ClubName: %s, ClubDescription: %s",
//                        clubID, clubName, clubDescription);
//
//                data.add(clubString);
//            }
//            System.out.println("data: " + data);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error fetching data from the database");
//        }
//    }

    public ArrayList<ArrayList<String>> fetchDataFromDatabase() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        String query = "SELECT ClubID, ClubName, ClubDescription FROM club";

        try (Connection connection = DatabaseManager.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("in fetchDataFromDatabase");

            while (resultSet.next()) {
                String clubID = resultSet.getString("ClubID");
                String clubName = resultSet.getString("ClubName");
                String clubDescription = resultSet.getString("ClubDescription");

                // Create a list for each row and add it to the ArrayList
                ArrayList<String> row = new ArrayList<>();
                row.add(clubID);
                row.add(clubName);
                row.add(clubDescription);

                data.add(row);
            }
            System.out.println("data: " + data);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching data from the database");
        }

        return data;
    }




//    public void viewTable(TableView<TableViewEncapsulation> viewClubsTable,
//                          TableColumn<TableViewEncapsulation, String> clubIDColumn,
//                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
//                          TableColumn<TableViewEncapsulation, String> clubDescriptionColumn) {
//
//        ObservableList<TableViewEncapsulation> clubDetailsObservableList = FXCollections.observableArrayList();
//        for (ArrayList<String> row : fetchDataFromDatabase()) {
//            clubDetailsObservableList.add(new TableViewEncapsulation(row.get(0), row.get(1), row.get(3)));
//        }
//        fetchDataFromDatabase(clubDetailsObservableList);
//
//
//        clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));
//        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
//        clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));
//
//        viewClubsTable.setItems(clubDetailsObservableList);
//
//    }

    public void viewTable(TableView<TableViewEncapsulation> viewClubsTable,
                          TableColumn<TableViewEncapsulation, String> clubIDColumn,
                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
                          TableColumn<TableViewEncapsulation, String> clubDescriptionColumn) {

        ArrayList<ArrayList<String>> clubDetailsList = fetchDataFromDatabase();
        ObservableList<TableViewEncapsulation> clubDetailsObservableList = FXCollections.observableArrayList();

        for (ArrayList<String> row : clubDetailsList) {
            // Create a TableViewEncapsulation object directly
            clubDetailsObservableList.add(new TableViewEncapsulation(row.get(0), row.get(1), row.get(2)));
        }

        clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));

        viewClubsTable.setItems(clubDetailsObservableList);
    }




//    public void viewTable(TableView<TableViewEncapsulation> viewClubsTable,
//                          TableColumn<TableViewEncapsulation, String> clubIDColumn,
//                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
//                          TableColumn<TableViewEncapsulation, String> clubDescriptionColumn) {
//
//        ObservableList<TableViewEncapsulation> clubDetailsObservableList = fetchDataFromDatabase();
//        System.out.println("in view clubs");
//        System.out.println("Printing ObservableList:");
//        for (TableViewEncapsulation club : clubDetailsObservableList) {
//            System.out.println("Club ID: " + club.getClubID());
//            System.out.println("Club Name: " + club.getClubName());
//            System.out.println("Club Description: " + club.getClubDescription());
//            // Add more print statements for additional properties if needed
//            System.out.println("------------------------");
//        }
//
//        if (clubIDColumn != null && clubNameColumn != null && clubDescriptionColumn != null) {
//            clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));
//            clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
//            clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));
//
//            viewClubsTable.setItems(clubDetailsObservableList);
//        } else {
//            System.out.println("One or more TableColumn is null. Check your FXML file.");
//        }
//    }


}
