package mediaplayer.orpheus.Controler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import mediaplayer.orpheus.model.Media.MediaUtil;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.util.AnsiColorCode;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

// implementing the initializable interface in the HomeViewController class
public class HomeViewController implements Initializable {

    @FXML
    public HBox hBoxButtons;
    @FXML
    public Label mediaViewTitle;
    @FXML
    private BorderPane homePane;
    @FXML
    private MediaView mediaViewDisplay;
    @FXML
    private Slider sliderVolume;
    @FXML
    private Slider sliderProgres;
    @FXML
    private Label labCurrentTime;
    @FXML
    private Label labMediaLength;
    @FXML
    private ImageView btnPlayPauseIcon;
    @FXML
    private ImageView btnMuteIcon;
    @FXML
    private ImageView imgThumbnail;

    public static String mediaPath = "src/main/java/mediaplayer/orpheus/mediaFiles/CAN T STOP THE FEELING! (from DreamWorks Animation s  TROLLS ) (Official Video).mp4";
    private SceneController viewControler = new SceneController();
    private double heightOfScene;
    private double widthOfScene;
    private String playImageURL = "file:src/main/resources/css/images/play-circle.png";
    private String pauseImageURL = "file:src/main/resources/css/images/pause-circle.png";
    private String muteImageURL = "file:src/main/resources/css/images/volume-x.png";
    private String soundStepOneImageURL = "file:src/main/resources/css/images/volume-1.png";
    private String soundStepTwoImageURL = "file:src/main/resources/css/images/volume-2.png";
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
    public static ArrayList<MediaObj> mediaObjQue = new ArrayList<>();

    public static int cntQueIni;
    public static int cntQue;
    private  static final double ASPECT_RATIO = 16.0 / 9.0;
    private boolean isInitialized = false;



    /**
     * Initialization method that loads and initializes data.
     * The method is called on startup to prepare and load necessary data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!isInitialized) {
            int count = 0;

            count ++;
            System.out.println("!initalized entered: " + count);
            try {
                mediaObjQue.add(new MediaObj(21));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            loadMedia(cntQueIni);


            cntQueIni++;
            isInitialized = true;

            System.out.println("count (if): " + cntQue);

        }
        else {
            loadMedia(cntQue);

            cntQue++;
            System.out.println("count (else): " + cntQue);
        }
    }

    private void loadMedia(int cnt) {
        // creates a file object based on the media path
        file = new File(mediaObjQue.get(cnt % mediaObjQue.size()).getMediaPath());

        System.out.println("title: " + file);

        // checks if the file exists
        if (file.exists()) {
            // creates a media object based on the file's URI
            media = new Media(file.toURI().toString());
            // creates a media player based on the media object
            mediaPlayer = new MediaPlayer(media);
            // prints a message if the file is found
            System.out.printf("%s[HomeViewControl][initialize] The file is found at the specified path%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        } else {
            // prints an error message if the file wasn't found
            System.out.printf("%s[HomeViewControl][initialize] The file was not found at the specified path%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }

        // associates the mediaPlayer with the mediaViewDisplay for content playback
        mediaViewDisplay.setMediaPlayer(mediaPlayer);

        homePane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double padding = 100.0;
                mediaViewDisplay.setFitWidth(t1.doubleValue() - padding);

                //Saving the width everytime its changed for later use.
                widthOfScene = (double) number;
                //System.out.println("Width " + widthOfScene);

            }
        });

        homePane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double padding = 100.0;
                mediaViewDisplay.setFitHeight(t1.doubleValue() - padding);

                //Saving the height every time its changed for later use.
                heightOfScene = (double) number;
                //System.out.println("Height " + heightOfScene);

            }
        });



        mediaPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                sliderProgres.setMax(newValue.toSeconds());
            }
        });

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    sliderProgres.setValue(newValue.toSeconds());
                }
            }
        });
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
    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
        //TODO - when making skipMedia
    }



    @FXML
    private void onSliderVolumeMouseClick(){
        mediaChangeVol();
    }

    @FXML
    private void onSliderVolumeMouseReleased(){
        mediaChangeVol();
    }

    private void mediaChangeVol() {
        if (mute) {
            // gets the slider volume in %
            double volumeValue = sliderVolume.getValue();

            // sets the volume on mediaPlayer based on the volume slider's value
            mediaPlayer.setVolume(volumeValue * 0.01);
            // * multiplies the volume slider's value by 0.01 to scale it to the appropriate range for mediaPlayer
            // volume (0.0 to 1.0)

            System.out.println("[listener] volume value: "+volumeValue);
            volumeMedia(volumeValue);
        }
    }




    @FXML
    public void onActionbtnImportClick(){

        FileChooser.fileChooser();
    }


    /**
     * Method that handles the button click event for the play/pause button.
     */
    @FXML
    private void onBtnPlayPauseClick(){
        mediaPlayPause();
        updateMediaViewTitle();
    }


