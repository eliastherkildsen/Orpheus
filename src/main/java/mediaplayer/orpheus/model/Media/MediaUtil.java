package mediaplayer.orpheus.model.Media;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
     * Method
     *
     * @param mediaPlayer
     * @param playSwitchStage
     * @param btnPlayIcon
     */
    public static void playMedia(MediaPlayer mediaPlayer, boolean playSwitchStage, ImageView btnPlayIcon) {

        if (playSwitchStage) {
            mediaPlayer.play();
            updatePlayButtonImage(pauseImageURL, btnPlayIcon);
        } else {
            mediaPlayer.pause();
            updatePlayButtonImage(playImageURL, btnPlayIcon);
        }
    }

    /**
     * Method
     *
     * @param imageURL
     */
    public static void updatePlayButtonImage(String imageURL, ImageView btnPlayIcon){

        Image image = new Image(imageURL);
        btnPlayIcon.setImage(image);
    }



    public static String imageURL (){

        return "hej";
    }

    /**
     *
     * @param imageURL
     */
    public static Image updatePlayButtonImage(String imageURL){

        Image image = new Image(imageURL);
        return image;

    }


    /**
     *
     * @param durationTime
     * @return
     */
    //anden classe
    public String secondsFormattedToTime(int durationTime) {
        //
        int hours = durationTime / 3600;
        int secondsLeft = durationTime % 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


    /**
     *
     */

    public static int forwardTime(int trackTime, int durationTime){
        int forwardTime;

        //egen metode (ren btnskipmetode)
        if (trackTime < durationTime - 15) {
            forwardTime = trackTime + 15;
        }
        else {
            forwardTime = durationTime;
        }

        return forwardTime;

        updateCurrentTimeLabel(forwardTime);

        mediaPlayer.seek(Duration.seconds(forwardTime));
        mediaPlayer.play();
    }



}
