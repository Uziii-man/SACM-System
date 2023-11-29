package SACMS_package_system_2601_group13.User;

import SACMS_package_system_2601_group13.Common.ManageData;
import SACMS_package_system_2601_group13.Common.SignIn;
import SACMS_package_system_2601_group13.Common.SignUp;
import SACMS_package_system_2601_group13.Common.Validation;
import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class Student extends Validation implements Person {
    // Declared variables for the student profile view page
    @FXML
    private Label userIDLabelProfileView,studentProfileManagementLeaveClubErrorLabel;

    // Declared variables for the student profile management page
    @FXML
    private Label firstNameErrorLabel, lastNameErrorLabel, gradeErrorLabel, emailErrorLabel, passwordErrorLabel,
            rePasswordErrorLabel, passwordMismatchErrorLabel, UserIDLabel;
    @FXML
    private TextField firstNameTextField, lastNameTextField, gradeTextField, emailTextField, passwordTextField,
            rePasswordTextField;;
    @FXML
    private TableView<TableViewEncapsulation> studentLeaveClubTable;
    @FXML
    private TableColumn<TableViewEncapsulation, Integer> studentClubIDColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> studentClubNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> studentClubDescriptionColumn;


    // Object Instantiation for the classes
    MainController mainController = new MainController();
    TableViewController tableViewController = new TableViewController();
    SignIn signIn = new SignIn();
    SignUp signUp = new SignUp();
    ManageData manageData = new ManageData();


    // Method to set label colors and texts
    private void setLabelProperties(Label label, Color color, String text) {
        label.setTextFill(color);
        label.setText(text);
    }


    // if the student wants view all the available club
    @FXML
    protected void viewClubsOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "View_Join_Clubs.fxml", "View and Join Clubs");
    }


    // if the student wants to view upcoming events
    @FXML
    protected void studentViewEventsOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Events.fxml", "Upcoming Events");
    }

    // if the student wants to manage profile (this code directs to the student profile management page)
    @FXML
    protected void studentProfileManagementOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Student_Profile_Manager.fxml", "Student Profile Management");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Student Profile Management Page
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Creating runnable object to execute the object frequently and assigned using lambda
    Runnable viewJoinedClubs = () -> tableViewController.viewTable(studentLeaveClubTable, studentClubIDColumn,
            studentClubNameColumn, studentClubDescriptionColumn, get2DArrayDataForStudentClub());


    // In here name validation is not needed because the name is already validated by SignUp class
    @Override
    public boolean nameValidator(Label labelName) {
        return false;
    }

    // To get the data to display in the student clubs in the table view
    public ArrayList<ArrayList<Object>> get2DArrayDataForStudentClub() {
        ArrayList<ArrayList<Object>> clubDetailsList = new ArrayList<>();
        // Query the database and get the data -> clubDetailsList
        String queryClubDetails = "SELECT * FROM club";
        String queryStudentClub = "SELECT * FROM club_and_student";

        // Execute the provided query to get the data
        ArrayList<ArrayList<Object>> clubDetails2DArray = manageData.get2DArrayData(queryClubDetails);
        ArrayList<ArrayList<Object>> studentClub2DArray = manageData.get2DArrayData(queryStudentClub);

        String studentID = signIn.getLoginUserID();

        for(ArrayList<Object> studentDetails : studentClub2DArray){
            if (studentDetails.get(1).equals(studentID)){
                for(ArrayList<Object> clubDetails : clubDetails2DArray){
                    if (studentDetails.get(0).equals(clubDetails.get(0))){
                        ArrayList<Object> newRow = new ArrayList<>(Arrays.asList(clubDetails.get(0), clubDetails.get(1), clubDetails.get(4)));
                        clubDetailsList.add(newRow);
                        break;
                    }
                }
            }
        }
        return clubDetailsList;
    }


    // To load the student joined table
    @FXML
    protected void loadTableOnActionButton() {
        // To load the event management table
        viewJoinedClubs.run();
    }


    // if the student wants leave a club
    @FXML
    protected void studentLeaveClubOnActionButton(ActionEvent actionEvent){
        TableView.TableViewSelectionModel<TableViewEncapsulation> selectionModel = studentLeaveClubTable.getSelectionModel();
        if (selectionModel.isEmpty()) {
            // If the student didn't select a row to delete the club
            setLabelProperties(studentProfileManagementLeaveClubErrorLabel, Color.RED, "Select a row to leave the club");
        } else {
            // If the student selected a row to leave the club
            TableViewEncapsulation selectedClub = studentLeaveClubTable.getSelectionModel().getSelectedItem();
            int clubID = selectedClub.getClubID();
            String studentID = signIn.getLoginUserID();

            // To get the event ID from the selected row
            String getEventIDQuery = "SELECT * FROM event";
            ArrayList<ArrayList<Object>> eventDetailsList = manageData.get2DArrayData(getEventIDQuery);

            // Order --> attendance table --> --> club and student table ;
            for (ArrayList<Object> event : eventDetailsList) {
                if (event.get(1).equals(clubID)) {
                    // First have to delete attendance table related to the event
                    System.out.println(event.get(0));
                    String attendanceDataDeleteQuery = "DELETE FROM attendance WHERE EventID = '" + event.get(0) +
                            "' AND StudentID = '" + studentID + "'";
                    manageData.modifyData(attendanceDataDeleteQuery);
                }
            }
            // Query for club and student relation table to delete data
            String clubAndClubAdvisorDataDeleteQuery = "DELETE FROM club_and_student WHERE ClubID = '" + clubID +
                    "' AND StudentID = '" + studentID + "'";
            manageData.modifyData(clubAndClubAdvisorDataDeleteQuery);

            // Set the label properties
            setLabelProperties(studentProfileManagementLeaveClubErrorLabel, Color.GREEN, "You have left the club");

            // Refresh the table
            viewJoinedClubs.run();
        }
    }

    // Method to load the student details to the text fields
    @Override
    public void loadDetailsToTextFields(){
        // Query to get the student details
        String queryStudentDetails = "SELECT * FROM student";
        ArrayList<ArrayList<Object>> studentDetailsList = manageData.get2DArrayData(queryStudentDetails);

        for (ArrayList<Object> studentDetails : studentDetailsList){
            if (studentDetails.get(0).equals(signIn.getLoginUserID())){
                firstNameTextField.setText((String) studentDetails.get(1));
                lastNameTextField.setText((String) studentDetails.get(2));
                gradeTextField.setText((String) studentDetails.get(3));
                emailTextField.setText((String) studentDetails.get(4));
                passwordTextField.setText((String) studentDetails.get(5));
                rePasswordTextField.setText((String) studentDetails.get(5));
                break;
            }
        }
    }


    // To load the student data to the text fields
    @FXML
    protected void loadStudentDataOnActionButton(ActionEvent event){
        loadDetailsToTextFields();
    }


    boolean isValidCommonValidator;
    String studentFirstName;
    String studentLastName;
    String studentGrade;
    String studentEmail;
    String studentPassword;
    String studentRePassword;
    // To check if all the details entered are correct
    @Override
    public boolean commonValidator() {
        // Get the entered new details
        // first name validation
        studentFirstName = firstNameTextField.getText();
        signUp.setName(studentFirstName);
        boolean validFirstName = signUp.nameValidator(firstNameErrorLabel);

        // last name validation
        studentLastName = lastNameTextField.getText();
        signUp.setName(studentLastName);
        boolean validLastName = signUp.nameValidator(lastNameErrorLabel);

        // grade validation
        studentGrade = gradeTextField.getText();
        setGrade(studentGrade);
        boolean validGrade = gradeValidator(gradeErrorLabel, gradeTextField);

        // email validation
        studentEmail = emailTextField.getText();
        setEmail(studentEmail);
        boolean validEmail = emailValidator(emailErrorLabel, emailTextField);

        // password validation
        studentPassword = passwordTextField.getText();
        setPassword(studentPassword);
        boolean validPassword = passwordValidator(passwordErrorLabel, passwordTextField);

        // rePassword validation
        studentRePassword = rePasswordTextField.getText();
        setPassword(studentRePassword);
        boolean validRePassword = passwordValidator(rePasswordErrorLabel, rePasswordTextField);

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
        if (validFirstName && validLastName && validGrade && validEmail && validPassword && validRePassword && isBothPasswordSame) {
            // If all the details are correct the common validator will be true
            isValidCommonValidator = true;
        }
        return isValidCommonValidator;
    }

    // If the student wants to edit details
    @FXML
    protected void studentUpdateDetailsOnActionButton(ActionEvent actionEvent) throws Exception {
        // If the common validator is true
        if (commonValidator()) {
            // Query to update the student details
            String updateStudentDetailsQuery = "UPDATE student SET FirstName = '" + studentFirstName + "', LastName = '" +
                    studentLastName + "', Grade = '" + studentGrade + "', Email = '" + studentEmail +
                    "', Password = '" + studentPassword + "' WHERE StudentID = '" + signIn.getLoginUserID() + "'";
            // Update the details in the database
            manageData.modifyData(updateStudentDetailsQuery);

            // After updating the details, navigate to the sign-in page
            mainController.navigateFunction(actionEvent, "SignIn_Page.fxml", "Student SignIn");
        }
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
}
