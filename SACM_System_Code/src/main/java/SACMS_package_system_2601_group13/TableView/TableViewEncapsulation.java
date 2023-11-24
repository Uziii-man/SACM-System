package SACMS_package_system_2601_group13.TableView;

public class TableViewEncapsulation {
    private String clubID;
    private String clubName;
    private String clubDescription;

    public TableViewEncapsulation(String clubID, String clubName, String clubDescription) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
    }

    // Getter methods

    public String getClubID() {
        return clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubDescription() {
        return clubDescription;
    }


}
