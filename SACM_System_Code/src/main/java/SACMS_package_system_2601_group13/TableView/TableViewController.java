package SACMS_package_system_2601_group13.TableView;

import SACMS_package_system_2601_group13.Common.ManageData;
import SACMS_package_system_2601_group13.Common.SignIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class TableViewController {

    ManageData manageData = new ManageData();
    SignIn signIn = new SignIn();

    /// Table View for the club advisor and student to view all the clubs
    public void viewTable(TableView<TableViewEncapsulation> tableName,
                          TableColumn<TableViewEncapsulation, String> clubIDColumn,
                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
                          TableColumn<TableViewEncapsulation, String> clubDescriptionColumn) {

        // Query the database and get the data -> clubDetailsList
        String query = "SELECT * FROM club";

        // Get the data from the database
        ArrayList<ArrayList<Object>> clubDetailsList = manageData.get2DArrayData(query);
        // ObservableList is a list that enables listeners to track changes when they occur
        ObservableList<TableViewEncapsulation> clubDetailsObservableList = FXCollections.observableArrayList();

        for (ArrayList<Object> row : clubDetailsList) {
            // Create a TableViewEncapsulation object directly
            clubDetailsObservableList.add(new TableViewEncapsulation(String.valueOf(row.get(0)), String.valueOf(row.get(1)), String.valueOf(row.get(4))));
        }

        clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));

        tableName.setItems(clubDetailsObservableList);
    }

    // Table View for Manage Club Page only for club advisor
    public void viewTable(TableView<TableViewEncapsulation> tableName,
                          TableColumn<TableViewEncapsulation, String> clubIDColumn,
                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
                          TableColumn<TableViewEncapsulation, String> clubNameAbbreviationColumn,
                          TableColumn<TableViewEncapsulation, String> clubDescriptionColumn) {

        // Query the database and get the data -> clubDetailsList
        String query = "SELECT * FROM club";

        // Get the data from the database
        ArrayList<ArrayList<Object>> clubDetailsList = manageData.get2DArrayData(query);
        // ObservableList is a list that enables listeners to track changes when they occur
        ObservableList<TableViewEncapsulation> clubDetailsObservableList = FXCollections.observableArrayList();

        // This done to show the clubs that the club advisor is managing
        String clubIDQuery = "SELECT clubID FROM club_and_club_advisor WHERE StaffID = '" + signIn.getLoginUserID() + "'";
        ArrayList<Object> clubIDList = manageData.get1DArrayData(clubIDQuery);

        // To add the clubs to the observable list that the club advisor is managing
        for (ArrayList<Object> row : clubDetailsList) {
            if (clubIDList.contains(row.get(0))) {
            // Create a TableViewEncapsulation object directly
            clubDetailsObservableList.add(new TableViewEncapsulation(String.valueOf(row.get(0)), String.valueOf(row.get(1)), String.valueOf(row.get(2)), String.valueOf(row.get(4))));
            }
        }

        clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubNameAbbreviationColumn.setCellValueFactory(new PropertyValueFactory<>("clubAbbreviation"));
        clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));

        System.out.println("hello");
        tableName.setItems(clubDetailsObservableList);
    }



}
