package mediaplayer.orpheus.model.Service;

import mediaplayer.orpheus.model.Database.DatabaseExtractorInsert;
import mediaplayer.orpheus.model.Metadata.MetaExtractorMp3;
import mediaplayer.orpheus.model.Metadata.ImportMedia;
import mediaplayer.orpheus.model.Metadata.MetaExtractorMp4;
import mediaplayer.orpheus.util.AnsiColorCode;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MetadataService {
    private final String filepath;
    private String filetype;

    public MetadataService(String filepath) {
        this.filepath = filepath;
    }

    public void mp3OrMp4() throws SQLException, IOException {
        System.out.printf("%s[METADATA SERVICE] Deciding if Mp3 or Mp4%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
        FileHandlerMedia media = new FileHandlerMedia(filepath);
        setFiletype(media.mp3OrMp4());
        switch (filetype) {
            case "mp3":
                insertAndGatherMediaMp3();
                break;
            case "mp4":
                insertAndGatherMediaMp4();
                break;
        }
    }

    private void insertAndGatherMediaMp3() throws IOException, SQLException {
        System.out.printf("%s[METADATA SERVICE] Mp3 Attempting to Insert into DB.%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);

        MetaExtractorMp3 song = new MetaExtractorMp3(filepath);
        FileHandlerMedia soundString = new FileHandlerMedia(filepath);

        ImportMedia media = new ImportMedia(filepath,song.gatherMetaDataTitle(),filetype,song.gatherMetaDataAlbumName(),song.gatherMetaDataArtist(),song.gatherMetaDataYear(),song.gatherMetaDataTrack(),song.gatherMetaDataLength());

        DatabaseExtractorInsert newsong = new DatabaseExtractorInsert(media.getMediaTitle(),filetype, media.getAlbum(),media.getMediaYear(),media.getMediaTrack(),media.getTrackLength(),filepath);
        newsong.insertIntoDBNewMp3();
        //DEBUG LOGGING
        System.out.printf("%s[METADATA SERVICE] Inserting MP3 into DB complete.%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
    }

    private void insertAndGatherMediaMp4() throws SQLException, IOException {
        System.out.printf("%s[METADATA SERVICE] Attempting to insert MP4 into DB.%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
        //Using Mp3 functions for now.
        MetaExtractorMp4 video = new MetaExtractorMp4(filepath);

        ImportMedia media = new ImportMedia(filepath,video.gatherMetaDataTitle(),null,null,null,null,null,video.gatherMetaDataLength());

        FileHandlerMedia videoString = new FileHandlerMedia(filepath);

        DatabaseExtractorInsert videoIntoDb = new DatabaseExtractorInsert(media.getMediaTitle(), null, null, null, null, media.getTrackLength(), filepath);
        videoIntoDb.insertIntoDBNewMp3();

        System.out.printf("%s[METADATA SERVICE] Inserting MP4 into DB complete.%s%n", AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
    }

    public String getFilepath() {
        return filepath;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }
}