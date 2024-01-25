package mediaplayer.orpheus.model.Playlist;

import mediaplayer.orpheus.Controler.HomeViewController;
import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.util.AlertPopup;
import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;

import java.sql.*;

public class PlaylistHandler {

    private static final Connection connection = JDBC.instance.getConnection();

    /**
     * Method for creating a new playlist
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

            // Tries to execute the prepared statement
            try{

                insertQuery(playListName).executeUpdate();
                // Information popup to tell the user that the playlist has been created
                AlertPopup alertPopupPlaylistAdded = new AlertPopup("Success"
                        , "Playlist has been created.");
                alertPopupPlaylistAdded.showInformation();
                debugMessage.debug(PlaylistHandler.class,"Playlist has been created.");

            } catch (SQLException e){

                debugMessage.error(PlaylistHandler.class, "Could not add the Playlist.");
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
     * @return Returns a SQL query in string from
     */
    private static PreparedStatement insertQuery(String playlistName) throws SQLException {

        String query = "INSERT INTO tblPlaylist (fldPlaylistName) VALUES (?)";

        PreparedStatement preparedStatement = connection.prepareCall(query);
        preparedStatement.setString(1, playlistName);

        return preparedStatement;
    }

    /**
     *  Method that adds the selected media to the selected playlist
     */
    public static void addMediaToPlaylist(int mediaID, String choiceBoxIndex){

        ResultSet resultSetTrackOrder;
        int nextTackOrder;
        // Tries to add the media to the playlist

        AlertPopup alertPopupPlaylistAdded = new AlertPopup("Success"
                    , "MediaObj has been added to " + choiceBoxIndex + ".");

        try {
            // executes query
            resultSetTrackOrder = DatabaseRead.getMaxTrackOrder(choiceBoxIndex).executeQuery();

            // Checks if there already is media within the playlist
            if(resultSetTrackOrder.next()){
                // Gets the max Track order and adds 1 to it
                nextTackOrder = resultSetTrackOrder.getInt("fldTrackOrder") + 1;
                // If there is no media in the playlist the track order is set to 1
            }else{
                nextTackOrder = 1;
            }

            // Executes the prepared statement
            insertMediaQuery(mediaID, choiceBoxIndex, nextTackOrder).executeUpdate();
            // Information popup to tell the user that the media has been added
            alertPopupPlaylistAdded.showInformation();
            debugMessage.debug(PlaylistHandler.class,"Media has been added to the Playlist.");
        }catch (SQLException e){
            debugMessage.error(PlaylistHandler.class,"Could not add media to the PlayList");
        }


    }

    /**
     * Method for making the SQL query needed to insert media into a playlist
     */
    private static PreparedStatement insertMediaQuery(int mediaID, String playlistName, int trackOrder) {

        String query = "INSERT INTO tblMediaPlaylist (fldPlaylistName, fldMediaID, fldTrackOrder) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, playlistName);
            preparedStatement.setInt(2, mediaID);
            preparedStatement.setInt(3, trackOrder);

            return preparedStatement;

        }catch (SQLException e){
            debugMessage.error(PlaylistHandler.class,"Insert Media Query Failed.");
            return null;
        }

    }

    /**
     * Method for deleting a playlist
     */
    public static void deletePlaylist(String playlistName){

        try{

            // Executes the queries
            deleteFromMediaPlaylistQuery(playlistName).executeUpdate();
            deleteFromPlaylistQuery(playlistName).executeUpdate();
            debugMessage.debug(PlaylistHandler.class,"PlayList has been deleted.");

        }catch (SQLException e) {
            debugMessage.error(PlaylistHandler.class,"Could not delete the PlayList.");
        }

    }

    /**
     * Method for making the prepared statement to delete playlists
     */
    private static PreparedStatement deleteFromPlaylistQuery(String playlistName) {


        try{

            String query = "Delete FROM tblPlaylist WHERE fldPlaylistName = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, playlistName);

            return preparedStatement;

        }catch(SQLException e){
            debugMessage.error(PlaylistHandler.class,"Delete from the PlayList Query failed.");
            return null;
        }
    }

    /**
     * Method for making the prepared statement to delete media playlist
     */
    public static PreparedStatement deleteFromMediaPlaylistQuery(String playlistName) {

        try{

            String query = "DELETE FROM tblMediaPlaylist WHERE fldPlaylistName = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, playlistName);

            return preparedStatement;

        }catch (SQLException e){

            debugMessage.error(PlaylistHandler.class,"Delete from Media PlayList Query Failed.");
            return null;
        }
    }

    /**
     * Method that creates the media queue from a playlist
     */
    public static void createMediaArray(String playlistName){
        // Clears the media queue
        HomeViewController.mediaObjQue.clear();
        // Resets the counter
        HomeViewController.cntQue = 0;

        // Gets the SQL query that gets all mediaID
        try{

            // Executes query
            ResultSet rsMediaID = DatabaseRead.getMediaIDFromPlaylistName(playlistName).executeQuery();
            // While there is something more in the result set run the loop
            while (rsMediaID.next()){
                // Gets the media id from the result set
                int mediaIDFromResultSet = rsMediaID.getInt("fldMediaID");
                // Creates a media object and adds it to the mediaObjQue
                HomeViewController.mediaObjQue.add(new MediaObj(mediaIDFromResultSet));

            }
            // Checks if the queue is empty
            if(HomeViewController.mediaObjQue.isEmpty()){
                // Waring popup that tells the user that the playlist is empty
                AlertPopup emptyPlaylist = new AlertPopup("Empty Playlist"
                        , "The selected playlist is empty.\nAdd media to be able to listen to the playlist.");

                emptyPlaylist.showWarning();
                debugMessage.error(PlaylistHandler.class,"CreateMediaArray: Media Queue is Empty.");
                return;

            }
            debugMessage.debug(PlaylistHandler.class,"Media Queue has been created.");

        }catch (SQLException e){
            debugMessage.error(PlaylistHandler.class,"Media Queue has not been created.");
        }


    }

}
