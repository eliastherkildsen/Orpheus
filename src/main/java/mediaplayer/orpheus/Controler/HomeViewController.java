package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

    public class HomeViewController implements Initializable {

        @FXML
        private MediaView mediaViewDisplay;

        private File file;
        private Media media;
        private MediaPlayer mediaPlayer;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            file = new File("src/main/java/mediaplayer/orpheus/mediaFiles/D-A-D - Sleeping My Day Away.mp3");

            System.out.println("før if ");

            if (file.exists()) {
                media = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
            } else {
                System.out.println("Filen blev ikke fundet på den specificerede sti.");
            }

            System.out.println("efter if - før media");

            //media = new Media(file.toURI().toString());
            //mediaPlayer = new MediaPlayer(media);

            System.out.println("efter mediaplayer - før mediaview");

            mediaViewDisplay.setMediaPlayer(mediaPlayer);

            System.out.println("efter mediaview");
        }

        public void onBtnPlayClick(){
            mediaPlayer.play();
        }
    }








    /*
    private Media me;
    private MediaPlayer mp;

    @FXML
    private MediaView mediaViewDisplay;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
// Build the path to the location of the media file
        String path = new File("src/sample/media/file_example_MP4_640_3MG.mp4").getAbsolutePath();
        // Create new Media object (the actual media content)
        me = new Media(new File(path).toURI().toString());
        // Create new MediaPlayer and attach the media to be played
        mp = new MediaPlayer(me);
        //
        mediaViewDisplay.setMediaPlayer(mp);
        // mp.setAutoPlay(true);
        // If autoplay is turned of the method play(), stop(), pause() etc controls how/when medias are played
        mp.setAutoPlay(false);
    }

    @FXML
    protected void onBtnPlayClick() {
        if (mp != null) {
            mp.play();
        } else {
            System.out.println("MediaPlayer is null. Check initialization.");
        }
    }

    @FXML
    protected void onBtnPauseClick() {
        if (mp != null) {
            mp.pause();
        } else {
            System.out.println("MediaPlayer is null. Check initialization.");
        }
    }

    @FXML
    protected void onBtnStopClick() {
        if (mp != null) {
            mp.stop();
        } else {
            System.out.println("MediaPlayer is null. Check initialization.");
        }
    }*/

