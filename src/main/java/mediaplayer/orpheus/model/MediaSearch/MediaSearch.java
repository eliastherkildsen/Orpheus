package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseUtil;
import mediaplayer.orpheus.model.Media.GeneralMediaObject;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.model.Playlist.PlaylistObj;
import mediaplayer.orpheus.util.AnsiColorCode;

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

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String query = appendQuerySearchForMedia(searchCriteria);

        try {

            preparedStatement = connection.prepareCall(query);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException err) {

            System.out.printf("%s[MediaSearch][searchMediaForMedia] An error occurred: " +
                    "%s %s%n", AnsiColorCode.ANSI_RED, err, AnsiColorCode.ANSI_RESET);

            return null;

        }

        System.out.printf("%s[MediaSearch][searchMediaForMedia] Quarrying for media related to the search: " +
                "%s %s%n", AnsiColorCode.ANSI_YELLOW, searchCriteria, AnsiColorCode.ANSI_RESET);

        return resultSet;
    }

    public ResultSet searchMediaForPlaylist(String searchCriteria) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String query = appendQuerySearchForPlaylist(searchCriteria);

        try {

            preparedStatement = connection.prepareCall(query);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%s[MediaSearch][searchMediaForPlaylist] Quarrying for playlists related to the search: " +
                "%s %s%n", AnsiColorCode.ANSI_YELLOW, searchCriteria, AnsiColorCode.ANSI_RESET);


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

        System.out.printf("%s[MediaSearch][processResultSetMedia] processing result... " +
                " %s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

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

        System.out.printf("%s[MediaSearch][processResultSetPlaylist] processing result... " +
                " %s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

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
     * @return SQL query as string
     */
    private String appendQuerySearchForMedia(String searchCriteria){

        // Generic quarry for searching the database media ether by artistName, ArtistFirstName, ArtistLastName or MediaTitle.
        // with use of string builder to avoid String concatenation.
        return "SELECT tblMedia.fldMediaID " +
                "FROM tblMedia " +
                "LEFT JOIN tblMediaPerson ON tblMedia.fldMediaID = tblMediaPerson.fldMediaID " +
                "LEFT JOIN tblPerson ON tblMediaPerson.fldPersonID = tblPerson.fldPersonID " +
                "LEFT JOIN tblMediaGenre ON tblMedia.fldMediaID = tblMediaGenre.fldMediaID " +
                "WHERE " +
                "tblPerson.fldFirstName LIKE '%" +
                searchCriteria +
                "%' OR " +
                "tblPerson.fldArtistName LIKE '%" +
                searchCriteria +
                "%' OR " +
                "tblPerson.fldLastName LIKE '%" +
                searchCriteria +
                "' OR " +
                "tblMedia.fldMediaTitle LIKE '%" +
                searchCriteria +
                "%' OR " +
                "tblMedia.fldFileType LIKE '%" +
                searchCriteria +
                "%' OR " +
                "tblMediaGenre.fldGenre LIKE '%" +
                searchCriteria +
                "%'";

    }



    /**
     * Method for appending the sql query needed to search in the database.
     * @param searchCriteria What is being searched for
     * @return SQL query as string
     */
    private String appendQuerySearchForPlaylist(String searchCriteria){

        // Generic quarry for searching the database media ether by artistName, ArtistFirstName, ArtistLastName or MediaTitle.
        // with use of string builder to avoid String concatenation.
        return "SELECT fldPlaylistName FROM tblPlaylist WHERE fldPlaylistName like '" +
                searchCriteria +
                "%'";

    }


}