package SACMS_package_system_2601_group13.Common;

public class TestClass {


    public static void main(String[] args) {
        ManageData manageData = new ManageData();
//        manageData.get1DArrayData("SELECT * FROM staff");
        String ID = "stf1234";
        String password = "asdasd";
        String querySearch1 = "SELECT StaffID, Password FROM club_advisor";
        String querySearch = "SELECT StaffID, Password FROM club_advisor WHERE StaffID = " + ID;
        System.out.println(manageData.get1DArrayData(querySearch));

    }

}

