package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.MediaEdit.MediaEdit;
import mediaplayer.orpheus.model.MediaEdit.MediaEditUtil;
import mediaplayer.orpheus.model.Service.FileChooser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditMediaViewController implements Initializable {

    @FXML
    private Button btnSearch, btnPlaylist, btnImport, btnDelete, btnUpdate, btnCancel;
    private SceneController sceneController = new SceneController();
    @FXML
    private TextField fldMediaTitle, fldYear;
    @FXML
    private ChoiceBox cbArtist, cbGenre;
    @FXML
    private Label labMedia;
    public static int selectedMediaID;
    private MediaEdit mediaEdit;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();
    }
    @FXML
    private void onBtnUpdataClick(){
        updataMediaData();
        switchToSearchView();
    }

    @FXML
    public void onBtnCancelClick() {
        switchToSearchView();
    }

    /**
     * method for adding a String to a choice box
     *
     * @param choiceBox
     * @param item String to be added to the choice box.
     */
    private void addItemToChoiceBox(ChoiceBox choiceBox, String item){
        choiceBox.getItems().add(item);
    }

    /**
     * method for clearing a choiceBox
     * @param choiceBox
     */
    private void clearChoiceBox(ChoiceBox choiceBox){
        choiceBox.getItems().clear();
    }

    /**
     * Method for getting the selected items index in a choice box
     * returns -1 if no item is selected.
     * @param choiceBox
     * @return the selected items index, -1 if no item is selected.
     */
    private int getSelectedItemIndex(ChoiceBox choiceBox){
        return choiceBox.getSelectionModel().getSelectedIndex();
    }
    private String getSelectedItem(ChoiceBox choiceBox){
        if (choiceBox.getSelectionModel().getSelectedItem() != null) {
            return choiceBox.getSelectionModel().getSelectedItem().toString();
        }
        return null;
    }

    private void presentItem(ChoiceBox choiceBox, String string){
        choiceBox.getSelectionModel().select(string);
    }

    private void load() {

        // initializing the mediaEdit obj.
        mediaEdit = new MediaEdit(selectedMediaID);

        // load all genres in to cbGenre
        loadGenre();

        // load all artists in to cbArtist
        loadArtist();

        // sets the choice box to select the selected medias genre.
        setMediaGenre();

        setMediaTitleOnScene();

        setMediaArtist();

        System.out.println("SELECTED MEDIA ID " + selectedMediaID);

    }

    private void setMediaArtist() {
        presentItem(cbArtist, mediaEdit.getMediaArtistName());
    }

    private void setMediaTitleOnScene(){
        labMedia.setText(mediaEdit.getMediaTitle());
    }

    private void setMediaGenre(){
        presentItem(cbGenre, mediaEdit.getMediaGenre());
    }


    private void loadGenre(){
        for (String genre : MediaEditUtil.getAllGenre()){
            addItemToChoiceBox(cbGenre, genre);
        }
    }

    private void loadArtist(){
        for (String artist : MediaEditUtil.getAllArtist()){
            addItemToChoiceBox(cbArtist, artist);
        }
    }

    private void updataMediaData() {

        mediaEdit.setMediaGenre(getSelectedItem(cbGenre));

        mediaEdit.setMediaArtistName(getSelectedItem(cbArtist));

        // set year
        mediaEdit.setMediaYear(fldYear.getText());

        // set media title
        mediaEdit.setMediaTitle(fldMediaTitle.getText());

    }

    @FXML
    public void switchToPlaylistView() {
        try {
            sceneController.switchToPlaylistScene();
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

}
