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
        sceneController.switchToPlaylistScene();
    }
    @FXML
    public void switchToEditView() {
        sceneController.switchToEditScene();
    }
    @FXML
    public void switchToSearchView() {
        sceneController.switchToSearchScene();
    }
    @FXML
    public void switchToHomeView() {
        sceneController.switchToHomeScene();
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
