package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.util.AnsiColorCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseRead {

    private static final Connection connection = JDBC.instance.getConnection();

    public static PreparedStatement getMediaIdFromTitle(String mediaTitle){

        try {
            String query = "SELECT fldMediaID FROM tblMedia WHERE fldMediaTitle = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, mediaTitle);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaIdFromTitle] Get mediaID from title failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }
    public static PreparedStatement getMediaTitle(int mediaId) {

        try {
            String query = "SELECT fldMediaTitle FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaTitle] Get media title failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement getMediaType(int mediaId){

        try {

            String query = "SELECT fldFileType FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaType] Get media type failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement getMediaAlbum(int mediaId){

        try {

            String query = "SELECT fldFilePath FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaAlbum] Get media album failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }
    public static PreparedStatement getMediaYear(int mediaId){

        try {
            String query = "SELECT fldMediaYear FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaYear] Get media year failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }
    public static PreparedStatement getMediaTrack(int mediaId){

        try {

            String query = "SELECT fldMediaTrack FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaTrack] Get media track failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement getMediaLength(int mediaId){

        try {

            String query = "SELECT fldTrackLength FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaLength] Get media length failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }
    public static PreparedStatement getMediaPath(int mediaId){
        try {

            String query = "SELECT fldFilePath FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaPath] Get media path failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }
    public static PreparedStatement getMediaArtistArtName(int mediaId){
        try {
            String query = "SELECT DISTINCT p.fldArtistName FROM tblPerson p " +
                    "JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID" +
                    " WHERE mp.fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaArtistArtName] Get media artist art name failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }
    public static PreparedStatement getMediaArtistFirstName(int mediaId){

        try {

            String query = "SELECT DISTINCT p.fldFirstName FROM tblPerson p" +
                    " JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID" +
                    " WHERE mp.fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaArtistFirstName] Get media artist first name failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }
    public static PreparedStatement getMediaArtistLastName(int mediaId){

        try {

            String query = "SELECT DISTINCT p.fldLastName FROM tblPerson p" +
                    " JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID" +
                    " WHERE mp.fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaArtistLastName] Get media artist last name failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }

    public static PreparedStatement getAllArtists (){
        try {

            return connection.prepareCall("SELECT DISTINCT fldArtistName FROM tblPerson");

        }catch (SQLException e){
            System.out.printf("%s[DatabaseRead][getAllArtists] Get all artists failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement getMediaGenre(int mediaId){

        try {
            String query = "SELECT DISTINCT mg.fldGenre FROM tblMedia m" +
                    " JOIN tblMediaGenre mg ON m.fldMediaID = mg.fldMediaID" +
                    " WHERE mg.fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        }catch (SQLException e){
            System.out.printf("%s[DatabaseRead][getMediaGenre] Get media genres failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement getAllGenres (){

        try {

            return connection.prepareCall("SELECT * FROM tblGenre");

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getAllGenres] Get all genres failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }
    }

    public static PreparedStatement getAllPlaylistNames() {

        try {

            return connection.prepareCall("SELECT fldPlaylistName FROM tblPlaylist");

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getAllPlaylistNames] Get all playlist names failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }

    public static PreparedStatement getMaxTrackOrder(String playlistName){

        try {
            String query = "SELECT fldTrackOrder FROM tblMediaPlaylist WHERE fldTrackOrder = " +
                    "(SELECT MAX(fldTrackOrder) FROM tblMediaPlaylist WHERE fldPlaylistName = ?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, playlistName);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMaxTrackOrder] Get max track order failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }


    }
    public static PreparedStatement getMediaArtistIDFromName(String mediaArtistName) {

        try {
            String query = "SELECT fldPersonID FROM tblPerson WHERE fldArtistName = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, mediaArtistName);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaArtistIDFromName] Get media artist from name failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement getMediaIDFromPlaylistName(String playlistName){

        try {

            String query = "SELECT fldMediaID FROM tblMediaPlaylist WHERE fldPlaylistName = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, playlistName);

            return preparedStatement;

        }catch(SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaIDFromPlaylistName] Get mediaID from playlist name failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }
    public static PreparedStatement getMediaImagePathFromMediaID(int mediaID){

        try {
            String query = "SELECT fldImagePath FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaID);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaImagePathFromMediaID] Get media image path from mediaID failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }

    public static PreparedStatement getMediaExtensionFromMediaID(int mediaID){

        try {
            String query = "SELECT fldFileType FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaID);

            return preparedStatement;

        }catch (SQLException e){

            System.out.printf("%s[DatabaseRead][getMediaExtensionFromMediaID] Get media extension from mediaID failed%s%n"
                    , AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return null;
        }

    }

}