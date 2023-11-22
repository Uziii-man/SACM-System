module com.example.sacm_system_code {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sacm_system_code to javafx.fxml;
    exports com.example.sacm_system_code;
}