package mediaplayer.orpheus.model.Metadata;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import mediaplayer.orpheus.model.Service.FileHandlerMedia;

import mediaplayer.orpheus.util.debugMessage;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class MetaExtractorMp3 {
    private String filePath;
    public MetaExtractorMp3(String filePath) {
        setFilePath(filePath);

        debugMessage.debug(this,"Object Created:%n" + filePath);
    }

    /**
     * A inbetween method that tries to handle Exceptions, not gracefully, needs to be done better? Research required?
     * @return
     * @throws IOException
     */
    private AudioFile readAudioFile() throws IOException {
        try {
            return AudioFileIO.read(new File(getFilePath()));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            Logger.getLogger(MetaExtractorMp3.class.getName()).log(Level.SEVERE, "Error reading audio .mp3 file.", e);
            throw new IOException("Error reading audio .mp3 file.", e);
        }
    }

    /**
     * Gets Title from the Mp3's tag.
     * If no Tag found returns the name of the file instead without extension.
     * @return String
     */
    public String gatherMetaDataTitle() {
        debugMessage.debug(this,"Getting Title.");
        AudioFile audioFile;
        try {
            audioFile = AudioFileIO.read(new File(getFilePath()));

        } catch (CannotReadException | InvalidAudioFrameException | ReadOnlyFileException | TagException | IOException e) {
            throw new RuntimeException(e);
        }
        Tag tag = audioFile.getTag();
        if (tag == null || Objects.equals(tag.getFirst(FieldKey.TITLE), "")) {
            FileHandlerMedia newTitle = new FileHandlerMedia(getFilePath());

            debugMessage.debug(this,"No Title found, using file name instead." + newTitle.getFileNameWithoutExtension());
            return newTitle.getFileNameWithoutExtension();
        }

        debugMessage.success(this, "Title found: " + tag.getFirst(FieldKey.TITLE));
        return tag.getFirst(FieldKey.TITLE);

    }

    /**
     * Gets the Artist/Composer from a Mp3 file's tag.
     * Returns NULL if no Composer is found.
     * @return String
     * @throws IOException
     */
    public String gatherMetaDataArtist() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();

            debugMessage.debug(this,"Getting Artist.");
            if (tag == null || Objects.equals(tag.getFirst(FieldKey.COMPOSER), "")){
                debugMessage.debug(this,"No Artist found returning: " + null);
                return null;
            }
            debugMessage.success(this,"Artist found: " + tag.getFirst(FieldKey.COMPOSER));
            return tag.getFirst(FieldKey.COMPOSER);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Gets Track Length from Mp3 files audioheader.
     * @return Integer
     * @throws IOException
     */
    public Integer gatherMetaDataLength() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            int length = audioFile.getAudioHeader().getTrackLength();

            debugMessage.success(this,"Getting TrackLength in seconds: " + length);
            return length;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Gets AlbumName from Mp3 files tag
     * returns Null if nothing found.
     * @return String
     * @throws IOException
     */
    public String gatherMetaDataAlbumName() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();

            debugMessage.debug(this,"Getting Album name.");
            if (tag == null || Objects.equals(tag.getFirst(FieldKey.ALBUM), "")){
                debugMessage.debug(this,"No Album found returning: " + null);
                return null;
            } else {
                //DEBUG LOGGING
                debugMessage.success(this,"Getting Album name: " + tag.getFirst(FieldKey.ALBUM));
                return tag.getFirst(FieldKey.ALBUM);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Gets year from Mp3 files tag
     * returns NULL if nothing found.
     * @return Integer
     * @throws IOException
     */
    public Integer gatherMetaDataYear() throws IOException {
        try {
            debugMessage.debug(this,"Getting release year.");

            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();
            if (tag == null || Objects.equals(tag.getFirst(FieldKey.YEAR), "")){
                debugMessage.debug(this,"No release year found, returning: " + null);
                return null;
            } else {
                debugMessage.success(this,"Release year found: " + tag.getFirst(FieldKey.YEAR));
                return Integer.parseInt(tag.getFirst(FieldKey.YEAR));
            }
        } catch (IOException e) {
            throw new IOException("Error: Parsing release year from meta data.", e);
        }
    }

    /**
     * Gets metadata Track from Mp3 files tag
     * Returns NULL if nothing found.
     * @return
     * @throws IOException
     */
    public Integer gatherMetaDataTrack() throws IOException {
        try {
            debugMessage.debug(this,"Getting Track number.");

            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();

            if (tag == null || Objects.equals(tag.getFirst(FieldKey.TRACK), "")){
                debugMessage.debug(this,"Track number not found returning: " + null);
                return null;
            } else {
                debugMessage.success(this, "Track number found: " + tag.getFirst(FieldKey.TRACK));
                return Integer.parseInt(tag.getFirst(FieldKey.TRACK));
            }
        } catch (IOException e) {
            throw new IOException("Error: Parsing track number from meta data.", e);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
