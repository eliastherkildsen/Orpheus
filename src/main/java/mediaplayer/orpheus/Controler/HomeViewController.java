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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import mediaplayer.orpheus.model.Media.MediaSkip;
import mediaplayer.orpheus.model.Media.MediaUtil;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.model.Service.FileHandlerMedia;
import mediaplayer.orpheus.util.AnsiColorCode;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

// implementing the initializable interface in the HomeViewController class
public class HomeViewController implements Initializable {

    private SceneController viewControler = new SceneController();

    @FXML
    public HBox hBoxButtons;
    @FXML
    public Label mediaViewTitle;
    @FXML
    private BorderPane homePane;
    @FXML
    private MediaView mediaViewDisplay;
    @FXML
    private ImageView imageViewTN;
    @FXML
    private Slider sliderVolume, sliderProgres;
    @FXML
    private Label labCurrentTime, labMediaLength, labMediaName, labArtistName;
    @FXML
    private ImageView btnPlayPauseIcon, btnMuteIcon, imgThumbnail;


    private String playImageURL = "file:src/main/resources/css/images/play-circle.png";
    private String pauseImageURL = "file:src/main/resources/css/images/pause-circle.png";
    private String muteImageURL = "file:src/main/resources/css/images/volume-x.png";
    private String soundStepOneImageURL = "file:src/main/resources/css/images/volume-1.png";
    private String soundStepTwoImageURL = "file:src/main/resources/css/images/volume-2.png";

    private double heightOfScene;
    private double widthOfScene;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private TimerTask task;

    //laves om til int
    private double currentTrackTime;
    private double currentSliderVol;
    public static int cntQue;
    private static int mediaArrIndex;

    private boolean playSwitchStage = true;
    private boolean mute = true;
    private boolean isInitialized = false;

    public static ArrayList<MediaObj> mediaObjQue = new ArrayList<>();

    private  static final double ASPECT_RATIO = 16.0 / 9.0;



    /**
     * Initialization method that loads and initializes data.
     * The method is called on startup to prepare and load necessary data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (!isInitialized && !mediaObjQue.isEmpty()) {

            try {
                loadMedia();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("%s[HomeViewController][Initialized] Class has been init.", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        }
        else if (!mediaObjQue.isEmpty()) {

            try {
                loadMedia();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("%s[HomeViewController][Initialized] Class has been init.", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        }
        else {
            System.out.printf("%s[HomeViewController][Initialized] Class has not been init.%s", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
            loadListenersView();

        }


    }

    private void loadListenersMediaPlay() {
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

    private void loadListenersView() {
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
                double padding = 85;
                mediaViewDisplay.setFitHeight(t1.doubleValue() - padding);

                //Saving the height every time its changed for later use.
                heightOfScene = (double) number;
                //System.out.println("Height " + heightOfScene);

            }
        });

    }


    private void loadMedia() throws FileNotFoundException {

        System.out.printf("%s[HomeViewControl][loadMedia] Trying to load media%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        if (mediaObjQue.size() == 1){
            file = new File(mediaObjQue.get(0).getMediaPath());
        }

        else {
            mediaArrIndex =  cntQue % mediaObjQue.size();
            // creates a file object based on the media path
            file = new File(mediaObjQue.get(mediaArrIndex).getMediaPath());
        }

        if (file.exists()) {

            // creates a media object based on the file's URI
            media = new Media(file.toURI().toString());
            // creates a media player based on the media object
            mediaPlayer = new MediaPlayer(media);
            // prints a message if the file is found

            System.out.printf("%s[HomeViewControl][loadMedia] The file is found at the specified path%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }

        else {
            // prints an error message if the file wasn't found
            System.out.printf("%s[HomeViewControl][loadMedia] The file was not found at the specified path%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }





        System.out.printf("%s[HomeViewControl][loadMedia] Setting display...%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        FileHandlerMedia fileType = new FileHandlerMedia(mediaObjQue.get(mediaArrIndex).getMediaPath());
        String type = fileType.mp3OrMp4();

        if ((type).equalsIgnoreCase("mp3")) {

            imageViewTN.setVisible(true);

            // TODO - new Image(mediaImagePath);
            // gets the objects image path
            // String mediaImagePath = "file:" + mediaObjQue.get(mediaArrIndex).getImagePath();

            Image image = new Image("file:src/main/resources/css/images/audio-lines.png");
            imageViewTN.setImage(image);

            System.out.printf("%s[HomeViewController][load] selected media is a mp3, loading thumbnail.... %s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        }
        else {
            imageViewTN.setVisible(false);
            mediaViewDisplay.setMediaPlayer(mediaPlayer);

            System.out.printf("%s[HomeViewController][load] selected media is a mp4, loading video.... %s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }

        changeThumbnailAndImageLabels();
        beginTimer();
        loadListenersMediaPlay();
        loadListenersView();
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
    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
        //TODO - when making skipMedia
    }


    /**
     * Method that handles "click with mouse" event for the volume slider.
     */
    @FXML
    private void onSliderVolumeMouseClick(){
        mediaChangeVol();
    }


