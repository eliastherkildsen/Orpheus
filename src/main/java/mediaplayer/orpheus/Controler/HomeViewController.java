package mediaplayer.orpheus.Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mediaplayer.orpheus.Controler.ViewControler;

import java.io.IOException;
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

            file = new File("src/main/java/mediaplayer/orpheus/mediaFiles/Bryan Adams - Summer Of  69 (Official Music Video).mp4");

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

    @FXML
    private Button btnSearch, btnPlaylist, btnDelete;

    private ViewControler viewControler = new ViewControler();

    public void switchToPlaylistView(ActionEvent event) {
        try {
            viewControler.switchToPlaylistScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSearchView(ActionEvent event) {
        try {
            viewControler.switchToSearchScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHomeView(ActionEvent event) {
        try {
            viewControler.switchToHomeScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
