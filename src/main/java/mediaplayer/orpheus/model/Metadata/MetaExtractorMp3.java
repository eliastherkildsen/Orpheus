package mediaplayer.orpheus.model.Metadata;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.model.Service.FileHandlerMedia;

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

        //DEBUG LOGGING
        System.out.printf("%s[METADATA] Object Created.%s%n" + filePath + "%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
    }

    private AudioFile readAudioFile() throws IOException {
        try {
            return AudioFileIO.read(new File(getFilePath()));
        } catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            Logger.getLogger(MetaExtractorMp3.class.getName()).log(Level.SEVERE, "Error reading audio .mp3 file", e);
            throw new IOException("Error reading audio .mp3 file", e);
        }
    }

    public String gatherMetaDataTitle() throws IOException {
        try {
            //DEBUG LOGGING
            System.out.printf("%s[METADATA] Getting Title.%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();
            if (Objects.equals(tag.getFirst(FieldKey.TITLE), "")){
                FileHandlerMedia newTitle = new FileHandlerMedia(getFilePath());
                //DEBUG LOGGING
                System.out.printf("%s[METADATA] No Title found, using file name instead.%s%n" + newTitle.getFileNameWithoutExtension() + "%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
                return newTitle.getFileNameWithoutExtension();
            }
            //DEBUG LOGGING
            System.out.printf("%s[METADATA] Title found.%s%n" + tag.getFirst(FieldKey.TITLE) + "%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
            return tag.getFirst(FieldKey.TITLE);
        } catch (IOException e) {
            throw e;
        }
    }
    public String gatherMetaDataArtist() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();
            //DEBUG LOGGING
            System.out.printf("%s[METADATA] Getting Artist.%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
            if (Objects.equals(tag.getFirst(FieldKey.COMPOSER), "")){
                //DEBUG LOGGING
                System.out.printf("%s[METADATA] No Artist found returning%s%nNULL%n", AnsiColorCode.ANSI_RED,AnsiColorCode.ANSI_RESET);
                return null;
            }
            //DEBUG LOGGING
            System.out.printf("%s[METADATA] Artist found.%s%n" + tag.getFirst(FieldKey.COMPOSER) + "%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
            return tag.getFirst(FieldKey.COMPOSER);
        } catch (IOException e) {
            throw e;
        }
    }

    public Integer gatherMetaDataLength() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            //DEBUG LOGGING
            System.out.printf("%s[METADATA] Getting TrackLength in seconds.%s%n" + audioFile.getAudioHeader().getTrackLength() + "%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
            return audioFile.getAudioHeader().getTrackLength();
        } catch (IOException e) {
            throw e;
        }
    }

    public String gatherMetaDataAlbumName() throws IOException {
        try {
            AudioFile audioFile = readAudioFile();
            Tag tag = audioFile.getTag();
            //DEBUG LOGGING
            System.out.printf("%s[METADATA] Getting Album name.%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
            if (Objects.equals(tag.getFirst(FieldKey.ALBUM), "")){
                System.out.printf("%s[METADATA] No Album fund returning%s%nNULL%n", AnsiColorCode.ANSI_RED,AnsiColorCode.ANSI_RESET);
                return null;
            } else {
                //DEBUG LOGGING
                System.out.printf("%s[METADATA] Getting Album name.%s%n" + tag.getFirst(FieldKey.ALBUM) + "%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
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
                System.out.printf("%s[METADATA] No release year fund returning%s%nNULL%n", AnsiColorCode.ANSI_RED,AnsiColorCode.ANSI_RESET);
                return null;
            } else {
                //DEBUG LOGGING
                System.out.printf("%s[METADATA] Getting release year.%s%n" + tag.getFirst(FieldKey.YEAR) + "%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
                return Integer.parseInt(tag.getFirst(FieldKey.YEAR));
            }
        } catch (IOException e) {
            throw new IOException("Error: Parsing release year from meta data.", e);
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
