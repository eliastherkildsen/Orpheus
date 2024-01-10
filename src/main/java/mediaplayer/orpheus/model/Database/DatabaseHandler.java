package mediaplayer.orpheus.model.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 *       model
 *     │   └── Metadata.java
 *     ├── db
 *     │   └── DatabaseHandler.java
 *     ├── metadata
 *     │   └── MetadataExtractor.java
 *     ├── service
 *     │   └── MetadataService.java
 *     └── Main.java
 *
 *     Metadata represents the data structure for metadata.
 *     DatabaseHandler handles database interactions.
 *     MetadataExtractor uses JAudioTagger to extract metadata.
 *     MetadataService coordinates metadata extraction and database interaction.
 */

public class DatabaseHandler {
    private String filePath;
    private String mediaTitle;
    private String fileType;
    private String album;
    int mediaYear;
    int mediaTrack;
    int trackLength;

    public DatabaseHandler(String mediaTitle, String fileType, String album, int mediaYear, int mediaTrack, int trackLength, String filePath) {
        setMediaTitle(mediaTitle);
        setFileType(fileType);
        setAlbum(album);
        setMediaYear(mediaYear);
        setMediaTrack(mediaTrack);
        setTrackLength(trackLength);
        setFilePath(filePath);
    }
    public void insertNewMp3() throws SQLException {
        JDBC db = new JDBC();
        String sql = "INSERT INTO tblMedia (fldMediaTitle,fldFileType,fldAlbum,fldMediaYear,fldMediaTrack,fldTrackLength,fldFilePath) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatment = db.getConnection().prepareStatement(sql)){
            preparedStatment.setString(1, getMediaTitle());
            preparedStatment.setString(2, getFileType());
            preparedStatment.setString(3, getAlbum());
            preparedStatment.setInt(4, getMediaYear());
            preparedStatment.setInt(5, getMediaTrack());
            preparedStatment.setInt(6, getTrackLength());
            preparedStatment.setString(7,getFilePath());

            preparedStatment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            throw e;
        }
    }

//region Getter and setter
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getMediaYear() {
        return mediaYear;
    }

    public void setMediaYear(int mediaYear) {
        this.mediaYear = mediaYear;
    }

    public int getMediaTrack() {
        return mediaTrack;
    }

    public void setMediaTrack(int mediaTrack) {
        this.mediaTrack = mediaTrack;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(int trackLength) {
        this.trackLength = trackLength;
    }
    //endregion
}
