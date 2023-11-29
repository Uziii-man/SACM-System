package SACMS_package_system_2601_group13.User;

import SACMS_package_system_2601_group13.Common.ManageData;
import SACMS_package_system_2601_group13.Common.SignIn;
import SACMS_package_system_2601_group13.Common.SignUp;
import SACMS_package_system_2601_group13.Common.Validation;
import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ClubAdvisor extends Validation implements Person{
    // Declared variables for the club advisor profile management page
    @FXML
    private TextField firstNameTextField, lastNameTextField, emailTextField, passwordTextField,
            rePasswordTextField;

    @FXML
    private Label firstNameErrorLabel, lastNameErrorLabel, emailErrorLabel, passwordErrorLabel, rePasswordErrorLabel,
            passwordMismatchErrorLabel;


    // Building new constructor methods for existing classes
    MainController mainController = new MainController();
    ManageData manageData = new ManageData();
    SignIn signIn = new SignIn();
    SignUp signUp = new SignUp();

    // Method to set label colors and texts
    private void setLabelProperties(Label label, Color color, String text) {
        label.setTextFill(color);
        label.setText(text);
    }


    // If the club advisor wants to view all the available club and details in a table
    @FXML
    protected void viewClubsOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "View_Join_Clubs.fxml", "SACM System");
    }


    // If the club advisor wants to create a club
    @FXML
    protected void createClubProfileOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Create_Club.fxml", "Club Creation");
    }


    // If the club advisor wants to manage a clubs that who is joined as an advisor
    @FXML
    protected void clubManageOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Management.fxml", "Club Management");
    }


    // If the club advisor wants to edit his or her profile
    @FXML
    protected void profileManageOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Advisor_Profile_Manager.fxml",
                "Club Advisor Profile Management");
    }


    // if the club advisor wants to signOut from the system
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Club Manager Profile Management
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean nameValidator(Label labelName) {
        return false;
    }

    // To load the student data to the text fields
    @FXML
    protected void loadClubAdvisorDetailsOnActionButton(ActionEvent event){
        loadDetailsToTextFields();
    }


    @Override
    public void loadDetailsToTextFields(){
        // Query to get the student details
        String queryStudentDetails = "SELECT * FROM club_advisor";
        ArrayList<ArrayList<Object>> clubAdvisorDetailsList = manageData.get2DArrayData(queryStudentDetails);

        for (ArrayList<Object> clubDetails : clubAdvisorDetailsList){
            if (clubDetails.get(0).equals(signIn.getLoginUserID())){
                firstNameTextField.setText((String) clubDetails.get(1));
                lastNameTextField.setText((String) clubDetails.get(2));
                emailTextField.setText((String) clubDetails.get(3));
                passwordTextField.setText((String) clubDetails.get(4));
                rePasswordTextField.setText((String) clubDetails.get(4));
                break;
            }
        }
    }

    boolean isValidCommonValidator;
    String clubAdvisorFirstName;
    String clubAdvisorLastName;
    String clubAdvisorEmail;
    String clubAdvisorPassword;
    String clubAdvisorRePassword;
    // To check if all the details entered are correct
    @Override
    public boolean commonValidator() {
        isValidCommonValidator = false;
        // Get the entered new details
        // first name validation
        clubAdvisorFirstName = firstNameTextField.getText();
        signUp.setName(clubAdvisorFirstName);
        boolean clubAdvisorFirstNameValidation = signUp.nameValidator(firstNameErrorLabel);

        // last name validation
        clubAdvisorLastName = lastNameTextField.getText();
        signUp.setName(clubAdvisorLastName);
        boolean clubAdvisorLastNameValidation = nameValidator(lastNameErrorLabel);

        // email validation
        clubAdvisorEmail = emailTextField.getText();
        setEmail(clubAdvisorEmail);
        boolean clubAdvisorEmailValidation = emailValidator(emailErrorLabel, emailTextField);

        // password validation
        clubAdvisorPassword = passwordTextField.getText();
        setPassword(clubAdvisorPassword);
        boolean clubAdvisorPasswordValidation = passwordValidator(passwordErrorLabel, passwordTextField);

        // re-password validation
        clubAdvisorRePassword = rePasswordTextField.getText();
        setPassword(clubAdvisorRePassword);
        boolean clubAdvisorRePasswordValidation = passwordValidator(rePasswordErrorLabel, rePasswordTextField);

        // To check if the password and re-entered password are same
        // Checking whether if both passwords are same
        boolean isBothPasswordSame = false;
        if (passwordTextField.getText().equals(rePasswordTextField.getText())) {
            isBothPasswordSame = true;
            setLabelProperties(passwordMismatchErrorLabel, Color.DARKGREEN, "Password Matches");
        } else {
            setLabelProperties(passwordMismatchErrorLabel, Color.RED, "Both Password Doesn't Matches");
        }

        // If all the details added are correct in the common validator
        if (clubAdvisorFirstNameValidation && clubAdvisorLastNameValidation && clubAdvisorEmailValidation
                && clubAdvisorPasswordValidation && clubAdvisorRePasswordValidation && isBothPasswordSame) {
            // If all the details are correct the common validator will be true
            isValidCommonValidator = true;
        }

        return isValidCommonValidator;
    }


    // If the club advisor wants to update the details of the profile
    @FXML
    protected void updateClubAdvisorOnActionButton(ActionEvent actionEvent) throws Exception {
        if(commonValidator()){
         // Query to update the club advisor details
            String queryUpdateClubAdvisorDetails = "UPDATE club_advisor SET FirstName = '" + clubAdvisorFirstName
                    + "', LastName = '" + clubAdvisorLastName + "', Email = '" + clubAdvisorEmail + "', Password = '"
                    + clubAdvisorPassword + "' WHERE StaffID = '" + signIn.getLoginUserID() + "'";
            // Update the details in the database
            manageData.modifyData(queryUpdateClubAdvisorDetails);

            // Alert box to show the details are updated successfully
            manageData.alertFunctionBox("Club Advisor Profile Update", "Club Advisor Profile Updated Successfully",
                    "Details Updated Successfully in Database of the Club Advisor Profile");

            // If everything is correct the details will update and navigate into sign in page of the student
            mainController.navigateFunction(actionEvent, "SignIn_Page.fxml", "Club Advisor SignIn");
        }
    }


    // If the club advisor wants to go back to club advisor profile
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor");
    }
}
