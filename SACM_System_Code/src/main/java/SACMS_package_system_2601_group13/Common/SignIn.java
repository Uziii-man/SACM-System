package SACMS_package_system_2601_group13.Common;

import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

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
    private static String loginUserPassword;
    public String getUserPassword() {return loginUserPassword;}
    public void setUserPassword(String userPassword) {
        SignIn.loginUserPassword = userPassword;
    }

    // Building new constructor methods for existing classes
    MainController mainController = new MainController();
    ManageData manageData = new ManageData();

    // To check login credentials
    private boolean validLogin(String querySearch, Label IDErrorLabel, Label passwordErrorLabel, TextField IDTextField,
                               TextField passwordTextField){
        // To store the login details that comes from the database
        ArrayList<ArrayList<Object>> loginDetails = manageData.get2DArrayData(querySearch);
        boolean validPassword = false;
        boolean validUser = false;
        // To check if the user ID exists
        for (ArrayList<Object> loginDetail : loginDetails) {
            if (loginDetail.get(0).equals(loginUserID)) {
                // If the user ID exists
                validUser = true;
                if (loginDetail.get(1).equals(loginUserPassword)) {
                    // Password is correct
                    validPassword = true;
                }
                // To break the loop if the user ID is found cause there is no point of checking the rest
                break;
            }
        }
        // To display the error messages
        if (validUser) {
            // If the login is valid
            IDErrorLabel.setTextFill(Color.GREEN);
            IDErrorLabel.setText("ID Correct");
        }else{
            // If the login is invalid
            IDErrorLabel.setTextFill(Color.RED);
            IDErrorLabel.setText("ID does not Exist");
            IDTextField.clear();
        }
        if (!validPassword) {
            // If the password is invalid
            passwordErrorLabel.setTextFill(Color.RED);
            passwordErrorLabel.setText("Incorrect Password");
            passwordTextField.clear();
        }
        // Return the boolean value if the login is valid or not
        return validPassword;
    }

    // If the user clicks login button
    @FXML
    protected void loginOnActionButton(ActionEvent actionEvent) throws IOException {
        // Get the entered username and password
        setLoginUserID(IDTextField.getText());
        setUserPassword(passwordTextField.getText());

        // To check if the user is a club advisor or student
        // If the user has selected club advisor previously
        if (mainController.getUserProfileClubAdvisor()) {
            // Query to search for the userID and Password in the database
             String queryClubAdvisorSearch = "SELECT StaffID, Password FROM club_advisor";

            // Validate the username and password of the club advisor
            if(validLogin(queryClubAdvisorSearch, IDErrorLabel, passwordErrorLabel, IDTextField, passwordTextField)) {
                System.out.println("Club advisor login correct");

                // Direct to club advisor profile after successful login
                mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml",
                        "Club Advisor Profile");
            }
        }
        // If the user has selected student previously
        else {
            String queryStudentSearch = "SELECT StudentID, Password FROM student";
            if(validLogin(queryStudentSearch, IDErrorLabel, passwordErrorLabel, IDTextField, passwordTextField)) {
                System.out.println("Student login correct");

                // Direct to student profile after successful login
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
