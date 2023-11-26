package SACMS_package_system_2601_group13.ClubAdvisor;

import SACMS_package_system_2601_group13.Common.ManageData;
import SACMS_package_system_2601_group13.Common.SignIn;
import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ClubAdvisorProfile {
    @FXML
    private Label viewClubErrorLabel;
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
    ManageData manageData = new ManageData();
    SignIn signIn = new SignIn();

    // Getting the userID from the signIn class
    String userID = signIn.getLoginUserID();

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
    protected void loadTableOnActionButton(){
        viewClub.run();
    }

    // If the club advisor wants to join a club
    @FXML
    protected void joinClubOnActionButton() throws Exception {
        // To get the selected row
        TableView.TableViewSelectionModel<TableViewEncapsulation> selectionModel = viewClubsTable.getSelectionModel();
        if(selectionModel.isEmpty()){
            // The club is not selected to join
            viewClubErrorLabel.setTextFill(Color.RED);
            viewClubErrorLabel.setText("Select a row to join the club");
        }else{
            // To get the selected and insert row
            TableViewEncapsulation selectedClub = viewClubsTable.getSelectionModel().getSelectedItem();
            // Getting the clubID from the selected row
            String clubID = selectedClub.getClubID();

            // Creating local date time to get the current date
            // Get the current date in the local desktop's time zone
            LocalDate currentDate = LocalDate.now();
            // Convert to java.sql.Date for insertion into the database
            Date ClubOriginDate = Date.valueOf(currentDate);

            // To check if the club advisor has already joined the club
            // Get the corresponding clubID and staffID from the club and club advisor relation table
            String clubAndClubAdvisorQuery = "SELECT * FROM club_and_club_advisor";
            ArrayList<ArrayList<Object>> clubAndClubAdvisorList = manageData.get2DArrayData(clubAndClubAdvisorQuery);

            // To check if the club advisor has already joined the club
            boolean isClubAdvisorJoinedClub = false;
            int clubAdvisorJoinedClubIndex = 0;
            for (ArrayList<Object> clubAndClubAdvisor : clubAndClubAdvisorList) {
                // To check if the club advisor has already joined the club
                if (clubAndClubAdvisor.get(1).equals(userID)) {
                    // To calculate the number of clubs the club advisor has joined
                    clubAdvisorJoinedClubIndex++;
                    if (clubAndClubAdvisor.get(0).equals(clubID)) {
                        // The club advisor has already joined the club
                        isClubAdvisorJoinedClub = true;
                        break;
                    }
                }
            }

            // To check if the club advisor has not joined the club
            if (isClubAdvisorJoinedClub) {
                // for the club advisor has already joined the club
                // Setting the error label
                viewClubErrorLabel.setTextFill(Color.RED);
                viewClubErrorLabel.setText("You have already joined the club");
            } else if (clubAdvisorJoinedClubIndex >= 3) {
                // for the club advisor has already joined 3 clubs
                // Setting the error label
                viewClubErrorLabel.setTextFill(Color.RED);
                viewClubErrorLabel.setText("You have already joined 3 clubs");
            }
            else {
                // for the club advisor has not joined the club
                // Add the data to the array list
                ArrayList<Object> clubAdvisorClubDetails = new ArrayList<>(Arrays.asList(
                        clubID,
                        userID,
                        ClubOriginDate));

                // Setting the error label
                viewClubErrorLabel.setTextFill(Color.GREEN);
                viewClubErrorLabel.setText("You have joined the club successfully");

                // Query for club and club advisor relation table to inset data
                String clubAndClubAdvisorDataInsertQuery = "Insert INTO club_and_club_advisor (ClubID, StaffID, JoinDate) VALUES (?, ?, ?)";
                manageData.insertData(clubAdvisorClubDetails, clubAndClubAdvisorDataInsertQuery);
            }
        }
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
        mainController.navigateFunction(actionEvent, "Club_Advisor_Profile_Editor.fxml",
                "Club Advisor Profile Management");
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