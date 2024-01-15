package mediaplayer.orpheus.model.Service;

import mediaplayer.orpheus.model.Database.DatabaseCreate;
import mediaplayer.orpheus.model.Metadata.MetaExtractorMp3;
import mediaplayer.orpheus.model.Metadata.Mp3;
import mediaplayer.orpheus.util.AnsiColorCode;

import java.io.IOException;
import java.sql.SQLException;

public class MetadataService {
    private final String filepath;

    public MetadataService(String filepath) {
        this.filepath = filepath;
    }

    public void insertAndGatherMediaMp3() throws IOException, SQLException {
        //Debugging filepath, obviously don't want this hardcoded.

        MetaExtractorMp3 song = new MetaExtractorMp3(filepath);
        FileHandlerMedia medialength = new FileHandlerMedia(filepath);
        String filetype = medialength.mp3OrMp4();

        Mp3 media = new Mp3(filepath,song.gatherMetaDataTitle(),filetype,song.gatherMetaDataAlbumName(),song.gatherMetaDataArtist(),song.gatherMetaDataYear(),song.gatherMetaDataTrack(),song.gatherMetaDataLength());

        DatabaseCreate newsong = new DatabaseCreate(media.getMediaTitle(),filetype, media.getAlbum(),media.getMediaYear(),media.getMediaTrack(),media.getTrackLength(),filepath);
        newsong.insertIntoDBNewMp3();
        //DEBUG LOGGING
        System.out.printf("%s[META SERVICE] Inserting into DB complete.%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
    }
    /*public void insertAndGatherMediaMp4() throws SQLException, IOException {
        Mp3 media = new Mp3();
        MetaExtractorMp3 video = new MetaExtractorMp3(filepath);
        FileHandlerMedia videoLength = new FileHandlerMedia(filepath);

        String filetype = videoLength.mp3OrMp4();

        media.setMediaTitle(video.gatherMetaDataTitle());

        DatabaseCreate newSong = new DatabaseCreate(media.getMediaTitle(),filetype, null,media.getMediaYear(),null,media.getTrackLength(),filepath);
        newSong.insertIntoDBNewMp3();
    }*/
}