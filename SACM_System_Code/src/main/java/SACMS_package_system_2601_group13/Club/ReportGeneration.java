package SACMS_package_system_2601_group13.Club;

import SACMS_package_system_2601_group13.Common.ManageData;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ReportGeneration {

    // Declared variables for the report generation
    private String fileName;

    //  Getters and setters
    private String eventName;
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    // Object construction for the classes
    ManageData manageData = new ManageData();

    // Generating LocalTime and Date
    public static String generateCurrentDateTime() {

        LocalDateTime currentDateTime = LocalDateTime.now();
        // Date time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }


    // To generate an event report with attendance details
    public void eventReport(String randomDate, String randomLocation){
        // Event report file name
        fileName = "event_report: " + eventName + " " + generateCurrentDateTime();

        fileName = "race_stimulation_file";
        String[] driverDetailsHeader = {"Position", "Driver Name", "Age", "Team Name", "Race Car", "Points", "Total Points"};
        try {
            FileWriter writeFile = new FileWriter(fileName, true);
            writeFile.write("=============================================================================================================\n");
            writeFile.write("----------------------------------------------- Event Summary -----------------------------------------------\n");
            writeFile.write("=============================================================================================================\n");
            writeFile.write("\t\tRace Date :" + randomDate + "\t\t\t\t\t\t\t\t\t\t\t\t\tRace Location :" + randomLocation + "\n");
            writeFile.write("=============================================================================================================\n");
            writeFile.write(String.format("| %-8s | %-20s | %-3s | %-20s | %-18s | %-6s | %-12s |\n", driverDetailsHeader));
            writeFile.write("=============================================================================================================\n");

            // Loop to store the driver details with position and points fileName in the text file
            for (ArrayList<String> driverPositionPointsDetails : positionPointsDriverArray) {
                String formatString = "| %-8s | %-20s | %-3s | %-20s | %-18s | %-6s | %-12s |\n";
                Object[] args = driverPositionPointsDetails.toArray();
                writeFile.write(String.format(formatString, args));
            }
            writeFile.write("=============================================================================================================\n\n");
            writeFile.close();
        } catch (IOException e) {
            System.err.println("Error: unable to write race stimulate file in SRR function");
            e.printStackTrace();
        }
    }

}
