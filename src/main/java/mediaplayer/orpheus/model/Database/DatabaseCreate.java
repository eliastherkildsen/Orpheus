package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.util.debugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseCreate {

    private static final Connection connection = JDBC.instance.getConnection();

    public static PreparedStatement insertMediaGenre(String genre, String mediaId) {

        try {

            String query = "INSERT INTO tblMediaGenre (fldGenre, fldMediaID) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, genre);
            preparedStatement.setString(2, mediaId);

            debugMessage.success(DatabaseCreate.class, "Insert media genre succeeded");
            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseCreate.class, "Insert media genre failed");
            return null;
        }
    }

    public static PreparedStatement insertMediaPerson(int person, String mediaId) {

        try {

            String query = "INSERT INTO tblMediaPerson (fldPersonID, fldMediaID) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, person);
            preparedStatement.setString(2, mediaId);

            debugMessage.success(DatabaseCreate.class, "Insert media person succeeded");
            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseCreate.class, "Insert media person failed");
            return null;
        }

    }

    public static PreparedStatement insertPerson(String firstName, String lastName, String artistName) {

        try {

            String query = "INSERT INTO tblPerson (fldFirstName, fldLastName, fldArtistName) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, artistName);

            debugMessage.success(DatabaseCreate.class, "Insert person succeeded");
            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseCreate.class, "Insert person failed");
            return null;
        }

    }

    public static PreparedStatement insertGenre(String genre) {

        try {

            String query = "INSERT INTO tblGenre (fldGenre) VALUES (?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, genre);

            debugMessage.success(DatabaseCreate.class, "Insert genre succeeded");
            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseCreate.class, "Insert genre failed");
            return null;
        }
    }
}
