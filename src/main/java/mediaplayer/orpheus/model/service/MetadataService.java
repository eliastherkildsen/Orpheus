package mediaplayer.orpheus.model.service;

import mediaplayer.orpheus.model.Database.DatabaseHandler;
import mediaplayer.orpheus.model.metadata.MetaExtractor;
import mediaplayer.orpheus.model.metadata.Metadata;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;

public class MetadataService {
    private final String filepath = "src/main/java/mediaplayer/orpheus/mediafiles/BIMINI - No Way (with Avi Snow)  Latin Dance  NCS - Copyright Free Music.mp3";
    public void insertAndGatherMedia(String filepath) throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        //Debugging filepath, obviously don't want this hardcoded.

        Metadata media = new Metadata();
        MetaExtractor song = new MetaExtractor(filepath);
        FileHandlerMedia medialength = new FileHandlerMedia(filepath);

        String filetype = medialength.mp3OrMp4();

        media.setMediaTrack(song.gatherMetaDataTrack());
        media.setMediaYear(song.gatherMetaDataYear());
        media.setAlbum(song.gatherMetaDataAlbumName());
        media.setTrackLength(song.gatherMetaDataLength());
        media.setMediaTitle(song.gatherMetaDataTitle());
        media.setArtist(song.gatherMetaDataArtist());

        song.gatherMetaDataArtist();

        DatabaseHandler newsong = new DatabaseHandler(media.getMediaTitle(),filetype, media.getAlbum(),media.getMediaYear(),media.getMediaTrack(),media.getTrackLength(),filepath);

    }
}
