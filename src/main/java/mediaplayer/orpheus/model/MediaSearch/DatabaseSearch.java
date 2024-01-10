package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.model.Database.JDBC;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseSearch {

    // creating a JDBC connection
    private static final JDBC jdbc = new JDBC();
    private static final Connection connection = jdbc.getConnection();
    /**
     * Method executing a search in the database fields [tblMedia] & [tblPerson]
     * @param searchCriteria String to search for in the database.
     * @return resultSet of the executed quarry, returns an empty result set if
     *         no fields matching the searchCriteria is found.
     */
    public ResultSet searchMedia(String searchCriteria) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        // Generic quarry for searching the database media ether by artistName, ArtistFirstName, ArtistLastName or MediaTitle.
        // with use of string builder to avoid String concatenation.
        String query = new StringBuilder().append("SELECT tblMedia.fldMediaTitle, tblPerson.fldArtistName, " +
                        "tblMedia.fldTrackLength, tblMedia.fldFilePath, tblPerson.fldFirstName, tblPerson.fldLastName ")
                .append("FROM tblMedia ")
                .append("LEFT JOIN tblMediaPerson ON tblMedia.fldMediaID = tblMediaPerson.fldMediaID ")
                .append("LEFT JOIN tblPerson ON tblMediaPerson.fldPersonID = tblPerson.fldPersonID ")
                .append("WHERE ")
                .append("tblPerson.fldFirstName LIKE '%")
                .append(searchCriteria)
                .append("%' or ")
                .append("tblPerson.fldArtistName LIKE '%")
                .append(searchCriteria)
                .append("%' or ")
                .append("tblPerson.fldLastName LIKE '%")
                .append(searchCriteria)
                .append("' or ")
                .append("tblMedia.fldMediaTitle LIKE '%")
                .append(searchCriteria)
                .append("%' or ")
                .append("tblMedia.fldFileType LIKE '%")
                .append(searchCriteria)
                .append("%'").toString();

        try {
            preparedStatement = connection.prepareCall(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // executes quarry
        try {
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

            String[] data = new String[7];

            // storing values of the result set in a String array, for insuring a fixed index of fields,
            // to make it easy to make a formatted print.

            data[0] = validateResultNotNull("fldArtistName",  resultSet);
            data[1] = validateResultNotNull("fldFirstName",   resultSet);
            data[2] = validateResultNotNull("fldLastName",    resultSet);
            data[3] = validateResultNotNull("fldMediaTitle",  resultSet);
            data[4] = validateResultNotNull("fldFileType",    resultSet);
            data[5] = validateResultNotNull("fldFilePath",    resultSet);
            data[6] = validateResultNotNull("fldTrackLength", resultSet);

            // adds the string array to an arraylist.
            dataSet.add(data);

        }

        return dataSet;

    }
    /**
     * Method for checking if a field is empty, if not it returns the String value of the field data.
     * @param fieldName reference to the field in the result to search for.
     * @param resultSet reference to the result to search through.
     * @return String value of the field. or "NULL" if the database field is empty.
     */
    public String validateResultNotNull(String fieldName, ResultSet resultSet){
        try {
            return resultSet.getString(fieldName);
        } catch (SQLException e) {
            return "NULL";
        }

    }
}