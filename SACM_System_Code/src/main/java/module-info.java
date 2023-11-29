module com.example.sacm_system_code {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens SACMS_package_system_2601_group13 to javafx.fxml;
    exports SACMS_package_system_2601_group13;

    exports SACMS_package_system_2601_group13.User;
    opens SACMS_package_system_2601_group13.User to javafx.fxml;
    exports SACMS_package_system_2601_group13.Common;
    opens SACMS_package_system_2601_group13.Common to javafx.fxml;
    exports SACMS_package_system_2601_group13.TableView;
    opens SACMS_package_system_2601_group13.TableView to javafx.base;
    exports SACMS_package_system_2601_group13.Club;
    opens SACMS_package_system_2601_group13.Club to javafx.fxml;
}