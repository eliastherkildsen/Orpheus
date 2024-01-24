package mediaplayer.orpheus.model.Playlist;

import mediaplayer.orpheus.Controler.HomeViewController;
import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.util.AlertPopup;
import mediaplayer.orpheus.util.AnsiColorCode;

import java.sql.*;

public class PlaylistHandler {

    private static final Connection connection = JDBC.instance.getConnection();

    /**
     * Method for creating a new playlist
     * @param playListName
     */
    public static void createPlaylist(String playListName){

        // Checks if there is anything entered
        if(playListName.isEmpty()){
            // Error popup to tell the user what is wrong
            AlertPopup alertPopupNoName = new AlertPopup("Name text is empty"
                    , "Please enter another name for the playlist.");
            alertPopupNoName.showError();
            // Checks if there is anything entered
        }else if(PlaylistVerify.verifyPlaylistName(playListName)){

            PreparedStatement preparedStatement;
            String query = insertQuery(playListName);
            // Tries to execute the prepared statement
            try{

                preparedStatement = connection.prepareCall(query);
                preparedStatement.executeUpdate();
                // Information popup to tell the user that the playlist has been created
                AlertPopup alertPopupPlaylistAdded = new AlertPopup("Success"
                        , "Playlist has been created.");
                alertPopupPlaylistAdded.showInformation();
                System.out.printf("%s[PlaylistHandler][createPlaylist] Playlist has been created%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);


            } catch (SQLException e){

                System.out.printf("%s[PlaylistHandler][createPlaylist] Could not add the playlist%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

            }

        }else {
            // Error popup to tell the user that a playlist is already named this
            AlertPopup alertPopupAlreadyExists = new AlertPopup("Playlist name Error"
                    ,"A playlist with this name already exists.");
            alertPopupAlreadyExists.showError();

        }

    }

    /**
     * Method that makes a string in the form of the desired SQL query
     * @param playlistName
     * @return
     */
    private static String insertQuery(String playlistName) {

        return new StringBuilder().append("INSERT INTO tblPlaylist (fldPlaylistName) VALUES ('")
                .append(playlistName)
                .append("')").toString();


    }

    /**
     *  Method that adds the selected media to the selected playlist
     * @param mediaID
     * @param choiceBoxIndex
     */
    public static void addMediaToPlaylist(int mediaID, String choiceBoxIndex){

        PreparedStatement pSTrackOrder;
        PreparedStatement pSInsertIntoPlaylist;
        ResultSet resultSetTrackOrder;
        int nextTackOrder;
        // Tries to add the media to the playlist

        AlertPopup alertPopupPlaylistAdded = new AlertPopup("Success"
                    , "MediaObj has been added to " + choiceBoxIndex + ".");

        try {

            pSTrackOrder = connection.prepareCall(DatabaseRead.getMaxTrackOrder(choiceBoxIndex));
            resultSetTrackOrder = pSTrackOrder.executeQuery();

            // Checks if there already is media within the playlist
            if(resultSetTrackOrder.next()){
                // Gets the max Track order and adds 1 to it
                nextTackOrder = resultSetTrackOrder.getInt("fldTrackOrder") + 1;
                // If there is no media in the playlist the track order is set to 1
            }else{
                nextTackOrder = 1;
            }

            String insertQuery = insertMediaQuery(mediaID, choiceBoxIndex, nextTackOrder);
            // Creates prepared statement
            pSInsertIntoPlaylist = connection.prepareCall(insertQuery);
            // Executes the prepared statement
            pSInsertIntoPlaylist.executeUpdate();
            // Information popup to tell the user that the media has been added
            alertPopupPlaylistAdded.showInformation();
            System.out.printf("%s[PlaylistHandler][addMediaToPlaylist] Media has been added to the playlist%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        }catch (SQLException e){
            System.out.printf("%s[PlaylistHandler][addMediaToPlaylist] Could not add media to the playlist%s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }


    }

    /**
     * Method for making the SQL query needed to insert media into a playlist
     * @param mediaID
     * @param playlistName
     * @param trackOrder
     * @return
     */
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

    /**
     * Method for deleting a playlist
     * @param playlistName
     */
    public static void deletePlaylist(String playlistName){

        PreparedStatement psDeleteFromPlaylist;
        PreparedStatement psDeleteFromMediaPlaylist;
        // Getting the SQL queries needed to delete a playlist and all the relations between the media and the playlist
        String queryDeletePlaylist = deleteFromPlaylistQuery(playlistName);
        String queryDeleteMediaPlaylist = deleteFromMediaPlaylistQuery(playlistName);

        try{
            // Prepares the prepared statements
            psDeleteFromPlaylist = connection.prepareCall(queryDeletePlaylist);
            psDeleteFromMediaPlaylist = connection.prepareCall(queryDeleteMediaPlaylist);
            // Executes the queries
            psDeleteFromMediaPlaylist.executeUpdate();
            psDeleteFromPlaylist.executeUpdate();
            System.out.printf("%s[PlaylistHandler][deletePlaylist] Playlist has been deleted%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);


        }catch (SQLException e) {
            System.out.printf("%s[PlaylistHandler][deletePlaylist] Could not delete the playlist%s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);

        }

    }

    /**
     * Method that makes the SQL query for deleting the chosen playlist
     * @param playlistName
     * @return
     */
    private static String deleteFromPlaylistQuery(String playlistName){

        return new StringBuilder()
                .append("Delete FROM tblPlaylist WHERE fldPlaylistName = '")
                .append(playlistName)
                .append("'")
                .toString();

    }

    /**
     * Method that makes the SQL query that deletes the relations between media and the playlist
     * @param playlistName
     * @return
     */
    private static String deleteFromMediaPlaylistQuery(String playlistName){

        return new StringBuilder()
                .append("DELETE FROM tblMediaPlaylist WHERE fldPlaylistName = '")
                .append(playlistName)
                .append("'")
                .toString();

    }

    /**
     * Method that creates the media queue from a playlist
     * @param playlistName
     */
    public static void createMediaArray(String playlistName){
        // Clears the media queue
        HomeViewController.mediaObjQue.clear();
        // Resets the counter
        HomeViewController.cntQue = 0;

        PreparedStatement psMediaID;
        // Gets the SQL query that gets all mediaID
        String query = DatabaseRead.getMediaIDFromPlaylistName(playlistName);
        try{
            // Prepares the prepared statement
            psMediaID = connection.prepareCall(query);
            // Executes query
            ResultSet rsMediaID = psMediaID.executeQuery();
            // While there is something more in the result set run the loop
            while (rsMediaID.next()){
                // Gets the media id from the result set
                int mediaIDFromResultset = rsMediaID.getInt("fldMediaID");
                // Creates a media object and adds it to the mediaObjQue
                HomeViewController.mediaObjQue.add(new MediaObj(mediaIDFromResultset));

            }
            // Checks if the queue is empty
            if(HomeViewController.mediaObjQue.isEmpty()){
                // Waring popup that tells the user that the playlist is empty
                AlertPopup emptyPlaylist = new AlertPopup("Empty Playlist"
                        , "The selected playlist is empty.\nAdd media to be able to listen to the playlist.");

                emptyPlaylist.showWarning();

                System.out.printf("%s[PlaylistHandler][createMediaArray] Media Queue is empty%s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        System.out.printf("%s[PlaylistHandler][createMediaArray] Media Queue has been created%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

    }

}
