package mediaplayer.orpheus.model.Service;

import mediaplayer.orpheus.model.Database.DatabaseExtractorInsert;
import mediaplayer.orpheus.model.Metadata.MetaExtractorMp3;
import mediaplayer.orpheus.model.Metadata.ImportMedia;
import mediaplayer.orpheus.model.Metadata.MetaExtractorMp4;
import mediaplayer.orpheus.util.AlertPopup;
import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MetadataService {
    private String filepath;
    private String filetype;

    public MetadataService(String filepath) {
        setFilepath (filepath);
    }

    private boolean checkValidTagsMp3(){
        AudioFile audioFile;
        try {
            debugMessage.debug(this, "Attempting to read AudioFile");
            System.out.println(getFilePath());

            audioFile = AudioFileIO.read(new File(getFilePath()));
            debugMessage.debug(this,"Audio read successfully");

        } catch (CannotReadException | InvalidAudioFrameException | ReadOnlyFileException | TagException | IOException e) {
            throw new RuntimeException(e);
        }
        debugMessage.debug(this,"Trying to get TAGs");
        Tag tag = audioFile.getTag();
        //DEBUG
        debugMessage.debug(this,"TAGs found");
        System.out.println(tag);

        if(tag == null){
            debugMessage.error(this,"Tags Corrupt");
            return false;
        } else {
            return true;
        }
    }

    public void mp3OrMp4() throws SQLException, IOException {
        debugMessage.debug(this,"Deciding if Mp3 or Mp4");

        FileHandlerMedia media = new FileHandlerMedia(getFilePath());
        setFiletype(media.mp3OrMp4());

        switch (getFiletype()) {
            case "mp3":
                if (checkValidTagsMp3()){
                    insertAndGatherMediaMp3();
                } else{
                    AlertPopup error = new AlertPopup("Corrupt MP3","The MP3 file you're trying to import is corrupt.");
                    error.showError();
                    return;
                }

                break;
            case "mp4":
                insertAndGatherMediaMp4();
                break;
        }
    }

    private void insertAndGatherMediaMp3() throws IOException, SQLException {
        debugMessage.debug(this,"MP3 - Attempting to Insert into DB.");

        ImportMedia media = getImportMediaMp3();

        DatabaseExtractorInsert newsong = new DatabaseExtractorInsert(
                media.getMediaTitle(),
                getFiletype(),
                media.getAlbum(),
                media.getMediaYear(),
                media.getMediaTrack(),
                media.getTrackLength(),
                getFilePath()
        );

        newsong.insertIntoDBNewMp3();
        //DEBUG LOGGING
        debugMessage.debug(this,"MP3 - Inserting into DB Complete");
    }

    private ImportMedia getImportMediaMp3() throws IOException {
        MetaExtractorMp3 song = new MetaExtractorMp3(getFilePath());

        //ImportMedia media = new ImportMedia(getFilepath(), getFiletype(), song.gatherMetaDataLength());
        return new ImportMedia(
                    getFilePath(),
                    song.gatherMetaDataTitle(),
                    getFiletype(),
                    song.gatherMetaDataAlbumName(),
                    song.gatherMetaDataArtist(),
                    song.gatherMetaDataYear(),
                    song.gatherMetaDataTrack(),
                    song.gatherMetaDataLength()
            );
    }

    private void insertAndGatherMediaMp4() throws SQLException, IOException {
        debugMessage.debug(this,"MP4 - Attempting to Insert into DB");

        //Using Mp3 functions for now.
        MetaExtractorMp4 video = new MetaExtractorMp4(getFilePath());

        ImportMedia media = new ImportMedia(
                getFilePath(),
                video.gatherMetaDataTitle(),
                getFiletype(),
                null,
                null,
                null,
                null,
                video.gatherMetaDataLength()
        );

        DatabaseExtractorInsert videoIntoDb = new DatabaseExtractorInsert(
                media.getMediaTitle(),
                media.getFileType(),
                null,
                null,
                null,
                media.getTrackLength(),
                getFilePath()
        );

        videoIntoDb.insertIntoDBNewMp3();

        debugMessage.debug(this,"MP4 - Inserting into DB complete");
    }

    public String getFilePath() {
        return filepath;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}