    /**
     * Method that handles "mouse released" event for the volume slider.
     */
    @FXML
    private void onSliderVolumeMouseReleased(){
        mediaChangeVol();
    }


    /**
     * Method that handles the button click event for the import button.
     */
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


    /**
     * Method that handles the button click event for the next button.
     */
    @FXML
    private void onBtnNextClick() throws FileNotFoundException {
        mediaNext();
    }


    /**
     *
     * @throws FileNotFoundException
     */
    @FXML
    private void onBtnPreviousClick() throws FileNotFoundException {
        mediaPrevious();
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


    /**
     * Method that handles "mouse pressed" event for the progress slider.
     */
    @FXML
    private void onSliderProgresMousePressed() {
        sliderProgresOnDrag();
    }


    /**
     * Method that handles "mouse released" event for the progress slider.
     */
    @FXML
    private void onSliderProgresMouseReleased() {
        Platform.runLater(() -> sliderProgresMouseRelease());
    }


    /**
     * Method that handles "click with mouse" event for the progress slider.
     */
    @FXML
    private void onSliderProgresMouseClick(){
        pauseWithValidation();
    }



    // Associated methods for button click events

    private void mediaChangeVol() {
        if (mute) {
            // gets the slider volume in %
            double volumeValue = sliderVolume.getValue();

            // sets the volume on mediaPlayer based on the volume slider's value
            mediaPlayer.setVolume(volumeValue * 0.01);
            // * multiplies the volume slider's value by 0.01 to scale it to the appropriate range for mediaPlayer
            // volume (0.0 to 1.0)

            volumeMedia(volumeValue);
        }
    }


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

        if (!mediaObjQue.isEmpty()){
            cancelTimer();
            beginTimer();

            if (playSwitchStage) {

                playWithValidation();

            } else {
                pauseWithValidation();
            }

            togglePlayState();
        }
    }

