package SACMS_package_system_2601_group13.ClubAdvisor;

import SACMS_package_system_2601_group13.Common.*;
import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ClubManagement {
    @FXML
    private TextField clubNameTextField, clubAbbreviationTextField;
    @FXML
    private TextArea clubDescriptionTextArea;
    @FXML
    private Label clubNameErrorLabel,clubAbbreviationErrorLabel, clubDescriptionErrorLabel;

    // Building a new constructor
    MainController mainController = new MainController();
    UserValidation validation = new UserValidation();
    FindRecords findRecords = new FindRecords();
    DatabaseManager databaseManager = new DatabaseManager();
    ManageData manageData = new ManageData();
    ClubValidation clubValidation = new ClubValidation();
    SignIn signIn = new SignIn();

    // If the club advisor wants to create a club after navigating to club creation page
    @FXML
    protected void createClubOnActionButton(ActionEvent actionEvent) throws Exception {
        // setting up the club details using getters in the validation class
        validation.setName(clubNameTextField.getText());
        boolean isValidClubName = validation.nameValidator(clubNameErrorLabel, clubNameTextField, 10, 25);

        clubValidation.setClubAbbreviation(clubAbbreviationTextField.getText());
        boolean isValidClubAbbreviation = clubValidation.clubAbbreviationValidator(clubAbbreviationErrorLabel,
                clubAbbreviationTextField);

        clubValidation.setClubDescription(clubDescriptionTextArea.getText());
        boolean isValidClubDescription = clubValidation.clubDescriptionValidator(clubDescriptionErrorLabel, clubDescriptionTextArea);

        // If all the details added are correct
        if(isValidClubName && isValidClubAbbreviation && isValidClubDescription){
            // Get the current date in the local desktop's time zone
            LocalDate currentDate = LocalDate.now();
            // Convert to java.sql.Date for insertion into the database
            Date ClubOriginDate = Date.valueOf(currentDate);

            // For the club table
            // Query for club table to insert data
            String clubDataInsertQuery = "INSERT INTO club (ClubName, ClubAbbreviation, ClubOriginDate, ClubDescription) VALUES (?, ?, ?, ?)";

            // Adding club details into an arraylist
            ArrayList<Object> clubDetailsArrayList = new ArrayList<>(Arrays.asList(
                    clubNameTextField.getText(),
                    clubAbbreviationTextField.getText(),
                    ClubOriginDate,
                    clubDescriptionTextArea.getText()));

            // Add details and update the database of the club table
            manageData.insertData(clubDetailsArrayList, clubDataInsertQuery);

            // For the club and club advisor relation table
            // Query for club and club advisor relation table to insert data
            String club_and_advisorDataInsertQuery = "INSERT INTO club_and_club_advisor (ClubID, StaffID, JoinDate) VALUES (?, ?, ?)";

            ArrayList<Object> club_clubAdvisorDetailsArrayList = new ArrayList<>(Arrays.asList(
                    findRecords.getClubIdByClubName(validation.getName()),
                    signIn.getLoginUserID(),
                    ClubOriginDate));

            // Add details into the club and club advisor relationship table
            manageData.insertData(club_clubAdvisorDetailsArrayList, club_and_advisorDataInsertQuery);

            // Alert box
            // if all the details are valid it will display an alert box showing all the details
            String[] clubHeader = {"Club Name" ,"Club Abbreviation", "Club Origin Date", "Club Description"};

            databaseManager.userCreateAlertFunctionBox(clubHeader, clubDetailsArrayList,
                    "Club Creation", "You have created a club successfully");

            // Then navigate to the club advisor profile
            mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor");
        }
    }

    // If the club advisor wants to go back from the system
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor");
    }


    // If the club advisor wants to signOut from the system
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }

}
