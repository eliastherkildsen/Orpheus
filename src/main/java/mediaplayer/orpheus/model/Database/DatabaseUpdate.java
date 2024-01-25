package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUpdate {

    private static final Connection connection = JDBC.instance.getConnection();

    public static PreparedStatement setMediaTitle(String newTitle, int mediaId) {

        try {

            String query = "UPDATE tblMedia SET fldMediaTitle = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, newTitle);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media title failed");
            return null;
        }
    }

    public static PreparedStatement setMediaType(String fileType, int mediaId) {

        try {

            String query = "UPDATE tblMedia SET fldFileType = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, fileType);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media type failed");
            return null;
        }
    }

    public static PreparedStatement setMediaAlbum(String albumName, int mediaId) {

        try {

            String query = "UPDATE tblMedia SET fldAlbum = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, albumName);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media album failed");
            return null;
        }
    }

    public static PreparedStatement setMediaYear(String year, int mediaId) {

        try {

            String query = "UPDATE tblMedia SET fldMediaYear = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, year);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media year failed");
            return null;
        }
    }

    public static PreparedStatement setMediaTrack(int trackNumber, int mediaId) {

        try {

            String query = "UPDATE tblMedia SET fldMediaTrack = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, trackNumber);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media track failed");
            return null;
        }
    }

    public static PreparedStatement setMediaLength(int lengthInSeconds, int mediaId) {

        try {

            String query = "UPDATE tblMedia SET fldTrackLength = ? WHERE fldMediaId = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setInt(1, lengthInSeconds);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media length failed");
            return null;
        }
    }

    public static PreparedStatement setMediaImagePath(String imagePath, int mediaId) {

        try {

            String query = "UPDATE tblMedia SET fldImagePath = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, imagePath);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media image path failed");
            return null;
        }
    }

    public static PreparedStatement setFilePath(String filePath, int mediaId) {

        try {

            String query = "Update tblMedia SET fldFilePath = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, filePath);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media file path failed");
            return null;
        }
    }

    public static PreparedStatement setArtistName(String artistName, int personId) {

        try {

            String query = "Update tblPerson SET fldArtistName = ? WHERE fldPersonID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, artistName);
            preparedStatement.setInt(2, personId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media artist name failed");
            return null;
        }
    }

    public static PreparedStatement setPersonFirstName(String firstName, int personId) {

        try {

            String query = "Update tblPerson SET fldFirstName = ? WHERE fldPersonID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, personId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set person first name failed");
            return null;
        }
    }

    public static PreparedStatement setPersonLastName(String lastName, int personId) {

        try {

            String query = "Update tblPerson SET fldLastName = ? WHERE fldPersonID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, personId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set person last name failed");
            return null;
        }
    }

    public static PreparedStatement setMediaArtist(String personId, int mediaId) {

        try {

            String query = "Update tblMediaPerson SET fldPersonID = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, personId);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media artist failed");
            return null;
        }
    }

    public static PreparedStatement setMediaGenre(String genre, int mediaId) {

        try {

            String query = "UPDATE tblMediaGenre SET fldGenre = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, genre);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media genre failed");
            return null;
        }
    }

    public static PreparedStatement updateMediaGenre(String updatedGenre, int mediaId) {

        try {

            String query = "UPDATE tblMediaGenre SET fldGenre = ? WHERE fldMediaID = ?";

            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, updatedGenre);
            preparedStatement.setInt(2, mediaId);

            return preparedStatement;

        } catch (SQLException e) {

            debugMessage.error(DatabaseUpdate.class, "Set media genre failed");
            return null;
        }
    }
}
