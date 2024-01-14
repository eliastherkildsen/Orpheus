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

    private static String playImageURL = "file:src/main/resources/css/images/play-circle.png";
    private static String pauseImageURL = "file:src/main/resources/css/images/pause-circle.png";
    private static String muteImageURL = "file:src/main/resources/css/images/volume-x.png";
    private static String soundStepOneImageURL = "file:src/main/resources/css/images/volume-1.png";
    private static String soundStepTwoImageURL = "file:src/main/resources/css/images/volume-2.png";

    /**
     * Method that plays or pauses the media based on the current state and updates the play button's image accordingly
     *
     * @param mediaPlayer
     * @param playSwitchStage
     * @param btnPlayIcon
     */
    public static void playMedia(MediaPlayer mediaPlayer, boolean playSwitchStage, ImageView btnPlayIcon) {
        // if in play state; play the media and update the play button image to pause
        if (playSwitchStage) {
            mediaPlayer.play();
            updatePlayButtonImage(pauseImageURL, btnPlayIcon);
        // if in pause state; pause the media and update the play button image to play
        } else {
            mediaPlayer.pause();
            updatePlayButtonImage(playImageURL, btnPlayIcon);
        }
    }

    /**
     * Method for updating the play button's image with the specified URL
     *
     * @param imageURL
     */
    public static void updatePlayButtonImage(String imageURL, ImageView btnPlayIcon){
        // creates an image object using the provided URL
        Image image = new Image(imageURL);
        // assigns the specified image from the given imageURL to the play button
        btnPlayIcon.setImage(image);
    }


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

    /**
     * Method for skipping the media forward by 15 seconds and updating the UI components
     *
     * @param currentTrackTime
     * @param media
     * @param mediaPlayer
     * @param labCurrentTime
     */
    public static void skipMediaForward(double currentTrackTime, Media media, MediaPlayer mediaPlayer, Label labCurrentTime){
        // variable that determines the new track time after skipping forward by 15 seconds
        int forwardTime;

        if (currentTrackTime < media.getDuration().toSeconds() - 15) {
            currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
            forwardTime = (int) currentTrackTime + 15;
        }
        else {
            forwardTime = (int) media.getDuration().toSeconds();
        }

        // updates the current time label with the new track time
        updateCurrentTimeLabel(forwardTime, labCurrentTime);

        // sets the media player to the new track time and continues playing
        mediaPlayer.seek(Duration.seconds(forwardTime));
        mediaPlayer.play();
    }



    /**
     * Method for skipping the media backward by 15 seconds and updating the UI components
     *
     * @param currentTrackTime
     * @param media
     * @param mediaPlayer
     * @param labCurrentTime
     */
    public static void skipMediaBackward(double currentTrackTime, Media media, MediaPlayer mediaPlayer, Label labCurrentTime){
        // variable that determines the new track time after skipping backward by 15 seconds
        int backwardTime;

        // checks if the current track time is greater than the first 14 seconds of the media
        if (currentTrackTime > 14) {
            currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
            backwardTime = (int) currentTrackTime - 15;
        }
        // if the current track time is less than 15 seconds (less than the skip backward time)
        else {
            backwardTime = (int) Duration.ZERO.toSeconds();
        }

        // updates the current time label with the new track time
        updateCurrentTimeLabel(backwardTime, labCurrentTime);

        // sets the media player to the new track time and continues playing
        mediaPlayer.seek(Duration.seconds(backwardTime));
        mediaPlayer.play();
    }


    /**
     * Method for updating the current time label with the formatted time based on the given time in seconds
     *
     * @param time
     */
    private static void updateCurrentTimeLabel(int time, Label labCurrentTime) {

        labCurrentTime.setText(secondsFormattedToTime(time));
    }



    /**
     * Method for mute or onmute the media based on the current state and the provided volume
     *
     * @param sliderVolume
     * @param currentSliderVol
     * @param mute
     */
    public static void muteMedia(Slider sliderVolume, double currentSliderVol, boolean mute){
        // checks if muted, and then sets the volume to 0
        if (mute) {
            sliderVolume.setValue(0);
        }
        // otherwise set the volume to the previously saved value
        else {
            sliderVolume.setValue(currentSliderVol);
        }
    }




}
