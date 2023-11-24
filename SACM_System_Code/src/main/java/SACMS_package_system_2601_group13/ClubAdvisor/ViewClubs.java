package SACMS_package_system_2601_group13.ClubAdvisor;

import SACMS_package_system_2601_group13.MainController;
import SACMS_package_system_2601_group13.TableView.TableViewController;
import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewClubs {
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

    // If the club advisor wants to view all the available club and details in a table
    Runnable viewClub = () -> tableViewController.viewTable(viewClubsTable, clubIDColumn, clubNameColumn, clubDescriptionColumn);

    public void initialize() {
        viewClub.run();
    }

    @FXML
    protected void signOutOnActionButton(ActionEvent actionEvent) throws Exception {
        mainController.navigateFunction(actionEvent, "Main_User_Selection_Page.fxml", "SACM System");
    }
}
