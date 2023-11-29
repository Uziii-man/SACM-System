package SACMS_package_system_2601_group13.Club;

import SACMS_package_system_2601_group13.Common.ManageData;
import SACMS_package_system_2601_group13.Common.Validation;
import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;

import java.sql.Date;
import java.sql.Time;

public class EventManagement extends Validation {
    // Defining JavaFx IDs to connect Java FXML file for event management page
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
    ManageData manageData = new ManageData();
    TableViewController tableViewController = new TableViewController();
    ReportGeneration reportGeneration = new ReportGeneration();

    EventCreation eventCreation = new EventCreation();

    // Creating variables for the event management page
    private String queryUpdate;

//    // To store the eventID
//    private int eventID;
//    public int getEventID() {
//        return eventID;
//    }
//    public void setEventID(int eventID) {
//        this.eventID = eventID;
//    }


    // Creating runnable object to execute the object frequently and assigned using lambda
    // Use to view the event table in the event management page
    Runnable viewEventManagementTable = () -> tableViewController.viewTable(eventManagementTable, eventIDColumn,
            eventNameColumn, eventDateColumn, eventStartTimeColumn, eventEndTimeColumn, eventDescriptionColumn);


    // No need of another name validator because the event name validator is already in the event creation page
    @Override
    public boolean nameValidator(Label labelName) {
        return false;
    }

    // Method to set label colors and texts
    private void setLabelProperties(Color color, String text) {
        eventManagementErrorLabel.setTextFill(color);
        eventManagementErrorLabel.setText(text);
    }

    private void setLabelProperties(Label eventManagementErrorLabel, Color color, String text) {
        eventManagementErrorLabel.setTextFill(color);
        eventManagementErrorLabel.setText(text);
    }

    // To load the event management table
    @FXML
    protected void loadTableOnActionButton() {
        // To load the event management table
        viewEventManagementTable.run();
        // To edit the event management
        eventNameColumn.setCellFactory(TextFieldTableCell.<TableViewEncapsulation>forTableColumn());
        eventDescriptionColumn.setCellFactory(TextFieldTableCell.<TableViewEncapsulation>forTableColumn());
        updateEventTable();

    }

    // To update the event management table
    private void updateEventTable() {
        // Setting the error label to show the user to select a row to edit
        // To display the club name is updated
        setLabelProperties(eventManagementErrorLabel, Color.BLACK, "Please select a row to edit");

        // Event ID column cannot be edited because it is the primary key in the database
        // To edit event name colum
        eventNameColumn.setOnEditCommit(event -> {
            TableViewEncapsulation tableViewEncapsulation = event.getTableView().getItems().get(event.getTablePosition().getRow());

            // Validation for event name
            eventCreation.setEventName(event.getNewValue());
            boolean validName =  eventCreation.nameValidator(eventManagementErrorLabel);
            if (validName) {
                tableViewEncapsulation.setClubAbbreviation(event.getNewValue());
                queryUpdate = "UPDATE event SET EventName = '" + event.getNewValue() + "' WHERE EventID = '" + tableViewEncapsulation.getEventID() + "'";
                manageData.modifyData(queryUpdate);
                // To display the club name is updated
                setLabelProperties(eventManagementErrorLabel, Color.GREEN, "Event Name is Updated");
            }
        });

        // To edit event description column
        eventDescriptionColumn.setOnEditCommit(event -> {
            TableViewEncapsulation tableViewEncapsulation = event.getTableView().getItems().get(event.getTablePosition().getRow());

            // Validation for event description
            setDescription(event.getNewValue());
            boolean validDescription = descriptionValidator(eventManagementErrorLabel);
            if (validDescription) {
                queryUpdate = "UPDATE event SET EventDescription = '" + event.getNewValue() + "' WHERE EventID = '" + tableViewEncapsulation.getEventID() + "'";
                manageData.modifyData(queryUpdate);
                // To display the club description is updated
                setLabelProperties(eventManagementErrorLabel, Color.GREEN, "Event Description is Updated");
            }
        });
    }


    // If the club advisor wants to delete an event
    @FXML
    protected void deleteEventOnActionButton(ActionEvent actionEvent) throws Exception {
        TableView.TableViewSelectionModel<TableViewEncapsulation> selectionModel = eventManagementTable.getSelectionModel();
        if (selectionModel.isEmpty()) {
            // The club advisor doesn't select a row to delete
            setLabelProperties(eventManagementErrorLabel, Color.RED, "Select a event to delete");
        } else {
            TableViewEncapsulation selectedClub = eventManagementTable.getSelectionModel().getSelectedItem();
            int eventID = selectedClub.getEventID();

            String attendanceDataDeleteQuery = "DELETE FROM attendance WHERE EventID = '" + eventID + "'";
            manageData.modifyData(attendanceDataDeleteQuery);

            // Query for event table to delete data
            String clubAndClubAdvisorDataDeleteQuery = "DELETE FROM event WHERE EventID = '" + eventID + "'";
            manageData.modifyData(clubAndClubAdvisorDataDeleteQuery);

            // Refresh the table after deleting an event
            viewEventManagementTable.run();
        }
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
            setLabelProperties(Color.RED , "Select a event to mark attendance");
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


    @FXML
    protected void eventSummaryOnActionButton(ActionEvent actionEvent) {
        TableView.TableViewSelectionModel<TableViewEncapsulation> selectionModel = eventManagementTable.getSelectionModel();
        if (selectionModel.isEmpty()) {
            // The club is not selected to join
            setLabelProperties(Color.RED, "Select a event to Generate Report");
        } else {
            TableViewEncapsulation selectedEvent = eventManagementTable.getSelectionModel().getSelectedItem();

            // Setting the event name to the report generation class
            reportGeneration.setEventName(selectedEvent.getEventName());
            reportGeneration.setEventID(selectedEvent.getEventID());
            reportGeneration.setEventDate(selectedEvent.getEventDate());
            reportGeneration.setEventTime(selectedEvent.getEventStartTime());
            reportGeneration.eventReport();

        }
    }

    // To go back to the previous page
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Management.fxml", "Club Management");
    }


}
