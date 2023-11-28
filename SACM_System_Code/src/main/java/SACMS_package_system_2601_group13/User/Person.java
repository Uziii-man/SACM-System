package SACMS_package_system_2601_group13.User;

public class Person {
    private String userID;
    private String password;
    private String firstName;
    private String lastName;


    public Person(String userID, String password, String firstName, String lastName) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
