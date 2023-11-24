package SACMS_package_system_2601_group13;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MainController {
    // Variable for the profile selection
    private static boolean userProfileClubAdvisor;

    public void setUserProfileClubAdvisor(boolean userProfileClubAdvisor){
        MainController.userProfileClubAdvisor = userProfileClubAdvisor;
    }
    public boolean getUserProfileClubAdvisor(){return userProfileClubAdvisor;}


    // Panel Navigation for different scenes in the program
    public void navigateFunction(ActionEvent actionEvent, String fxmlName, String panelName) throws IOException {
        // Identifying and closing the previous stage
        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();

        // Opening a new stage
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlName)));
        stage.setScene(new Scene(root,600,400));
        stage.setTitle(panelName);
        stage.setResizable(false);
        stage.show();
    }

    // if user selects club advisor profile --> 1
    @FXML
    protected void clubAdvisorProfileOnActionClickButton(ActionEvent actionEvent) throws Exception{
        navigateFunction(actionEvent, "SignIn_SignUp_Club_Advisor_Page.fxml", "Club Advisor SignIn/SignUp");
        setUserProfileClubAdvisor(true);}

    // if user select student profile --> 1
    @FXML
    protected void studentProfileOnActionClickButton(ActionEvent actionEvent) throws Exception{
        navigateFunction(actionEvent, "SignIn_SignUp_Student_Page.fxml", "Student SignIn/SignUp");
        setUserProfileClubAdvisor(false);}

    // To the signIn Page --> common for both club advisor and student
    @FXML
    protected void signInOnActionButton(ActionEvent actionEvent) throws Exception{
        // signIn --> student
        navigateFunction(actionEvent, "SignIn_Page.fxml", "SignIn");}

    //  To the signUp Page --> Club Advisor
    @FXML
    protected void signUpClubAdvisorOnActionButton(ActionEvent actionEvent) throws Exception{
        // signUp --> student
        navigateFunction(actionEvent, "SignUp_ClubAdvisor_Page.fxml", "Club Advisor SignUp");
    }

    //  To the signUp Page -->  Student
    @FXML
    protected void signUpStudentOnActionButton(ActionEvent actionEvent) throws Exception{
        // signUp --> student
        navigateFunction(actionEvent, "Signup_Student_Page.fxml", "Student SignUp");
    }

    // To direct to the back menu
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception{
        navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");}


    // To show about the Program
    @FXML
    protected void aboutUsOnActionButton() {
    }

    // To exit the Program
    @FXML
    protected void exitOnActionButton() {
        System.exit(0);
    }
}
