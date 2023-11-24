package SACMS_package_system_2601_group13.ClubAdvisor;

import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClubAdvisorProfile {
    @FXML
    private TableView<TableViewEncapsulation>  viewClubsTable;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubIDColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubDescriptionColumn;
    MainController mainController = new MainController();
    TableViewController tableViewController = new TableViewController();

    Runnable viewClub = () -> tableViewController.viewTable(viewClubsTable, clubIDColumn, clubNameColumn, clubDescriptionColumn);

    // If the club advisor wants to create a club
    @FXML
    protected void createClubProfileOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Create_Club.fxml", "Club Creation");
    }
    // Redirect to the main selection page
    @FXML
    protected void clubManageOnActionButton(ActionEvent actionEvent) throws Exception {

    }

    // If the club advisor wants to view all the available club and details in a table
    @FXML
    protected void viewClubsOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "View&SelectClubs.fxml", "SACM System");
        viewClub.run();
        System.out.println("in runnable");
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