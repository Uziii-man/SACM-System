package SACMS_package_system_2601_group13.Club;

import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EventManagement {
    @FXML
    private Label eventManagementErrorLabel;
    @FXML
    private TableView<TableViewEncapsulation> eventManagementTable;
    @FXML
    private TableColumn<TableViewEncapsulation, String> eventIDColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubIDColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> eventNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> eventDateColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> eventDescriptionColumn;



    MainController mainController = new MainController();

    // To load the event management table
    @FXML
    protected void loadTableOnActionButton() {

    }

    // To update the event management table
    private void updateEventTable() {

    }


    // To delete an event
    @FXML
    protected void deleteEventOnActionButton(ActionEvent actionEvent) throws Exception {

    }

    // To create an event
    @FXML
    protected void createEventOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Event_Creation.fxml", "Event Creation");
    }

    // To mark attendance
    @FXML
    protected void markAttendanceOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "View_Join_Clubs.fxml", "SACM System");
    }


    // To go back to the previous page
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Management.fxml", "Club Management");
    }


}
