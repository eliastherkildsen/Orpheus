package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseUtil;
import mediaplayer.orpheus.model.Media.GeneralMediaObject;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.model.Playlist.PlaylistObj;
import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;

import java.sql.*;
import java.util.ArrayList;

public class    MediaSearch {

    // creating a JDBC connection
    private static final Connection connection = JDBC.instance.getConnection();


    /**
     * Method executing a search in the database fields [tblMedia] & [tblPerson]
     * @param searchCriteria String to search for in the database.
     * @return resultSet of the executed quarry, returns an empty result set if
     *         no fields matching the searchCriteria is found.
     */
    public ResultSet searchMediaForMedia(String searchCriteria) {

        ResultSet resultSet;

        try {

            resultSet = appendQuerySearchForMedia(searchCriteria).executeQuery();

        } catch (SQLException err) {

            debugMessage.error(this,"SearchForMedia: An Error has occurred during the search.");
            return null;

        }
        debugMessage.debug(this,"SearchForMedia: Quarrying for Media related to the search. " + searchCriteria);
        return resultSet;
    }

    public ResultSet searchMediaForPlaylist(String searchCriteria) {

        ResultSet resultSet;


        try {

            resultSet = appendQuerySearchForPlaylist(searchCriteria).executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        debugMessage.debug(this,"Quarrying to Playlist Related to the search: " + searchCriteria);
        return resultSet;
    }


    /**
     * Method for parsing resultSet to String array
     * @param resultSet s
     * @return ArrayList<String[]> a list of String arrays
     *         containing the resultSets data parsed to a formatted String
     */
    public ArrayList<GeneralMediaObject> processResultSetMedia(ResultSet resultSet) {

        ArrayList<GeneralMediaObject> dataSet = new ArrayList<>();

        debugMessage.debug(this,"ResultSetMedia: Processing Result...");
        // loops through the result set.
        while (true) {
            try {
                // breaks out of the loop when end is reached.
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // create media obj for each media returned from the quarry.
            try {

                int mediaID = Integer.parseInt(DatabaseUtil.validateResultNotNull("fldMediaID", resultSet));
                dataSet.add(new GeneralMediaObject(new MediaObj(mediaID)));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return dataSet;

    }
    public ArrayList<GeneralMediaObject> processResultSetPlaylist(ResultSet resultSet) {

        ArrayList<GeneralMediaObject> dataSet = new ArrayList<>();

        debugMessage.debug(this,"Processing Result...");
        // loops through the result set.

        while (true) {
            try {
                // breaks out of the loop when end is reached.
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String playlistName = DatabaseUtil.validateResultNotNull("fldPlaylistName", resultSet);
            dataSet.add(new GeneralMediaObject(new PlaylistObj(playlistName)));

        }

        return dataSet;

    }



    /**
     * Method for appending the sql query needed to search in the database.
     * @param searchCriteria What is being searched for
     * @return PreparedStatement
     */
    private PreparedStatement appendQuerySearchForMedia(String searchCriteria){

        try {

            // Generic quarry for searching the database media ether by artistName, ArtistFirstName, ArtistLastName or MediaTitle.
            // with use of string builder to avoid String concatenation.
            String query = "SELECT tblMedia.fldMediaID " +
                    "FROM tblMedia " +
                    "LEFT JOIN tblMediaPerson ON tblMedia.fldMediaID = tblMediaPerson.fldMediaID " +
                    "LEFT JOIN tblPerson ON tblMediaPerson.fldPersonID = tblPerson.fldPersonID " +
                    "LEFT JOIN tblMediaGenre ON tblMedia.fldMediaID = tblMediaGenre.fldMediaID " +
                    "WHERE " +
                    "tblPerson.fldFirstName LIKE ? OR " +
                    "tblPerson.fldArtistName LIKE ? OR " +
                    "tblPerson.fldLastName LIKE ? OR " +
                    "tblMedia.fldMediaTitle LIKE ? OR " +
                    "tblMedia.fldFileType LIKE ? OR " +
                    "tblMediaGenre.fldGenre LIKE ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, "%" + searchCriteria + "%");
            preparedStatement.setString(2, "%" + searchCriteria + "%");
            preparedStatement.setString(3, "%" + searchCriteria + "%");
            preparedStatement.setString(4, "%" + searchCriteria + "%");
            preparedStatement.setString(5, "%" + searchCriteria + "%");
            preparedStatement.setString(6, "%" + searchCriteria + "%");

            return preparedStatement;

        }catch (SQLException e){

            debugMessage.error(this,"Append Query search for Media FAILED.");
            return null;
        }

    }



    /**
     * Method for appending the sql query needed to search in the database.
     * @param searchCriteria What is being searched for
     * @return SQL query as string
     */
    private PreparedStatement appendQuerySearchForPlaylist(String searchCriteria){

        try {

            // Generic quarry for searching the database media ether by artistName, ArtistFirstName, ArtistLastName or MediaTitle.
            // with use of string builder to avoid String concatenation.
            String query = "SELECT fldPlaylistName FROM tblPlaylist WHERE fldPlaylistName like ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, "%" + searchCriteria + "%");

            return preparedStatement;

        }catch (SQLException e){

            debugMessage.error(this,"Append Query for search for PlayList FAILED");
            return null;
        }

    }


}