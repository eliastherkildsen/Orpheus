package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.MediaEdit.AddArtist;
import mediaplayer.orpheus.model.MediaEdit.AddGenre;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.util.AlertPopup;

import java.io.IOException;

public class AddGenreController {

    private SceneController sceneController = new SceneController();
    @FXML
    private TextField fldGenre;

    @FXML
    public void switchToPlaylistView() {
        try {
            sceneController.switchToPlaylistScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void switchToEditView() {
        try {
            sceneController.switchToEditScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void switchToSearchView() {
        try {
            sceneController.switchToSearchScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void switchToHomeView() {
        try {
            sceneController.switchToHomeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onActionbtnImportClick(){

        FileChooser.fileChooser();
    }

    @FXML
    public void onBtnUpdateClick() {
        addGenre();
    }

    @FXML
    public void onBtnCancelClick() {
        switchToEditView();
    }

    private void addGenre() {

        // get textfield values

        String genre = fldGenre.getText();

        if (!genre.isEmpty()) {
            AddGenre.createGenre(genre);
            new AlertPopup("INFO", "Genre has been added").showInformation();
            switchToEditView();
        }

        else {
            new AlertPopup("INFO", "You need to give the Genre a name!").showInformation();
        }



    }



}
