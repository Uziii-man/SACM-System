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
    DatabaseManager databaseManager = new DatabaseManager();
    ClubManagement clubManagement = new ClubManagement();

    // Setting up getters and setters for the event date
    private String eventName;
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
        eventNameTextField.setText(eventName);
    }


    // For the event name validation
    @Override
    public boolean nameValidator(Label labelName) {
        isValidData = false;
        // To check if the event name is valid character length
        if (eventName.length() < 5 || eventName.length() > 20) {
            labelName.setText("Invalid Event Name");
            labelName.setTextFill(Color.RED);
        } else {
            // To check if the event name contains only letters and numbers
            if (!eventName.matches("^[a-zA-Z0-9 ]+")) {
                labelName.setTextFill(Color.RED);
                labelName.setText("Invalid Event Name");
            } else {
                labelName.setTextFill(Color.GREEN);
                labelName.setText("Valid Event Name");
                isValidData = true;
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
                setLabelProperties(Color.RED, "Invalid Time");
            }else{
                // To check if the start time is greater than the end time
                if(startTimeHour > endTimeHour){
                    setLabelProperties(Color.RED, "Invalid Time");
                }else{
                    // To check if the start time and end time is the same
                    if(startTimeHour == endTimeHour){
                        // If they are same, check the minutes of the start time and end time
                        if(startTimeMinute > endTimeMinute){
                            setLabelProperties(Color.RED, "Invalid Time");
                        }else{
                            setLabelProperties(Color.GREEN, "Valid Time");
                            isValidData = true;
                        }
                    }else{
                        setLabelProperties(Color.GREEN, "Valid Time");
                        isValidData = true;
                    }
                }
            }
        }
        catch (NumberFormatException e){
            setLabelProperties(Color.RED, "Invalid Time");
        }
        return isValidData;
    }

    // Method to set label colors and texts
    private void setLabelProperties(Color color, String text) {
        eventTimeErrorLabel.setTextFill(color);
        eventTimeErrorLabel.setText(text);
    }

    // To create an event
    @FXML
    private void createEventOnActionButton(ActionEvent actionEvent){
        // To check if the event name is valid
        setEventName(eventNameTextField.getText());
        boolean isValidEventName = nameValidator(eventNameErrorLabel);
        if(!isValidEventName){
            eventNameTextField.clear();
        }

        // To check if the date is empty or not
        boolean isValidDate = false;
        Date datePicked = null;
        if (datePicker.getValue() != null) {
            System.out.println("\nDate Picked");
            datePicked = java.sql.Date.valueOf(datePicker.getValue());
            // To check if the date is valid
            setEventDate(datePicked);
            isValidDate = dateValidator(dateErrorLabel);
        } else {
            // Handle the case where no date is selected
            dateErrorLabel.setTextFill(Color.RED);
            dateErrorLabel.setText("Pick a Date");
        }

        // To check if the description is valid
        setDescription(eventDescriptionTextArea.getText());
        boolean isValidDescription = descriptionValidator(eventErrorDescriptionLabel);
        if(!isValidDescription){
            eventDescriptionTextArea.clear();
        }

        // To check if the time is valid
        boolean isValidTime = timeValidator();
        Time sqlStartTime = null;
        Time sqlEndTimes = null;
        if(!isValidTime){
            eventStartTimeHourTextField.clear();
            eventStartTimeMinuteTextField.clear();
            eventEndTimeHourTextField.clear();
            eventEndTimeMinuteTextField.clear();
        }
        else {
            // Combine hours and minutes to form a time
            String startTime = eventStartTimeHourTextField.getText() + ":" + eventStartTimeMinuteTextField.getText();
            System.out.println(startTime);
            String endTime = eventEndTimeHourTextField.getText() + ":" + eventEndTimeMinuteTextField.getText();
            System.out.println(endTime);

            // Parse the combined time string to LocalTime
            LocalTime combinedStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("H:m"));
            LocalTime combinedEndTimes = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("H:m"));

            // Convert LocalTime to Time
            sqlStartTime = Time.valueOf(combinedStartTime);
            sqlEndTimes = Time.valueOf(combinedEndTimes);
        }


        // To check if the event name, date, description and time is valid
        if(isValidEventName && isValidDescription && isValidDate && isValidTime){
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

            // Alert box to show the event is created successfully
            String[] eventHeader = {"Club ID", "Event Name", "Event Description", "Date", "Start Time", "End Time"};
            databaseManager.userCreateAlertFunctionBox(eventHeader, eventDetails, "Event Creation", "Event Created Successfully");

            // Displaying the error message in a error alert box
            databaseManager.alertFunctionBox("Event Creation", "Event Creation Failed", "Unable to create event");
        }
    }

    // To move back to the event management page
    @FXML
    void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Event_Management.fxml", "Event Management");
    }


    // To sign out from the system
    @FXML
    void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }
}
