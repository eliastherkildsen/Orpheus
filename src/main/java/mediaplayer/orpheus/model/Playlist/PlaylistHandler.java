package mediaplayer.orpheus.model.Playlist;

import mediaplayer.orpheus.Controler.HomeViewController;
import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;
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
                String insertQuery = insertMediaQuery(mediaID, choiceBoxIndex, nextTackOrder);

                pSInsertIntoPlaylist = connection.prepareCall(insertQuery);
                pSInsertIntoPlaylist.executeUpdate();

                AlertPopup alertPopupPlaylistAdded = new AlertPopup("Success"
                        , "MediaObj has been added to " + choiceBoxIndex + ".");
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

    public static void deletePlaylist(String playlistName){

        PreparedStatement psDeleteFromPlaylist;
        PreparedStatement psDeleteFromMediaPlaylist;

        String queryDeletePlaylist = deleteFromPlaylistQuery(playlistName);
        String queryDeleteMediaPlaylist = deleteFromMediaPlaylistQuery(playlistName);

        try{

            psDeleteFromPlaylist = connection.prepareCall(queryDeletePlaylist);
            psDeleteFromMediaPlaylist = connection.prepareCall(queryDeleteMediaPlaylist);

            psDeleteFromPlaylist.executeUpdate();
            psDeleteFromMediaPlaylist.executeUpdate();


        }catch (SQLException e) {throw new RuntimeException(e);}

    }

    private static String deleteFromPlaylistQuery(String playlistName){

        return new StringBuilder()
                .append("Delete FROM tblPlaylist WHERE fldPlaylistName = '")
                .append(playlistName)
                .append("'")
                .toString();

    }

    private static String deleteFromMediaPlaylistQuery(String playlistName){

        return new StringBuilder()
                .append("DELETE FROM tblMediaPlaylist WHERE fldPlaylistName = '")
                .append(playlistName)
                .append("'")
                .toString();

    }

    private static void createMediaArray(String playlistName){

        HomeViewController.mediaObjQue.clear();
        HomeViewController.queCnt = 0;


        //HomeViewController.mediaObjQue.add();

    }

}
