package mediaplayer.orpheus.model.Playlist;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Media {

    private int mediaID;
    private String mediaPath;
    private String mediaTitle;
    private String mediaArtist;
    private int trackLength;
    private String imagePath = "src/main/resources/css/images/audio-lines.png";

    private static final Connection connection = OrpheusApp.jdbc.getConnection();

    public Media(int mediaID) throws SQLException {
        this.mediaID = mediaID;
        this.imagePath = imagePath;
        generateObject(mediaID);
    }

    private void generateObject(int mediaID) throws SQLException {

        this.mediaPath = generateMediaPath(mediaID);
        this.mediaTitle = generateMediaTitle(mediaID);
        this.mediaArtist = generateMediaArtist(mediaID);
        this.trackLength = generateMediaTrackLength(mediaID);

    }

    private int generateMediaTrackLength(int mediaID) throws SQLException {

        PreparedStatement psMediaTackLength;

        String query = DatabaseRead.getMediaLength(mediaID);
        psMediaTackLength = connection.prepareCall(query);

        try(ResultSet rsMediaTrackLength = psMediaTackLength.executeQuery()){
            while (rsMediaTrackLength.next()){
                return rsMediaTrackLength.getInt("fldTrackLength");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }

    private String generateMediaArtist(int mediaID) throws SQLException {

        PreparedStatement psMediaArtist;

        String query = DatabaseRead.getMediaArtistArtName(mediaID);
        psMediaArtist = connection.prepareCall(query);

        try(ResultSet rsMediaArtist = psMediaArtist.executeQuery()){
            while (rsMediaArtist.next()){
                return rsMediaArtist.getString("fldArtistName");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    private static String generateMediaPath(int mediaID) throws SQLException {

        PreparedStatement psMediaPath;

        String query = DatabaseRead.getMediaPath(mediaID);
        psMediaPath = connection.prepareCall(query);

        try(ResultSet rsMediaPath = psMediaPath.executeQuery()){
            while (rsMediaPath.next()){
                return rsMediaPath.getString("fldFilePath");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    private static String generateMediaTitle(int mediaID) throws SQLException {

        String query = DatabaseRead.getMediaTitle(mediaID);
        PreparedStatement psMediaTitle = connection.prepareCall(query);

        try(ResultSet rsMediaTitle = psMediaTitle.executeQuery()){
            while (rsMediaTitle.next()){
                return rsMediaTitle.getString("fldMediaTitle");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }


    public String getMediaPath() {
        return mediaPath;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public String getMediaArtist() {
        return mediaArtist;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public String getImagePath() {
        return imagePath;
    }
}
