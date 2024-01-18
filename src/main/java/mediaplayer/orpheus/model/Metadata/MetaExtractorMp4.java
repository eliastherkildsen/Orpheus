package mediaplayer.orpheus.model.Metadata;

import javafx.scene.shape.Path;
import mediaplayer.orpheus.model.Service.FileHandlerMedia;
import mediaplayer.orpheus.util.AnsiColorCode;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
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
        tryFileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)
    }

    private static long getVideoDuration(Path filePath) throws IOException {
        try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            IsoFile isoFile = new IsoFile(channel);
            MovieHeaderBox mvhd = isoFile.getMovieBox().getMovieHeaderBox();
            long durationInSeconds = mvhd.getDuration() / mvhd.getTimescale();
            return durationInSeconds;
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
