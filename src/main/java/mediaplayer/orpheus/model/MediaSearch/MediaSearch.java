package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseUtil;
import mediaplayer.orpheus.model.Media.MediaObj;

import java.sql.*;
import java.util.ArrayList;

public class MediaSearch {

    // creating a JDBC connection
    private static final Connection connection = JDBC.instance.getConnection();


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
    public ArrayList<MediaObj> processResultSet(ResultSet resultSet) {

        ArrayList<MediaObj> dataSet = new ArrayList<>();

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
                dataSet.add(new MediaObj(mediaID));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

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
                .append("SELECT tblMedia.fldMediaID ")
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

}