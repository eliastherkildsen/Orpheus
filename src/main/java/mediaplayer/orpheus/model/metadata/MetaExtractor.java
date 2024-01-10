package mediaplayer.orpheus.model.metadata;

import java.io.File;
import java.io.IOException;

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

    public String gatherMetaDataTitle() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            return tag.getFirst(FieldKey.TITLE);
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException |
                 IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String gatherMetaDataArtist() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            return tag.getFirst(FieldKey.COMPOSER);
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException |
                 IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int gatherMetaDataLength() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));

            return audioFile.getAudioHeader().getTrackLength();
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException |
                 IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String gatherMetaDataAlbumName() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            return tag.getFirst(FieldKey.ALBUM);
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException |
                 IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int gatherMetaDataYear() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            return Integer.parseInt(tag.getFirst(FieldKey.YEAR));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException |
                 IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int gatherMetaDataTrack() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            return Integer.parseInt(tag.getFirst(FieldKey.TRACK));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException |
                 IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
