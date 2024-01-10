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

public class jaudiotagger {
    private String filePath;
    private int mediaLength;
    private String title;
    private String Artist;
    public jaudiotagger(String filePath) {
        setFilePath(filePath);
    }
    public void getMetaDataTitle() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
        Tag tag = audioFile.getTag();

        setTitle(tag.getFirst(FieldKey.TITLE));
    }
    public void getMetaDataArtist() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));
        Tag tag = audioFile.getTag();

        setArtist(tag.getFirst(FieldKey.ARTIST));
    }
    public void getMetaDataLength() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        AudioFile audioFile = AudioFileIO.read(new File(getFilePath()));

        setMediaLength(audioFile.getAudioHeader().getTrackLength());
    }

    //region getter and setter
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
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }
    //endregion
}
