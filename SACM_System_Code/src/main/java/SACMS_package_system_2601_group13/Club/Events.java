package SACMS_package_system_2601_group13.Club;

import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.sql.Date;
import java.sql.Time;

public class Events {

    // Defining JavaFx IDs to connect Java FXML file for upcoming events page
    @FXML
    private TableView<TableViewEncapsulation> eventTable;
    @FXML
    private TableColumn<TableViewEncapsulation, String> eventNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, Date> eventDateColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, Time> eventTimeColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> eventDescriptionColumn;


    // Creating object instantiation for classes
    TableViewController tableViewController = new TableViewController();
    MainController mainController = new MainController();


     // Method to initialize the table view
    public void initialize() {
        tableViewController.viewTable(eventTable, eventNameColumn, clubNameColumn, eventDateColumn, eventTimeColumn,
                eventDescriptionColumn);
    }


    // Redirect to the student profile page
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Student_Profile.fxml", "Student Profile");

    }


    // Redirect to the main selection page
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }
}
