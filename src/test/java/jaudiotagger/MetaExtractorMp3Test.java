package jaudiotagger;

import mediaplayer.orpheus.model.Metadata.MetaExtractorMp3;
import mediaplayer.orpheus.model.Service.MetadataService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: This entire test needs refactoring. Maybe just delete it?.
 */
public class MetaExtractorMp3Test {
    //File to test up against.
    String filePath = "src/main/java/mediaplayer/orpheus/mediafiles/Fallout - Teaser Trailer Prime Video.mp4";
    @Test
    void notNullTrackLength() throws IOException {
        MetaExtractorMp3 song = new MetaExtractorMp3(filePath);
        song.gatherMetaDataLength();

        assertTrue(song.gatherMetaDataLength() > 0);
    }
    @Test
    void getArtist() throws IOException {
        MetaExtractorMp3 song = new MetaExtractorMp3(filePath);
        System.out.println(song.gatherMetaDataArtist());
    }
    @Test
    void getTitle() throws IOException {
        MetaExtractorMp3 song = new MetaExtractorMp3(filePath);
        song.gatherMetaDataTitle();
        System.out.println(song.gatherMetaDataTitle());
    }
    @Test
    void canInssertIntoMedia() throws SQLException, IOException {
        MetadataService thing = new MetadataService(filePath);
        thing.insertAndGatherMediaMp3();
    }
}
