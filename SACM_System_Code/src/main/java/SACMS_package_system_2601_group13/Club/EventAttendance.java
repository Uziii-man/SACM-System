package SACMS_package_system_2601_group13.Club;

import SACMS_package_system_2601_group13.Common.ManageData;
import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EventAttendance {

    @FXML
    private TableView<TableViewEncapsulation> attendanceTable;
    @FXML
    private TableColumn<TableViewEncapsulation, String> StudentIDColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> studentFirstNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> studentLastNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, CheckBox> attendanceMarkColumn;
    @FXML
    private TextField eventNameTextField, eventDateTextField;


    // Creating object instantiation for classes
    MainController mainController = new MainController();
    TableViewController tableViewController = new TableViewController();
    ManageData manageData = new ManageData();

    // Initializing the table view
    // When the page is loaded, the table view is loaded with the event name, date and attendance mark
    @FXML
    private void initialize() {
        // To load the table on page load
        tableViewController.viewTables(attendanceTable, StudentIDColumn, studentFirstNameColumn, studentLastNameColumn, attendanceMarkColumn);
        // To set the event name and date in the text fields
        eventNameTextField.setText(tableViewController.getEventName());
        eventDateTextField.setText(tableViewController.getEventDate());
    }


    // If the user wants to update the database with the attendance marked in the table
    @FXML
    private void markAttendanceOnActionButton(ActionEvent actionEvent) throws Exception {
        // To get the values of the checkboxes in the table
        ObservableList<TableViewEncapsulation> items = attendanceTable.getItems();
        int eventID = tableViewController.getEventID();
        // To get check box values for each row
        for (TableViewEncapsulation item : items) {
            CheckBox checkBox = item.getAttendanceCheckbox();
            // To get the attendance mark of the student corresponding to the checkbox selected
            // Since the database stores the attendance as 1 or 0, for that value of the checkbox is converted to 1 or 0
            // 1 for present and 0 for absent
            int attendanceMark;
            if (checkBox.isSelected()) {
                attendanceMark = 1;
            }
            else {
                attendanceMark = 0;
            }
            // To update the attendance table in the database
            String queryUpdateAttendance = "UPDATE attendance SET Attendance = '" + attendanceMark + "' WHERE StudentID = '" +
                    item.getStudentID() +"' AND EventID = '" + eventID + "'";
            manageData.modifyData(queryUpdateAttendance);
        }

        // To display an alert box to show that the attendance has been marked successfully
        manageData.alertFunctionBox("Attendance ", "Attendance Marked Successfully", "Attendance has been successfully updated in the database");

        // Redirecting to the previous page after the attendance has been marked
        mainController.navigateFunction(actionEvent, "Event_Management.fxml", "Event Management");
    }


    // If the user wants to go back to the previous page
    @FXML
    public void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Event_Management.fxml", "Event Management");
    }


    // To sign out from the system
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }
}
