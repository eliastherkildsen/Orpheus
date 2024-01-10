package mediaplayer.orpheus.model.MediaSearch;


import mediaplayer.orpheus.model.Database.JDBC;
import java.sql.*;
import static mediaplayer.orpheus.util.AnsiColorCode.ANSI_GREEN;
import static mediaplayer.orpheus.util.AnsiColorCode.ANSI_RESET;

public class DatabaseSearch {

    // creating JDBC connection
    private static JDBC jdbc = new JDBC();
    private static Connection connection = jdbc.getConnection();

    public void searchMedia(String searchCriteria) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
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

        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String artistName, firstName, lastName, MediaTitle, fileType;

        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                artistName = resultSet.getString("fldArtistName");

                try {
                    firstName = resultSet.getString("fldFirstName");
                } catch (SQLException e){
                    firstName = "NULL";
                }

                try {
                    lastName = resultSet.getString("fldLastName");
                } catch (SQLException e) {
                    lastName = "NULL";
                }
                try{
                MediaTitle = resultSet.getString("fldMediaTitle");
                } catch (SQLException e) {
                    MediaTitle = "NULL";
                }

                try{
                fileType = resultSet.getString("fldFileType");
                } catch (SQLException e) {
                    fileType = "NULL";
                }

                System.out.printf("%s%s%n",ANSI_GREEN,"ArtistName: " + artistName);
                System.out.printf("%s%n","FirstNAme: " + firstName);
                System.out.printf("%s%n","LastName: " + lastName);
                System.out.printf("%s%n","fileType: " + fileType);
                System.out.printf("%s%s%n","MediaTitle: " + MediaTitle,ANSI_RESET);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

}