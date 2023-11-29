package SACMS_package_system_2601_group13.Common;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ViewAndJoinClubs {
    @FXML
    private Label viewClubErrorLabel;
    @FXML
    private TableView<TableViewEncapsulation> viewClubsTable;
    @FXML
    private TableColumn<TableViewEncapsulation, Integer> clubIDColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubDescriptionColumn;

    // Building new constructor methods for existing classes
    MainController mainController = new MainController();
    TableViewController tableViewController = new TableViewController();
    TableViewEncapsulation tableViewEncapsulation = new TableViewEncapsulation();
    ManageData manageData = new ManageData();
    SignIn signIn = new SignIn();

    // Getting the userID from the signIn class
    String userID = signIn.getLoginUserID();

    // To store the clubID
    private int clubID;

    // To store the join date
    Date joinDate;


    // To view the table when it loads using initialize method
    @FXML
    private void initialize() {
        tableViewController.viewTable(viewClubsTable, clubIDColumn, clubNameColumn, clubDescriptionColumn,
                get2DArrayDataForViewJoinClub());
    }

    // Array list
    public ArrayList<ArrayList<Object>> get2DArrayDataForViewJoinClub() {
        ArrayList<ArrayList<Object>> clubDetailsList = new ArrayList<>();
        // Query the database and get the data -> clubDetailsList
        String query = "SELECT * FROM club";

        // Execute the provided query to get the data
        ArrayList<ArrayList<Object>> clubDetails2DArray = manageData.get2DArrayData(query);

        // Extract specific columns (0, 1, and 4) and form a new 2D ArrayList
        for (ArrayList<Object> row : clubDetails2DArray) {
            ArrayList<Object> newRow = new ArrayList<>(Arrays.asList(row.get(0), row.get(1), row.get(4)));
            clubDetailsList.add(newRow);
        }
        return clubDetailsList;
    }

    // To check if the user has already joined the club and the number of clubs the user has joined
    boolean userJoinedClub;
    int userClubIndex;
    private void isUserJoinedClub(String clubAndUserQuery) {
        // To check if the user has already joined the club
        // Get the corresponding clubID and UserID from the club and user relation table
        ArrayList<ArrayList<Object>> clubAndUserList = manageData.get2DArrayData(clubAndUserQuery);

        // To check if the user has already joined the club
        userJoinedClub = false;
        userClubIndex = 0;
        for (ArrayList<Object> clubAndUser : clubAndUserList) {
            // To check if the user has already joined the club
            if (clubAndUser.get(1).equals(userID)) {
                // To calculate the number of clubs the club advisor has joined
                userClubIndex++;
                // In here value is converted to string to compare because the value is an object
                // Value is Got in integer
                if (clubAndUser.get(0).equals(clubID)) {
                    // The user has already joined the club
                    userJoinedClub = true;
                    break;
                }
            }
        }
        tableViewEncapsulation.setIsUserJoinedClub(userJoinedClub);
        tableViewEncapsulation.setUserJoinedClubIndex(userClubIndex);
    }


  // Method to set label colors and texts
    private void setLabelProperties(Color color, String text) {
        viewClubErrorLabel.setTextFill(color);
        viewClubErrorLabel.setText(text);
    }


    // If the club advisor wants to view all the available club and details in a table and join
    private void joinClubAsAdvisor() {
        // To check if the club advisor has already joined the club
        // Get the corresponding clubID and staffID from the club and club advisor relation table
        String clubAndClubAdvisorQuery = "SELECT * FROM club_and_club_advisor";

        // To check if the club advisor has already joined the club and the number of clubs the club advisor has joined
        isUserJoinedClub(clubAndClubAdvisorQuery);

        // To check if the club advisor has not joined the club
        if (tableViewEncapsulation.getIsUserJoinedClub()) {
            // for the club advisor has already joined the club
            setLabelProperties(Color.RED , "You have already joined the club");
        } else if (tableViewEncapsulation.getUserJoinedClubIndex() >= 3) {
            // for the club advisor has already joined 3 clubs
            setLabelProperties(Color.RED , "You have already joined 3 clubs");
        } else {
            // for the club advisor has not joined the club
            // Add the data to the array list
            ArrayList<Object> clubAdvisorClubDetails = new ArrayList<>(Arrays.asList(
                    clubID,
                    userID,
                    joinDate));

            // Query for club and club advisor relation table to inset data
            String clubAndClubAdvisorDataInsertQuery = "Insert INTO club_and_club_advisor (ClubID, StaffID, JoinDate) VALUES (?, ?, ?)";
            manageData.insertData(clubAdvisorClubDetails, clubAndClubAdvisorDataInsertQuery);

            // Setting the error label
            setLabelProperties(Color.GREEN , "You have joined the club successfully");
        }
    }


    // If the student wants to view all the available club and details in a table and join
    private void joinClubAsStudent() {
        // To check if the student has already joined the club
        // Get the corresponding clubID and studentID from the club and student relation table
        String clubAndStudentQuery = "SELECT * FROM club_and_student";

        // To check if the student has already joined the club
        isUserJoinedClub(clubAndStudentQuery);

        // To check if the student has not joined the club
        if (tableViewEncapsulation.getIsUserJoinedClub()) {
            // for the student has already joined the club
            setLabelProperties(Color.RED , "You have already joined the club");
        } else {
            // for the student has not joined the club
            // Add the data to the array list
            ArrayList<Object> studentClubDetails = new ArrayList<>(Arrays.asList(
                    clubID,
                    userID,
                    joinDate));

            // Query for club and student relation table to inset data
            String clubAndStudentDataInsertQuery = "Insert INTO club_and_student (ClubID, StudentID, JoinDate) VALUES (?, ?, ?)";
            manageData.insertData(studentClubDetails, clubAndStudentDataInsertQuery);

            // Setting the error label
            setLabelProperties(Color.GREEN , "You have joined the club successfully");
        }
    }


    // If the club advisor or student wants to join a club
    @FXML
    protected void joinClubOnActionButton(ActionEvent action){
        // To get the selected row
        TableView.TableViewSelectionModel<TableViewEncapsulation> selectionModel = viewClubsTable.getSelectionModel();
        if (selectionModel.isEmpty()) {
            // The club is not selected to join
            setLabelProperties(Color.RED , "Select a row to join the club");
        } else {
            // To get the selected and insert row
            TableViewEncapsulation selectedClub = viewClubsTable.getSelectionModel().getSelectedItem();
            // Getting the clubID from the selected row
            clubID = selectedClub.getClubID();

            // Creating local date time to get the current date
            // Get the current date in the local desktop's time zone
            LocalDate currentDate = LocalDate.now();
            // Convert to java.sql.Date for insertion into the database
            joinDate = Date.valueOf(currentDate);

            // To check if the user is a club advisor or student
            if (mainController.getUserProfileClubAdvisor()) {
                // If the user is a club advisor
                joinClubAsAdvisor();
            }
            else {
                // If the user is a student
                joinClubAsStudent();
            }
        }
    }

    // If the club advisor wants to signOut from the system
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }

    // If the user wants to move back to the previous page
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        // if the user is a club advisor
         if (mainController.getUserProfileClubAdvisor()) {
            mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor Profile");}
         else {
             // if the user is a student
            mainController.navigateFunction(actionEvent, "Student_Profile.fxml", "Student Profile");}
    }
}

