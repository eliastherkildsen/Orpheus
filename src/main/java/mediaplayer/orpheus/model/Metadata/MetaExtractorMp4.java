package mediaplayer.orpheus.model.Metadata;


import mediaplayer.orpheus.model.Service.FileHandlerMedia;

import mediaplayer.orpheus.util.debugMessage;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;

import java.io.FileInputStream;
import java.io.IOException;


public class MetaExtractorMp4 {
    private String filePath;

    public MetaExtractorMp4(String filePath) {
        setFilePath(filePath);
    }
    public String gatherMetaDataTitle() {
        FileHandlerMedia newTitle = new FileHandlerMedia(getFilePath());
        debugMessage.debug(this,"Using file name as title. " + newTitle.getFileNameWithoutExtension());
        return newTitle.getFileNameWithoutExtension();
    }

    public Integer gatherMetaDataLength() throws IOException {
            IsoFile isoFile = new IsoFile(new FileInputStream(filePath).getChannel());
            MovieHeaderBox movieHeaderBox = isoFile.getMovieBox().getMovieHeaderBox();
            long durationInSeconds = movieHeaderBox.getDuration() / movieHeaderBox.getTimescale();
            debugMessage.debug(this,"Getting length of MP4. " + Math.toIntExact(durationInSeconds));
            return Math.toIntExact(durationInSeconds);

    }



    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