    @FXML
    private void onBtnNextClick(){
        mediaNext();
    }

    private void mediaNext() {
        mediaPlayer.stop();
        cancelTimer();

        cntQue++;
        System.out.println("mediaNext count: "+cntQue);

        loadMedia(cntQue);
        mediaPlayer.play();
    }






    @FXML
    private void onBtnPreviousClick(){
        mediaPrevious();
    }

    private void mediaPrevious() {
        if (cntQue == 0) {
            mediaPlayer.stop();
            cancelTimer();

            mediaObjQue.get(0);

            System.out.println("count (før): " + cntQue);

            loadMedia(cntQue);
            mediaPlayer.play();

            System.out.println("count (efter): " + cntQue);

        }
        else {
            mediaPlayer.stop();
            cancelTimer();

            cntQue--;
            System.out.println("mediaPrevious count: " + cntQue);

            loadMedia(cntQue);
            mediaPlayer.play();
        }
    }





    /**
     * Method that handles the button click event for the skip forward button.
     */
    @FXML
    private void onBtnSkipForwardsClick(){
        mediaSkipForward();
    }


    /**
     * Method that handles the button click event for the skip backward button.
     */
    @FXML
    private void onBtnSkipBackwardsClick(){
        mediaSkipBackward();
    }



    /**
     * Method that handles the button click event for toggling volume mute
     */
    @FXML
    private void onBtnVolumeMuteClick(){
        mediaMute();
    }

    @FXML
    private void onSliderProgresMousePressed() {
        sliderProgresOnDrag();
    }

    @FXML
    private void onSliderProgresMouseReleased() {
        Platform.runLater(() -> sliderProgresMouseRelease());
    }

    @FXML
    private void onSliderProgresMouseClick(){
        mediaPlayer.pause();
    }


    private void sliderProgresOnDrag() {
        mediaPlayer.pause();
        System.out.println("pause");
    }

    private void sliderProgresMouseRelease() {
        cancelTimer();

        double newCurrentVal = sliderProgres.getValue();
        System.out.println("track time før 0.01: " + newCurrentVal);

        int newSpot = (int) newCurrentVal;

        updateCurrentTimeLabel(newSpot);

        mediaPlayer.seek(Duration.seconds(newSpot));
        mediaPlayer.play();

        beginTimer();
        System.out.println("YEAH!");
    }








