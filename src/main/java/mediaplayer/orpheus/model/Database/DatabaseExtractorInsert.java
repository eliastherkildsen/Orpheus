package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.OrpheusApp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
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

public class DatabaseExtractorInsert {
    private String filePath;
    private String mediaTitle;
    private String fileType;
    private String album;
    Integer mediaYear;
    Integer mediaTrack;
    Integer trackLength;
    public DatabaseExtractorInsert(String mediaTitle, String fileType, String album, Integer mediaYear, Integer mediaTrack, Integer trackLength, String filePath) {
        setMediaTitle(mediaTitle);
        setFileType(fileType);
        setAlbum(album);
        setMediaYear(mediaYear);
        setMediaTrack(mediaTrack);
        setTrackLength(trackLength);
        setFilePath(filePath);
    }

    /**
     *
     * @throws SQLException
     */
    public void insertIntoDBNewMp3() throws SQLException {
        JDBC db = new JDBC();
        String sql = "INSERT INTO tblMedia (fldMediaTitle,fldFileType,fldAlbum,fldMediaYear,fldMediaTrack,fldTrackLength,fldFilePath) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatment = db.getConnection().prepareStatement(sql)){
            preparedStatment.setString(1, getMediaTitle());
            preparedStatment.setString(2, getFileType());
            preparedStatment.setString(3, getAlbum());
            if (getMediaYear() != null) {
                preparedStatment.setInt(4, getMediaYear());
            } else {
                preparedStatment.setNull(4, Types.INTEGER);
            }
            if (getMediaTrack() != null) {
                preparedStatment.setInt(5, getMediaTrack());
            } else {
                preparedStatment.setNull(5, Types.INTEGER);
            }
            if (getTrackLength() != null) {
                preparedStatment.setInt(6, getTrackLength());
            } else {
                preparedStatment.setNull(6, Types.INTEGER);
            }
            preparedStatment.setString(7,getFilePath());

            preparedStatment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        String mediaID = " ";
        try {
            ResultSet ress = OrpheusApp.jdbc.executeQuary(DatabaseRead.getMediaIdFromTitle(this.mediaTitle));
            while (ress.next()){
                mediaID =  ress.getString("fldMediaID");
            }
        }catch (SQLException e ){}

        OrpheusApp.jdbc.executeQuary(DatabaseCreate.insertMediaPerson(1,mediaID));
        OrpheusApp.jdbc.executeQuary(DatabaseCreate.insertMediaGenre("Unknown",mediaID));

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

    public Integer getMediaYear() {
        return mediaYear;
    }

    public void setMediaYear(Integer mediaYear) {
        this.mediaYear = mediaYear;
    }

    public Integer getMediaTrack() {
        return mediaTrack;
    }

    public void setMediaTrack(Integer mediaTrack) {
        this.mediaTrack = mediaTrack;
    }

    public Integer getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(Integer trackLength) {
        this.trackLength = trackLength;
    }
    //endregion
}
