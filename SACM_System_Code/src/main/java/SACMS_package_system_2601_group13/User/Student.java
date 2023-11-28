package SACMS_package_system_2601_group13.User;

import SACMS_package_system_2601_group13.Common.SignIn;
import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Student extends Person {
    // Declared variables for the student profile view page
    @FXML
    private Label userIDLabelProfileView;

    // Declared variables for the student profile management page
    @FXML
    private Label firstNameErrorLabel, lastNameErrorLabel, gradeErrorLabel, emailErrorLabel, passwordErrorLabel,
            rePasswordErrorLabel, passwordMismatchErrorLabel, UserIDLabel;
    @FXML
    private TextField firstNameTextField, lastNameTextField, gradeTextField, emailTextField, passwordTextField,
            rePasswordTextField;;
    @FXML
    private TableView<?> studentLeaveClubTable;
    @FXML
    private TableColumn<?, ?> studentClubIDColumn;
    @FXML
    private TableColumn<?, ?> studentClubNameColumn;
    @FXML
    private TableColumn<?, ?> studentClubDescriptionColumn;



    // Object Instantiation for the classes
    MainController mainController = new MainController();

    //
    public Student(String userID, String password, String firstName, String lastName) {
        super(userID, password, firstName, lastName);
    }

    // if the student wants view all the available club
    @FXML
    protected void viewClubsOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "View_Join_Clubs.fxml", "View and Join Clubs");
    }


    // if the student wants to view upcoming events
    @FXML
    protected void studentViewEventsOnActionButton(ActionEvent actionEvent) throws Exception {

    }

    // if the student wants to manage profile (this code directs to the student profile management page)
    @FXML
    protected void studentProfileManagementOnActionButton(ActionEvent actionEvent) throws Exception {

    }

    // If the student wants to edit details --> profile management page
    @FXML
    protected void studentUpdateDetailsOnActionButton(ActionEvent event) {

    }

    // if the student wants leave a club --> profile management page
    @FXML
    protected void studentLeaveClubOnActionButton(ActionEvent actionEvent) throws Exception {

    }



    // Redirect to the student profile page
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Student_Profile.fxml", "Student Profile");

    }


    // Redirect to the main selection page
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }


    // If the user wants to exit
    @FXML
    protected void exitOnActionButton() {System.exit(0);}
}
