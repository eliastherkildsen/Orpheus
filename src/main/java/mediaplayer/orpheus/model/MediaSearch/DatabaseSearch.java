package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.model.Database.JDBC;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseSearch {

    // creating JDBC connection
    private static JDBC jdbc = new JDBC();
    private static Connection connection = jdbc.getConnection();

    public ResultSet searchMedia(String searchCriteria) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        // Generic quarry for shearching the database media ether by artistName, ArtistFirstName, ArtistLastName or MediaTitle.
        String query = "SELECT tblMedia.fldMediaTitle, tblPerson.fldArtistName, tblMedia.fldTrackLength, tblMedia.fldFilePath " +
                "FROM tblMedia " +
                "LEFT JOIN tblMediaPerson ON tblMedia.fldMediaID = tblMediaPerson.fldMediaID " +
                "LEFT JOIN tblPerson ON tblMediaPerson.fldPersonID = tblPerson.fldPersonID " +
                "WHERE " +
                "tblPerson.fldFirstName LIKE '%"+ searchCriteria +"%' or " +
                "tblPerson.fldArtistName LIKE '%"+ searchCriteria +"%' or " +
                "tblPerson.fldLastName LIKE '%"+ searchCriteria +"' or " +
                "tblMedia.fldMediaTitle LIKE '%"+ searchCriteria +"%' or " +
                "tblMedia.fldFileType LIKE '%"+ searchCriteria +"%'";


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

    public ArrayList<String[]> processResultSet(ResultSet resultSet) {
        String artistName, firstName, lastName, MediaTitle, fileType;
        ArrayList<String[]> dataSet = new ArrayList<>();
        String[] data = new String[5];

        // loops through the result set.
        while (true) {
            try {
                // breaks out of the loop when end is reached.
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // storing values of the result set.
            data[0] = validataResultNotNull("fldArtistName", resultSet);
            data[1] = validataResultNotNull("fldFirstName", resultSet);
            data[2] = validataResultNotNull("fldLastName", resultSet);
            data[3] = validataResultNotNull("fldMediaTitle", resultSet);
            data[4] = validataResultNotNull("fldFileType", resultSet);
            dataSet.add(data);
        }

        return dataSet;
    }


    public String validataResultNotNull(String fieldName, ResultSet resultSet){
        try {
            return resultSet.getString(fieldName);
        } catch (SQLException e) {
            return "NULL";
        }

    }

}



