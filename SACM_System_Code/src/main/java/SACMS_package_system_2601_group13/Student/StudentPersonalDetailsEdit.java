package SACMS_package_system_2601_group13.Student;

import SACMS_package_system_2601_group13.Common.SignUp;
import SACMS_package_system_2601_group13.Common.Validation;
import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StudentPersonalDetailsEdit extends Validation {


    @FXML
    private TextField firstNameTextField, lastNameTextField, gradeTextField, emailTextField, passwordTextField,
            rePasswordTextField;

    @FXML
    private Label firstNameErrorLabel, lastNameErrorLabel, gradeErrorLabel, emailErrorLabel, passwordErrorLabel,
            rePasswordErrorLabel, passwordMismatchErrorLabel, UserIDLabel;


    MainController mainController = new MainController();
    SignUp signUp = new SignUp();
    // If the user wants to update the details of his profile from the profile
    @FXML
    protected void updateOnActionButton(ActionEvent actionEvent) throws Exception {

//        signUp.commonValidationChecker();

        // grade validation
        setGrade(gradeTextField.getText());
        boolean isValidGrade = gradeValidator(gradeErrorLabel, gradeTextField);


        // if everything is correct the details will update and navigate into sign in page of the student
        mainController.navigateFunction(actionEvent, "SignIn_Page.fxml", "Student SignIn");
    }


    // If the user wants to signOut from the profile
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }

    // if the user wants to go to the back menu
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }

    @Override
    public boolean nameValidator(Label labelName, TextField textFieldName) {
        return false;
    }
}