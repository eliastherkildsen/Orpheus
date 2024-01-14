package mediaplayer.orpheus.Controler;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Slider;
import javafx.util.Duration;

import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import mediaplayer.orpheus.model.Media.MediaUtil;
import mediaplayer.orpheus.model.Service.FileHandlerMedia;
import mediaplayer.orpheus.util.AnsiColorCode;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

// implementing the initializable interface in the HomeViewController class
public class HomeViewController implements Initializable {

    @FXML
    private MediaView mediaViewDisplay;

    @FXML
    private Slider sliderVolume;

    @FXML
    private Label labCurrentTime;

    @FXML
    private Label labMediaLength;

    @FXML
    private ImageView btnPlayIcon;


    public static String mediaPath = "src/main/java/mediaplayer/orpheus/mediaFiles/CAN T STOP THE FEELING! (from DreamWorks Animation s  TROLLS ) (Official Video).mp4";
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private boolean playSwitchStage = true;
    private Timer timer;
    private TimerTask task;
    //laves om til int
    private double currentTrackTime;
    private double currentSliderVol;
    private boolean mute = true;


    /**
     * Initialization method that loads and initializes data.
     *
     * The method is called on startup to prepare and load necessary data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // creates a file object based on the media path
        file = new File(mediaPath);

        // checks if the file exists
        if (file.exists()) {
            // creates a media object based on the file's URI
            media = new Media(file.toURI().toString());
            // creates a media player based on the media object
            mediaPlayer = new MediaPlayer(media);
            // prints a message if the file is found
            System.out.printf("%s[HomeViewControl][initialize] The file is found at the specified path%s\\n\",AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET");
        } else {
            // prints an error message if the file wasn't found
            System.out.printf("%s[HomeViewControl][initialize] The file was not found at the specified path%s\n",AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
        }

        // associates the mediaPlayer with the mediaViewDisplay for content playback
        mediaViewDisplay.setMediaPlayer(mediaPlayer);



        // listens for changes in the value of the volume slider
        sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {

            /**
             * Method that responds on changes in the volume slider's value
             *
             * @param observableValue
             * @param number
             * @param t1
             */
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                // sets the volume on mediaPlayer based on the volume slider's value
                mediaPlayer.setVolume(sliderVolume.getValue() * 0.01);
                // * multiplies the volume slider's value by 0.01 to scale it to the appropriate range for mediaPlayer
                // volume (0.0 to 1.0)
            }
        });
    }



    @FXML
    public void onActionbtnImportClick(){

        FileHandlerMedia.fileChooser();
    }




    /**
     * Method that handles the button click event for the play button.
     *
     * The method starts a timer, calls a utility method that plays or pauses the media based on the current state,
     * and updates the play button icon accordingly.
     * Finally, toggles the playSwitchStage for the next button click.
     *
     */
    @FXML
    public void onBtnPlayClick(){

        // starts a timer
        beginTimer();
        // play or pause the media and update the play button icon
        MediaUtil.playMedia(mediaPlayer,playSwitchStage,btnPlayIcon);
        // toggles the playSwitchStage for the next button click
        playSwitchStage = !playSwitchStage;
    }




    /**
     * Method that initializes and starts a timer to update the current time display of the media player.
     *
     * The timer updates UI components on the JavaFX Application Thread at fixed intervals,
     * starting with a 1000 milliseconds (1-second) delay and repeating every 1000 milliseconds (1 second).
     * The timer continues until the end of the media track, at which point it is automatically canceled.
     */
    public void beginTimer() {
        // initializes a timer and timer task
        timer = new Timer();
        task = new TimerTask() {

            /**
             * Method that runs the timer task and updates the UI components with the current time of the media player
             */
            @Override
            public void run() {

                // The code within Platform.runLater() ensures that UI updates occur on the JavaFX Application Thread,
                // preventing the "Not on FX application thread" error.
                Platform.runLater(() -> {
                    if (mediaPlayer != null) {
                        currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
                        double trackLength = media.getDuration().toSeconds();
                        String formattedTime = MediaUtil.secondsFormattedToTime(currentTrackTime);
                        // updates the current time display
                        labCurrentTime.setText(formattedTime);

                        // checks if the end of the media track is reached and cancel the timer if so
                        if (currentTrackTime == trackLength) {
                            cancelTimer();
                        }
                        // updates the media length display
                        labMediaLength.setText(MediaUtil.secondsFormattedToTime(trackLength));
                    }
                });
            }
        };

        // schedules the timer task to run at fixed intervals (starting after 1000 milliseconds and repeating every 1000 milliseconds).
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }



    /**
     *
     */
    public void cancelTimer(){
        timer.cancel();
        //TODO - when making skipMedia
    }




    /**
     * Method that handles the button click event for the skip forward button.
     *
     * The method calls a utility method to skip the media forward by 15 seconds and update the UI components.
     */
    @FXML
    public void onBtnSkipForwardsClick(){

        MediaUtil.skipMediaForward(currentTrackTime, media, mediaPlayer, labCurrentTime);
    }



    /**
     * Method that handles the button click event for the skip backward button.
     *
     * The method calls a utility method to skip the media backward by 15 seconds and update the UI components.
     */
    @FXML
    public void onBtnSkipBackwardsClick(){

        MediaUtil.skipMediaBackward(currentTrackTime, media, mediaPlayer, labCurrentTime);
    }




    /**
     * Method that handles the button click event for toggling volume mute
     *
     * The method calls a utility method to mute/unmute the volume
     */
    @FXML
    private void onBtnVolumeMuteClick(){
        // gets the current slider volume
        currentSliderVol = sliderVolume.getValue();

        MediaUtil.muteMedia(sliderVolume, currentSliderVol, mute);
        // toggles the mute switch for the next button click
        mute = !mute;
    }





    //to handle stage-switch
    @FXML
    private Button btnSearch, btnPlaylist, btnDelete;

    private ViewControler viewControler = new ViewControler();

    public void switchToPlaylistView(ActionEvent event) {

        mediaPlayer.pause();

        try {
            viewControler.switchToPlaylistScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSearchView(ActionEvent event) {

        mediaPlayer.pause();

        try {
            viewControler.switchToSearchScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHomeView(ActionEvent event) {

        mediaPlayer.pause();

        try {
            viewControler.switchToHomeScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

