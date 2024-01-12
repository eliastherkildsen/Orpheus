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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

    public class HomeViewController implements Initializable {

        @FXML
        private MediaView mediaViewDisplay;

        @FXML
        private Slider sliderVolume;

        @FXML
        private Label labCurrentTime;

        @FXML
        private Label labMediaLength;
        public static String mediaPath = "src/main/java/mediaplayer/orpheus/mediaFiles/CAN T STOP THE FEELING! (from DreamWorks Animation s  TROLLS ) (Official Video).mp4";
        private File file;
        private Media media;
        private MediaPlayer mediaPlayer;
        private boolean playSwitchStage;
        private Timer timer;
        private TimerTask task;
        private double current;
        private double currentSliderVol;
        private boolean mute;



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            labCurrentTime.setText("");
            labMediaLength.setText("");

            file = new File(mediaPath);

            if (file.exists()) {
                media = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
            } else {
                System.out.println("The file was not found at the specified path");
            }

            // when the MediaPlayer is ready to play the media
            mediaPlayer.setOnReady(() -> {
                //double trackLength = media.getDuration().toSeconds();
                //String formattedTime = secondsFormattedToTime(trackLength);
            });

            mediaViewDisplay.setMediaPlayer(mediaPlayer);

            sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

                    mediaPlayer.setVolume(sliderVolume.getValue() * 0.01);
                }
            });
        }

        public void onActionbtnImportClick(){

            FileHandlerMedia.fileChooser();

        }

        public void onBtnPlayClick(){

            beginTimer();

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


        @FXML
        private ImageView btnPlayIcon;



        public void updatePlayButtonImage(String imageURL){

            Image image = new Image(imageURL);
            btnPlayIcon.setImage(image);

        }


        public void beginTimer(){

            timer = new Timer();
            task = new TimerTask() {

                @Override
                public void run() {

                    Platform.runLater(() -> {
                        if (mediaPlayer != null) {
                            current = mediaPlayer.getCurrentTime().toSeconds();
                            double trackLength = media.getDuration().toSeconds();
                            String formattedTime = secondsFormattedToTime(current);
                            labCurrentTime.setText(formattedTime);

                            if (current == trackLength){
                                cancelTimer();
                            }

                            labMediaLength.setText(secondsFormattedToTime(trackLength));
                        }
                    });
                }
            };

            timer.scheduleAtFixedRate(task,1000,1000);
        }


        public void cancelTimer(){

            timer.cancel();
        }



        public String secondsFormattedToTime(double durationTime) {
            //test: durationTime = 5420;
            int hours = (int) durationTime / 3600;
            int secondsLeft = (int) durationTime % 3600;
            int minutes = secondsLeft / 60;
            int seconds = secondsLeft % 60;

            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }



        public void onBtnSkipForwardsClick(){
            int forwardTime;

            if (current < media.getDuration().toSeconds() - 15) {
                System.out.println("current time: " + current);
                current = mediaPlayer.getCurrentTime().toSeconds();
                forwardTime = (int) current + 15;
            }
            else {
                forwardTime = (int) media.getDuration().toSeconds();
            }

            updateCurrentTimeLabel(forwardTime);

            mediaPlayer.seek(Duration.seconds(forwardTime));
            mediaPlayer.play();
        }


        public void onBtnSkipBackwardsClick(){

            int backwardTime;

            if (current < media.getDuration().toSeconds() - 15) {
                current = mediaPlayer.getCurrentTime().toSeconds();
                current = mediaPlayer.getCurrentTime().toSeconds();
                backwardTime = (int) current - 15;
            }
            else {
                backwardTime = (int) Duration.ZERO.toSeconds();
            }

            updateCurrentTimeLabel(backwardTime);

            mediaPlayer.seek(Duration.seconds(backwardTime));
            mediaPlayer.play();
        }

        private void updateCurrentTimeLabel(int time) {

            labCurrentTime.setText(secondsFormattedToTime(time));
        }


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




       // "@../../css/images/play-circle.png" (play knap)







        //to handle stage-switch
        @FXML
        private Button btnSearch, btnPlaylist, btnDelete;

        private SceneController sceneController = new SceneController();

        public void switchToPlaylistView() {

            mediaPlayer.pause();

            try {
                sceneController.switchToPlaylistScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void switchToSearchView() {

            mediaPlayer.pause();

            try {
                sceneController.switchToSearchScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void switchToHomeView() {

            mediaPlayer.pause();

            try {
                sceneController.switchToHomeScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
