package mediaplayer.orpheus.model.Playlist;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.util.AlertPopup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaylistHandler {

    private static final JDBC jdbc = new JDBC();
    private static final Connection connection = jdbc.getConnection();

    public static void createPlaylist(String playListName){

        if(!PlaylistVerify.emptyPlaylistName(playListName)){

            AlertPopup alertPopupNoName = new AlertPopup("Name text is empty"
                    , "Please enter another name for the playlist.");
            alertPopupNoName.showError();

        }else if(PlaylistVerify.verifyPlaylistName(playListName)){

            PreparedStatement preparedStatement;
            String query = insertQuery(playListName);

            try{

                preparedStatement = connection.prepareCall(query);
                preparedStatement.executeUpdate();

                AlertPopup alertPopupPlaylistAdded = new AlertPopup("Success"
                        , "Playlist has been created.");
                alertPopupPlaylistAdded.showInformation();

            } catch (SQLException e){

                throw new RuntimeException(e);

            }

        }else {

            AlertPopup alertPopupAlreadyExists = new AlertPopup("Playlist name Error"
                    ,"A playlist with this name already exists.");
            alertPopupAlreadyExists.showError();

        }


    }

    private static String insertQuery(String playlistName) {

        return new StringBuilder().append("INSERT INTO tblPlaylist (fldPlaylistName) VALUES ('")
                .append(playlistName)
                .append("')").toString();


    }

}
