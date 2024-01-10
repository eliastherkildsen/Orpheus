package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.model.metadata.MetaExtractor;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;
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
    private String column;
    private String conditionString;
    private int conditionInt;
    private String valueString;
    private int valueInt;
    private String filePath;

    public DatabaseHandler(String filePath) {
        setFilePath(filePath);
    }

    private String returnNameOfSong;

    public void updateMp3() throws SQLException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        JDBC db = new JDBC();
        MetaExtractor jtagger = new MetaExtractor(filePath);
        jtagger.getMetaDataArtist();

        String sql = "INSERT INTO tblMedia (fldMediaTitle,fldFileType,fldAlbum,fldMediaYear,fldMediaTrack,fldTrackLength,fldFilePath) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatment = db.getConnection().prepareStatement(sql)){
            preparedStatment.setString(1, filePath);
        };

    }

//region Getter and setter
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public int getConditionInt() {
        return conditionInt;
    }

    public void setConditionInt(int conditionInt) {
        this.conditionInt = conditionInt;
    }
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getConditionString() {
        return conditionString;
    }

    public void setConditionString(String conditionString) {
        this.conditionString = conditionString;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public int getValueInt() {
        return valueInt;
    }

    public void setValueInt(int valueInt) {
        this.valueInt = valueInt;
    }
//endregion
}
