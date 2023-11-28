package SACMS_package_system_2601_group13;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {
    // This method is called when the application is started
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main_User_Selection_Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 450);
        primaryStage.setTitle("SACM System");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

  // Method to run the application
    public static void main(String[] args) {
        launch();
    }
}
