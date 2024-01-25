package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseRead {

    private static final Connection connection = JDBC.instance.getConnection();

    public static PreparedStatement getMediaIdFromTitle(String mediaTitle) {

        try {
            String query = "SELECT fldMediaID FROM tblMedia WHERE fldMediaTitle = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, mediaTitle);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get mediaID from title failed");
            return null;
        }
    }

    public static PreparedStatement getMediaTitle(int mediaId) {

        try {
            String query = "SELECT fldMediaTitle FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media title failed");
            return null;
        }

    }

    public static PreparedStatement getMediaType(int mediaId) {

        try {

            String query = "SELECT fldFileType FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media type failed");
            return null;
        }
    }

    public static PreparedStatement getMediaAlbum(int mediaId) {

        try {

            String query = "SELECT fldFilePath FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media album failed");
            return null;
        }
    }

    public static PreparedStatement getMediaYear(int mediaId) {

        try {
            String query = "SELECT fldMediaYear FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media year failed");
            return null;
        }
    }

    public static PreparedStatement getMediaTrack(int mediaId) {

        try {

            String query = "SELECT fldMediaTrack FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media track failed");
            return null;
        }
    }

    public static PreparedStatement getMediaLength(int mediaId) {

        try {

            String query = "SELECT fldTrackLength FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media length failed");
            return null;
        }
    }

    public static PreparedStatement getMediaPath(int mediaId) {
        try {

            String query = "SELECT fldFilePath FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media path failed");
            return null;
        }
    }

    public static PreparedStatement getMediaArtistArtName(int mediaId) {
        try {
            String query = "SELECT DISTINCT p.fldArtistName FROM tblPerson p " +
                    "JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID" +
                    " WHERE mp.fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media artist art name failed");
            return null;
        }
    }

    public static PreparedStatement getMediaArtistFirstName(int mediaId) {

        try {

            String query = "SELECT DISTINCT p.fldFirstName FROM tblPerson p" +
                    " JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID" +
                    " WHERE mp.fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media artist first name failed");
            return null;
        }
    }

    public static PreparedStatement getMediaArtistLastName(int mediaId) {

        try {

            String query = "SELECT DISTINCT p.fldLastName FROM tblPerson p" +
                    " JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID" +
                    " WHERE mp.fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media artist last name failed");
            return null;
        }
    }

    public static PreparedStatement getAllArtists() {
        try {

            return connection.prepareCall("SELECT DISTINCT fldArtistName FROM tblPerson");

        } catch (SQLException e) {
            debugMessage.error(DatabaseRead.class, "Get all artists failed");
            return null;
        }
    }

    public static PreparedStatement getMediaGenre(int mediaId) {

        try {
            String query = "SELECT DISTINCT mg.fldGenre FROM tblMedia m" +
                    " JOIN tblMediaGenre mg ON m.fldMediaID = mg.fldMediaID" +
                    " WHERE mg.fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaId);

            return preparedStatement;

        } catch (SQLException e) {
            debugMessage.error(DatabaseRead.class, "Get media genres failed");
            return null;
        }

    }

    public static PreparedStatement getAllGenres() {

        try {

            return connection.prepareCall("SELECT * FROM tblGenre");

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get all genres failed");
            return null;
        }
    }

    public static PreparedStatement getAllPlaylistNames() {

        try {

            return connection.prepareCall("SELECT fldPlaylistName FROM tblPlaylist");

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get all playlist names failed");
            return null;
        }

    }

    public static PreparedStatement getMaxTrackOrder(String playlistName) {

        try {
            String query = "SELECT fldTrackOrder FROM tblMediaPlaylist WHERE fldTrackOrder = " +
                    "(SELECT MAX(fldTrackOrder) FROM tblMediaPlaylist WHERE fldPlaylistName = ?)";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, playlistName);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get max track order failed");
            return null;
        }
    }

    public static PreparedStatement getMediaArtistIDFromName(String mediaArtistName) {

        try {
            String query = "SELECT fldPersonID FROM tblPerson WHERE fldArtistName = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, mediaArtistName);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media artist from name failed");
            return null;
        }
    }

    public static PreparedStatement getMediaIDFromPlaylistName(String playlistName) {

        try {

            String query = "SELECT fldMediaID FROM tblMediaPlaylist WHERE fldPlaylistName = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, playlistName);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get mediaID from playlist name failed");
            return null;
        }

    }

    public static PreparedStatement getMediaImagePathFromMediaID(int mediaID) {

        try {
            String query = "SELECT fldImagePath FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaID);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media image path from mediaID failed");
            return null;
        }
    }

    public static PreparedStatement getMediaExtensionFromMediaID(int mediaID) {

        try {
            String query = "SELECT fldFileType FROM tblMedia WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, mediaID);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseRead.class, "Get media extension from mediaID failed");
            return null;
        }
    }

}
