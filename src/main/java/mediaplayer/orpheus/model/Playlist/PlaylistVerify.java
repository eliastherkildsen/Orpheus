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

    public static boolean verifyPlaylistName(String playlistName){



        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String query = verificationQuery(playlistName);

        try{

            preparedStatement = connection.prepareCall(query);
            resultSet = preparedStatement.executeQuery();

            return !resultSet.next();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private static String verificationQuery(String playlistName){

        return new StringBuilder()
                .append("SELECT fldPlaylistName FROM tblPlaylist WHERE fldPlaylistName = '")
                .append(playlistName)
                .append("'").toString();

    }

    public static boolean emptyPlaylistName(String playListName){

        return !playListName.isEmpty();
    }

}
