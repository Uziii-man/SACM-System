module com.example.sacm_system_code {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens SACMS_package_system_2601_group13 to javafx.fxml;
    exports SACMS_package_system_2601_group13;
}