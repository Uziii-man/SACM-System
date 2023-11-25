package SACMS_package_system_2601_group13.Common;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ClubValidation {
    // Common variable for all validation
    boolean isValidData;

    // club name validation
    private String clubName;
    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public boolean nameValidator(Label labelName, TextField textFieldName) {
        isValidData = false;
        String clubNameSearch = "SELECT ClubName FROM club";

        // Length validation
        if (clubName.length() < 10 || clubName.length() > 25) {
            labelName.setTextFill(Color.RED);
            labelName.setText("Club Name characters between 10 and 25");
            textFieldName.clear();
        } else {
            // Character validation
            if (!clubName.matches("[a-zA-Z]+")) {
                labelName.setTextFill(Color.RED);
                labelName.setText("Name must contain only alphabets");
                textFieldName.clear();
            } else {
                // Space/special character validation
                if (clubName.contains(" ")) {
                    labelName.setTextFill(Color.RED);
                    labelName.setText("Name must not contain spaces");
                    textFieldName.clear();
                } else {
                    if (clubName.contains(" ")) {
                        labelName.setTextFill(Color.RED);
                        labelName.setText("Name must not contain spaces");
                        textFieldName.clear();
                    } else {
                        // Valid name
                        labelName.setTextFill(Color.GREEN);
                        labelName.setText("Name is Valid");
                        isValidData = true;
                    }
                }
            }
        }
        return isValidData;
    }



    // Validation for Club Abbreviation
    private String clubAbbreviation;

    public String getClubAbbreviation() {
        return clubAbbreviation;
    }

    public void setClubAbbreviation(String clubAbbreviation) {
        this.clubAbbreviation = clubAbbreviation;
    }

    public boolean clubAbbreviationValidator(Label labelAbbreviation, TextField textFieldAbbreviation) {
        isValidData = false;
        int maxLength = 5;
        // Length validation
        if (getClubAbbreviation().length() > maxLength) {
            labelAbbreviation.setTextFill(Color.RED);
            labelAbbreviation.setText("Abbreviation must be at most " + maxLength + " characters");
            textFieldAbbreviation.clear();
        } else {
            // Character case validation
            if (!getClubAbbreviation().matches("[A-Z]+")) {
                labelAbbreviation.setTextFill(Color.RED);
                labelAbbreviation.setText("Abbreviation must contain only uppercase letters");
                textFieldAbbreviation.clear();
            } else {
                // Valid abbreviation
                labelAbbreviation.setTextFill(Color.GREEN);
                labelAbbreviation.setText("Abbreviation is Valid");
                isValidData = true;
            }
        }
        return isValidData;
    }


    // Validation for club description
    private String clubDescription;

    public String getClubDescription() {
        return clubDescription;
    }
    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public boolean clubDescriptionValidator(Label labelDescription, TextArea textAreaDescription) {
        isValidData = false;
        int minLength = 10;
        int maxLength = 50;

        // Length validation
        int descriptionLength = getClubDescription().length();
        if (descriptionLength < minLength || descriptionLength > maxLength) {
            labelDescription.setTextFill(Color.RED);
            labelDescription.setText("Description must be between " + minLength + " and " + maxLength + " characters");
            textAreaDescription.clear();
        } else {
            // Character validation
            if (!getClubDescription().matches("[a-zA-Z0-9 ]+")) {
                labelDescription.setTextFill(Color.RED);
                labelDescription.setText("Description must contain only numbers and alphabets");
                textAreaDescription.clear();
            } else {
                // Valid description
                labelDescription.setTextFill(Color.GREEN);
                labelDescription.setText("Description is Valid");
                isValidData = true;
            }
        }
        return isValidData;
    }

}
