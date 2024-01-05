package jaudiotagger;

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

public class JaudiotaggerTest {
    @Test
    void testMetaData() throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        String filePath = "src/main/java/mediaplayer/orpheus/res/Summer - Bensound Royalty Free Music - No Copyright Music.mp3";
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
}
