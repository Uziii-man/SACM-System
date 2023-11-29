package SACMS_package_system_2601_group13.Common;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.sql.Date;

public abstract class Validation {
    boolean isValidData;
    String querySearch;
    // Made an object of ManageData class to use the methods in it
    ManageData manageData = new ManageData();

    // For name validation as an abstract method
    public abstract boolean nameValidator(Label labelName);

    // Method to set label colors and texts
    private void setLabelValidProperties(Label label, String text) {
        label.setTextFill(Color.DARKGREEN);
        label.setText(text);
    }

    // Method to set label colors and texts and clear text fields if the data is invalid
    private void setLabelInvalidProperties(Label label, TextField textField , String text) {
        label.setTextFill(Color.RED);
        label.setText(text);
        textField.clear();
    }

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
            // If the ID does not meet the length criteria
            setLabelInvalidProperties(labelID, textFieldID, "ID must be " + requiredLength + " characters");
        } else {
            // Format validation
            if (!userID.matches(IDPattern)) {
                // If the ID does not match the format criteria
                setLabelInvalidProperties(labelID, textFieldID, "Invalid ID format");
            } else {
                // Check if ID exists in the database
                if(manageData.get1DArrayData("SELECT " + columName + " FROM " + tableName).contains(userID)){
                    // ID already exists in the database
                    setLabelInvalidProperties(labelID, textFieldID, "ID Exists");
                } else {
                    // ID available and valid
                    setLabelValidProperties(labelID, "ID is Valid");
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
            setLabelInvalidProperties(labelName, textFieldName, "Invalid Email");
        } else {
            setLabelValidProperties(labelName, "Email is Valid");
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
            // If the password does not meet the length criteria
            setLabelInvalidProperties(labelName, textFieldName, "Password must be between " + minLength + " and " + maxLength + " characters");
        } else {
            // Check if the password contains at least one special character
            if (!getPassword().matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?].*")) {
                setLabelInvalidProperties(labelName, textFieldName, "Password must contain at least one special character");
            } else {
                setLabelValidProperties(labelName, "Password is Valid");
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
                setLabelValidProperties(labelGrade, "Grade is Valid");
                isValidData = true;
            } else {
                // If the grade is not within the specified range
                setLabelInvalidProperties(labelGrade, textFieldGrade, "Enter a valid grade between " + minGrade + " and " + maxGrade);
            }
        } catch (NumberFormatException e) {
            // If the grade is not a number
            setLabelInvalidProperties(labelGrade, textFieldGrade, "Enter a valid numeric grade");
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
                    labelAbbreviation.setTextFill(Color.DARKGREEN);
                    labelAbbreviation.setText("Club Abbreviation is Valid");
                    isValidData = true;
                }
                return isValidData;
            }
        }
        return isValidData;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean descriptionValidator(Label labelDescription) {
        isValidData = false;
        int minLength = 10;
        int maxLength = 50;

        // Length validation
        int descriptionLength = getDescription().length();
        if (descriptionLength < minLength || descriptionLength > maxLength) {
            labelDescription.setTextFill(Color.RED);
            labelDescription.setText("Description must be between " + minLength + " and " + maxLength + " characters");
        } else {
            // Character validation
            if (!getDescription().matches("[a-zA-Z0-9 ]+")) {
                labelDescription.setTextFill(Color.RED);
                labelDescription.setText("Description must contain only numbers and alphabets");
            } else {
                // Valid description
                labelDescription.setTextFill(Color.DARKGREEN);
                labelDescription.setText("Description is Valid");
                isValidData = true;
            }
        }
        return isValidData;
    }

    // Date validation for event creation
    private Date eventDate;

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    // In here SQL date validation is done
    public boolean dateValidator(Label labelName) {
        isValidData = false;

        // Get the current date in the local desktop's time zone
        LocalDate currentDate = LocalDate.now();
        // Convert to java.sql.Date for insertion into the database
        Date presentDate = java.sql.Date.valueOf(currentDate);
        if (eventDate.after(presentDate)) {
            labelName.setTextFill(Color.DARKGREEN);
            labelName.setText("Date is valid");
            isValidData = true;
        } else {
            labelName.setTextFill(Color.RED);
            labelName.setText("Pick a future date");
        }
        return isValidData;
    }


}
