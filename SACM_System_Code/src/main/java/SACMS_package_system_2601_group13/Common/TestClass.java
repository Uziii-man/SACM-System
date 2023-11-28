package SACMS_package_system_2601_group13.Common;

//import SACMS_package_system_2601_group13.Common.ManageData;

import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TestClass {

    public static void main(String[] args) {
//        TestClass testClass = new TestClass();
        ManageData manageData = new ManageData();

        String stuDI = "stu12345";
        String eventID = "8";


        String queryUpdateAttendance = "UPDATE attendance SET Attendance = '" + true +"' WHERE StudentID = '" +
                stuDI +"' AND EventID = '" + eventID + "'";
        manageData.modifyData(queryUpdateAttendance);
    }

}

