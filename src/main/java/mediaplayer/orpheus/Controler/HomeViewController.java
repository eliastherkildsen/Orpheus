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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import mediaplayer.orpheus.model.Media.MediaShuffle;
import mediaplayer.orpheus.model.Media.MediaSkip;
import mediaplayer.orpheus.model.Media.MediaUtil;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.model.Service.FileHandlerMedia;
import mediaplayer.orpheus.util.AnsiColorCode;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

// implementing the initializable interface in the HomeViewController class
public class HomeViewController implements Initializable {
    @FXML
    public AnchorPane anchor;
    private SceneController viewControler = new SceneController();

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
    private ImageView btnPlayPauseIcon, btnMuteIcon, imgThumbnail, bntShuffleIcon;


    private String playImageURL = "file:src/main/resources/css/images/play-circle.png";
    private String pauseImageURL = "file:src/main/resources/css/images/pause-circle.png";
    private String muteImageURL = "file:src/main/resources/css/images/volume-x.png";
    private String soundStepOneImageURL = "file:src/main/resources/css/images/volume-1.png";
    private String soundStepTwoImageURL = "file:src/main/resources/css/images/volume-2.png";
    private String shuffleActImageURL = "file:src/main/resources/css/images/shuffle.png";
    private String shuffleDeactImageURL = "file:src/main/resources/css/images/shufflegrey.png";

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private TimerTask task;
    private double currentTrackTime;
    private double currentSliderVol;
    public static int cntQue;
    private static int mediaArrIndex;

    private boolean playSwitchStage = true;
    private boolean mute = true;
    private boolean shuffle = false;
    private boolean isInitialized = false;

    public static ArrayList<MediaObj> mediaObjQue = new ArrayList<>();
    private static ArrayList<MediaObj> shufflePlaylist = new ArrayList<>();





    /**
     * Initialization method that loads and initializes data.
     * The method is called on startup to prepare and load necessary data.
     *
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resource bundle containing localized resources for this root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        shuffle = false;

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


    /**
     * Listeners that changes the progress sliders location depending on the current track time.
     *
     * The first listener updates the maximum value of the progress slider based on the total duration of the media,
     * and the second listener dynamically updates the slider position as the media plays.
     */
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


