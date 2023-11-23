package SACMS_package_system_2601_group13.Common;

import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SignUp extends UserValidation {
    @FXML
    private TextField firstNameTextArea, lastNameTextArea, staffIDTextArea, studentIDTextArea, gradeTextArea,
    emailTextArea, passwordTextArea, rePasswordTextArea;

    @FXML
    private Label firstNameErrorLabel, lastNameErrorLabel, staffIDErrorLabel, studentIDErrorLabel, gradeErrorLabel,
            emailErrorLabel, passwordErrorLabel, rePasswordErrorLabel, passwordMismatchErrorLabel;

    MainController mainController = new MainController();

    boolean commonValidationChecker;
    private void commonValidationChecker(){
        // Getting validation in terms of boolean from the UserValidator class
        commonValidationChecker = false;
        // first name validation
        setName(firstNameTextArea.getText());
        boolean isValidFirstName = nameValidator(firstNameErrorLabel, firstNameTextArea);
        // last name validation
        setName(lastNameTextArea.getText());
        boolean isValidLastName = nameValidator(lastNameErrorLabel, lastNameTextArea);
        // email validation
        setEmail(emailTextArea.getText());
        boolean isValidEmail = emailValidator(emailErrorLabel, emailTextArea);
        // password validation
        setPassword(passwordTextArea.getText());
        boolean isValidPassword = passwordValidator(passwordErrorLabel, passwordTextArea);
        // rePassword validation
        setPassword(rePasswordTextArea.getText());
        boolean isValidRePassword = passwordValidator(rePasswordErrorLabel, rePasswordTextArea);

        // Checking whether if both passwords are same
        boolean isBothPasswordSame = false;
        if (passwordTextArea.getText().equals(rePasswordTextArea.getText())) {
            isBothPasswordSame = true;
            passwordMismatchErrorLabel.setTextFill(Color.GREEN);
            passwordMismatchErrorLabel.setText("Password Matches");
        } else {
            passwordMismatchErrorLabel.setTextFill(Color.RED);
            passwordMismatchErrorLabel.setText("Both Password Doesn't Matches");
            passwordTextArea.clear();
            rePasswordTextArea.clear();
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
        setStaffID(staffIDTextArea.getText());
        boolean isValidStaffID = IDValidator(staffIDErrorLabel, staffIDTextArea, getStaffID());

        // Check if the primary key exists in the database in the club advisor table
        String primaryKey = staffIDTextArea.getText();
        FindRecords findRecords = new FindRecords();
        boolean clubAdvisorPrimaryKeyExists = findRecords.isPrimaryKeyValid
                (primaryKey, "club_advisor", "StaffID", staffIDErrorLabel, staffIDTextArea);

        System.out.print("end of club valid");
        // check if all the criteria are valid to signup
        if (commonValidationChecker && isValidStaffID && clubAdvisorPrimaryKeyExists){
            // Add an alert box here...
            //????????????????????????????

            System.out.println("validation success");
            // Add details and update the database
            InsertRecords insertRecords = new InsertRecords(staffIDTextArea.getText(), firstNameTextArea.getText(),
                    lastNameTextArea.getText(), emailTextArea.getText(), passwordTextArea.getText());

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
        setStudentID(studentIDTextArea.getText());
        boolean isValidStudentID = IDValidator(studentIDErrorLabel, studentIDTextArea, getStudentID());

        // grade validation
        setGrade(gradeTextArea.getText());
        boolean isValidGrade = gradeValidator(gradeErrorLabel, gradeTextArea);

        // Check if the primary key exists in the database in the student table
        String primaryKey = studentIDTextArea.getText();
        FindRecords findRecords = new FindRecords();
        boolean studentPrimaryKeyExists = findRecords.isPrimaryKeyValid
                (primaryKey, "student", "StudentID", studentIDErrorLabel, studentIDTextArea);

        if (commonValidationChecker && isValidStudentID && isValidGrade && studentPrimaryKeyExists) {
            // Add an alert box here...


            // Add details and update the database
            InsertRecords insertRecords = new InsertRecords(studentIDTextArea.getText(), firstNameTextArea.getText(),
                    lastNameTextArea.getText(), gradeTextArea.getText(),emailTextArea.getText(),
                    passwordTextArea.getText());


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
