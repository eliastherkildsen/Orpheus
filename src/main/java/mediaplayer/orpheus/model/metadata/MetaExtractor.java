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
    private int mediaLength;
    private String title;
    private String artist;
    private String album;
    private int year;
    private int track;
    public MetaExtractor(String filePath) {
        setFilePath(filePath);
    }
    public void gatherMetaDataTitle() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            setTitle(tag.getFirst(FieldKey.TITLE));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void gatherMetaDataArtist() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            setArtist(tag.getFirst(FieldKey.COMPOSER));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void gatherMetaDataLength() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));

            setMediaLength(audioFile.getAudioHeader().getTrackLength());
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void gatherAlbumName() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            setAlbum(tag.getFirst(FieldKey.ALBUM));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void gatherYear() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            setYear(Integer.parseInt(tag.getFirst(FieldKey.YEAR)));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void gatherTrack() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
            Tag tag = audioFile.getTag();

            setYear(Integer.parseInt(tag.getFirst(FieldKey.TRACK)));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


    //region getter and setter

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getMediaLength() {
        return mediaLength;
    }

    public void setMediaLength(int mediaLength) {
        this.mediaLength = mediaLength;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    //endregion
}
