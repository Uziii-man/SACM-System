package SACMS_package_system_2601_group13.Common;

import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SignUp extends Validation {
    @FXML
    private TextField firstNameTextField, lastNameTextField, staffIDTextField, studentIDTextField, gradeTextField,
            emailTextField, passwordTextField, rePasswordTextField;

    @FXML
    private Label firstNameErrorLabel, lastNameErrorLabel, staffIDErrorLabel, studentIDErrorLabel, gradeErrorLabel,
            emailErrorLabel, passwordErrorLabel, rePasswordErrorLabel, passwordMismatchErrorLabel;

    MainController mainController = new MainController();

    boolean commonValidationChecker;
    private void commonValidationChecker(){
        // Getting validation in terms of boolean from the UserValidator class
        commonValidationChecker = false;
        // first name validation
        setName(firstNameTextField.getText());
        boolean isValidFirstName = nameValidator(firstNameErrorLabel, firstNameTextField);
        // last name validation
        setName(lastNameTextField.getText());
        boolean isValidLastName = nameValidator(lastNameErrorLabel, lastNameTextField);
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
        setStaffID(staffIDTextField.getText());
        boolean isValidStaffID = IDValidator(staffIDErrorLabel, staffIDTextField, getStaffID());

        // Check if the primary key exists in the database in the club advisor table
        String primaryKey = staffIDTextField.getText();
        FindRecords findRecords = new FindRecords();
        boolean clubAdvisorPrimaryKeyExists = findRecords.isPrimaryKeyValid
                (primaryKey, "club_advisor", "StaffID", staffIDErrorLabel, staffIDTextField);

        System.out.print("end of club valid");
        // check if all the criteria are valid to signup
        if (commonValidationChecker && isValidStaffID && clubAdvisorPrimaryKeyExists){
            // Add an alert box here...
            //????????????????????????????

            System.out.println("validation success");
            // Add details and update the database
            InsertRecords insertRecords = new InsertRecords(staffIDTextField.getText(), firstNameTextField.getText(),
                    lastNameTextField.getText(), emailTextField.getText(), passwordTextField.getText());

            System.out.print("added success");

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
        setStudentID(studentIDTextField.getText());
        boolean isValidStudentID = IDValidator(studentIDErrorLabel, studentIDTextField, getStudentID());

        // grade validation
        setGrade(gradeTextField.getText());
        boolean isValidGrade = gradeValidator(gradeErrorLabel, gradeTextField);

        // Check if the primary key exists in the database in the student table
        String primaryKey = studentIDTextField.getText();
        FindRecords findRecords = new FindRecords();
        boolean studentPrimaryKeyExists = findRecords.isPrimaryKeyValid
                (primaryKey, "student", "StudentID", studentIDErrorLabel, studentIDTextField);

        if (commonValidationChecker && isValidStudentID && isValidGrade && studentPrimaryKeyExists) {
            // Add an alert box here...


            // Add details and update the database
            InsertRecords insertRecords = new InsertRecords(studentIDTextField.getText(), firstNameTextField.getText(),
                    lastNameTextField.getText(), gradeTextField.getText(), emailTextField.getText(),
                    passwordTextField.getText());


            // if all the entered details are correct it will navigate to the signIn Page to log to the profile
            mainController.navigateFunction(actionEvent, "SignIn_Page.fxml", "Student SignIn");
        }
    }


    // If the user wants to the back
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        // If the user has selected club advisor previously
        if (mainController.getUserProfileClubAdvisor()) {
            mainController.navigateFunction(actionEvent, "SignIn_SignUp_Club_Advisor_Page.fxml", "Club Advisor SignIn/SignUp");}
        // If the user has selected student previously
        else{
            mainController.navigateFunction(actionEvent, "SignIn_SignUp_Student_Page.fxml", "Student SignIn/SignUp");}
    }
    // If user clicks exit button
    @FXML
    protected void exitOnActionButton() {
        System.exit(0);}
}
