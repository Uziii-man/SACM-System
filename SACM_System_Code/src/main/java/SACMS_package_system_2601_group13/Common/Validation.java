package SACMS_package_system_2601_group13.Common;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Validation {
    boolean isValidData;
    FindRecords findRecords = new FindRecords();

    // For ID validation
    private String userID;

    public void setUserID(String userID) {
        this.userID = userID;
        // Pattern for the staffID: starts with "stf" followed by exactly 4 digits
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
                if (findRecords.isPrimaryKeyValid(userID, tableName, columName)) {
                    // ID available
                    labelID.setTextFill(Color.GREEN);
                    labelID.setText("ID Valid");
                    isValidData = true;
                } else {
                    // ID already exists in the database
                    labelID.setTextFill(Color.RED);
                    labelID.setText("ID Exists");
                }
            }
        }
        return isValidData;
    }

    // first name and last name validation will be same in validation and parameters (assumption)
    // name validation
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean nameValidator(Label labelName, TextField textFieldName, int minLength, int maxLength) {
        isValidData = false;
        // Length validation
        if (getName().length() < minLength || getName().length() > maxLength) {
            labelName.setTextFill(Color.RED);
            labelName.setText("Name must be between " + minLength + " and " + maxLength + " characters");
            textFieldName.clear();
        } else {
            // Character validation
            if (!getName().matches("[a-zA-Z]+")) {
                labelName.setTextFill(Color.RED);
                labelName.setText("Name must contain only alphabets");
                textFieldName.clear();
            } else {
                // Space/special character validation
                if (getName().contains(" ")) {
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
