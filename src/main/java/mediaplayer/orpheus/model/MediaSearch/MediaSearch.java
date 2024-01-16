package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseUtil;
import mediaplayer.orpheus.util.AnsiColorCode;

import java.sql.*;
import java.util.ArrayList;

public class MediaSearch {

    // creating a JDBC connection
    private static final Connection connection = OrpheusApp.jdbc.getConnection();

    public void deleteMediaFromDatabase(int mediaId){

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String quarry = deleteMediaQuarry(mediaId);

        try {

            preparedStatement = connection.prepareCall(quarry);
            preparedStatement.executeUpdate();

            System.out.printf("%s[DarabaseSearch][deleteMedia] media with %s as media id was deleted%s%n", AnsiColorCode.ANSI_YELLOW, mediaId, AnsiColorCode.ANSI_RESET);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method executing a search in the database fields [tblMedia] & [tblPerson]
     * @param searchCriteria String to search for in the database.
     * @return resultSet of the executed quarry, returns an empty result set if
     *         no fields matching the searchCriteria is found.
     */
    public ResultSet searchMedia(String searchCriteria) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String query = appendQuerySearch(searchCriteria);

        try {
            preparedStatement = connection.prepareCall(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }


    /**
     * Method for parsing resultSet to String array
     * @param resultSet s
     * @return ArrayList<String[]> a list of String arrays
     *         containing the resultSets data parsed to a formatted String
     */
    public ArrayList<String[]> processResultSet(ResultSet resultSet) {

        ArrayList<String[]> dataSet = new ArrayList<>();

        // loops through the result set.
        while (true) {
            try {
                // breaks out of the loop when end is reached.
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String[] data = new String[9];

            // storing values of the result set in a String array, for insuring a fixed index of fields,
            // to make it easy to make a formatted print.

            data[0] = DatabaseUtil.validateResultNotNull("fldArtistName",  resultSet);
            data[1] = DatabaseUtil.validateResultNotNull("fldFirstName",   resultSet);
            data[2] = DatabaseUtil.validateResultNotNull("fldLastName",    resultSet);
            data[3] = DatabaseUtil.validateResultNotNull("fldMediaTitle",  resultSet);
            data[4] = DatabaseUtil.validateResultNotNull("fldFileType",    resultSet);
            data[5] = DatabaseUtil.validateResultNotNull("fldFilePath",    resultSet);
            data[6] = DatabaseUtil.validateResultNotNull("fldTrackLength", resultSet);
            data[7] = DatabaseUtil.validateResultNotNull("fldGenre",       resultSet);
            data[8] = DatabaseUtil.validateResultNotNull("fldMediaID",     resultSet);

            // adds the string array to an arraylist.
            dataSet.add(data);

        }

        return dataSet;

    }


    /**
     * Method for appending the sql query needed to search in the database.
     * @param searchCriteria What is being searched for
     * @return SQL query as string
     */
    private String appendQuerySearch(String searchCriteria){

        // Generic quarry for searching the database media ether by artistName, ArtistFirstName, ArtistLastName or MediaTitle.
        // with use of string builder to avoid String concatenation.
        return new StringBuilder()
                .append("SELECT tblMedia.fldMediaTitle, tblPerson.fldArtistName, ")
                .append("tblMedia.fldTrackLength, tblMedia.fldFilePath, tblPerson.fldFirstName, ")
                .append("tblPerson.fldLastName, tblMediaGenre.fldGenre, tblMedia.fldMediaID ")
                .append("FROM tblMedia ")

                .append("LEFT JOIN tblMediaPerson ON tblMedia.fldMediaID = tblMediaPerson.fldMediaID ")
                .append("LEFT JOIN tblPerson ON tblMediaPerson.fldPersonID = tblPerson.fldPersonID ")
                .append("LEFT JOIN tblMediaGenre ON tblMedia.fldMediaID = tblMediaGenre.fldMediaID ")

                .append("WHERE ")

                .append("tblPerson.fldFirstName LIKE '%")
                .append(searchCriteria)
                .append("%' OR ")

                .append("tblPerson.fldArtistName LIKE '%")
                .append(searchCriteria)
                .append("%' OR ")

                .append("tblPerson.fldLastName LIKE '%")
                .append(searchCriteria)
                .append("' OR ")

                .append("tblMedia.fldMediaTitle LIKE '%")
                .append(searchCriteria)
                .append("%' OR ")

                .append("tblMedia.fldFileType LIKE '%")
                .append(searchCriteria)
                .append("%' OR ")

                .append("tblMediaGenre.fldGenre LIKE '%")
                .append(searchCriteria)
                .append("%'")

                .toString();

    }

    private String deleteMediaQuarry(int mediaID){


        return new StringBuilder()
                .append("DELETE FROM tblMediaGenre WHERE fldMediaID= ")
                .append(mediaID)

                .append("DELETE FROM tblMediaPerson WHERE fldMediaID= ")
                .append(mediaID)

                .append("DELETE FROM tblMediaPlaylist WHERE fldMediaID= ")
                .append(mediaID)

                .append("DELETE FROM tblMedia WHERE fldMediaID= ")
                .append(mediaID)

                .toString();

    }


}