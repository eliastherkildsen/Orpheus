package mediaplayer.orpheus.model.Metadata;


import mediaplayer.orpheus.model.Service.FileHandlerMedia;
import mediaplayer.orpheus.util.AnsiColorCode;

import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;

import java.io.FileInputStream;
import java.io.IOException;


public class MetaExtractorMp4 {
    private String filePath;

    public MetaExtractorMp4(String filePath) {
        setFilePath(filePath);
    }
    public String gatherMetaDataTitle() throws IOException {
        FileHandlerMedia newTitle = new FileHandlerMedia(getFilePath());
        System.out.printf("%s[METADATA MP4]%s Using file name as title.%s%n" + newTitle.getFileNameWithoutExtension() + "%n", AnsiColorCode.ANSI_BLUE, AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        return newTitle.getFileNameWithoutExtension();
    }

    public Integer gatherMetaDataLength() throws IOException {
            IsoFile isoFile = new IsoFile(new FileInputStream(filePath).getChannel());
            MovieHeaderBox movieHeaderBox = isoFile.getMovieBox().getMovieHeaderBox();
            long durationInSeconds = movieHeaderBox.getDuration() / movieHeaderBox.getTimescale();
            System.out.printf("%s[METADATA MP4]%s Getting length of MP4.%s%n" + Math.toIntExact(durationInSeconds)
                    + "%n", AnsiColorCode.ANSI_BLUE, AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            return Math.toIntExact(durationInSeconds);

    }



    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
