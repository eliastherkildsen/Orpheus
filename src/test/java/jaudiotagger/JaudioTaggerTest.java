package jaudiotagger;

import mediaplayer.orpheus.model.metadata.jaudiotagger;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JaudioTaggerTest {
    String filePath = "src/main/java/mediaplayer/orpheus/media/Summer - Bensound Royalty Free Music - No Copyright Music.mp3";
    @Test
    void testGetMetaData() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        MP3File f = (MP3File) AudioFileIO.read(new File(filePath));
        MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();

        int trackLength = audioHeader.getTrackLength();
        float sampleRate = audioHeader.getSampleRateAsNumber();
        String channels = audioHeader.getChannels();
        boolean isVBR = audioHeader.isVariableBitRate();

        System.out.println("Track Length: " + trackLength + " seconds");
        System.out.println("Sample Rate: " + sampleRate + " Hz");
        System.out.println("Channels: " + channels);
        System.out.println("Variable Bit Rate: " + isVBR);
    }
    @Test
    void notNullTrackLength() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        MP3File mp3File = (MP3File) AudioFileIO.read(new File(filePath));
        MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
        assertTrue(audioHeader.getTrackLength() > 0);
    }

    @Test
    void checkJagTag() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        jaudiotagger test = new jaudiotagger("src/main/java/mediaplayer/orpheus/mediafiles/BIMINI - No Way (with Avi Snow)  Latin Dance  NCS - Copyright Free Music.mp3");
        test.getMetaDataLength();

        assertTrue(test.getMediaLength() > 0);
    }
}
