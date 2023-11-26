package SACMS_package_system_2601_group13.Common;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public abstract class Validation {
    boolean isValidData;
    String querySearch;
    // Made an object of ManageData class to use the methods in it
    ManageData manageData = new ManageData();

    // For name validation as an abstract method
    public abstract boolean nameValidator(Label labelName);

    // For ID validation
    private String userID;

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserID() {
        return userID;
    }

    // ID validation common for both club advisor and student accept for the parameters in validation
    public boolean IDValidator(Label labelID, TextField textFieldID, String IDPattern, int requiredLength, String tableName, String columName) {
        isValidData = false;
        // Length validation
        if (userID.length() != requiredLength) {
            labelID.setTextFill(Color.RED);
            labelID.setText("ID must be " + requiredLength + " characters");
            textFieldID.clear();
        } else {
            // Format validation
            if (!userID.matches(IDPattern)) {
                labelID.setTextFill(Color.RED);
                labelID.setText("Invalid ID format");
                textFieldID.clear();
            } else {
                // Check if ID exists in the database
                if(manageData.get1DArrayData("SELECT " + columName + " FROM " + tableName).contains(userID)){
                    // ID already exists in the database
                    labelID.setTextFill(Color.RED);
                    labelID.setText("ID Exists");
                    textFieldID.clear();
                } else {
                    // ID available
                    labelID.setTextFill(Color.GREEN);
                    labelID.setText("ID Valid");
                    isValidData = true;
                }
            }
        }
        return isValidData;
    }

    // For email validation
    private String email;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean emailValidator(Label labelName, TextField textFieldName) {
        isValidData = false;
        // Regular expression for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Check if the email matches the regular expression
        String email = getEmail().trim(); // Assuming getEmail() is a method that retrieves the email from somewhere
        int emailLength = email.length();

        if (!email.matches(emailRegex) || emailLength < 10 || emailLength > 50) {
            labelName.setTextFill(Color.RED);
            if (!email.matches(emailRegex)) {
                labelName.setText("Invalid Email");
            } else {
                labelName.setText("Characters Limited");
            }
            textFieldName.clear();
        } else {
            labelName.setTextFill(Color.GREEN);
            labelName.setText("Email is Valid");
            isValidData = true;
        }
        return isValidData;
    }

    // For password validation
    private String password;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean passwordValidator(Label labelName, TextField textFieldName) {
        isValidData = false;

        // Minimum and maximum character lengths for the password
        int minLength = 5;
        int maxLength = 10;
        // Check if the password meets the length criteria
        if (getPassword().length() < minLength || getPassword().length() > maxLength) {
            labelName.setTextFill(Color.RED);
            labelName.setText("Password must be between " + minLength + " and " + maxLength + " characters");
            textFieldName.clear();
        } else {
            // Check if the password contains at least one special character
            if (!getPassword().matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?].*")) {
                labelName.setTextFill(Color.RED);
                labelName.setText("Password must contain at least one special character");
                textFieldName.clear();
            } else {
                labelName.setTextFill(Color.GREEN);
                labelName.setText("Password is Valid");
                isValidData = true;
            }
        }
        return isValidData;
    }

    // Validation for grade
    private String grade;

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    int minGrade = 6;
    int maxGrade = 13;
    public boolean gradeValidator(Label labelGrade, TextField textFieldGrade) {
        isValidData = false;
        try {
            int intGrade = Integer.parseInt(getGrade());
            // Check if the grade is within the specified range
            if (intGrade >= minGrade && intGrade <= maxGrade) {
                labelGrade.setTextFill(Color.GREEN);
                labelGrade.setText("Grade is Valid");
                isValidData = true;
            } else {
                labelGrade.setTextFill(Color.RED);
                labelGrade.setText("Enter a valid grade between " + minGrade + " and " + maxGrade);
                textFieldGrade.clear();
            }
        } catch (NumberFormatException e) {
            labelGrade.setTextFill(Color.RED);
            labelGrade.setText("Enter a valid numeric grade");
            textFieldGrade.clear();
        }
        return isValidData;
    }


    // Setting up the getter and setter for club abbreviation
    private String clubAbbreviation;
    public String getClubAbbreviation() {
        return clubAbbreviation;
    }
    public void setClubAbbreviation(String clubAbbreviation) {
        this.clubAbbreviation = clubAbbreviation;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // For Club Validation
    // Validation for club abbreviation
    public boolean clubAbbreviationValidator(Label labelAbbreviation) {
        isValidData = false;
        int maxLength = 5;
        // Query for club table to search for club abbreviation
        querySearch = "SELECT ClubAbbreviation FROM club";
        // Length validation
        if (getClubAbbreviation().length() > maxLength) {
            labelAbbreviation.setTextFill(Color.RED);
            labelAbbreviation.setText("Abbreviation exceeds limit of " + maxLength + " characters");
        } else {
            // Character case validation
            if (!getClubAbbreviation().matches("[A-Z]+")) {
                labelAbbreviation.setTextFill(Color.RED);
                labelAbbreviation.setText("Abbreviation must contain only uppercase letters");
            } else {
                // Assumption that same club can have the same abbreviation
                // Check if club abbreviation exists in the database
                if(manageData.get1DArrayData(querySearch).contains(clubAbbreviation)){
                    // Club abbreviation already exists in the database
                    labelAbbreviation.setTextFill(Color.RED);
                    labelAbbreviation.setText("Club Abbreviation Exists");
                } else {
                    // Club abbreviation available
                    labelAbbreviation.setTextFill(Color.GREEN);
                    labelAbbreviation.setText("Club Abbreviation is Valid");
                    isValidData = true;
                }
                return isValidData;
            }
        }
        return isValidData;
    }

    private String clubDescription;

    public String getClubDescription() {
        return clubDescription;
    }
    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public boolean clubDescriptionValidator(Label labelDescription) {
        isValidData = false;
        int minLength = 10;
        int maxLength = 50;

        // Length validation
        int descriptionLength = getClubDescription().length();
        if (descriptionLength < minLength || descriptionLength > maxLength) {
            labelDescription.setTextFill(Color.RED);
            labelDescription.setText("Description must be between " + minLength + " and " + maxLength + " characters");
        } else {
            // Character validation
            if (!getClubDescription().matches("[a-zA-Z0-9 ]+")) {
                labelDescription.setTextFill(Color.RED);
                labelDescription.setText("Description must contain only numbers and alphabets");
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
