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
//
////        manageData.get1DArrayData("SELECT * FROM staff");
        String ID = "stf1234";
//        String password = "asdasd";


        // Query the database and get the data -> clubDetailsList
        String query = "SELECT * FROM event";

        // Get the data from the database
        ArrayList<ArrayList<Object>> eventDetailsList = manageData.get2DArrayData(query);
        // ObservableList is a list that enables listeners to track changes when they occur
        ObservableList<TableViewEncapsulation> eventDetailsObservableList = FXCollections.observableArrayList();

        // This done to show the clubs that host events
        String clubDetailsQuery = "SELECT * FROM club";
        ArrayList<ArrayList<Object>> clubDetailsList = manageData.get2DArrayData(clubDetailsQuery);

        // To add the clubs to the observable list that the club advisor is managing
        for (ArrayList<Object> eventRow : eventDetailsList) {

            for (ArrayList<Object> clubRow : clubDetailsList) {
                if (eventRow.get(1).equals(clubRow.get(0))) {
                    // Create a TableViewEncapsulation object directly
                    System.out.println("Club eve" + eventRow.get(1));
                    System.out.println(clubRow.get(0));
                    System.out.println(eventRow.get(2));
                    System.out.println(eventRow.get(3));

//                    eventDetailsObservableList.add(new TableViewEncapsulation((Integer) eventRow.get(0), String.valueOf(eventRow.get(2)), String.valueOf(clubRow.get(1)), String.valueOf(eventRow.get(3)), String.valueOf(eventRow.get(4))));
                    break;
                }
            }
        }

    }

}

