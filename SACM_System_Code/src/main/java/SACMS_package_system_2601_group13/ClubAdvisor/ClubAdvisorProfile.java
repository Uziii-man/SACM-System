package SACMS_package_system_2601_group13.ClubAdvisor;

import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClubAdvisorProfile {

    MainController mainController = new MainController();


    // If the club advisor wants to view all the available club and details in a table
    @FXML
    protected void viewClubsOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "ViewSelectClubs.fxml", "SACM System");
        System.out.println("Directing to View Clubs");
    }


    // If the club advisor wants to create a club
    @FXML
    protected void createClubProfileOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Create_Club.fxml", "Club Creation");
    }

    // Redirect to the main selection page
    @FXML
    protected void clubManageOnActionButton(ActionEvent actionEvent) throws Exception {

    }



    // If the club advisor wants to join a club
    @FXML
    protected void joinAsAClubAdvisorOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }


    // If the club advisor wants to edit his or her profile
    @FXML
    protected void profileManageOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }

    // if the club advisor wants to signOut from the system
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }

    // If the club advisor wants to exit
    @FXML
    protected void exitOnActionButton() {System.exit(0);}
}