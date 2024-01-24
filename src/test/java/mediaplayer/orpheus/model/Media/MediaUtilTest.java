package mediaplayer.orpheus.model.Media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaUtilTest {

    @Test
    void secondsFormattedToTime() {
        // Arrange
        double durationTime = 3678.0; // 1 hour, 1 minute, and 5 seconds

        // Act
        String formattedTime = MediaUtil.secondsFormattedToTime(durationTime);

        // Assert
        assertEquals("01:01:18", formattedTime);
    }
}