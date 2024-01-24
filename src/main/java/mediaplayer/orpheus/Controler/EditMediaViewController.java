package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.MediaEdit.MediaEditUtil;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.util.debugMessage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditMediaViewController implements Initializable {

    private final SceneController SCENE_CONTROLLER = new SceneController();
    @FXML
    private TextField fldMediaTitle, fldYear;
    @FXML
    private ChoiceBox cbArtist, cbGenre;
    @FXML
    private Label labMedia;
    public static MediaObj selectedMediaObj;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        debugMessage.debug(this,"Initialized.");
        load();
    }


    @FXML
    private void onBtnUpdateClick(){
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
     * @param choiceBox the choiceBox to interact with.
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

        // empty all cb
        cbGenre.getItems().clear();
        cbArtist.getItems().clear();

        // load all genres in to cbGenre
        loadGenre();

        // load all artists in to cbArtist
        loadArtist();

        // sets the choice box to select the selected medias genre.
        setMediaGenre();

        setMediaTitleOnScene();

        setMediaArtist();

        setMediaTextField();

        setMediaYear();

        debugMessage.debug(this,"SELECTED MEDIA ID: " + selectedMediaObj.getMEDIA_ID());

    }
    public void setMediaYear(){
        fldYear.setText(selectedMediaObj.getMediaYear());
    }

    private void setMediaArtist() {
        presentItem(cbArtist, selectedMediaObj.getMediaArtistStageName());
    }

    private void setMediaTitleOnScene(){
        labMedia.setText(selectedMediaObj.getMediaTitle());
    }
    private void setMediaTextField(){
        fldMediaTitle.setText(selectedMediaObj.getMediaTitle());
    }

    private void setMediaGenre(){
        presentItem(cbGenre, selectedMediaObj.getMediaGenre());
    }

    private void loadGenre(){
        cbGenre.getItems().clear();
        for (String genre : MediaEditUtil.getAllGenre()){
            addItemToChoiceBox(cbGenre, genre);
        }
    }

    private void loadArtist(){
        cbArtist.getItems().clear();
        for (String artist : MediaEditUtil.getAllArtist()){
            addItemToChoiceBox(cbArtist, artist);
        }
    }

    private void updataMediaData() {

        // set media genre.
        if (getSelectedItem(cbGenre) != null){
            selectedMediaObj.setMediaGenre(getSelectedItem(cbGenre));
        }

        if (getSelectedItem(cbArtist) != null) {
            selectedMediaObj.setMediaArtistName(getSelectedItem(cbArtist));
        }

        // set year
        selectedMediaObj.setMediaYear(fldYear.getText());

        // set media title
        selectedMediaObj.setMediaTitle(fldMediaTitle.getText());

    }

    @FXML
    public void switchToPlaylistView() {
            SCENE_CONTROLLER.switchToPlaylistScene();
    }
    @FXML
    public void switchToSearchView() {
        SCENE_CONTROLLER.switchToSearchScene();
    }
    @FXML
    public void switchToHomeView() {
        SCENE_CONTROLLER.switchToHomeScene();
    }

    @FXML
    public void switchToAddArtistView() {
        SCENE_CONTROLLER.switchToAddArtistView();
    }
    @FXML
    public void switchToAddGenreView() {
        SCENE_CONTROLLER.switchToAddGenreView();
    }
    @FXML
    public void onActionbtnImportClick(){
        FileChooser.fileChooser();
    }

    @FXML
    public void onBtnAddGenreClick() {
        switchToAddGenreView();
    }
    @FXML
    public void onBtnAddArtistClick() {
        switchToAddArtistView();
    }

    @FXML
    public void onActionbtnAddThumbnail(){
        String imagePath = FileChooser.imageChooser();
        selectedMediaObj.setMediaImagePath(imagePath);
    }
}
