package mediaplayer.orpheus.Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mediaplayer.orpheus.Controler.ViewControler;

import java.io.IOException;

public class HomeViewController {

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