    //to handle stage-switch
    @FXML
    private Button btnSearch, btnPlaylist, btnDelete;
    public void switchToPlaylistView() {

        mediaPlayer.pause();

        try {
            viewControler.switchToPlaylistScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSearchView() {

        mediaPlayer.pause();

        try {
            viewControler.switchToSearchScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHomeView() {

        mediaPlayer.pause();

        try {
            viewControler.switchToHomeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    // Associated methods for button click events


    /**
     * Method that updates the mute button image based on the volume slider's value (in percentage - from 0 to 100 %)
     * @param volumeValue
     */
    private void volumeMedia(double volumeValue) {

        if (volumeValue == 0){
            updateMuteButtonImage(muteImageURL);
        } else if (volumeValue > 0 && volumeValue < 50) {
            updateMuteButtonImage(soundStepOneImageURL);
        }
        else {
            updateMuteButtonImage(soundStepTwoImageURL);
        }
    }

    /**
     * Method for playing/pausing media
     * The method starts a timer, plays or pauses the media based on the current state,
     * and updates the play button icon accordingly.
     * Finally, toggles the playSwitchStage for the next button click.
     */
    private void mediaPlayPause() {

        cancelTimer();

        beginTimer();

        if (playSwitchStage) {
            mediaPlayer.play();
            updatePlayButtonImage(pauseImageURL);
        } else {
            mediaPlayer.pause();
            updatePlayButtonImage(playImageURL);
        }

        togglePlayState();
    }

    /**
     * Method for updating the play/pause button image
     *
     * @param imageURL
     */
    private void updatePlayButtonImage(String imageURL){
        Image image = new Image(imageURL);
        // assigns the specified image from the given imageURL to the play button
        btnPlayPauseIcon.setImage(image);
    }

    /**
     * Method to toggle play/pause state
     */
    private void togglePlayState() {
        playSwitchStage = !playSwitchStage;
    }


    /**
     * Method for skipping media 15 seconds forward
     * The method skip the media forward by 15 seconds and updates the current time label
     */
    private void mediaSkipForward() {
        currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
        // variable that determines the new track time after skipping forward by 15 seconds
        int forwardTime;

        if (currentTrackTime < media.getDuration().toSeconds() - 15) {
            forwardTime = (int) currentTrackTime + 15;
        }
        else {
            forwardTime = (int) media.getDuration().toSeconds();
        }

        // updates the current time label with the new track time
        updateCurrentTimeLabel(forwardTime);

        // sets the media player to the new track time and continues playing
        mediaPlayer.seek(Duration.seconds(forwardTime));
        mediaPlayer.play();
    }


    /**
     * Method for updating the current time label with the formatted time based on the given time in seconds
     * @param time
     */
    private void updateCurrentTimeLabel(int time){

        labCurrentTime.setText(MediaUtil.secondsFormattedToTime(time));
    }


    /**
     * Method for skipping media 15 seconds backward
     * The method skip the media backward by 15 seconds and updates the current time label
     */
    private void mediaSkipBackward() {
        currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
        // variable that determines the new track time after skipping backward by 15 seconds
        int backwardTime;

        // checks if the current track time is greater than the first 14 seconds of the media
        if (currentTrackTime > 14) {
            backwardTime = (int) currentTrackTime - 15;
        }
        // if the current track time is less than 15 seconds (less than the skip backward time)
        else {
            backwardTime = (int) Duration.ZERO.toSeconds();
        }

        updateCurrentTimeLabel(backwardTime);

        // sets the media player to the new track time and continues playing
        mediaPlayer.seek(Duration.seconds(backwardTime));
        mediaPlayer.play();
    }


    /**
     * Method for mute/unmute the media
     * The method mute or mutes the media based on the current state,
     * and updates the mute button icon accordingly.
     * Finally, toggles the mute stage for the next button click.
     */
    private void mediaMute() {
    // VIRKER!
        if (mute) {
            // gets the current slider volume in %
            currentSliderVol = sliderVolume.getValue();
            // updates the slider value before setMute
            sliderVolume.setValue(0);
            mediaPlayer.setMute(true);
            updateMuteButtonImage(muteImageURL);
        }
        else {
            mediaPlayer.setMute(false);
            sliderVolume.setValue(currentSliderVol);

            // if current slider volume is less than 50%
            if (currentSliderVol > 0 && currentSliderVol < 50) {
                updateMuteButtonImage(soundStepOneImageURL);
            }
            // if current slider volume is more than 50%
            else if (currentSliderVol >= 50 && currentSliderVol <= 100){
                updateMuteButtonImage(soundStepTwoImageURL);
            }
        }

        toggleMuteState();
    }

    /**
     * Method for updating the mute/unmute button image
     * @param imageURL
     */
    private void updateMuteButtonImage(String imageURL) {
        Image image = new Image(imageURL);
        // assigns the specified image from the given imageURL to the play button
        btnMuteIcon.setImage(image);
    }

    /**
     * Updates the title overlaying the mediaView with a song title.
     * Then makes it disappear after X seconds.
     */
    public void updateMediaViewTitle() {
        mediaViewTitle.setVisible(true);
        mediaViewTitle.setText("I need a dynamic Title"); //TODO I NEED A TITLE DUDE
        // Set the time duration for which the label should be visible (in seconds)
        double visibilityTimeInSeconds = 6.0;

        // Create a Timeline that hides the label after the specified duration
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(visibilityTimeInSeconds), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Use Platform.runLater to ensure UI updates are on the JavaFX Application Thread
                        Platform.runLater(() -> mediaViewTitle.setVisible(false));
                    }
                })
        );
        // Start the timeline
        timeline.play();
    }

    /**
     * Method to toggle mute/unmute state
     */
    private void toggleMuteState() {
        mute = !mute;
    }

    /**
     * Method for switching thumbnail in the homeview
     * @param arrIndex
     */
    private void changethumbnail(int arrIndex){
        //Gets the objects image path
        String imagePath =  "file:" + mediaObjQue.get(arrIndex).getImagePath();
        Image thumbnailImage = new Image(imagePath);

        imgThumbnail.setImage(thumbnailImage);

    }

}

