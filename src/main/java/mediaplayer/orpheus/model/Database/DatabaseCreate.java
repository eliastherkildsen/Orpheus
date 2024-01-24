package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.util.AnsiColorCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseCreate {

    private static final Connection connection = JDBC.instance.getConnection();

    //INSERT INTO tblMediaGenre (fldGenre, fldMediaID) VALUES ('Pop', 29)
    public static PreparedStatement insertMediaGenre(String genre, String mediaId){

        try {

            String query = "INSERT INTO tblMediaGenre (fldGenre, fldMediaID) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, genre);
            preparedStatement.setString(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseCreate][insertMediaGenre] Insert media genre failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }
    //INSERT INTO tblMediaPerson(fldMediaID, fldPersonID) VALUES (29, 16)
    public static PreparedStatement insertMediaPerson(int person, String mediaId){

        try {

            String query = "INSERT INTO tblMediaPerson (fldPersonID, fldMediaID) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, person);
            preparedStatement.setString(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseCreate][insertMediaPerson] Insert media person failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    //INSERT INTO tblPerson (fldFirstName, fldLastName, fldArtistName) VALUES ()
    public static PreparedStatement insertPerson(String firstName, String lastName, String artistName){

        try {

            String query = "INSERT INTO tblPerson (fldFirstName, fldLastName, fldArtistName) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, artistName);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseCreate][insertPerson] Insert person failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }


    public static PreparedStatement insertGenre(String genre) {

        try {

            String query = "INSERT INTO tblGenre (fldGenre) VALUES (?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, genre);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseCreate][insertGenre] Insert genre failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }
}
