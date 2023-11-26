package SACMS_package_system_2601_group13.TableView;

import SACMS_package_system_2601_group13.Common.ManageData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class TableViewController {

    ManageData manageData = new ManageData();

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

        System.out.println("hello");
        tableName.setItems(clubDetailsObservableList);
    }

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

        for (ArrayList<Object> row : clubDetailsList) {
            // Create a TableViewEncapsulation object directly
            clubDetailsObservableList.add(new TableViewEncapsulation(String.valueOf(row.get(0)), String.valueOf(row.get(1)), String.valueOf(row.get(2)), String.valueOf(row.get(4))));
        }

        clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubNameAbbreviationColumn.setCellValueFactory(new PropertyValueFactory<>("clubAbbreviation"));
        clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));

        System.out.println("hello");
        tableName.setItems(clubDetailsObservableList);
    }



}
