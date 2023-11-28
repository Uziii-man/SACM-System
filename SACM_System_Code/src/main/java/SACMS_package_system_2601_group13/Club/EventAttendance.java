//package SACMS_package_system_2601_group13.Club;
//
//import SACMS_package_system_2601_group13.MainController;
//import SACMS_package_system_2601_group13.TableView.TableViewController;
//import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//
//public class EventAttendance {
//
//    @FXML
//    private TableView<TableViewEncapsulation> attendanceTable;
//    @FXML
//    private TableColumn<TableViewEncapsulation, String> StudentIDColumn;
//    @FXML
//    private TableColumn<TableViewEncapsulation, String> studentFirstNameColumn;
//    @FXML
//    private TableColumn<TableViewEncapsulation, String> studentLastNameColumn;
//    @FXML
//    private TableColumn<TableViewEncapsulation, CheckBox> attendanceMarkColumn;
//    @FXML
//    private Label attendanceManagementErrorLabel;
//    @FXML
//    private TextField eventNameTextField, eventDateTextField;
//
//
//    // Creating constructor for classes
//    MainController mainController = new MainController();
//    TableViewController tableViewController = new TableViewController();
//
//
//    Runnable viewAttendance = () -> tableViewController.viewTables(attendanceTable, StudentIDColumn, studentFirstNameColumn, studentLastNameColumn, attendanceMarkColumn);
//
////    void Initialize() {
////
////
////    }
//
//
//
//    @FXML
//    void loadTableOnActionButton(ActionEvent event) {
//        viewAttendance.run();
////        getCheckboxValues(attendanceTable);
//    }
//
//
//    public void getCheckboxValues(TableView<TableViewEncapsulation> tableName) {
//        ObservableList<TableViewEncapsulation> items = tableName.getItems();
//        for (TableViewEncapsulation item : items) {
//            CheckBox checkBox = item.getAttendanceCheckbox();
//            boolean isChecked = checkBox.isSelected();
//            System.out.println("Student ID: " + item.getStudentID() + ", Checkbox Value: " + isChecked);
//        }
//    }
//
//    @FXML
//    private void markAttendanceOnActionButton(ActionEvent actionEvent){
//
//        // To get the values of the checkboxes in the table
//        ObservableList<TableViewEncapsulation> items = attendanceTable.getItems();
//        for (TableViewEncapsulation item : items) {
//            CheckBox checkBox = item.getAttendanceCheckbox();
//            boolean isChecked = checkBox.isSelected();
//            System.out.println("Student ID: " + item.getStudentID() + ", Checkbox Value: " + isChecked);
//        }
//    }
//
////    public void change(ActionEvent event) {
////
////        if(myCheckBox.isSelected()) {
////            myLabel.setText("ON");
////            myImageView.setImage(myImage1);
////        }
////        else {
////            myLabel.setText("OFF");
////            myImageView.setImage(myImage2);
////        }
////    }
//
//
//
//    // If the user wants to go back to the previous page
//    @FXML
//    public void backOnActionButton(ActionEvent actionEvent) throws Exception {
//        mainController.navigateFunction(actionEvent, "Event_Management.fxml", "Event Management");
//    }
//}
