package mediaplayer.orpheus.model.Metadata;

import mediaplayer.orpheus.model.Service.FileHandlerMedia;
import mediaplayer.orpheus.util.AnsiColorCode;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp4.Mp4AudioHeader;
import org.jaudiotagger.audio.mp4.Mp4FileReader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetaExtractorMp4 {
    private String filePath;

    public MetaExtractorMp4(String filePath) {
        setFilePath(filePath);
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
        FileHandlerMedia newTitle = new FileHandlerMedia(getFilePath());
        System.out.printf("%s[METADATA] Using file name as title.%s%n" + newTitle.getFileNameWithoutExtension() + "%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        return newTitle.getFileNameWithoutExtension();
    }

    public Integer gatherMetaDataLength() throws IOException {

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
