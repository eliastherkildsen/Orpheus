package jaudiotagger;

import mediaplayer.orpheus.model.metadata.MetaExtractor;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: This entire test needs refactoring. Maybe just delete it?.
 */
public class MetaExtractorTest {
    //File to test up against.
    String filePath = "C:\\Users\\Mads\\IdeaProjects\\Orpheus\\src\\main\\java\\mediaplayer\\orpheus\\mediafiles\\Summer - Bensound Royalty Free Music - No Copyright Music.mp3";
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
}
