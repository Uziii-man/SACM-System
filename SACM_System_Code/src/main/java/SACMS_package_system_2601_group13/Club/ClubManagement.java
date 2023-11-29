package SACMS_package_system_2601_group13.Club;

import SACMS_package_system_2601_group13.Common.*;
import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ClubManagement extends Validation {

    // Club creation panel variables
    @FXML
    private TextField clubNameTextField, clubAbbreviationTextField;
    @FXML
    private TextArea clubDescriptionTextArea;
    @FXML
    private Label clubNameErrorLabel,clubAbbreviationErrorLabel, clubDescriptionErrorLabel;

    // Club Management panel variables
    @FXML
    private TableView <TableViewEncapsulation> clubManagementTable;
    @FXML
    private TableColumn <TableViewEncapsulation , Integer> clubIDColumn;
    @FXML
    private TableColumn <TableViewEncapsulation , String> clubNameColumn;
    @FXML
    private TableColumn <TableViewEncapsulation , String> clubAbbreviationColumn;
    @FXML
    private TableColumn <TableViewEncapsulation , String> clubDescriptionColumn;
    @FXML
    private Label clubManagementErrorLabel;

    // Building new constructor objects for the classes
    MainController mainController = new MainController();
    ManageData manageData = new ManageData();
    SignIn signIn = new SignIn();
    TableViewController tableViewController = new TableViewController();


    // Initializing variables
    private boolean isValidData;
    private ArrayList<Object> clubIDArray;
    private String querySearch;


    // Lambda expression to view the table in the club management page
    Runnable viewClubManagementTable = () -> tableViewController.viewTable(clubManagementTable, clubIDColumn, clubNameColumn,
            clubAbbreviationColumn, clubDescriptionColumn);


    // Getting the userID from the signIn class
    private String userID = signIn.getLoginUserID();


    // Setting Up getters and setters clubID
    private static int clubID;
    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }


    // club name validation
    private String clubName;
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }


    // Method to set label colors and texts
    private void setLabelProperties(Label label, Color color, String text) {
        label.setTextFill(color);
        label.setText(text);
    }


    // club name validation function
    @Override
    public boolean nameValidator(Label labelName) {
        isValidData = false;
        // Query for club table to search for club name
        querySearch = "SELECT ClubName FROM club";
        // Length validation
        if (clubName.length() < 10 || clubName.length() > 25) {
            setLabelProperties(labelName, Color.RED, "Club Name characters between 10 and 25");
        } else {
            // Character validation alphabets only and spaces allowed
            if (!clubName.matches("[a-zA-Z ]+")) {
                setLabelProperties(labelName, Color.RED, "Name must contain only alphabets");
            } else {
                if(manageData.get1DArrayData(querySearch).contains(clubName)){
                    // Club name already exists in the database
                    setLabelProperties(labelName, Color.RED, "Club Name Exists");
                } else {
                    if(clubAdvisorInOtherClubValidator()){
                        // Club Advisor can create a club
                        setLabelProperties(labelName, Color.GREEN, "Club Name is Valid");
                        isValidData = true;
                    } else {
                        // Club advisor cannot create a club
                        setLabelProperties(labelName, Color.RED, "Club Advisor reach maximum create clubs");
                    }
                }
            }
        }
        return isValidData;
    }


    // check if the club advisor has already in other clubs when creating a new club (club advisor can only be in maximum of club)
    public boolean clubAdvisorInOtherClubValidator() {
        isValidData = false;
        // Query for club and club advisor relation table if the club advisor is in other clubs
        querySearch = "SELECT ClubID FROM club_and_club_advisor WHERE StaffID = '" + userID + "'";

        // Get arraylist of club IDs that the club advisor is in
        clubIDArray = new ArrayList<>(manageData.get1DArrayData(querySearch));
        // Check if the club advisor in less than 3 clubs
        if(!(clubIDArray.size() >= 3)){
            isValidData = true;
        }

        return isValidData;
    }


    // If the club advisor wants to create a club after navigating to club creation page
    @FXML
    protected void createClubOnActionButton(ActionEvent actionEvent) throws Exception {
        // validation for club name
        setClubName(clubNameTextField.getText());
        boolean isValidClubName = nameValidator(clubNameErrorLabel);
        if(!isValidClubName){
            clubNameTextField.clear();
        }

        // validation for club abbreviation
        setClubAbbreviation(clubAbbreviationTextField.getText());
        boolean isValidClubAbbreviation = clubAbbreviationValidator(clubAbbreviationErrorLabel);
        if(!isValidClubAbbreviation){
            clubAbbreviationTextField.clear();
        }

        // validation for club description
        setDescription(clubDescriptionTextArea.getText());
        boolean isValidClubDescription = descriptionValidator(clubDescriptionErrorLabel);
        if(!isValidClubDescription){
            clubDescriptionTextArea.clear();
        }

        // To check if all the details are correct
        if (isValidClubName && isValidClubAbbreviation && isValidClubDescription){
            // Get the current date in the local desktop's time zone
            LocalDate currentDate = LocalDate.now();
            // Convert to java.sql.Date for insertion into the database
            Date ClubOriginDate = Date.valueOf(currentDate);

            // For the club table
            // Query for club table to insert data
            String clubDataInsertQuery = "INSERT INTO club (ClubName, ClubAbbreviation, ClubOriginDate, ClubDescription) VALUES (?, ?, ?, ?)";

            // Adding club details into an arraylist
            ArrayList<Object> clubDetailsArrayList = new ArrayList<>(Arrays.asList(
                    clubNameTextField.getText(),
                    clubAbbreviationTextField.getText(),
                    ClubOriginDate,
                    clubDescriptionTextArea.getText()));

            // Add details and update the database of the club table
            manageData.insertData(clubDetailsArrayList, clubDataInsertQuery);

            // For the club and club advisor relation table
            // Query for club and club advisor relation table to insert data
            String club_and_advisorDataInsertQuery = "INSERT INTO club_and_club_advisor (ClubID, StaffID, JoinDate) VALUES (?, ?, ?)";

            // Getting club ID to add to the club and club advisor relation table
            // There can be only one club ID with only same name
            clubIDArray = new ArrayList<>(manageData.get1DArrayData("SELECT ClubID FROM club WHERE ClubName = '" + clubName + "'"));
            int clubID = (int) clubIDArray.get(0);

            // Adding club and club advisor relation details into an arraylist
            ArrayList<Object> club_clubAdvisorDetailsArrayList = new ArrayList<>(Arrays.asList(
                    clubID,
                    userID,
                    ClubOriginDate));

            // Add details into the club and club advisor relationship table
            manageData.insertData(club_clubAdvisorDetailsArrayList, club_and_advisorDataInsertQuery);

            // Alert box
            // if all the details are valid it will display an alert box showing all the details
            String[] clubHeader = {"Club Name" ,"Club Abbreviation", "Club Origin Date", "Club Description"};

            manageData.userCreateAlertFunctionBox(clubHeader, clubDetailsArrayList,
                    "Club Creation", "You have created a club successfully");

            // Then navigate to the club advisor profile
            mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor");
        }
    }


    // Button to load the club management table
    @FXML
    protected void loadTableOnActionButton(){
        viewClubManagementTable.run();
        // To edit club name column
        clubNameColumn.setCellFactory(TextFieldTableCell.<TableViewEncapsulation>forTableColumn());
        clubAbbreviationColumn.setCellFactory(TextFieldTableCell.<TableViewEncapsulation>forTableColumn());
        clubDescriptionColumn.setCellFactory(TextFieldTableCell.<TableViewEncapsulation>forTableColumn());
        updateClubTable();
    }


    // Edit and Update the Table View and Club Table in the Database
    private void updateClubTable(){
        // Setting the error label to show the user to select a row to edit
        // To display the club name is updated
        setLabelProperties(clubManagementErrorLabel, Color.BLACK, "Please select a row to edit");

        // Club ID column cannot be edited because it is the primary key in the database
        // To edit club name colum
        clubNameColumn.setOnEditCommit(event -> {
            TableViewEncapsulation tableViewEncapsulation = event.getTableView().getItems().get(event.getTablePosition().getRow());

            // Validation for club name
            setClubName(event.getNewValue());
            boolean validName = nameValidator(clubManagementErrorLabel);
            if (validName) {
                tableViewEncapsulation.setClubAbbreviation(event.getNewValue());
                querySearch = "UPDATE club SET ClubName = '" + event.getNewValue() + "' WHERE ClubID = '" + tableViewEncapsulation.getClubID() + "'";
                manageData.modifyData(querySearch);
                // To display the club name is updated
                setLabelProperties(clubManagementErrorLabel, Color.GREEN, "Club Name is Updated");
            }
        });

        // To edit club abbreviation column
        clubAbbreviationColumn.setOnEditCommit(event -> {
            TableViewEncapsulation tableViewEncapsulation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            setClubAbbreviation(event.getNewValue());

            // Validation for club abbreviation
            boolean validAbbreviation = clubAbbreviationValidator(clubManagementErrorLabel);
            if (validAbbreviation) {
                // To update the club abbreviation in the table
                querySearch = "UPDATE club SET ClubAbbreviation = '" + event.getNewValue() + "' WHERE ClubID = '" + tableViewEncapsulation.getClubID() + "'";
                manageData.modifyData(querySearch);
                // To display the club abbreviation is updated
                setLabelProperties(clubManagementErrorLabel, Color.GREEN, "Club Abbreviation is Updated");
            }
        });

        // To edit club description column
        clubDescriptionColumn.setOnEditCommit(event -> {
            TableViewEncapsulation tableViewEncapsulation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            setDescription(event.getNewValue());

            // Validation for club description
            boolean validDescription = descriptionValidator(clubManagementErrorLabel);
            if (validDescription) {
                querySearch = "UPDATE club SET ClubDescription = '" + event.getNewValue() + "' WHERE ClubID = '" + tableViewEncapsulation.getClubID() + "'";
                manageData.modifyData(querySearch);
                // To display the club description is updated
                setLabelProperties(clubManagementErrorLabel, Color.GREEN, "Club Description is Updated");
            }
        });
    }


    // If the club advisor wants to delete the club
    @FXML
    protected void deleteClubDetailsOnActionButton(ActionEvent actionEvent){
        TableView.TableViewSelectionModel<TableViewEncapsulation> selectionModel = clubManagementTable.getSelectionModel();
        if (selectionModel.isEmpty()) {
            // The club advisor doesn't select a row to delete
            setLabelProperties(clubManagementErrorLabel, Color.RED, "Select a row to delete");
        } else {
            TableViewEncapsulation selectedClub = clubManagementTable.getSelectionModel().getSelectedItem();
            int clubID = selectedClub.getClubID();

            // Query for event table to delete data
            // Order --> attendance table --> event table --> club and club advisor table  --> club and student table --> club table
            // To get the event ID from the selected row
            String getEventIDQuery = "SELECT * FROM event";
            ArrayList<ArrayList<Object>> eventIDList = manageData.get2DArrayData(getEventIDQuery);

            for (ArrayList<Object> row : eventIDList) {
                if (row.get(1).equals(clubID)) {
                    // First have to delete attendance table related to the event
                    String attendanceDataDeleteQuery = "DELETE FROM attendance WHERE EventID = '" + row.get(0) + "'";
                    manageData.modifyData(attendanceDataDeleteQuery);

                    // Then have to delete event table related to the event
                    String eventDataDeleteQuery = "DELETE FROM event WHERE EventID = '" + row.get(0) + "'";
                    manageData.modifyData(eventDataDeleteQuery);
                }
            }

            // Query for club and club advisor relation table to delete data
            String clubAndClubAdvisorDataDeleteQuery = "DELETE FROM club_and_club_advisor WHERE ClubID = '" + clubID + "'";
            manageData.modifyData(clubAndClubAdvisorDataDeleteQuery);

            // Query for club and student relation table to delete data
            String clubAndStudentDataDeleteQuery = "DELETE FROM club_and_student WHERE ClubID = '" + clubID + "'";
            manageData.modifyData(clubAndStudentDataDeleteQuery);

            // Query for club table to delete data
            String clubDataDeleteQuery = "DELETE FROM club WHERE ClubID = '" + clubID + "'";
            manageData.modifyData(clubDataDeleteQuery);

            // Refresh the table
            viewClubManagementTable.run();
        }
    }


    // If the club advisor wants to leave the club
    @FXML
    protected void leaveClubAdvisorClubOnActionButton(ActionEvent actionEvent){
        TableView.TableViewSelectionModel<TableViewEncapsulation> selectionModel = clubManagementTable.getSelectionModel();
        if(selectionModel.isEmpty()){
            // The club advisor doesn't select a row to delete
            setLabelProperties(clubManagementErrorLabel, Color.RED, "Select a row to Leave");
        }
        else {
            // To get the selected and deleted row
            TableViewEncapsulation selectedClub = clubManagementTable.getSelectionModel().getSelectedItem();
            // Get the club ID from the selected row
            int clubID = selectedClub.getClubID();
            // Query for club and club advisor relation table to delete data
            String clubAndClubAdvisorDataDeleteQuery = "DELETE FROM club_and_club_advisor WHERE StaffID = '" + userID + "' AND ClubID = '" + clubID + "'";
            manageData.modifyData(clubAndClubAdvisorDataDeleteQuery);

            // To display you have left the club successfully\
            setLabelProperties(clubManagementErrorLabel, Color.GREEN, "You have left the club successfully");

            // To refresh the table
            viewClubManagementTable.run();
        }
    }


    // If the club advisor wants to manage events
    @FXML
    protected void eventManageClubOnActionButton(ActionEvent actionEvent) throws Exception {
        TableView.TableViewSelectionModel<TableViewEncapsulation> selectionModel = clubManagementTable.getSelectionModel();

        if (selectionModel.isEmpty()) {
            // The club advisor doesn't select a row to delete
            setLabelProperties(clubManagementErrorLabel, Color.RED, "Select a Handle Events");
        } else {
            // To get the selected and deleted row
            TableViewEncapsulation selectedClub = clubManagementTable.getSelectionModel().getSelectedItem();
            // Get the club ID from the selected row and set it
            int clubID = selectedClub.getClubID();
            tableViewController.setClubID(clubID);
            setClubID(clubID);

            // Navigate to the event management page
            mainController.navigateFunction(actionEvent, "Event_Management.fxml", "Event Management");
        }
    }


    // If the club advisor wants to go back to club advisor profile
    @FXML
    protected void backOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor");
    }


    // If the club advisor wants to signOut from the system
    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }
}
