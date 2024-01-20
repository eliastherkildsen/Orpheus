package mediaplayer.orpheus.Controler;
import  javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import mediaplayer.orpheus.model.MediaEdit.DeleteMedia;
import mediaplayer.orpheus.model.MediaSearch.MediaSearchUtil;
import mediaplayer.orpheus.model.MediaSearch.MediaSearch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.model.Playlist.PlaylistHandler;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.util.AlertPopup;
import mediaplayer.orpheus.util.AnsiColorCode;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {

    @FXML
    private Button btnSearch, btnPlaylist, btnImport, btnDelete, btnEdit, btnListen, btnAddToPlaylist, btnDeleteMedia;
    @FXML
    private TextField FldSearch;
    @FXML
    private ListView<String> LWSearchResult;
    private final MediaSearch mediaSearch = new MediaSearch();
    private ArrayList<MediaObj> dataSet = new ArrayList<>();
    private ArrayList<String> playListNamesArr = new ArrayList<>();
    private final SceneController sceneController = new SceneController();
    @FXML
    private ChoiceBox cbPlaylist;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // to auto-populate the search results.
        refreshSearchResults();
        loadChoiceBox();
    }

    private void loadChoiceBox() {
        System.out.printf("Loading Choice box?");
        for (String playlistName : MediaSearchUtil.getAllPlaylist()) {
            addItemToChoiceBox(cbPlaylist, playlistName);
            System.out.printf("Adding item" + playlistName);
        }
    }

    private void addItemToChoiceBox(ChoiceBox choiceBox, String item){choiceBox.getItems().add(item);}

    @FXML
    private void onActionbtnEditClick(){
        editMedia();
    }

    /**
     * method for switching the media player song reference to the user selected.
     *
     */
    @FXML
    private void onActionbtnListenClick(){
        // retrieves the path for the selected media.
        switchMedia();
    }

    @FXML
    private void onActionbtnAddToPlaylistClick() throws IndexOutOfBoundsException {

        String cbItem = getSelectedChoiceBoxItem();
        if(cbItem != null){
            int selectedMedia = dataSet.get(getSelectedItemIndex()).getMediaID();
            PlaylistHandler.addMediaToPlaylist(selectedMedia, cbItem);
        }else{
            AlertPopup alertPopupNoItemSelected = new AlertPopup("Failed"
                    ,"No playlist was selected.");
            alertPopupNoItemSelected.showError();
        }
    }

    /**
     * method for deleting the selected item from the database.
     */
    @FXML
    private void onActionbtnDeleteMediaClick(){
        deleteMedia(getSelectedItemIndex());
    }

    /**
     * method for getting the selected item in LW
     * @return the index of the selected item. return -1 if no item is selected.
     */


    @FXML
    private void onActionbtnSearchBarClick(){

        loadSearchedMedia();

        // clears the search LW  (list-view)
        clearListView();

        // addes each MediaObj obj. to the list view.
        for (MediaObj mediaObj : dataSet) {
            // formats the result.
            // adds the result to the search list.
            LWSearchResult.getItems().add(mediaObj.getMediaTitle());
        }
    }

    private void loadSearchedMedia() {
        // quarry's the users search input.
        ResultSet res = mediaSearch.searchMedia(FldSearch.getText());
        dataSet = mediaSearch.processResultSet(res);
    }

    @FXML
    public void onActionbtnImportClick(){

        FileChooser.fileChooser();
        refreshSearchResults();
    }
    @FXML
    private void switchToPlaylistView() {
        try {
            sceneController.switchToPlaylistScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToSearchView() {
        try {
            sceneController.switchToSearchScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToHomeView() {
        try {
            sceneController.switchToHomeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToEditView() {
        try {
            sceneController.switchToEditScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method for updating the mediaPath in Home view, and switching scene view.
     *
     */
    private void switchMedia() {
        // switches the filepath for the media view to the user selected filepath
        HomeViewController.mediaObjQue.clear();
        HomeViewController.mediaObjQue.add(dataSet.get(getSelectedItemIndex()));

        switchToHomeView();

    }


    /**
     * method for deleting a MediaObj in the database.
     * @param itemIndex
     */
    private void deleteMedia(int itemIndex) {

        // checks if an item in LW has been selected.
        if (getSelectedItemIndex() != -1){

            DeleteMedia.deleteMediaFromDatabase(dataSet.get(itemIndex).getMediaID());
            DeleteMedia.deleteMediaFileFromDir(dataSet.get(itemIndex).getMediaPath());

            refreshSearchResults();
            System.out.printf("%s[SearchViewController][DeleteMedia] the selected media has been deleted%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }
        else {
            System.out.printf("%s[SearchViewController][DeleteMedia] No media has been selected%s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
            new AlertPopup("No media selected.", "No media to delete has been selected, please select a media to delete.").showInformation();
        }

    }


    /**
     * method for refreshing the search results, with a search for all media,
     */
    private void refreshSearchResults() {
        onActionbtnSearchBarClick();
    }

    /**
     * method for fetching the selecedt items index in the list view.
     * Return -1 if no item is selected.
     *
     * @return int selected items index.
     */
    private int getSelectedItemIndex() {
        return LWSearchResult.getSelectionModel().getSelectedIndex();
    }

    /**
     * Method for getting the selected item in the choice box
     * @return Returns the selected item as a string
     */
    private String getSelectedChoiceBoxItem(){
        // Checks if there is anything selected in the choice box
        if(cbPlaylist.getSelectionModel().getSelectedItem() == null){
            // Returns null if nothing is selected
            return null;
        }
        return cbPlaylist.getSelectionModel().getSelectedItem().toString();
    }

    /**
     * method for clearing the list view for all items.
     */
    private void clearListView(){
        LWSearchResult.getItems().clear();
    }

    private void editMedia() {
        // get selected medias mediaID.
        if (getSelectedItemIndex()!= -1) {
            EditMediaViewController.selectedMediaObj = dataSet.get(getSelectedItemIndex());
            switchToEditView();
        }

        else {
            System.out.printf("%s[SearchViewController][editMedia] no media has been picked.%s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
            new AlertPopup("No media selected", "No media has been selected! pleas select a media to edit.").showInformation();
        }

    }



}
