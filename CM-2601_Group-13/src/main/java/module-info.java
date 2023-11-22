module com.example.cm2601_group13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens SACMS_package_system_2601_group13 to javafx.fxml;
    exports SACMS_package_system_2601_group13;

    exports SACMS_package_system_2601_group13.ClubAdvisor;
    opens SACMS_package_system_2601_group13.ClubAdvisor to javafx.fxml;
    exports SACMS_package_system_2601_group13.Student;
    opens SACMS_package_system_2601_group13.Student to javafx.fxml;
    exports SACMS_package_system_2601_group13.Common;
    opens SACMS_package_system_2601_group13.Common to javafx.fxml;
    exports SACMS_package_system_2601_group13.SignUp;
    opens SACMS_package_system_2601_group13.SignUp to javafx.fxml;
}