package SACMS_package_system_2601_group13.TableView;

public class TableViewEncapsulation {
    private String clubID;
    private String clubName;
    private String clubDescription;
//    private String clubAdvisor;

    // for the club view
    public TableViewEncapsulation(String clubID, String clubName, String clubDescription) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
//        this.clubAdvisor = clubAdvisor;
    }

    public String getClubID() {
        return clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubDescription() {
        return clubDescription;
    }

//    public String getClubAdvisor() {
//        return clubAdvisor;
//    }
}
