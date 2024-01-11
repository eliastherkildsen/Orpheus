package mediaplayer.orpheus.model.Metadata;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import mediaplayer.orpheus.model.Service.FileHandlerMedia;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class MetaExtractor {
    private String filePath;
    public MetaExtractor(String filePath) {
        setFilePath(filePath);
    }

    private AudioFile readAudioFile() throws IOException {
        try {
            return AudioFileIO.read(new File(getFilePath()));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            Logger.getLogger(MetaExtractor.class.getName()).log(Level.SEVERE, "Error reading audio file", e);
            throw new IOException("Error reading audio file", e);
        }
    }

    public String gatherMetaDataTitle() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();
            if (Objects.equals(tag.getFirst(FieldKey.TITLE), "")){
                FileHandlerMedia newTitle = new FileHandlerMedia(getFilePath());
                return newTitle.getFileNameWithoutExtension();
            }
            return tag.getFirst(FieldKey.TITLE);
        } catch (IOException e) {
            throw e;
        }
    }
    public String gatherMetaDataArtist() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();
            if (Objects.equals(tag.getFirst(FieldKey.COMPOSER), "")){
                return null;
            }
            return tag.getFirst(FieldKey.COMPOSER);
        } catch (IOException e) {
            throw e;
        }
    }

    public Integer gatherMetaDataLength() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();

            return audioFile.getAudioHeader().getTrackLength();
        } catch (IOException e) {
            throw e;
        }
    }

    public String gatherMetaDataAlbumName() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();
            if (Objects.equals(tag.getFirst(FieldKey.ALBUM), "")){
                return null;
            } else {
                return tag.getFirst(FieldKey.ALBUM);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public Integer gatherMetaDataYear() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();
            if (Objects.equals(tag.getFirst(FieldKey.YEAR), "")){
                return null;
            } else {
                return Integer.parseInt(tag.getFirst(FieldKey.YEAR));
            }
        } catch (IOException e) {
            throw new IOException("Error: parsing year from meta data.", e);
        }
    }

    public Integer gatherMetaDataTrack() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();
            if (Objects.equals(tag.getFirst(FieldKey.TRACK), "")){
                return null;
            } else {
                return Integer.parseInt(tag.getFirst(FieldKey.TRACK));
            }
        } catch (IOException e) {
            throw new IOException("Error: parsing track from meta data.", e);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
