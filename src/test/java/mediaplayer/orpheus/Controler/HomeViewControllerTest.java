package mediaplayer.orpheus.Controler;

import javafx.scene.media.MediaPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HomeViewControllerTest {

    private static MediaPlayer mediaPlayer;

    @Test
    void mediaChangeVol() {

        // Arrange
        double volumeValue = 50.0;

        // Act
        mediaPlayer.setVolume(volumeValue * 0.01);
        // * multiplies the volume slider's value by 0.01 to scale it to the appropriate range for mediaPlayer
        // volume (0.0 to 1.0)

        // Assert
        assertEquals(0.5,newSkipTime);


        // Assert

    }

}