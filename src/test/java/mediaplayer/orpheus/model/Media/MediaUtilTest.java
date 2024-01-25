package mediaplayer.orpheus.model.Media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaUtilTest {

    // EQ TEST - valid / invalid
    @Test
    void validSecondsFormattedToTime() {

        // Arrange
        double durationTime = 3678.0; // 1 hour, 1 minute, and 18 seconds

        // Act
        String formattedTime = MediaUtil.secondsFormattedToTime(durationTime);

        // Assert
        assertEquals("01:01:18", formattedTime);
    }

    @Test
    void invalidSecondsFormattedToTime() {

        // Arrange
        double durationTime = -10.0; // - 10 seconds

        // Act
        String formattedTime = MediaUtil.secondsFormattedToTime(durationTime);

        // Assert
        assertEquals("00:00:00", formattedTime);
    }

}