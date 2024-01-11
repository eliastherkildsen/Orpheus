package mediaplayer.orpheus.model.Service;

import mediaplayer.orpheus.model.Database.DatabaseHandler;
import mediaplayer.orpheus.model.Metadata.MetaExtractorMp3;
import mediaplayer.orpheus.model.Metadata.Metadata;
import mediaplayer.orpheus.util.AnsiColorCode;

import java.io.IOException;
import java.sql.SQLException;

public class MetadataService {
    private final String filepath;

    public MetadataService(String filepath) {
        this.filepath = filepath;
    }

    public void insertAndGatherMedia() throws IOException, SQLException {
        //Debugging filepath, obviously don't want this hardcoded.

        Metadata media = new Metadata();
        MetaExtractorMp3 song = new MetaExtractorMp3(filepath);
        FileHandlerMedia medialength = new FileHandlerMedia(filepath);

        String filetype = medialength.mp3OrMp4();

        media.setMediaTrack(song.gatherMetaDataTrack());
        media.setMediaYear(song.gatherMetaDataYear());
        media.setAlbum(song.gatherMetaDataAlbumName());
        media.setTrackLength(song.gatherMetaDataLength());
        media.setMediaTitle(song.gatherMetaDataTitle());
        media.setArtist(song.gatherMetaDataArtist());

        DatabaseHandler newsong = new DatabaseHandler(media.getMediaTitle(),filetype, media.getAlbum(),media.getMediaYear(),media.getMediaTrack(),media.getTrackLength(),filepath);
        newsong.insertIntoDBNewMp3();
        //DEBUG LOGGING
        System.out.printf("%s[META SERVICE] Inserting into DB complete.%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
    }
}