    /**
     *
     */
    private void stopWithValidation(){
        try{
            mediaPlayer.stop();
        }catch (NullPointerException e){
            System.out.printf("%s[HomeViewController][stopWithValidation] No media to stop%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
    }


    /**
     *
     */
    private void pauseWithValidation(){
        try{
            mediaPlayer.pause();
            updatePlayButtonImage(playImageURL);

            System.out.printf("%s[HomeViewControl][pauseWithValidation] Media is paused%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

         } catch (NullPointerException e){
            System.out.printf("%s[HomeViewController][pauseWithValidation] No media to pause%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
    }


    /**
     *
     */
    private void playWithValidation(){
        try{
            mediaPlayer.play();
            updatePlayButtonImage(pauseImageURL);

            System.out.printf("%s[HomeViewControl][playWithValidation] Media plays%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        } catch (NullPointerException e){
            System.out.printf("%s[HomeViewController][playWithValidation] No media to play%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
    }


    /**
     *
     * @throws FileNotFoundException
     */
    private void mediaNext() throws FileNotFoundException {

        // handles stopping the media
        stopWithValidation();
        cancelTimer();

        // loads next media
        cntQue++;
        loadMedia();

        // plays the next media
        playSwitchStage = true;
        onBtnPlayPauseClick();
    }


    /**
     * Method for updating the play/pause button image
     *
     * @param imageURL button image
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
     *
     * @throws FileNotFoundException
     */
    private void mediaPrevious() throws FileNotFoundException {

        // handles stopping the media
        stopWithValidation();
        cancelTimer();

        if (cntQue == 0) {
            mediaObjQue.get(0);
        }

        else {
            cntQue--;
        }

        // loads next media
        loadMedia();

        // plays the next media
        playSwitchStage = true;
        onBtnPlayPauseClick();
    }


    /**
     * Method for skipping the media forward by 15 seconds and updates the current time label
     */
    private void mediaSkipForward() {

        currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
        double mediaLength = media.getDuration().toSeconds();

        int newTrackTime = MediaSkip.mediaSkipForward(currentTrackTime, mediaLength);

        // updates the current time label with the new track time
        updateCurrentTimeLabel(newTrackTime);

        // sets the media player to the new track time and continues playing
        mediaPlayer.seek(Duration.seconds(newTrackTime));
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
     * Method for skipping the media backward by 15 seconds and updates the current time label
     */
    private void mediaSkipBackward() {
        currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
        double mediaLength = media.getDuration().toSeconds();

        int newTrackTime = MediaSkip.mediaSkipBackward(currentTrackTime, mediaLength);

        // updates the current time label with the new track time
        updateCurrentTimeLabel(newTrackTime);

        // sets the media player to the new track time and continues playing
        mediaPlayer.seek(Duration.seconds(newTrackTime));
        mediaPlayer.play();
    }


    /**
     * Method for mute/unmute the media
     * The method mute or mutes the media based on the current state,
     * and updates the mute button icon accordingly.
     * Finally, toggles the mute stage for the next button click.
     */
    private void mediaMute() {
        if (mute) {
            // gets and saves the current slider volume in %
            currentSliderVol = sliderVolume.getValue();

            // updates the slider value before setMute
            sliderVolume.setValue(0);
            mediaPlayer.setMute(true);

            updateMuteButtonImage(muteImageURL);

            System.out.printf("%s[HomeViewControl][mediaMute] The media is muted%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }
        else {
            mediaPlayer.setMute(false);
            sliderVolume.setValue(currentSliderVol);

            // imageURL if current slider volume is less than 50%
            if (currentSliderVol > 0 && currentSliderVol < 50) {
                updateMuteButtonImage(soundStepOneImageURL);
            }
            // imageURL if current slider volume is more than 50%
            else if (currentSliderVol >= 50 && currentSliderVol <= 100){
                updateMuteButtonImage(soundStepTwoImageURL);
            }

            System.out.printf("%s[HomeViewControl][mediaMute] The media is unmuted%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
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
     *
     */
    private void sliderProgresOnDrag() {
        pauseWithValidation();
    }


    /**
     *
     */
    private void sliderProgresMouseRelease() {

        cancelTimer();

        // gets the now updated user selected media time
        int selectedMediaTime = (int) sliderProgres.getValue();

        updateCurrentTimeLabel(selectedMediaTime);
        mediaPlayer.seek(Duration.seconds(selectedMediaTime));
        mediaPlayer.play();

        beginTimer();
    }


    /**
     * Updates the title overlaying the mediaView with a song title.
     * Then makes it disappear after X seconds.
     */
    public void updateMediaViewTitle() {

        if (!mediaObjQue.isEmpty()){
            mediaViewTitle.setVisible(true);
            mediaViewTitle.setText(mediaObjQue.get(mediaArrIndex).getMediaTitle());
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
    }


    /**
     * Method to toggle mute/unmute state
     */
    private void toggleMuteState() {
        mute = !mute;
    }


    /**
     * Method that calls the change methods
     */
    private void changeThumbnailAndImageLabels(){
        changeThumbnail();
        changeImagelables();
    }


    /**
     * Method for switching thumbnail in the homeView
     */
    private void changeThumbnail(){

        // gets the objects image path
        String imagePath = "file:" + mediaObjQue.get(mediaArrIndex).getImagePath();

        Image thumbnailImage = new Image(imagePath);
        imgThumbnail.setImage(thumbnailImage);
    }


    /**
     * Method for changing the media title displayed next to the thumbnail
     */
    private void changeImagelables(){

        labMediaName.setText(mediaObjQue.get(mediaArrIndex).getMediaTitle());
        labArtistName.setText(mediaObjQue.get(mediaArrIndex).getMediaArtist());

    }



    //to handle stage-switch
    @FXML
    private Button btnSearch, btnPlaylist, btnDelete;
    public void switchToPlaylistView() {

        pauseWithValidation();

        try {
            viewControler.switchToPlaylistScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSearchView() {

        pauseWithValidation();

        try {
            viewControler.switchToSearchScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHomeView() {

        pauseWithValidation();

        try {
            viewControler.switchToHomeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

