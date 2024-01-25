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

    /**
     * AudioTagger returns NULL if a tag is invalid,
     * If the tag is invalid the file must have an issue of sorts.
     * So we return false for later use.
     * @return boolean
     */
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

    /**
     * We check if the file is a Mp3 or Mp4 then we do something with that information.
     * @throws SQLException
     * @throws IOException
     */
    public void mp3OrMp4() throws SQLException, IOException {
        debugMessage.debug(this,"Deciding if Mp3 or Mp4");

        FileHandlerMedia media = new FileHandlerMedia(getFilePath());
        setFiletype(media.mp3OrMp4());

        switch (getFiletype()) {
            case "mp3":
                if (checkValidTagsMp3()){
                    //If its a valid mp3 file we'll move on.
                    insertAndGatherMediaMp3();
                } else{
                    //If we are getting invalid tags, then we'll stop here and let the user know the file is unusable.
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

    /**
     * Lets insert a mp3 file into the DB
     * @throws IOException
     * @throws SQLException
     */
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

    /**
     * Lets make a media object with information.
     * @return
     * @throws IOException
     */
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

    /**
     * Gather data for Mp4 file and insert it into a DB.
     * @throws SQLException
     * @throws IOException
     */
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