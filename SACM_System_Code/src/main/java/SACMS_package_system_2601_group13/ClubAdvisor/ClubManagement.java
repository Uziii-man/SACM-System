package SACMS_package_system_2601_group13.ClubAdvisor;

import SACMS_package_system_2601_group13.Common.*;
import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

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
    private TableColumn <TableViewEncapsulation , String> clubIDColumn;
    @FXML
    private TableColumn <TableViewEncapsulation , String> clubNameColumn;
    @FXML
    private TableColumn <TableViewEncapsulation , String> clubAbbreviationColumn;
    @FXML
    private TableColumn <TableViewEncapsulation , String> clubDescriptionColumn;


    // Building a new constructor
    MainController mainController = new MainController();

    // Creating constructor objects for the classes
    DatabaseManager databaseManager = new DatabaseManager();
    ManageData manageData = new ManageData();
    SignIn signIn = new SignIn();

    boolean isValidData;
    ArrayList<Object> clubIDArray;
    String querySearch;

    // club name validation
    private String clubName;
    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    // club name validation function
    @Override
    public boolean nameValidator(Label labelName, TextField textFieldName) {
        isValidData = false;
        // Query for club table to search for club name
        querySearch = "SELECT ClubName FROM club";
        // Length validation
        if (clubName.length() < 10 || clubName.length() > 25) {
            labelName.setTextFill(Color.RED);
            labelName.setText("Club Name characters between 10 and 25");
            textFieldName.clear();
        } else {
            // Character validation alphabets only and spaces allowed
            if (!clubName.matches("[a-zA-Z ]+")) {
                labelName.setTextFill(Color.RED);
                labelName.setText("Name must contain only alphabets");
                textFieldName.clear();
            } else {
                if(manageData.get1DArrayData(querySearch).contains(clubName)){
                    // Club name already exists in the database
                    labelName.setTextFill(Color.RED);
                    labelName.setText("Club Name Exists");
                    textFieldName.clear();
                } else {
                    if(clubAdvisorInOtherClubValidator()){
                        // Club Advisor can create a club
                        labelName.setTextFill(Color.GREEN);
                        labelName.setText("Club Name is Valid");
                        isValidData = true;
                    } else {
                        // Club advisor cannot create a club
                        labelName.setTextFill(Color.RED);
                        labelName.setText("Club Advisor reach maximum create clubs");
                        textFieldName.clear();
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
        querySearch = "SELECT ClubID FROM club_and_club_advisor WHERE StaffID = '" + signIn.getLoginUserID() + "'";

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
        // setting up the club details using getters in the validation class
        setClubName(clubNameTextField.getText());
        boolean isValidClubName = nameValidator(clubNameErrorLabel, clubNameTextField);

        setClubAbbreviation(clubAbbreviationTextField.getText());
        boolean isValidClubAbbreviation = clubAbbreviationValidator(clubAbbreviationErrorLabel,
                clubAbbreviationTextField);

        setClubDescription(clubDescriptionTextArea.getText());
        boolean isValidClubDescription = clubDescriptionValidator(clubDescriptionErrorLabel, clubDescriptionTextArea);

        // If all the details added are correct
        if(isValidClubName && isValidClubAbbreviation && isValidClubDescription){
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
                    signIn.getLoginUserID(),
                    ClubOriginDate));

            // Add details into the club and club advisor relationship table
            manageData.insertData(club_clubAdvisorDetailsArrayList, club_and_advisorDataInsertQuery);

            // Alert box
            // if all the details are valid it will display an alert box showing all the details
            String[] clubHeader = {"Club Name" ,"Club Abbreviation", "Club Origin Date", "Club Description"};

            databaseManager.userCreateAlertFunctionBox(clubHeader, clubDetailsArrayList,
                    "Club Creation", "You have created a club successfully");

            // Then navigate to the club advisor profile
            mainController.navigateFunction(actionEvent, "Club_Advisor_Profile.fxml", "Club Advisor");
        }
    }


    // If the club advisor wants to update the club details
    @FXML
    protected void updateClubDetailsOnActionButton(ActionEvent actionEvent) throws Exception {

    }

    // If the club advisor wants to delete the club
    @FXML
    protected void deleteClubDetailsOnActionButton(ActionEvent actionEvent) throws Exception {
        TableView.TableViewSelectionModel selectionModel = clubManagementTable.getSelectionModel();
        if(selectionModel.isEmpty()){
            System.out.println("No row selected");
        }

        ObservableList<Integer> list = selectionModel.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);

        Arrays.sort(selectedIndices);

        for(int i = selectedIndices.length - 1; i >= 0; i--){
            selectionModel.clearSelection(selectedIndices[i].intValue());
            clubManagementTable.getItems().remove(selectedIndices[i].intValue());
        }
    }

    // If the club advisor wants to leave the club
    @FXML
    protected void leaveClubAdvisorClubOnActionButton(ActionEvent actionEvent) throws Exception {

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
