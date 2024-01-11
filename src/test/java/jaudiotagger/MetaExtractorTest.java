package jaudiotagger;

import mediaplayer.orpheus.model.metadata.MetaExtractor;
import mediaplayer.orpheus.model.service.MetadataService;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: This entire test needs refactoring. Maybe just delete it?.
 */
public class MetaExtractorTest {
    //File to test up against.
    String filePath = "src/main/java/mediaplayer/orpheus/mediafiles/BIMINI - No Way (with Avi Snow)  Latin Dance  NCS - Copyright Free Music.mp3";
    @Test
    void notNullTrackLength() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        MetaExtractor song = new MetaExtractor(filePath);
        song.gatherMetaDataLength();

        assertTrue(song.gatherMetaDataLength() > 0);
    }
    @Test
    void getArtist() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        MetaExtractor song = new MetaExtractor(filePath);
        System.out.println(song.gatherMetaDataArtist());
    }
    @Test
    void getTitle() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        MetaExtractor song = new MetaExtractor(filePath);
        song.gatherMetaDataTitle();
        System.out.println(song.gatherMetaDataTitle());
    }
    @Test
    void canInssertIntoMedia() throws CannotReadException, TagException, SQLException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        MetadataService thing = new MetadataService(filePath);
        thing.insertAndGatherMedia();
    }
}
