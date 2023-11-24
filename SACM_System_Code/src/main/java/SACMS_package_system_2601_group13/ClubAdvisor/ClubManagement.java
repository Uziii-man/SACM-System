package SACMS_package_system_2601_group13.ClubAdvisor;

import SACMS_package_system_2601_group13.Common.FindRecords;
import SACMS_package_system_2601_group13.Common.InsertRecords;
import SACMS_package_system_2601_group13.Common.SignIn;
import SACMS_package_system_2601_group13.Common.Validation;
import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClubManagement {
    @FXML
    private TextField clubNameTextField, clubAbbreviationTextField;
    @FXML
    private TextArea clubDescriptionTextArea;
    @FXML
    private Label clubNameErrorLabel,clubAbbreviationErrorLabel, clubDescriptionErrorLabel;

    // Building a new constructor
    MainController mainController = new MainController();
    Validation validation = new Validation();
    FindRecords findRecords = new FindRecords();
    SignIn signIn = new SignIn();


    // if the club advisor wants to create a club after navigating to club creation page
    @FXML
    protected void createClubOnActionButton(ActionEvent actionEvent) throws Exception {
        // setting up the club details using getters in the validation class
        validation.setName(clubNameTextField.getText());
        boolean isValidClubName = validation.nameValidator(clubNameErrorLabel, clubNameTextField, 10, 25);

        validation.setClubAbbreviation(clubAbbreviationTextField.getText());
        boolean isValidClubAbbreviation = validation.clubAbbreviationValidator(clubAbbreviationErrorLabel,
                clubAbbreviationTextField);

        validation.setClubDescription(clubDescriptionTextArea.getText());
        boolean isValidClubDescription = validation.clubDescriptionValidator(clubDescriptionErrorLabel, clubDescriptionTextArea);

        if(isValidClubName && isValidClubAbbreviation && isValidClubDescription){
            // if all the details are valid it will display an alert box showing all the details

            // add the details the club table in database
            InsertRecords clubRecords = new InsertRecords(validation.getName(), validation.getClubAbbreviation(), validation.getClubDescription());

            // add details into the club and club advisor relationship table
            InsertRecords clubAndAdvisorRecords = new InsertRecords(findRecords.getClubIdByClubName(validation.getName()), signIn.getUserID());

            // add an alert box
            // ???????????????

            // then navigate to the club advisor profile
            mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor");
        }

    }

    // if the club advisor wants to go back from the system
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor");
    }


    // if the club advisor wants to signOut from the system
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }

}
