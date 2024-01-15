package mediaplayer.orpheus.Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.model.Service.FileHandlerMedia;

import java.io.IOException;

public class PlaylistViewController {

    @FXML
    private Button btnSearch, btnPlaylist, btnImport, btnDelete;

    private SceneController sceneController = new SceneController();

    public void switchToPlaylistView() {
        try {
            sceneController.switchToPlaylistScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSearchView() {
        try {
            sceneController.switchToSearchScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHomeView() {
        try {
            sceneController.switchToHomeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionbtnImportClick(){

        FileChooser.fileChooser();
    }
}