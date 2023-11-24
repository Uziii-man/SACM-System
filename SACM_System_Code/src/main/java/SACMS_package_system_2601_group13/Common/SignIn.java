package SACMS_package_system_2601_group13.Common;

import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignIn {
    @FXML
    private TextField IDTextField, passwordTextField;
    @FXML
    private Label IDErrorLabel, passwordErrorLabel;

    // Getters and setters for the user ID
    private static String loginUserID;
    public String getLoginUserID() {return loginUserID;}
    public void setLoginUserID(String userID) {
        loginUserID = userID;
    }

    // Getters and setters for the user password
    private static String userPassword;
    public String getUserPassword() {return userPassword;}
    public void setUserPassword(String userPassword) {
        SignIn.userPassword = userPassword;
    }

    // Building new constructor methods for existing classes
    MainController mainController = new MainController();
    FindRecords findRecords = new FindRecords();

    // If the user clicks login button
    @FXML
    protected void loginOnActionButton(ActionEvent actionEvent) throws IOException {
        // Get the entered username and password
        setLoginUserID(IDTextField.getText());
        setUserPassword(passwordTextField.getText());

        // To check if the user is a club advisor or student
        // If the user has selected club advisor previously
        if (mainController.getUserProfileClubAdvisor()) {
            // Validate the username and password
            if(findRecords.validateLogin(getLoginUserID(), getUserPassword(), "club_advisor", "StaffID",
                    5, IDErrorLabel, passwordErrorLabel)){
                System.out.println("Club advisor login correct");

                mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml",
                    "Club Advisor Profile");
            }
        }
        // If the user has selected student previously
        else {
            if (findRecords.validateLogin(getLoginUserID(), getUserPassword(), "student", "StudentID",
                    6, IDErrorLabel, passwordErrorLabel)) {
                System.out.println("Student login correct");

                // direct to student profile after successful login
                mainController.navigateFunction(actionEvent, "Student_Profile.fxml", "Student Profile");
            }
        }
    }

    // if the user wants to go back
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        // If the user has selected club advisor previously
        if (mainController.getUserProfileClubAdvisor()) {
            mainController.navigateFunction(actionEvent, "SignIn_SignUp_Club_Advisor_Page.fxml",
                    "Club Advisor SignIn/SignUp");}
        // If the user has selected student previously
        else{
        mainController.navigateFunction(actionEvent, "SignIn_SignUp_Student_Page.fxml",
                "Student SignIn/SignUp");}
    }

    // If user wants to exit the program
    @FXML
    protected void exitOnActionButton() {System.exit(0);}
}
