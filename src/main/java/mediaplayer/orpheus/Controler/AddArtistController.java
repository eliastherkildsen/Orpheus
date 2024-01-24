package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.MediaEdit.AddArtist;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.util.AlertPopup;

public class AddArtistController {

    private final SceneController sceneController = new SceneController();
    @FXML
    private TextField fldArtistFirstName, fldArtistLastName, fldArtistName;

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
        addArtist();
    }

    @FXML
    public void onBtnCancelClick() {
        switchToEditView();
    }

    private void addArtist() {

        // get textfield values

        String artistName = fldArtistName.getText();
        String artistLastName = fldArtistLastName.getText();
        String artistFirstName = fldArtistFirstName.getText();

        if (!artistName.isEmpty()) {
            AddArtist.createArtist(artistFirstName, artistLastName, artistName);
            new AlertPopup("INFO", "Artist has been added").showInformation();
            switchToEditView();
        }

        else {
            new AlertPopup("INFO", "You need to give the artist an artist name!").showInformation();
        }



    }



}
