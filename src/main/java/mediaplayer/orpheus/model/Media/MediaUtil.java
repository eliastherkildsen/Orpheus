package mediaplayer.orpheus.model.Media;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import mediaplayer.orpheus.Controler.HomeViewController;

public class MediaUtil {

    /**
     * Method for converting a duration time in seconds to the HH:MM:SS format
     *
     * @param durationTime
     * @return
     */
    public static String secondsFormattedToTime(double durationTime) {
        int hours = (int) durationTime / 3600;
        int secondsLeft = (int) durationTime % 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;

        // formats the time into HH:MM:SS
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
