package mediaplayer.orpheus.model.Media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaSkipTest {

    private static final int SKIP_BY_SECONDS = 15;

    // EQ TEST - valid / invalid

    @Test
    void validMediaSkipForward() {

        // Arrange
        double currentTrackTime = 50.0;
        double mediaLength = 272.0;

        // Act
        int newSkipTime = MediaSkip.mediaSkipForward(currentTrackTime,mediaLength);

        // Assert
        assertEquals(65,newSkipTime);
    }


    @Test
    void invalidMediaSkipForward() {

        // Arrange
        double currentTrackTime = - 50.0;
        double mediaLength = 272.0;

        // Act
        int newSkipTime = MediaSkip.mediaSkipForward(currentTrackTime,mediaLength);

        // Assert
        assertEquals(0,newSkipTime);
    }



    @Test
    void validMediaSkipBackward() {

        // Arrange
        double currentTrackTime = 50.0;

        // Act
        int newSkipTime = MediaSkip.mediaSkipBackward(currentTrackTime);

        // Assert
        assertEquals(35,newSkipTime);
    }


    @Test
    void invalidMediaSkipBackward() {

        // Arrange
        double currentTrackTime = -50.0;

        // Act
        int newSkipTime = MediaSkip.mediaSkipBackward(currentTrackTime);

        // Assert
        assertEquals(0,newSkipTime);
    }
}