package SACMS_package_system_2601_group13.Common;

import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class SignUp extends Validation {
    @FXML
    private TextField firstNameTextField, lastNameTextField, staffIDTextField, studentIDTextField, gradeTextField,
            emailTextField, passwordTextField, rePasswordTextField;

    @FXML
    private Label firstNameErrorLabel, lastNameErrorLabel, staffIDErrorLabel, studentIDErrorLabel, gradeErrorLabel,
            emailErrorLabel, passwordErrorLabel, rePasswordErrorLabel, passwordMismatchErrorLabel;

    MainController mainController = new MainController();
    DatabaseManager databaseManager= new DatabaseManager();

    boolean commonValidationChecker;
    private void commonValidationChecker(){
        // Getting validation in terms of boolean from the UserValidator class
        commonValidationChecker = false;
        // first name validation
        setName(firstNameTextField.getText());
        boolean isValidFirstName = nameValidator(firstNameErrorLabel, firstNameTextField, 3, 10);
        // last name validation
        setName(lastNameTextField.getText());
        boolean isValidLastName = nameValidator(lastNameErrorLabel, lastNameTextField, 3, 10);
        // email validation
        setEmail(emailTextField.getText());
        boolean isValidEmail = emailValidator(emailErrorLabel, emailTextField);
        // password validation
        setPassword(passwordTextField.getText());
        boolean isValidPassword = passwordValidator(passwordErrorLabel, passwordTextField);
        // rePassword validation
        setPassword(rePasswordTextField.getText());
        boolean isValidRePassword = passwordValidator(rePasswordErrorLabel, rePasswordTextField);

        // Checking whether if both passwords are same
        boolean isBothPasswordSame = false;
        if (passwordTextField.getText().equals(rePasswordTextField.getText())) {
            isBothPasswordSame = true;
            passwordMismatchErrorLabel.setTextFill(Color.GREEN);
            passwordMismatchErrorLabel.setText("Password Matches");
        } else {
            passwordMismatchErrorLabel.setTextFill(Color.RED);
            passwordMismatchErrorLabel.setText("Both Password Doesn't Matches");
            passwordTextField.clear();
            rePasswordTextField.clear();
        }
        if (isValidFirstName && isValidLastName  && isValidEmail && isValidPassword && isValidRePassword && isBothPasswordSame) {
            commonValidationChecker = true;
        }
    }

    // If user clicks register button for club advisor
    @FXML
    protected void registerAsAdvisorOnActionButton(ActionEvent actionEvent) throws Exception{
        // Getting validation in terms of boolean from the user validator
        commonValidationChecker();

        // validation for the staff ID
        setUserID(staffIDTextField.getText());
        String clubAdvisorIDPattern = "stf\\d{4}";
        boolean isValidStaffID = IDValidator(staffIDErrorLabel, staffIDTextField, clubAdvisorIDPattern,
                7 , "club_advisor", "StaffID");

        System.out.print("end of club valid");
        // check if all the criteria are valid to signup
        if (commonValidationChecker && isValidStaffID){
            String[] clubAdvisorHeader = {"Staff ID", "First Name", "Last Name", "Email", "Password"};
            // Query for club advisor table to insert data
            String insertQuery = "INSERT INTO club_advisor (StaffID, FirstName, LastName, Email, Password) " +
                    "VALUES (?, ?, ?, ?, ?)";
            // Adding details into an arraylist
            ArrayList<Object> clubAdvisorDetailsArrayList = new ArrayList<>(Arrays.asList(
                    staffIDTextField.getText(),
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    emailTextField.getText(),
                    passwordTextField.getText()));

            // Add details and update the database
            InsertRecords insertClubAdvisorRecords = new InsertRecords(clubAdvisorDetailsArrayList, insertQuery);

            // Alert box
            databaseManager.userCreateAlertFunctionBox(clubAdvisorHeader, clubAdvisorDetailsArrayList,
                    "ClubAdvisor SignUp ", "You have SignUp as Club Advisor Successfully");

            // No need to reset all the text area and label cause ones it moves to another pane it automatically resets.
            // if all the entered details are correct it will navigate to the signIn Page to log to the profile
            mainController.navigateFunction(actionEvent, "SignIn_Page.fxml", "Club Advisor SignIn");
        }
    }

    // If user clicks register button for student
    @FXML
    protected void registerAsStudentOnActionButton(ActionEvent actionEvent) throws Exception {
        // Getting validation in terms of boolean from the user validator
        commonValidationChecker();

        // validation for the student ID
        setUserID(studentIDTextField.getText());
        String studentIDPattern = "stu\\d{5}";
        boolean isValidStudentID = IDValidator(studentIDErrorLabel, studentIDTextField, studentIDPattern,
                8, "student", "StudentID");

        // grade validation
        setGrade(gradeTextField.getText());
        boolean isValidGrade = gradeValidator(gradeErrorLabel, gradeTextField);

        // If all the details entered are correct
        if (commonValidationChecker && isValidStudentID && isValidGrade) {
            String[] studentHeader = {"Staff ID", "First Name", "Last Name", "Grade", "Email", "Password"};
            // Query for student table to insert data
            String insertQuery = "INSERT INTO student (StudentID, FirstName, LastName, Grade, Email, Password) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            // Adding student details to an arraylist
            ArrayList<Object> studentDetailsArrayList = new ArrayList<>(Arrays.asList(
                    studentIDTextField.getText(),
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    gradeTextField.getText(),
                    emailTextField.getText(),
                    passwordTextField.getText()));

            // Add details and update the database
            InsertRecords insertStudentRecords = new InsertRecords(studentDetailsArrayList , insertQuery);

            // Alert box
            databaseManager.userCreateAlertFunctionBox(studentHeader, studentDetailsArrayList,
                    "Club Advisor SignUp Alert Box", "You have SignUp as Student Successfully");

            // if all the entered details are correct it will navigate to the signIn Page to log to the profile
            mainController.navigateFunction(actionEvent, "SignIn_Page.fxml", "Student SignIn");
        }
    }

    // If the user wants to the back
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
    // If user clicks exit button
    @FXML
    protected void exitOnActionButton() {
        System.exit(0);}
}
