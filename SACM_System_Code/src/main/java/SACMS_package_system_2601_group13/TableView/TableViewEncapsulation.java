package SACMS_package_system_2601_group13.TableView;

public class TableViewEncapsulation {
    // Encapsulating the data from the database
    private String clubID;
    private String clubName;
    private String clubAbbreviation;
    private String clubDescription;

    public TableViewEncapsulation() {
    }

    // Setters for table view used in view club page
    public TableViewEncapsulation(String clubID, String clubName, String clubDescription) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
    }

    // Setters for table view used in club management page
    public TableViewEncapsulation(String clubID, String clubName, String clubAbbreviation, String clubDescription) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.clubAbbreviation = clubAbbreviation;
        this.clubDescription = clubDescription;
    }

    // Getter and setter for above declared variables
    public void setClubID(String clubID) {
        this.clubID = clubID;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setClubAbbreviation(String clubAbbreviation) {
        this.clubAbbreviation = clubAbbreviation;
    }
    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public String getClubID() {
        return clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubAbbreviation() {
        return clubAbbreviation;
    }

    public String getClubDescription() {
        return clubDescription;
    }




}
