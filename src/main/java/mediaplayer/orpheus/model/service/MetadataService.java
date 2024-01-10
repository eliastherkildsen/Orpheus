package mediaplayer.orpheus.model.service;

import mediaplayer.orpheus.model.Database.DatabaseHandler;
import mediaplayer.orpheus.model.metadata.MetaExtractor;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;

public class MetadataService {
    private final String filepath = "src/main/java/mediaplayer/orpheus/mediafiles/BIMINI - No Way (with Avi Snow)  Latin Dance  NCS - Copyright Free Music.mp3";
    public void insertAndGatherMedia(String filepath) throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        //Debugging filepath, obviously don't want this hardcoded.

        MetaExtractor song = new MetaExtractor(filepath);
        song.gatherMetaDataTrack();
        song.gatherMetaDataYear();
        song.gatherMetaDataAlbumName();
        song.gatherMetaDataLength();
        song.gatherMetaDataTitle();
        song.gatherMetaDataArtist();

        DatabaseHandler newsong = new DatabaseHandler();

    }
}
