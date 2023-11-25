package SACMS_package_system_2601_group13.Common;

import java.util.ArrayList;

public class TestClass {

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        ManageData manageData = new ManageData();

//        manageData.get1DArrayData("SELECT * FROM staff");
        String ID = "stf1234";
        String password = "asdasd";
        String querySearch1 = "SELECT StaffID, Password FROM club_advisor";
        String querySearch = "SELECT StaffID, Password FROM club_advisor WHERE StaffID = " + ID;

        String SEARCH_QUERY = "SELECT ClubID FROM club WHERE ClubName = 'asdasdasdasd'";
        System.out.println(manageData.get1DArrayData(SEARCH_QUERY));

    }

}

