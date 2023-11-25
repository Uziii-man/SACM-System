package SACMS_package_system_2601_group13.Common;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindRecords {

    // get the club ID
    public int getClubIdByClubName(String clubName) {
        // Default value if the club is not found
        int clubId = -1;
        String SEARCH_QUERY = "SELECT ClubID FROM club WHERE ClubName = ?";
        Connection connection = DatabaseManager.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY)) {
            preparedStatement.setString(1, clubName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve the ClubID if a matching record is found
                    clubId = resultSet.getInt("ClubID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubId;
    }
}
