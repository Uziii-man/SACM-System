package SACMS_package_system_2601_group13.Club;

import SACMS_package_system_2601_group13.Common.DatabaseManager;
import SACMS_package_system_2601_group13.Common.ManageData;
import SACMS_package_system_2601_group13.Common.Validation;
import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class EventCreation extends Validation {
    @FXML
    private Label eventNameErrorLabel, eventTimeErrorLabel, dateErrorLabel, eventErrorDescriptionLabel;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea eventDescriptionTextArea;

    @FXML
    private TextField eventNameTextField, eventStartTimeHourTextField, eventStartTimeMinuteTextField,
            eventEndTimeHourTextField, eventEndTimeMinuteTextField;

    boolean isValidData;

    // Creating new constructor methods for existing classes
    MainController mainController = new MainController();
    ManageData manageData = new ManageData();
    ClubManagement clubManagement = new ClubManagement();

    // Setting up getters and setters for the event date
    private String eventName;
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
//        eventNameTextField.setText(eventName);
    }


    // Method to set label colors and texts
    private void setLabelProperties(Label label, Color color, String text) {
        label.setTextFill(color);
        label.setText(text);
    }


    // For the event name validation
    @Override
    public boolean nameValidator(Label labelName) {
        isValidData = false;
        // To check if the event name is valid character length
        if (eventName.length() < 5 || eventName.length() > 20) {
            setLabelProperties(labelName ,Color.RED, "Invalid Event Name");
        } else {
            // To check if the event name contains only letters and numbers
            if (!eventName.matches("^[a-zA-Z0-9 ]+")) {
                setLabelProperties(labelName ,Color.RED, "Invalid Event Name");
            } else {
                // To check if the event name is already in the database
                String query = "SELECT EventName FROM event WHERE EventName = '" + eventName + "'";
                ArrayList<Object> eventNameList = manageData.get1DArrayData(query);
                if (!eventNameList.isEmpty()) {
                    setLabelProperties(labelName ,Color.RED, "Event Name Already Exists");
                } else {
                    setLabelProperties(labelName ,Color.GREEN, "Valid Event Name");
                    isValidData = true;
                }
//                setLabelProperties(labelName ,Color.GREEN, "Valid Event Name");
//                isValidData = true;
            }
        }
        return isValidData;
    }


    // To check if the time is valid
    public boolean timeValidator(){
        isValidData = false;
        try {
            // To check if the time is valid or not by converting the string to integer
            int startTimeHour = Integer.parseInt(eventStartTimeHourTextField.getText());
            int startTimeMinute = Integer.parseInt(eventStartTimeMinuteTextField.getText());
            int endTimeHour = Integer.parseInt(eventEndTimeHourTextField.getText());
            int endTimeMinute = Integer.parseInt(eventEndTimeMinuteTextField.getText());

            if(startTimeHour < 0 || startTimeHour > 23 || startTimeMinute < 0 || startTimeMinute > 59 ||
                    endTimeHour < 0 || endTimeHour > 23 || endTimeMinute < 0 || endTimeMinute > 59){
                setLabelProperties(eventTimeErrorLabel, Color.RED, "Invalid Time");
            }else{
                // To check if the start time is greater than the end time
                if(startTimeHour > endTimeHour){
                    setLabelProperties(eventTimeErrorLabel, Color.RED, "Invalid Time");
                }else{
                    // To check if the start time and end time is the same
                    if(startTimeHour == endTimeHour){
                        // If they are same, check the minutes of the start time and end time
                        if(startTimeMinute > endTimeMinute){
                            setLabelProperties(eventTimeErrorLabel, Color.RED, "Invalid Time");
                        }else{
                            setLabelProperties(eventTimeErrorLabel ,Color.GREEN, "Valid Time");
                            isValidData = true;
                        }
                    }else{
                        setLabelProperties(eventTimeErrorLabel ,Color.GREEN, "Valid Time");
                        isValidData = true;
                    }
                }
            }
        }
        catch (NumberFormatException e){
            setLabelProperties(eventTimeErrorLabel ,Color.RED, "Invalid Time");
        }
        return isValidData;
    }


    // To check if all the details are valid
    boolean isValidAllDetails;

    Time sqlStartTime = null;
    Time sqlEndTimes = null;
    Date datePicked = null;

    private boolean isValidAllEventDetails() {
        isValidAllDetails = false;

        // To check if the event name is valid
        setEventName(eventNameTextField.getText());
        boolean isValidEventName = nameValidator(eventNameErrorLabel);
        if (!isValidEventName) {
            eventNameTextField.clear();
        }

        // To check if the date is selected or not
        boolean isValidDate = false;
//        Date datePicked = null;
        if (datePicker.getValue() != null) {
            // To convert the date picker value to sql date
            datePicked = java.sql.Date.valueOf(datePicker.getValue());
            // To check if the date is valid
            setEventDate(datePicked);
            isValidDate = dateValidator(dateErrorLabel);
        } else {
            // Handle the case where no date is selected
            setLabelProperties(dateErrorLabel, Color.RED, "Pick a Date");
        }

        // To check if the description is valid
        setDescription(eventDescriptionTextArea.getText());
        boolean isValidDescription = descriptionValidator(eventErrorDescriptionLabel);
        if (!isValidDescription) {
            eventDescriptionTextArea.clear();
        }

        // To check if the time is valid
        boolean isValidTime = timeValidator();
//        Time sqlStartTime = null;
//        Time sqlEndTimes = null;
        if (!isValidTime) {
            eventStartTimeHourTextField.clear();
            eventStartTimeMinuteTextField.clear();
            eventEndTimeHourTextField.clear();
            eventEndTimeMinuteTextField.clear();
        } else {
            // Combine hours and minutes to form a time
            String startTime = eventStartTimeHourTextField.getText() + ":" + eventStartTimeMinuteTextField.getText();
            String endTime = eventEndTimeHourTextField.getText() + ":" + eventEndTimeMinuteTextField.getText();

            // Parse the combined time string to LocalTime
            LocalTime combinedStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("H:m"));
            LocalTime combinedEndTimes = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("H:m"));

            // Convert LocalTime to Time
            sqlStartTime = Time.valueOf(combinedStartTime);
            sqlEndTimes = Time.valueOf(combinedEndTimes);
        }


        // To check if the event name, date, description and time is valid
        if (isValidEventName && isValidDescription && isValidDate && isValidTime) {
            isValidAllDetails = true;

        }
    return isValidAllDetails;
    }

    // To create an event
    @FXML
    protected void createEventOnActionButton(ActionEvent actionEvent) throws Exception{
        // To check if the event name is valid

        if(isValidAllEventDetails()){
            // Adding the event details into an array list
            ArrayList<Object> eventDetails = new ArrayList<>(Arrays.asList(
                    clubManagement.getClubID(),
                    eventName,
                    eventDescriptionTextArea.getText(),
                    datePicked,
                    sqlStartTime,
                    sqlEndTimes));

            // Inserting the event details into the database
            String insertEventQuery = "INSERT INTO event (ClubID ,EventName, EventDescription, EventDate, EventStartTime, EventEndTime) VALUES (?, ?, ?, ?, ?, ?)";
            manageData.insertData(eventDetails, insertEventQuery);

            // After the event is created, attendance is created for the event
            // Get the eventID from the database
            String getEventIDQuery = "SELECT EventID FROM event WHERE EventName = '" + eventName + "'";
            ArrayList<Object> eventIDList = manageData.get1DArrayData(getEventIDQuery);
            int eventID = (int) eventIDList.get(0);

            // Get the studentID from the database to create attendance for the event who are in the club
            String getStudentIDQuery = "SELECT StudentID FROM club_and_student WHERE ClubID = '" + clubManagement.getClubID() + "'";
            ArrayList<Object> studentIDList = manageData.get1DArrayData(getStudentIDQuery);

            // To add the eventID and studentID to the attendance table
            // Automatically attendance table with event details will be created when the event is created
            for(Object studentID : studentIDList){
                ArrayList<Object> attendanceDetails = new ArrayList<>(Arrays.asList(
                        eventID,
                        studentID,
                        0));
                String insertAttendanceQuery = "INSERT INTO attendance (EventID, StudentID, Attendance) VALUES (?, ?, ?)";
                manageData.insertData(attendanceDetails, insertAttendanceQuery);
            }

            // Alert box to show the event is created successfully
            String[] eventHeader = {"Club ID", "Event Name", "Event Description", "Date", "Start Time", "End Time"};
            manageData.userCreateAlertFunctionBox(eventHeader, eventDetails, "Event Creation", "Event Created Successfully");

            // After the event is created, the user is navigated to the event management page
            mainController.navigateFunction(actionEvent, "Event_Management.fxml", "Event Management");
        }
    }

    // To move back to the event management page
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Event_Management.fxml", "Event Management");
    }


    // To sign out from the system
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }
}
