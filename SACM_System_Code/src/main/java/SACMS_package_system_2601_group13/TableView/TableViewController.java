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
import java.util.ArrayList;


public class TableViewController {
    ManageData manageData = new ManageData();
    SignIn signIn = new SignIn();


    /// Table View for the club advisor and student to view all the clubs
    public void viewTable(TableView<TableViewEncapsulation> tableName,
                          TableColumn<TableViewEncapsulation, Integer> clubIDColumn,
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
            clubDetailsObservableList.add(new TableViewEncapsulation((Integer) row.get(0), String.valueOf(row.get(1)), String.valueOf(row.get(4))));
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
        String query = "SELECT * FROM club";

        // Get the data from the database
        ArrayList<ArrayList<Object>> clubDetailsList = manageData.get2DArrayData(query);
        // ObservableList is a list that enables listeners to track changes when they occur
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
//    public void viewTable(TableView<TableViewEncapsulation> tableName,
//                          TableColumn<TableViewEncapsulation, Integer> eventIDColumn,
//                          TableColumn<TableViewEncapsulation, String> eventNameColumn,
//                          TableColumn<TableViewEncapsulation, String> clubNameColumn,
//                          TableColumn<TableViewEncapsulation, Date> eventDateColumn,
//                          TableColumn<TableViewEncapsulation, String> eventDescriptionColumn) {
//
//        // Query the database and get the data -> clubDetailsList
//        String query = "SELECT * FROM event";
//
//        // Get the data from the database
//        ArrayList<ArrayList<Object>> eventDetailsList = manageData.get2DArrayData(query);
//        // ObservableList is a list that enables listeners to track changes when they occur
//        ObservableList<TableViewEncapsulation> eventDetailsObservableList = FXCollections.observableArrayList();
//
//        // This done to show the clubs that host events
//        String clubDetailsQuery = "SELECT * FROM club";
//        ArrayList<ArrayList<Object>> clubDetailsList = manageData.get2DArrayData(clubDetailsQuery);
//
//        // To add the clubs to the observable list that the club advisor is managing
//        for (ArrayList<Object> eventRow : eventDetailsList) {
//            for (ArrayList<Object> clubRow : clubDetailsList) {
//                if (eventRow.get(1).equals(clubRow.get(0))) {
//                    // Create a TableViewEncapsulation object directly
//                    eventDetailsObservableList.add(new TableViewEncapsulation((Integer) eventRow.get(0), String.valueOf(eventRow.get(2)), String.valueOf(clubRow.get(1)), String.valueOf(eventRow.get(3)), String.valueOf(eventRow.get(4))));
//                    break;
//                }
//            }
//        }
//
//        eventIDColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));
//        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
//        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventAbbreviation"));
//        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDescription"));
//        eventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("eventDescription"));
//
//        tableName.setItems(eventDetailsObservableList);
//    }
//

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
        // ObservableList is a list that enables listeners to track changes when they occur
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
        String attendanceDtailsQuery = "SELECT * FROM attendance";
        ArrayList<ArrayList<Object>> attendanceDetailsList = manageData.get2DArrayData(attendanceDtailsQuery);

        // ObservableList is a list that enables listeners to track changes when they occur
        ObservableList<TableViewEncapsulation> attendaceDetailsObservableList = FXCollections.observableArrayList();

        // Get the student details from the database
        String studentDetailsQuery = "SELECT * FROM student";
        ArrayList<ArrayList<Object>> studentDetailsList = manageData.get2DArrayData(studentDetailsQuery);



        // To add the attendance to the observable list that the attendance table

        for (ArrayList<Object> attendanceRow : attendanceDetailsList) {
            System.out.println("\nEvent ID: " + attendanceRow.get(0));
            for (ArrayList<Object> studentRow : studentDetailsList) {
                System.out.println("Student ID: " + studentRow.get(0));
                System.out.println("Event ID " + attendanceRow.get(0));
                System.out.println("Event " + eventID);
                if (attendanceRow.get(0).equals(eventID)) {
                    System.out.println("\n First IF \n" );
                    if (attendanceRow.get(1).equals(studentRow.get(0))) {
                        System.out.println("\n Seconds IF \n");
                                // Create a TableViewEncapsulation object directly
                        System.out.println("Attendance: " + attendanceRow.get(2));
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
