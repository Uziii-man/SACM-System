package SACMS_package_system_2601_group13;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main_User_Selection_Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("SACM System");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/sacm_system";
            String username = "uzman";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();

            // ADD a prompt for the database connection failure alert box
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            MainApplication mainApplication = new MainApplication();
            Connection connection = mainApplication.connect();
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch();
    }
}
