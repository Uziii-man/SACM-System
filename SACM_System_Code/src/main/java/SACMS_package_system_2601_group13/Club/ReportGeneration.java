package SACMS_package_system_2601_group13.Club;

import SACMS_package_system_2601_group13.Common.ManageData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ReportGeneration {
    // Declared variables for the report generation
    private String fileName;

    //  Getters and setters
    // for the event name
    private String eventName;
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    // for the event ID
    private int eventID;
    public int getEventID() {
        return eventID;
    }
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }


    // for the event date
    private Date eventDate;
    public Date getEventDate() {
        return eventDate;
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    // for the event time
    private Time eventTime;
    public Time getEventTime() {
        return eventTime;
    }
    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }


    // Object construction for the classes
    ManageData manageData = new ManageData();


    // To generate an event report with attendance details
    public void eventReport() {
        // Event report file name
        fileName = "Event Report " + eventName + " " + LocalDate.now().toString().replace(":", "_") + ".txt";

        // Query to get the event details
        String queryAttendanceDetails = "SELECT * FROM attendance";
        String queryStudentDetails = "SELECT * FROM student";

        // To get the event details
        ArrayList<ArrayList<Object>> attendanceDetails = manageData.get2DArrayData(queryAttendanceDetails);
        ArrayList<ArrayList<Object>> studentDetails = manageData.get2DArrayData(queryStudentDetails);

        // Creating new array to store attendance summary
        ArrayList<ArrayList<String>> attendanceSummary2DArray = new ArrayList<>();

        // Adding details to the attendance summary
        for (ArrayList<Object> attendanceDetail : attendanceDetails) {
            if (eventID == (int) attendanceDetail.get(0)) {

                // If the event ID matches in both tables
                for (ArrayList<Object> studentDetail : studentDetails) {
                    if (attendanceDetail.get(1).equals(studentDetail.get(0))) {
                        // If the student ID matches in both tables
                        // Adding the student details to the array list
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(String.valueOf(attendanceDetail.get(1)));
                        temp.add(String.valueOf(studentDetail.get(1)));
                        temp.add(String.valueOf(studentDetail.get(2)));
                        temp.add(String.valueOf(attendanceDetail.get(2)));

                        attendanceSummary2DArray.add(temp);
                        break;
                    }
                }
            }
        }

        // Event report header
        String[] eventDetailHeader = {"Student ID", "First Name", "Last Name", "Attendance"};

        try (BufferedWriter writeFile = new BufferedWriter(new FileWriter(fileName))) {
            writeFile.write("==========================================================================\n");
            writeFile.write("----------------------------- Event Summary ------------------------------\n");
            writeFile.write("==========================================================================\n");
            writeFile.write("Event Name :" + eventName + "\t\t\t\t\t\tEvent Date :" + clubName + "\n");
            writeFile.write("Event Date :" + eventDate + "\t\t\t\t\t\tEvent Date :" + eventTime + "\n");
            writeFile.write("==========================================================================\n");
            writeFile.write(String.format("| %-15s | %-15s | %-15s | %-15s |\n", eventDetailHeader));
            writeFile.write("==========================================================================\n");

            // Loop to store the student attendance with position and points fileName in the text file
            for (ArrayList<String> studentAttendanceDetail : attendanceSummary2DArray) {
                String formatString = "| %-15s | %-15s | %-15s | %-15s |\n";
                Object[] args = studentAttendanceDetail.toArray();
                writeFile.write(String.format(formatString, args));
            }

            writeFile.write("==========================================================================\n\n");

            // To display the report is generated successfully
            manageData.alertFunctionBox("Event Report", "Event Report Generated Successfully",
                    "Event Report can be found in the project folder" + fileName);

        } catch (IOException e) {
            // To display the report is not generated
            manageData.alertFunctionBox("Event Report", "Event Report Generation Failed",
                    "Event Report cannot be generated");
        }
    }


    // Getters and setters
    // for the club ID
    private int clubID;
    public int getClubID() {
        return clubID;
    }
    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    // for the club name
    private String clubName;
    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }


    // To generate a club report
    public void clubMembershipReport() {
        // Club report file name
        fileName = "Club Report " + clubName + " " + LocalDate.now().toString().replace(":", "_") + ".txt";

        // Query to get the student details who are in specified club
        String queryClubStudentDetails = "SELECT * FROM club_and_student";
        String queryStudentDetails = "SELECT * FROM student";

        // To get club details
        ArrayList<ArrayList<Object>> clubStudentDetails = manageData.get2DArrayData(queryClubStudentDetails);
        ArrayList<ArrayList<Object>> studentDetails = manageData.get2DArrayData(queryStudentDetails);

        // Creating new array to store club summary
        ArrayList<ArrayList<String>> clubSummary2DArray = new ArrayList<>();

        int noOfMembers = 0;

        // Adding details to the club summary array
        for (ArrayList<Object> clubStudentDetail : clubStudentDetails) {
            if (clubID == (int) clubStudentDetail.get(0)) {

                // If the club ID matches in both tables
                for (ArrayList<Object> studentDetail : studentDetails) {
                    if (clubStudentDetail.get(1).equals(studentDetail.get(0))) {
                        // If the student ID matches in both tables
                        // Adding the student details to the array list
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(String.valueOf(clubStudentDetail.get(1)));
                        temp.add(String.valueOf(studentDetail.get(1)));
                        temp.add(String.valueOf(studentDetail.get(2)));
                        temp.add(String.valueOf(studentDetail.get(3)));
                        temp.add(String.valueOf(clubStudentDetail.get(2)));

                        clubSummary2DArray.add(temp);
                        // Incrementing the number of members
                        noOfMembers++;
                        break;
                    }
                }
            }
        }

        // Club report header
        String[] clubDetailsHeader = {"Student ID", "First Name", "Last Name", "Grade", "Joined Date" };

        try (BufferedWriter writeFile = new BufferedWriter(new FileWriter(fileName))) {
            writeFile.write("===========================================================================================\n");
            writeFile.write("-------------------------------------- Club Summary ---------------------------------------\n");
            writeFile.write("===========================================================================================\n");
            writeFile.write("Club Name :" + clubName + "\t\t\t\t\t\t\t\tNumber of Members :" + noOfMembers + "\n");
            writeFile.write("===========================================================================================\n");
            writeFile.write(String.format("| %-15s | %-15s | %-15s | %-15s | %-15s |\n", clubDetailsHeader));
            writeFile.write("===========================================================================================\n");

            // Loop to store the student details with position and points fileName in the text file
            for (ArrayList<String> studentDetail : clubSummary2DArray) {
                String formatString = "| %-15s | %-15s | %-15s | %-15s | %-15s |\n";
                Object[] args = studentDetail.toArray();
                writeFile.write(String.format(formatString, args));
            }

            writeFile.write("===========================================================================================\n\n");

            // To display the report is generated successfully
            manageData.alertFunctionBox("Club Report", "Club Report Generated Successfully",
                    "Club Report can be found in the project folder" + fileName);

        } catch (IOException e) {
            // To display the report is not generated
            manageData.alertFunctionBox("Club Report", "Club Report Generation Failed",
                    "Club Report cannot be generated");
        }
    }

    // To generate a club activity report
    public void clubActivityReport() {
        // Club activity report file name
        fileName = "Club Activity Report " + LocalDate.now().toString().replace(":", "_") + ".txt";

        // Query to get the club details
        String queryClubDetails = "SELECT * FROM club";
        String queryEventDetails = "SELECT * FROM event";

        // To get details from the database
        ArrayList<ArrayList<Object>> clubDetails = manageData.get2DArrayData(queryClubDetails);
        ArrayList<ArrayList<Object>> eventDetails = manageData.get2DArrayData(queryEventDetails);

        // Creating new array to store club activity summary
        ArrayList<ArrayList<String>> clubDetailsArray = new ArrayList<>();

        // Get the club ID and club name int club details array
        for (ArrayList<Object> clubDetail : clubDetails) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(String.valueOf(clubDetail.get(0)));
            temp.add(String.valueOf(clubDetail.get(1)));

            if (!clubDetailsArray.contains(temp)) {
                clubDetailsArray.add(temp);
            }
        }

        System.out.println(clubDetailsArray);

        String[] clubActivityDetailHeader = {"Event ID", "Event Name", "Event Date", "Event Start Time", "Event End Time"};

        try (BufferedWriter writeFile = new BufferedWriter(new FileWriter(fileName))) {
            writeFile.write("============================================================================================\n");
            writeFile.write("----------------------------------- Club Activity Report -----------------------------------\n");


            for (ArrayList<String> clubDetail : clubDetailsArray) {
                writeFile.write("============================================================================================\n");
                writeFile.write("\t\tClub ID :" + clubDetail.get(0) + "\t\t\t\t\t\t\t\t\tClub Name :" + clubDetail.get(1) + "\n");
                writeFile.write("============================================================================================\n");
                writeFile.write(String.format("| %-8s | %-10s | %-15s | %-15s | %-15s |\n", clubActivityDetailHeader));
                writeFile.write("============================================================================================\n");

                // Loop to store the student details with position and points fileName in the text file
                for (ArrayList<Object> eventDetail : eventDetails) {
                    if (clubDetail.get(0).equals(String.valueOf(eventDetail.get(1)))) {
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(String.valueOf(eventDetail.get(0)));
                        temp.add(String.valueOf(eventDetail.get(2)));
                        temp.add(String.valueOf(eventDetail.get(3)));
                        temp.add(String.valueOf(eventDetail.get(4)));
                        temp.add(String.valueOf(eventDetail.get(5)));

                        // Write club activity details to the file
                        String formatString = "| %-15s | %-15s | %-15s | %-15s | %-15s |\n";
                        Object[] args = temp.toArray();
                        writeFile.write(String.format(formatString, args));
                    }
                }
                writeFile.write("============================================================================================\n");
            }

            // To display the report is generated successfully
            manageData.alertFunctionBox("Club Report", "Club Report Generated Successfully",
                    "Club Report can be found in the project folder" + fileName);

        } catch (IOException e) {
            // To display the report is not generated
            manageData.alertFunctionBox("Club Report", "Club Report Generation Failed",
                    "Club Report cannot be generated");
        }
    }
}
