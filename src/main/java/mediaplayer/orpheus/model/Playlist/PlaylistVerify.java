package mediaplayer.orpheus.model.Playlist;

import mediaplayer.orpheus.model.Database.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistVerify {

    // creating a JDBC connection
    private static final JDBC jdbc = new JDBC();
    private static final Connection connection = jdbc.getConnection();

    /**
     * Method for verifying the playlist name
     * @param playlistName Given playlist name
     * @return Returns the opposite of the next() method
     */
    public static boolean verifyPlaylistName(String playlistName){

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        // Gets the needed query to verify if there already exists a playlist with that name
        String query = verificationQuery(playlistName);

        try{
            // Making the prepared statement
            preparedStatement = connection.prepareCall(query);
            // Executes the prepared statement and assigns the result to a result set
            resultSet = preparedStatement.executeQuery();

            return !resultSet.next();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    /**
     * Method for making the needed query for verifying if a name is available
     * @param playlistName Given playlist name
     * @return Returns the SQL query in the form of a string
     */
    private static String verificationQuery(String playlistName){

        return new StringBuilder()
                .append("SELECT fldPlaylistName FROM tblPlaylist WHERE fldPlaylistName = '")
                .append(playlistName)
                .append("'").toString();

    }
}
