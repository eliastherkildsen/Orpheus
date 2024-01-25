package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDelete {

    private static final Connection connection = JDBC.instance.getConnection();

    public static PreparedStatement deleteMediaQuarry(int mediaID) {

        try {

            String query = "DELETE FROM tblMediaGenre WHERE fldMediaID= ?;" +
                    "DELETE FROM tblMediaPerson WHERE fldMediaID= ?;" +
                    "DELETE FROM tblMediaPlaylist WHERE fldMediaID= ?;" +
                    "DELETE FROM tblMedia WHERE fldMediaID= ?;";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaID);
            preparedStatement.setInt(2, mediaID);
            preparedStatement.setInt(3, mediaID);
            preparedStatement.setInt(4, mediaID);

            debugMessage.debug(DatabaseDelete.class, "Delete Media succeeded");
            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseDelete.class, "Delete Media failed");
            return null;
        }
    }
}
