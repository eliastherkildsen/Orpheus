package mediaplayer.orpheus.model.Media;
/**
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaHandler implements Initializable {

    private String path = new File("src/main/java/mediaplayer/orpheus/mediaFiles/Sleeping My Day Away").getAbsolutePath();
    private Media media = new Media(new File(path).toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(media);


    private boolean running = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("??");
        System.out.println("hurra");

        mediaViewDisplay.setMediaPlayer(mediaPlayer);

    }

    public void stop(){
        mediaViewDisplay.setMediaPlayer(mediaPlayer);
        mediaPlayer.stop();
    }
    public void start(){
        mediaViewDisplay.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setVolume(100.00);
    }
    public MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }



}
 **/
