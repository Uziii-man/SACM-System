package SACMS_package_system_2601_group13.Student;

import SACMS_package_system_2601_group13.Common.SignIn;
import SACMS_package_system_2601_group13.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentProfile {
    // In here label ID is gotten to show the user login profile in the panel
    public Label userIDLabelView;

    SignIn signIn = new SignIn();
//    signIn.getUserID();

    MainController mainController = new MainController();

    // if the student wants view all the available club
    @FXML
    protected void viewClubsOnActionButton(ActionEvent actionEvent) throws Exception {

    }


    // if the student wants join a club
    @FXML
    protected void studentJoinClubOnActionButton(ActionEvent actionEvent) throws Exception {

    }

    // if the student wants to edit his or her profile
    @FXML
    protected void studentEditProfileOnActionButton(ActionEvent actionEvent) throws Exception {

    }

    // if the student wants leave a club
    @FXML
    protected void studentLeaveClubOnActionButton(ActionEvent actionEvent) throws Exception {

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
