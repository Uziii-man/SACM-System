package SACMS_package_system_2601_group13.TableView;

import SACMS_package_system_2601_group13.Club.ClubManagement;
import SACMS_package_system_2601_group13.Common.ManageData;
import SACMS_package_system_2601_group13.Common.SignIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;;


public class TableViewController {
    ManageData manageData = new ManageData();
    SignIn signIn = new SignIn();


    // Table View for the club advisor and student to view all the clubs and join
    // Also used for students to leave the club they joined
    public void viewTable(TableView<TableViewEncapsulation> tableName,
                          TableColumn<TableViewEncapsulation, Integer> clubIDColumn,
                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
                          TableColumn<TableViewEncapsulation, String> clubDescriptionColumn,
                          ArrayList<ArrayList<Object>> clubDetailsList) {

        // ObservableList is a list that enables listeners to track changes when they occur
        ObservableList<TableViewEncapsulation> clubDetailsObservableList = FXCollections.observableArrayList();

        // Add the details to the observable list
        for (ArrayList<Object> clubDetail : clubDetailsList) {
            // Create a TableViewEncapsulation object directly
            clubDetailsObservableList.add(new TableViewEncapsulation(
                    (Integer) clubDetail.get(0),
                    (String) clubDetail.get(1),
                    (String) clubDetail.get(2)));
        }

        clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));

        tableName.setItems(clubDetailsObservableList);
    }


    // Table View for Manage Club Page only for club advisor
    public void viewTable(TableView<TableViewEncapsulation> tableName,
                          TableColumn<TableViewEncapsulation, Integer> clubIDColumn,
                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
                          TableColumn<TableViewEncapsulation, String> clubNameAbbreviationColumn,
                          TableColumn<TableViewEncapsulation, String> clubDescriptionColumn) {

        // Query the database and get the data -> clubDetailsList
        String queryClub = "SELECT * FROM club";

        // Get the data from the database
        ArrayList<ArrayList<Object>> clubDetailsList = manageData.get2DArrayData(queryClub);
        // Initialize the observable list
        ObservableList<TableViewEncapsulation> clubDetailsObservableList = FXCollections.observableArrayList();

        // This done to show the clubs that the club advisor is managing
        String clubIDQuery = "SELECT clubID FROM club_and_club_advisor WHERE StaffID = '" + signIn.getLoginUserID() + "'";
        ArrayList<Object> clubIDList = manageData.get1DArrayData(clubIDQuery);

        // To add the clubs to the observable list that the club advisor is managing
        for (ArrayList<Object> row : clubDetailsList) {
            if (clubIDList.contains(row.get(0))) {
            // Create a TableViewEncapsulation object directly
            clubDetailsObservableList.add(new TableViewEncapsulation(
                    (Integer) row.get(0),
                    (String) row.get(1),
                    (String) row.get(2),
                    (String) row.get(4)));
            }
        }

        clubIDColumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubNameAbbreviationColumn.setCellValueFactory(new PropertyValueFactory<>("clubAbbreviation"));
        clubDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));

        tableName.setItems(clubDetailsObservableList);
    }


    // To view the events in a table
    public void viewTable(TableView<TableViewEncapsulation> tableName,
                          TableColumn<TableViewEncapsulation, String> eventNameColumn,
                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
                          TableColumn<TableViewEncapsulation, Date> eventDateColumn,
                          TableColumn<TableViewEncapsulation, Time> eventTimeColumn,
                          TableColumn<TableViewEncapsulation, String> eventDescriptionColumn) {

        // Query the database and get the data
        String queryEvents = "SELECT * FROM event";
        String queryClubs = "SELECT * FROM club";

        // Getting the data from the database
        ArrayList<ArrayList<Object>> eventDetailsList = manageData.get2DArrayData(queryEvents);
        ArrayList<ArrayList<Object>> clubDetailsList = manageData.get2DArrayData(queryClubs);

        // Get the local date from the system and compare with the date in the database
        Date localDate = new Date(System.currentTimeMillis());

        // Initialize the observable list
        ObservableList<TableViewEncapsulation> eventDetailsObservableList = FXCollections.observableArrayList();

        // To show upcoming events only
        for (ArrayList<Object> eventRow : eventDetailsList) {
            // To check if the event date is greater than the local date
            if (localDate.compareTo((Date) eventRow.get(3)) < 0) {
                for (ArrayList<Object> clubRow : clubDetailsList) {
                    if (eventRow.get(1).equals(clubRow.get(0))) {
                        // Create a TableViewEncapsulation object directly
                        eventDetailsObservableList.add(new TableViewEncapsulation(
                                (String) eventRow.get(2),
                                (String) clubRow.get(1),
                                (Date) eventRow.get(3),
                                (Time) eventRow.get(4),
                                (String) eventRow.get(6)));
                    }
                }
            }
        }

        // To set the table view columns for the upcoming events
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        eventTimeColumn.setCellValueFactory(new PropertyValueFactory<>("eventStartTime"));
        eventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("eventDescription"));

        // To set the table view for the upcoming events
        tableName.setItems(eventDetailsObservableList);
    }


    // Table view to manage events
    // Getters and setters for the club table view
    private static int clubID;
    public void setClubID(int clubID) {
        this.clubID = clubID;
    }
    public int getClubID() {
        return clubID;
    }

    public void viewTable(TableView<TableViewEncapsulation> tableName,
                          TableColumn<TableViewEncapsulation, Integer> eventIDColumn,
                          TableColumn<TableViewEncapsulation, String> eventNameColumn,
                          TableColumn<TableViewEncapsulation, Date> eventDateColumn,
                          TableColumn<TableViewEncapsulation, Time> eventStartTimeColumn,
                          TableColumn<TableViewEncapsulation, Time> eventEndTimeColumn,
                          TableColumn<TableViewEncapsulation, String> eventDescriptionColumn) {

        // Query the database and get the data -> clubDetailsList
        String query = "SELECT * FROM event";
        // Get the data from the database
        ArrayList<ArrayList<Object>> eventDetailsList = manageData.get2DArrayData(query);

        // Initialize the observable list
        ObservableList<TableViewEncapsulation> eventDetailsObservableList = FXCollections.observableArrayList();


        // To add the clubs to the observable list that the club advisor is managing
        for (ArrayList<Object> eventRow : eventDetailsList) {
            if (eventRow.get(1).equals(clubID)) {
                // Create a TableViewEncapsulation object directly
                eventDetailsObservableList.add(new TableViewEncapsulation(
                        (Integer) eventRow.get(0), (String) eventRow.get(2),
                        (Date) eventRow.get(3),
                        (Time) eventRow.get(4),
                        (Time) eventRow.get(5),
                        (String) eventRow.get(6)));
            }
        }

        // To set the table view columns
        eventIDColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        eventStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("eventStartTime"));
        eventEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("eventEndTime"));
        eventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("eventDescription"));

        // To set the table view
        tableName.setItems(eventDetailsObservableList);
    }


    // Table view to manage attendance
    // Getters and setters for the event table view
    // For event ID
    private static int eventID;

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }
    public int getEventID() {
        return eventID;
    }

    // For event name
    private static String eventName;

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventName() {
        return eventName;
    }

    // For event date
    private static String eventDate;

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public String getEventDate() {
        return eventDate;
    }


    // Method to create a checkbox
    private CheckBox createCheckBox(boolean isSelected) {
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(isSelected);
        return checkBox;
    }

    public void viewTables(TableView<TableViewEncapsulation> tableName,
                          TableColumn<TableViewEncapsulation, String> studentIDColumn,
                          TableColumn<TableViewEncapsulation, String> studentFirstNameColumn,
                          TableColumn<TableViewEncapsulation, String> studentLastNameColumn,
                          TableColumn<TableViewEncapsulation, CheckBox> attendanceCheckboxColumn) {

        // Get the attendance data from the database
        String attendanceDetailsQuery = "SELECT * FROM attendance";
        ArrayList<ArrayList<Object>> attendanceDetailsList = manageData.get2DArrayData(attendanceDetailsQuery);

        // Initialize the observable list
        ObservableList<TableViewEncapsulation> attendaceDetailsObservableList = FXCollections.observableArrayList();

        // Get the student details from the database
        String studentDetailsQuery = "SELECT * FROM student";
        ArrayList<ArrayList<Object>> studentDetailsList = manageData.get2DArrayData(studentDetailsQuery);

        // To add the attendance to the observable list that the attendance table
        for (ArrayList<Object> attendanceRow : attendanceDetailsList) {
            for (ArrayList<Object> studentRow : studentDetailsList) {
                // To check if the event ID and student ID matches
                if (attendanceRow.get(0).equals(eventID)) {
                    // To check if the student ID matches
                    if (attendanceRow.get(1).equals(studentRow.get(0))) {
                        // Create a TableViewEncapsulation object directly
                        attendaceDetailsObservableList.add(new TableViewEncapsulation(
                                (String) attendanceRow.get(1),
                                (String) studentRow.get(1),
                                (String) studentRow.get(2),
                                createCheckBox((boolean) attendanceRow.get(2))));
                        break;
                    }
                }
            }
        }

        // To set the table view columns
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        studentFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentFirstName"));
        studentLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentLastName"));
        attendanceCheckboxColumn.setCellValueFactory(new PropertyValueFactory<>("attendanceCheckbox"));

        // To set the table view
        tableName.setItems(attendaceDetailsObservableList);
    }
}
