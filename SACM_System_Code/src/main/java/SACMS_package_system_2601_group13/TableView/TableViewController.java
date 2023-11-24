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

    public ObservableList<TableViewEncapsulation> fetchDataFromDatabase() {

        ObservableList<TableViewEncapsulation> data = FXCollections.observableArrayList();

        // SQL query to retrieve data
        String query = "SELECT ClubID, ClubName, ClubDescription FROM your_table_name";
        Connection connection = DatabaseManager.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
             ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("in fetchDataFromDatabase");
            while (resultSet.next()) {
                String clubID = resultSet.getString("ClubID");
                String clubName = resultSet.getString("ClubName");
                String clubDescription = resultSet.getString("ClubDescription");
//                String clubAdvisor = resultSet.getString("ClubAdvisor");

                data.add(new TableViewEncapsulation(clubID, clubName, clubDescription));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public void viewTable(TableView<TableViewEncapsulation> viewClubsTable,
                          TableColumn<TableViewEncapsulation, String> clubIDColumn,
                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
                          TableColumn<TableViewEncapsulation, String> clubDescriptionColumn
                         ) {
//        ,TableColumn<TableViewEncapsulation, String> clubAdvisorColumn
        ObservableList<TableViewEncapsulation> clubDetailsObservableList = fetchDataFromDatabase();
        System.out.println("in view clubs");
        clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));
//        clubAdvisorColumn.setCellValueFactory(new PropertyValueFactory<>("clubAdvisor"));

        viewClubsTable.setItems(clubDetailsObservableList);
    }

}
