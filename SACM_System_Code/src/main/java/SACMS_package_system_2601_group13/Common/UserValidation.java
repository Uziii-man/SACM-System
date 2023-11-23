package SACMS_package_system_2601_group13.Common;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class UserValidation {
    boolean isValidData;

    // For ID validation
    String IDPattern;
    int requiredLength;

    // For staff ID validation
    private String staffID;

    // main usage of both these getters and setters is to give values of ID pattern and required length
    public void setStaffID(String staffID) {
        this.staffID = staffID;
        // Pattern for the staffID: starts with "stf" followed by exactly 4 digits
        IDPattern = "stf\\d{4}";
        requiredLength = 7;
    }

    public String getStaffID() {
        return staffID;
    }

    // For student ID validation
    private String studentID;

    public void setStudentID(String studentID) {
        this.studentID = studentID;
        // Pattern for the staffID: starts with "stu" followed by exactly 5 digits
        IDPattern = "stu\\d{5}";
        requiredLength = 8;
    }
    public String getStudentID() {
        return studentID;
    }

    // ID validation common for both club advisor and student accept for the parameters in validation
    public boolean IDValidator(Label labelID, TextField textFieldID, String ID) {
        isValidData = false;
        // Length validation
        if (ID.length() != requiredLength) {
            labelID.setTextFill(Color.RED);
            labelID.setText("ID must be " + requiredLength + " characters");
            textFieldID.clear();
        } else {
            // Format validation
            if (!ID.matches(IDPattern)) {
                labelID.setTextFill(Color.RED);
                labelID.setText("Invalid ID format");
                textFieldID.clear();
            } else {
                // Valid staffID
                labelID.setTextFill(Color.GREEN);
                labelID.setText("ID is Valid");
                isValidData = true;
            }
        }
        return isValidData;
    }

    // Common validation for both student and club advisor
    // first name and last name validation will be same in validation and parameters (assumption)
    // name validation
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean nameValidator(Label labelName, TextField textFieldName) {
        isValidData = false;
        // Minimum and maximum character lengths for the name
        int minLength = 3;
        int maxLength = 10;

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
}
