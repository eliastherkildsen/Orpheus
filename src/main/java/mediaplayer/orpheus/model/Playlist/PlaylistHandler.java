package mediaplayer.orpheus.model.Playlist;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.MediaSearch.MediaSearchUtil;
import mediaplayer.orpheus.util.AlertPopup;

import java.sql.*;

public class PlaylistHandler {

    private static final Connection connection = OrpheusApp.jdbc.getConnection();

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

    public static void addMediaToPlaylist(int mediaID, String choiceBoxIndex){

        PreparedStatement pSTrackOrder;
        PreparedStatement pSInsertIntoPlaylist;
        ResultSet resultSetTrackOrder;

        try {

            pSTrackOrder = connection.prepareCall(DatabaseRead.getMaxTrackOrder(choiceBoxIndex));
            resultSetTrackOrder = pSTrackOrder.executeQuery();


            if(resultSetTrackOrder.next()){
                int nextTackOrder = resultSetTrackOrder.getInt("fldTrackOrder") + 1;
                System.out.println(nextTackOrder);
                String insertQuery = insertMediaQuery(mediaID, choiceBoxIndex, nextTackOrder);

                pSInsertIntoPlaylist = connection.prepareCall(insertQuery);
                pSInsertIntoPlaylist.executeUpdate();

                AlertPopup alertPopupPlaylistAdded = new AlertPopup("Success"
                        , "Media has been added to " + choiceBoxIndex + ".");
                alertPopupPlaylistAdded.showInformation();

            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }



    }

    private static String insertMediaQuery(int mediaID, String playlistName, int trackOrder){

        return new StringBuilder()
                .append("INSERT INTO tblMediaPlaylist (fldPlaylistName, fldMediaID, fldTrackOrder) ")
                .append("VALUES ('")
                .append(playlistName)
                .append("', ")
                .append(mediaID)
                .append(", ")
                .append(trackOrder)
                .append(")")
                .toString();

    }


}
