package SACMS_package_system_2601_group13.Club;

import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.sql.Date;
import java.sql.Time;

public class EventManagement {

    @FXML
    private Label eventManagementErrorLabel;
    @FXML
    private TableView<TableViewEncapsulation> eventManagementTable;
    @FXML
    private TableColumn<TableViewEncapsulation, Integer> eventIDColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> eventNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, Date> eventDateColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, Time> eventStartTimeColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, Time> eventEndTimeColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> eventDescriptionColumn;


    // Creating constructor for classes
    MainController mainController = new MainController();
    TableViewController tableViewController = new TableViewController();

//    // To store the eventID
//    private int eventID;
//    public int getEventID() {
//        return eventID;
//    }
//    public void setEventID(int eventID) {
//        this.eventID = eventID;
//    }


    // Creating runnable object to execute the object frequently and assigned using lambda
    Runnable viewEvent = () -> tableViewController.viewTable(eventManagementTable, eventIDColumn, eventNameColumn, eventDateColumn, eventStartTimeColumn, eventEndTimeColumn, eventDescriptionColumn);


    // Method to set label colors and texts
    private void setLabelProperties(Color color, String text) {
        eventManagementErrorLabel.setTextFill(color);
        eventManagementErrorLabel.setText(text);
    }


    // To load the event management table
    @FXML
    protected void loadTableOnActionButton() {
        // To load the event management table
        viewEvent.run();
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
        TableView.TableViewSelectionModel<TableViewEncapsulation> selectionModel = eventManagementTable.getSelectionModel();
        if (selectionModel.isEmpty()) {
            // The club is not selected to join
            setLabelProperties(Color.RED , "Select a row to mark attendance");
        } else {
            // To get the selected and insert row
            TableViewEncapsulation selectedEvent = eventManagementTable.getSelectionModel().getSelectedItem();
            // Getting the clubID from the selected row
            int eventID = selectedEvent.getEventID();
            tableViewController.setEventID(eventID);

            String eventName = selectedEvent.getEventName();
            tableViewController.setEventName(eventName);

            Date eventDate = selectedEvent.getEventDate();
            tableViewController.setEventDate(String.valueOf(eventDate));


            // Directing to the event attendance page
            mainController.navigateFunction(actionEvent, "Event_Attendance.fxml", "Event Attendance");
        }
    }


    // To go back to the previous page
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Management.fxml", "Club Management");
    }


    public void eventSummaryOnActionButton(ActionEvent actionEvent) {

    }
}