    /**
     * Listeners that changes the size of the MediaView depending on the screensize OR the location of the default background image, Also depedant of screen size.
     */
    private void loadListenersView() {
        homePane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double padding = 85;
                mediaViewDisplay.setFitWidth(t1.doubleValue() - padding);

                //Saving the width everytime its changed for later use.
                //widthOfScene = (double) number;
                //System.out.println("Width " + widthOfScene);

                //Checks if a image is null or throwing an error, if not it'll get the size of the screen, the size of the image, divide that by 2, minus padding (Fixing design fault) and poof the image is center of the view.
                if (imageViewTN.getImage() != null && !imageViewTN.getImage().isError()) {
                    double x = (t1.doubleValue() - imageViewTN.getImage().getWidth()) / 2 - padding;
                    AnchorPane.setLeftAnchor(imageViewTN, x);
                }

            }
        });
        homePane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double padding = 47;
                mediaViewDisplay.setFitHeight(t1.doubleValue() - padding);

                //Saving the height every time its changed for later use.
                //heightOfScene = (double) number;
                //System.out.println("Height " + heightOfScene);

                //Checks if a image is null or throwing an error, if not it'll get the size of the screen, the size of the image, divide that by 2, minus padding (Fixing design fault) and poof the image is center of the view.
                if (imageViewTN.getImage() != null && !imageViewTN.getImage().isError()) {
                    double y = (t1.doubleValue() - imageViewTN.getImage().getHeight()) / 2 - padding;
                    AnchorPane.setTopAnchor(imageViewTN, y);
                }

            }
        });

    }


    /**
     * Method for loading and initializing media content for playback.
     *
     * This method attempts to load media based on the selected media type: a single media or a playlist (shuffled or regular mode).
     * It sets up a media player, handles end-of-media events, and displays thumbnail for MP3 or video for MP4.
     * It also initiates timers and listeners for media playback control.
     *
     * @throws FileNotFoundException if the specified media file is not found.
     */
    private void loadMedia() throws FileNotFoundException {

        System.out.printf("%s[HomeViewControl][loadMedia] Trying to load media%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        // if only one media is chosen to be played
        if (mediaObjQue.size() == 1){
            file = new File(mediaObjQue.get(0).getMediaPath());
        }

        // if a playlist is chosen to be played
        else {

            // shuffle button activated
            if (shuffle){

                System.out.printf("%s[HomeViewControl][loadMedia] Shuffle mode chosen%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

                // gets the index and the media path from the shuffled playlist for the next media to be played
                mediaArrIndex = cntQue % shufflePlaylist.size();
                file = new File(shufflePlaylist.get(mediaArrIndex).getMediaPath());
            }

            // regular playback of playlist
            else{

                System.out.printf("%s[HomeViewControl][loadMedia] Regular mode chosen%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

                // gets the index and the media path from the regular playlist for the next media to be played
                mediaArrIndex = cntQue % mediaObjQue.size();
                file = new File(mediaObjQue.get(mediaArrIndex).getMediaPath());
            }
        }

        if (file.exists()) {

            // creates a media object based on the file's URI
            media = new Media(file.toURI().toString());
            // creates a media player based on the media object
            mediaPlayer = new MediaPlayer(media);

            // when the end of media is reached
            mediaPlayer.setOnEndOfMedia(() -> {
                try {
                    cancelTimer();
                    mediaNext();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

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

            // gets the objects image path
            String mediaImagePath = "file:" + mediaObjQue.get(mediaArrIndex).getImagePath();

            Image image = new Image(mediaImagePath);
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
        timer.scheduleAtFixedRate(task, 100, 1000);
    }


    /**
     * Method for cancelling the timer if it is active.
     */
    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
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
     * Method that handles the button click event for the play/pause button, which includes updating the media view title.
     */
    @FXML
    private void onBtnPlayPauseClick(){

            mediaPlayPause();
            updateMediaViewTitle();
    }


    /**
     * Method that handles the button click event for the next media button.
     *
     * @throws FileNotFoundException if the next media file is not found.
     */
    @FXML
    private void onBtnNextClick() throws FileNotFoundException {
        mediaNext();
    }


    /**
     * Method that handles the button click event for the previous media button.
     *
     * @throws FileNotFoundException if the previous media file is not found.
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
        sliderProgresMouseRelease();
    }


    /**
     * Method that handles the button click event for the shuffle button.
     */
    @FXML
    private void onBtnShuffleClick() throws FileNotFoundException {
        mediaShuffle();
    }




    // ASSOCIATED METHODS FOR BUTTON CLICK EVENTS


    /**
     * Method for shuffling or resetting the order of a chosen playlist.
     *
     * Depending on the current shuffle state, this method either shuffles the chosen playlist
     * or sets it back to its original track order. It updates the shuffle button icon, resets
     * the counter "cntQue," toggles the shuffle state for the next button click, and loads
     * the media while setting the play switch stage ready for playing the media.
     *
     * @throws FileNotFoundException If the media file is not found.
     */
    private void mediaShuffle() throws FileNotFoundException {

        if (!mediaObjQue.isEmpty()) {
            // local variable used to set correct shuffle button image depending on the shuffle mode
            String shuffleImage;

            if (!shuffle) {

                System.out.printf("%s[HomeViewControl][mediaShuffle] Shuffle function activated%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

                // shuffle the chosen playlist
                shufflePlaylist = MediaShuffle.shufflePlaylist(mediaObjQue);

                // set button image - shuffle activated
                shuffleImage = shuffleActImageURL;

            } else {

                System.out.printf("%s[HomeViewControl][mediaShuffle] Shuffle function deactivated%s\n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

                // set button image - shuffle deactivated
                shuffleImage = shuffleDeactImageURL;
            }

            // handles stopping the media
            cancelTimer();
            stopWithValidation();

            // resets cntQue
            cntQue = 0;

            updateShuffleButtonImage(shuffleImage);
            toggleShuffleState();

            loadMedia();

            playSwitchStage = false;
            mediaPlayPause();
        }

    }


    /**
     * Method for updating the shuffle button image based on the shuffle mode (activated or deactivated)
     *
     * @param imageURL button image
     */
    private void updateShuffleButtonImage(String imageURL) {
        Image image = new Image(imageURL);
        // assigns the specified image from the given imageURL to the play button
        bntShuffleIcon.setImage(image);
    }


    /**
     * Method to toggle shuffle state
     */
    private void toggleShuffleState(){
        shuffle = !shuffle;
    }


    /**
     * Method for changing the volume of the media player.
     *
     * The method adjusts the volume of the media player based on the current value of the volume slider.
     * If not muted, it sets the volume to the corresponding percentage of the slider value and updates the button image.
     */
    private void mediaChangeVol() {

        if (!mediaObjQue.isEmpty()){
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
        else {
            System.out.printf("%s[HomeViewController][mediaChangeVol] No media to change volume on%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
    }


    /**
     * Method that updates the mute button image based on the volume slider's value (in percentage - from 0 to 100 %)
     *
     * @param volumeValue the volume level as a percentage (ranging from 0 to 100%).
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
     * Method for playing/pausing media.
     *
     * The method starts a timer, plays or pauses the media based on the current state,
     * and updates the play button icon accordingly.
     * Finally, it toggles the playSwitchStage for the next button click.
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
        else {
            System.out.printf("%s[HomeViewController][mediaPlayPause] No media to play or pause%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
    }


    /**
     * Method that stops the media player with validation.
     */
    private void stopWithValidation(){
        try{
            mediaPlayer.stop();
        }catch (NullPointerException e){
            System.out.printf("%s[HomeViewController][stopWithValidation] No media to stop%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
    }


    /**
     * Method that pauses the media player with validation.
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
     * Method that plays the media player with validation.
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
     * Method for skipping to the next media in the playlist and stopping the current media if playing.
     *
     * This method handles stopping the current media, loads the next media while setting the
     * play switch stage to true, and then playing the media.
     *
     * @throws FileNotFoundException if the next media file is not found.
     */
    private void mediaNext() throws FileNotFoundException {
        if (!mediaObjQue.isEmpty()){

            // handles stopping the media
            cancelTimer();
            stopWithValidation();

            // loads next media
            cntQue++;
            loadMedia();

            // plays the next media
            playSwitchStage = true;
            onBtnPlayPauseClick();
        }
        else {
            System.out.printf("%s[HomeViewController][mediaNext] No media to skip%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
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
     * Method for
     *
     * @throws FileNotFoundException if the previous media file is not found.
     */
    private void mediaPrevious() throws FileNotFoundException {
        if (!mediaObjQue.isEmpty()){
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
        else {
            System.out.printf("%s[HomeViewController][mediaPrevious] No media to skip backwards%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
    }


    /**
     * Method for skipping the media forward by 15 seconds and updates the current time label
     */
    private void mediaSkipForward() {

        if (!mediaObjQue.isEmpty()){
            currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
            double mediaLength = media.getDuration().toSeconds();

            int newTrackTime = MediaSkip.mediaSkipForward(currentTrackTime, mediaLength);

            // updates the current time label with the new track time
            updateCurrentTimeLabel(newTrackTime);

            // sets the media player to the new track time and continues playing
            mediaPlayer.seek(Duration.seconds(newTrackTime));
            mediaPlayer.play();
        }
        else {
            System.out.printf("%s[HomeViewController][mediaSkipForward] No media to skip 15 seconds forward%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
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

        if (!mediaObjQue.isEmpty()){
            currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
            //double mediaLength = media.getDuration().toSeconds();

            int newTrackTime = MediaSkip.mediaSkipBackward(currentTrackTime);
            System.out.println("current tracktime: " + currentTrackTime);
            System.out.println("new tracktime: " + newTrackTime);

            // updates the current time label with the new track time
            updateCurrentTimeLabel(newTrackTime);

            // sets the media player to the new track time and continues playing
            mediaPlayer.seek(Duration.seconds(newTrackTime));
            mediaPlayer.play();
        }
        else {
            System.out.printf("%s[HomeViewController][mediaSkipBackward] No media to skip 15 seconds backward%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
    }


    /**
     * Method for mute/unmute the media
     * The method mute or mutes the media based on the current state,
     * and updates the mute button icon accordingly.
     * Finally, toggles the mute stage for the next button click.
     */
    private void mediaMute() {

        if (!mediaObjQue.isEmpty()){
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
        else {
            System.out.printf("%s[HomeViewController][mediaMute] No media to mute or unmute%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }

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
        if (!mediaObjQue.isEmpty()){
            pauseWithValidation();
        }
        else {
            System.out.printf("%s[HomeViewController][sliderProgresOnDrag] No media to change playtime on%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
    }


    /**
     *
     */
    private void sliderProgresMouseRelease() {

        if (!mediaObjQue.isEmpty()) {
            cancelTimer();

            // gets the now updated user selected media time
            int selectedMediaTime = (int) sliderProgres.getValue();

            updateCurrentTimeLabel(selectedMediaTime);
            mediaPlayer.seek(Duration.seconds(selectedMediaTime));

            playWithValidation();

            beginTimer();
        }

        else {
            System.out.printf("%s[HomeViewController][sliderProgresMouseRelease] No media to change playtime on%s\n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
        }
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



    // TO HANDLE STAGE SWITCH


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
