package mediaplayer.orpheus.Controler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import mediaplayer.orpheus.model.Media.GeneralMediaObject;
import mediaplayer.orpheus.model.MediaEdit.DeleteMedia;
import mediaplayer.orpheus.model.MediaSearch.MediaSearchUtil;
import mediaplayer.orpheus.model.MediaSearch.MediaSearch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private Button btnPlaylist, btnImport, btnDelete, btnEdit, btnListen, btnAddToPlaylist, btnDeleteMedia;
    @FXML
    private TextField FldSearch;
    @FXML
    private ListView<String> LWSearchResult;
    private final MediaSearch mediaSearch = new MediaSearch();
    private ArrayList<GeneralMediaObject> dataSet = new ArrayList<>();
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

        for (String playlistName : MediaSearchUtil.getAllPlaylist()) {
            addItemToChoiceBox(cbPlaylist, playlistName);
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
        switchToHomeViewWithMediaChild();
    }

    @FXML
    private void onActionbtnAddToPlaylistClick() throws IndexOutOfBoundsException {
        addMediaToPlaylist();

    }

    private void addMediaToPlaylist() {
        String cbItem = getSelectedChoiceBoxItem();
        if(cbItem != null){
            int selectedMedia = dataSet.get(getSelectedItemIndex()).getMediaObj().getMediaID();
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
        search();
    }

    private void search() {
        loadSearchedMedia();

        // clears the search LW  (list-view)
        clearListView();

        // adds each MediaObj obj. to the list view.
        for (GeneralMediaObject generalMediaObject : dataSet) {

            // formats the result.
            // adds the result to the search list.

            if (generalMediaObject.getPlaylistObj() != null){
                LWSearchResult.getItems().add(generalMediaObject.getPlaylistObj().getPrestenedPlaylist());
            }

            if (generalMediaObject.getMediaObj() != null){
                LWSearchResult.getItems().add(generalMediaObject.getMediaObj().getPresentetedMedia());
            }

        }
    }


    /**
     * Method for loading relevant items from user search into the listview.
     */
    private void loadSearchedMedia() {

        // quarry's the users search input for media and adds to the listview.
        ResultSet res = mediaSearch.searchMediaForMedia(FldSearch.getText());
        dataSet = mediaSearch.processResultSetMedia(res);

        // quarry's the users search input for playlist and adds to the listview.
        res = mediaSearch.searchMediaForPlaylist(FldSearch.getText());
        dataSet.addAll(mediaSearch.processResultSetPlaylist(res));

    }

    /**
     * Method for updating the mediaPath in Home view, and switching scene view.
     *
     */
    private void switchToHomeViewWithMediaChild() {

        // switches the filepath for the media view to the user selected filepath
        HomeViewController.mediaObjQue.clear();

        // find out if the selected item is a playlist or a media.

        if (dataSet.get(getSelectedItemIndex()).getMediaObj() != null){

            HomeViewController.mediaObjQue.add(dataSet.get(getSelectedItemIndex()).getMediaObj());

        }

        // else checks if a playlist was selected.

        else if (dataSet.get(getSelectedItemIndex()).getPlaylistObj() != null){

            PlaylistHandler.createMediaArray(dataSet.get(getSelectedItemIndex()).getPlaylistObj().getPlaylistName());

        }

        switchToHomeView();

    }


    /**
     * method for deleting a MediaObj in the database.
     * @param itemIndex
     */
    private void deleteMedia(int itemIndex) {

        // checks if an item in LW has been selected.
        if (getSelectedItemIndex() != -1){

            // checks if a media has been selected.

            if (dataSet.get(itemIndex).getMediaObj() != null){

                DeleteMedia.deleteMediaFromDatabase(dataSet.get(itemIndex).getMediaObj().getMediaID());
                DeleteMedia.deleteMediaFileFromDir(dataSet.get(itemIndex).getMediaObj().getMediaPath());
            }

            // else checks if a playlist was selected.

            else if (dataSet.get(itemIndex).getPlaylistObj() != null){
                PlaylistHandler.deletePlaylist(dataSet.get(itemIndex).getPlaylistObj().getPlaylistName());
            }

            refreshSearchResults();
            System.out.printf("%s[SearchViewController][DeleteMedia] the selected media has been deleted%s%n",
                    AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }
        else {
            System.out.printf("%s[SearchViewController][DeleteMedia] No media has been selected%s%n",
                    AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);

            new AlertPopup("No media selected.",
                    "No media to delete has been selected, please select a media to delete.").showInformation();
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

    /**
     * Method for editing a media, method checks if the selected item is of type mediaObj,
     * prompts user if no item or a media playlist has been selected.
     */
    private void editMedia() {

        // get selected medias mediaID, and ensures that the selected media is not a playlist.
        if (getSelectedItemIndex() != -1 && dataSet.get(getSelectedItemIndex()).getMediaObj() != null ) {
            EditMediaViewController.selectedMediaObj = dataSet.get(getSelectedItemIndex()).getMediaObj();
            switchToEditView();
        }

        else {
            System.out.printf("%s[SearchViewController][editMedia] no media has been picked.%s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
            new AlertPopup("No media selected", "No media has been selected! pleas select a media to edit.").showInformation();
        }

    }
    @FXML
    public void switchToEditView() {
        sceneController.switchToEditScene();
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
    public void switchToPlaylistView() {
        sceneController.switchToPlaylistScene();
    }
}
