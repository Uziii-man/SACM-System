package SACMS_package_system_2601_group13.TableView;

import javafx.scene.control.CheckBox;

import java.sql.Date;
import java.sql.Time;

public class TableViewEncapsulation {
    // Encapsulating the data from the database

    // Declaring variables for the club table view
    private int clubID;
    private String clubName;
    private String clubAbbreviation;
    private String clubDescription;

    // Declaring variables for the event table view
    private int eventID;
    private String eventName;
    private Date eventDate;
    private String eventDescription;
    private Time eventStartTime;
    private Time eventEndTime;

    // Declaring variables for the attendance table view
//    private String studentID;
//    private String studentFirstName;
//    private String studentLastName;
//    private CheckBox attendanceCheckbox;


    // Method to construct the table view encapsulation object
    public TableViewEncapsulation() {
    }

    // Setters for table view used in view club page
    public TableViewEncapsulation(int clubID, String clubName, String clubDescription) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
    }

    // Setters for table view used in club management page
    public TableViewEncapsulation(int clubID, String clubName, String clubAbbreviation, String clubDescription) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.clubAbbreviation = clubAbbreviation;
        this.clubDescription = clubDescription;
    }

    // Setters for table view used in event management page
    public TableViewEncapsulation(int eventID, String eventName, Date eventDate, Time eventStartTime, Time eventEndTime ,String eventDescription) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventDescription = eventDescription;
    }

//    public TableViewEncapsulation(String studentID, String studentFirstName, String studentLastName, CheckBox attendanceCheckbox) {
//        this.studentID = studentID;
//        this.studentFirstName = studentFirstName;
//        this.studentLastName = studentLastName;
//        this.attendanceCheckbox = attendanceCheckbox;
//    }

//    public TableViewEncapsulation(int e, String s, String s1, String s2, String s3) {
//    }

    // Getter and setter for above declared variables
    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setClubAbbreviation(String clubAbbreviation) {
        this.clubAbbreviation = clubAbbreviation;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public int getClubID() {
        return clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubAbbreviation() {
        return clubAbbreviation;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    // Used in event management class
    // Getters and setters for the to check if the user joined the class
    // To check if the user has already joined the club
    private boolean isUserJoinedClub;
    public void setIsUserJoinedClub(boolean userJoinedClub) {this.isUserJoinedClub = userJoinedClub;}

    public boolean getIsUserJoinedClub() {return isUserJoinedClub;}

    // To check the number of clubs the club advisor has joined
    private int userJoinedClubIndex;
    public void setUserJoinedClubIndex(int userJoinedClubIndex) {this.userJoinedClubIndex = userJoinedClubIndex;}
    public int getUserJoinedClubIndex() {return userJoinedClubIndex;}


    // Getter and setter for above declared variables for event table view
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventStartTime(Time eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public void setEventEndTime(Time eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public int getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public Time getEventStartTime() {
        return eventStartTime;
    }

    public Time getEventEndTime() {
        return eventEndTime;
    }


    // Getter and setter for above declared variables for attendance table view
//    public void setStudentFirstName(String studentFirstName) {
//        this.studentFirstName = studentFirstName;
//    }
//
//    public void setStudentLastName(String studentLastName) {
//        this.studentLastName = studentLastName;
//    }
//
//    public void setAttendanceCheckbox(CheckBox attendanceCheckbox) {
//        this.attendanceCheckbox = attendanceCheckbox;
//    }
//
//    public void setStudentID(String studentID) {
//        this.studentID = studentID;
//    }
//    public String getStudentID() {
//        return studentID;
//    }
//
//    public String getStudentFirstName() {
//        return studentFirstName;
//    }
//    public String getStudentLastName() {
//        return studentLastName;
//    }
//
//    public CheckBox getAttendanceCheckbox() {
//        return attendanceCheckbox;
//    }
}
