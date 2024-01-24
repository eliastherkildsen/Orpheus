package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.util.AnsiColorCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUpdate {

    private static final Connection connection = JDBC.instance.getConnection();

    public static PreparedStatement setMediaTitle(String newTitle, int mediaId){

        try {

            String query = "UPDATE tblMedia SET fldMediaTitle = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, newTitle);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaTitle] Set media title failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    /**
     * Note, this method does not check if the mediaType is valid for the DB. It assumes you know what you are doing.
     * @param fileType file extension, mp3 or mp4
     * @param mediaId mediaId from DB
     * @return PreparedStatement
     */
    public static PreparedStatement setMediaType(String fileType, int mediaId){

        try {

            String query = "UPDATE tblMedia SET fldFileType = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, fileType);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaType] Set media type failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement setMediaAlbum(String albumName, int mediaId){

        try {

            String query = "UPDATE tblMedia SET fldAlbum = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, albumName);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaAlbum] Set media album failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement setMediaYear(String year, int mediaId){

        try {

            String query = "UPDATE tblMedia SET fldMediaYear = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, year);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaYear] Set media year failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement setMediaTrack(int trackNumber, int mediaId){

        try {

            String query = "UPDATE tblMedia SET fldMediaTrack = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, trackNumber);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaYear] Set media year failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement setMediaLength(int LengthInSeconds, int mediaId){

        try {

            String query = "UPDATE tblMedia SET fldTrackLength = ? WHERE fldMediaId = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, LengthInSeconds);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaLength] Set media length failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement setMediaImagePath(String imagePath, int mediaId){

        try {

            String query = "UPDATE tblMedia SET fldImagePath = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, imagePath);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaImagePath] Set media image path failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement setFilePath(String filepath, int mediaId){

        try {

            String query = "Update tblMedia SET fldFilePath = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, filepath);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setFilePath] Set media file path failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }



    /**
     * Setting the Artist name for the person.
     * @param artistName String
     * @param personId Int
     * @return PreparedStatement
     */
    public static PreparedStatement setArtistName(String artistName,int personId){

        try {

            String query = "Update tblPerson SET fldArtistName = ? WHERE fldPersonID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, artistName);
            preparedStatement.setInt(2, personId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setArtistName] Set media artist name failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement setPersonFirstName(String firstName, int personId){

        try {

            String query = "Update tblPerson SET fldFirstName = ? WHERE fldPersonID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, personId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setPersonFirstName] Set person first name failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement setPersonLastName(String lastName, int personId){

        try {

            String query = "Update tblPerson SET fldLastName = ? WHERE fldPersonID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, personId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setPersonLastName] Set person last name failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    /**
     * Setting the PersonId in relation to the MediaObj
     * @param personId Int
     * @param mediaId Int
     * @return PreparedStatement
     */
    public static PreparedStatement setMediaArtist(String personId, int mediaId){

        try {

            String query = "Update tblMediaPerson SET fldPersonID = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, personId);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaArtist] Set media artist failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
        /**
     * Setting the genre in relation to the MediaObj
     * @param genre Int
     * @param mediaId Int
     * @return PreparedStatement
     */
    public static PreparedStatement setMediaGenre(String genre, int mediaId){

        try {

            String query = "UPDATE tblMediaGenre SET fldGenre = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, genre);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaGenre] Set media genre failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }

    /**
     * Updates the Genre for a specific song. Obviously the Genre needs to match a Genre in the tblGenre.
     * @param updatedGenre String
     * @param mediaId int
     * @return String query
     */
    public static PreparedStatement updateMediaGenre(String updatedGenre, int mediaId){

        try {

            String query = "UPDATE tblMediaGenre SET fldGenre = ? WHERE = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, updatedGenre);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseUpdate][setMediaGenre] Set media genre failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }


}








