package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.Playlist.PlaylistHandler;
import mediaplayer.orpheus.model.Service.FileChooser;

import java.io.IOException;

public class PlaylistViewController {

    @FXML
    private Button btnSearch, btnPlaylist, btnImport, btnDelete;

    @FXML
    private TextField playlistCreateBar;

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

    @FXML
    private void onActionbtnCreateClick(){

        PlaylistHandler.createPlaylist(playlistCreateBar.getText());

    }

    @FXML
    private void onActionbtnCancelClick(){
        try {
            sceneController.switchToHomeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}