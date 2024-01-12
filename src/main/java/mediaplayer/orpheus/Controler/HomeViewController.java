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
         * This method is called on startup to prepare and load necessary data.
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            // Creates a File object based on the media path
            file = new File(mediaPath);

            // Checks if the file exists
            if (file.exists()) {
                media = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                System.out.printf("path fundet");
            } else {
                System.out.printf("%s[HomeViewControl][initialize] The file was not found at the specified path%s\n",AnsiColorCode.ANSI_YELLOW,AnsiColorCode.ANSI_RESET);
            }

            mediaViewDisplay.setMediaPlayer(mediaPlayer);


            sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
                /**
                 *
                 * @param observableValue
                 * @param number
                 * @param t1
                 */
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

                    mediaPlayer.setVolume(sliderVolume.getValue() * 0.01);
                }
            });
        }


        /**
         *
         */
        @FXML
        public void onActionbtnImportClick(){

            FileHandlerMedia.fileChooser();
        }

        /**
         *
         */
        @FXML
        public void onBtnPlayClick(){

            beginTimer();

            //global variabel
            String playImageURL = "file:src/main/resources/css/images/play-circle.png";
            String pauseImageURL = "file:src/main/resources/css/images/pause-circle.png";

            if (playSwitchStage) {

                mediaPlayer.play();
                updatePlayButtonImage(pauseImageURL);

                playSwitchStage = !playSwitchStage;
            }
            else {
                mediaPlayer.pause();
                updatePlayButtonImage(playImageURL);

                playSwitchStage = !playSwitchStage;
            }
        }

        /**
         *
         * @param imageURL
         */
        public void updatePlayButtonImage(String imageURL){

            Image image = new Image(imageURL);
            btnPlayIcon.setImage(image);
        }


        /**
         *
         */
        public void beginTimer() {
            timer = new Timer();
            task = new TimerTask() {

                /**
                 *
                 */
                @Override
                public void run() {

                    // The provided code (Platform.runLater(() -> {) ensures that the update of JavaFX components occurs on
                    // the JavaFX Application Thread, and this should prevent the "Not on FX application thread" error
                    Platform.runLater(() -> {
                        if (mediaPlayer != null) {
                            currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
                            double trackLength = media.getDuration().toSeconds();
                            String formattedTime = secondsFormattedToTime(currentTrackTime);
                            labCurrentTime.setText(formattedTime);

                            if (currentTrackTime == trackLength) {
                                cancelTimer();
                            }

                            labMediaLength.setText(secondsFormattedToTime(trackLength));
                        }
                    });
                }
            };

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
         *
         * @param durationTime
         * @return
         */
        //anden classe
        public String secondsFormattedToTime(double durationTime) {
            //
            int hours = (int) durationTime / 3600;
            int secondsLeft = (int) durationTime % 3600;
            int minutes = secondsLeft / 60;
            int seconds = secondsLeft % 60;

            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

        /**
         *
         */
        @FXML
        public void onBtnSkipForwardsClick(){
            int forwardTime;

            //egen metode (ren btnskipmetode)
            if (currentTrackTime < media.getDuration().toSeconds() - 15) {
                currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
                forwardTime = (int) currentTrackTime + 15;
            }
            else {
                forwardTime = (int) media.getDuration().toSeconds();
            }

            updateCurrentTimeLabel(forwardTime);

            mediaPlayer.seek(Duration.seconds(forwardTime));
            mediaPlayer.play();
        }


        /**
         *
         */
        @FXML
        public void onBtnSkipBackwardsClick(){

            int backwardTime;

            if (currentTrackTime > 14) {
                currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
                currentTrackTime = mediaPlayer.getCurrentTime().toSeconds();
                backwardTime = (int) currentTrackTime - 15;
            }
            else {
                backwardTime = (int) Duration.ZERO.toSeconds();
            }

            updateCurrentTimeLabel(backwardTime);

            mediaPlayer.seek(Duration.seconds(backwardTime));
            mediaPlayer.play();
        }

        /**
         *
         * @param time
         */
        private void updateCurrentTimeLabel(int time) {

            labCurrentTime.setText(secondsFormattedToTime(time));
        }


        /**
         *
         */
        @FXML
        private void onBtnVolumeMuteClick(){

            if (mute) {
                currentSliderVol = sliderVolume.getValue();

                sliderVolume.setValue(0);
                mute = !mute;
            }
            else {
                sliderVolume.setValue(currentSliderVol);
                mute = !mute;
            }
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
