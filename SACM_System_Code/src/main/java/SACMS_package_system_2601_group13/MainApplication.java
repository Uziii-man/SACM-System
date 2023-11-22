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

    public class DatabaseConnection {
        public static Connection connect() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/sacm_system";
            String username = "uzman";
            String password = "1234";

            return DriverManager.getConnection(url, username, password);
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                System.out.println("Connection successful!");
                connection.close();
            } else {
                System.out.println("Connection failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch();
    }
}
