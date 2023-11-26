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
    private TableView<TableViewEncapsulation> viewClubsTable;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubIDColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubNameColumn;
    @FXML
    private TableColumn<TableViewEncapsulation, String> clubDescriptionColumn;

    // Building new constructor methods for existing classes
    MainController mainController = new MainController();
    TableViewController tableViewController = new TableViewController();

    // If the club advisor wants to view all the available club and details in a table before joining
    // Creating a Runnable object to execute the object frequently and assigned using lambda
    // Instead of creating a new object of Runnable interface, we can use lambda expression to reduce the code
    Runnable viewClub = () -> tableViewController.viewTable(viewClubsTable, clubIDColumn, clubNameColumn, clubDescriptionColumn);


    // If the club advisor wants to view all the available club and details in a table
    @FXML
    protected void viewClubsOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "View_Join_Clubs.fxml", "SACM System");
    }

    // Button to load the table data in the view club page in View_Join_Clubs.fxml
    @FXML
    protected void loadTableOnActionButton() throws Exception {
        viewClub.run();
    }

    @FXML
    protected void joinClubOnActionButton() throws Exception {
        viewClub.run();
    }

    // If the club advisor wants to go back to club advisor profile
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor");
    }

    // If the club advisor wants to create a club
    @FXML
    protected void createClubProfileOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Create_Club.fxml", "Club Creation");
    }

    // If the club advisor wants to manage a clubs
    @FXML
    protected void clubManageOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Management.fxml", "Club Management");
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