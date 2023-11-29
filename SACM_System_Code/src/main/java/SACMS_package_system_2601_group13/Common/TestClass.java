package SACMS_package_system_2601_group13.Common;

//import SACMS_package_system_2601_group13.Common.ManageData;

import SACMS_package_system_2601_group13.TableView.TableViewEncapsulation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;

public class TestClass {

    public static void main(String[] args) {
//        TestClass testClass = new TestClass();
        ManageData manageData = new ManageData();

        ArrayList<ArrayList<Object>> clubDetailsList = new ArrayList<>();
        // Query the database and get the data -> clubDetailsList
        String queryClubDetails = "SELECT * FROM club";
        String queryStudentClub = "SELECT * FROM club_and_student";

        String ID = "stu11111";

        // Execute the provided query to get the data
        ArrayList<ArrayList<Object>> clubDetails2DArray = manageData.get2DArrayData(queryClubDetails);
        ArrayList<ArrayList<Object>> studentClub2DArray = manageData.get2DArrayData(queryStudentClub);

        System.out.println(ID);

        for(ArrayList<Object> studentDetails : studentClub2DArray){
            System.out.println(studentDetails.get(1));
            System.out.println(ID);
            if (studentDetails.get(1).equals(ID)){
                System.out.println(studentDetails.get(1));
                System.out.println(ID);
                for(ArrayList<Object> clubDetails : clubDetails2DArray){
                    if (studentDetails.get(0).equals(clubDetails.get(0))){
                        ArrayList<Object> newRow = new ArrayList<>(Arrays.asList(clubDetails.get(0), clubDetails.get(1), clubDetails.get(4)));
                        clubDetailsList.add(newRow);
                        break;
                    }
                }
            }
        }

        System.out.println(clubDetailsList);
    }

}

