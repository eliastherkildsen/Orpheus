package mediaplayer.orpheus.Controler;
import javafx.fxml.Initializable;
import mediaplayer.orpheus.model.MediaSearch.MediaSearch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.MediaSearch.MediaSearchUtil;
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
    private ArrayList<String[]> dataSet = new ArrayList<>();
    private final SceneController sceneController = new SceneController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // to auto-populate the search results.
        refreshSearchResults();
    }

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
        String filePath = MediaSearchUtil.getMediaPathFromDataset(getSelectedItemIndex(), dataSet);
        switchMedia(filePath);
    }

    @FXML
    private void onActionbtnAddToPlaylistClick(){

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

        /**
         * TODO: move into separate method.
         */

        // quarry's the users search input.
        ResultSet res = mediaSearch.searchMedia(FldSearch.getText());
        // parses the result of the quarry to a String array for each row.

        dataSet = mediaSearch.processResultSet(res);
        // clears the search LW  (list-view)

        clearListView();

        // loops through the resultset.
        for (String[] strings : dataSet) {
            // formats the result.
            String formatedResult = MediaSearchUtil.formatedQuarryResultString(strings);
            // adds the result to the search list.
            LWSearchResult.getItems().add(formatedResult);
        }
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
     * @param filePath
     */
    private void switchMedia(String filePath) {
        // switches the filepath for the media view to the user selected filepath
        if (!filePath.isEmpty()){
            HomeViewController.mediaPath = filePath;
            switchToHomeView();
            System.out.printf("%s[SearchViewController][switchMedia] the selected media has no file path%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }

        else {
            System.out.printf("%s[SearchViewController][switchMedia] switching media-player file pointer to %n path: %s path%s%n", AnsiColorCode.ANSI_YELLOW, filePath, AnsiColorCode.ANSI_RESET);
            new AlertPopup("No media selected", "No media has been selected, pleas select a media to listen to").showInformation();
        }

    }


    /**
     * method for deleting a Media in the database.
     * @param itemIndex
     */
    private void deleteMedia(int itemIndex) {

        // checks if an item in LW has been selected.
        if (getSelectedItemIndex() != -1){

            int mediaID = MediaSearchUtil.getMediaIDFromDataset(itemIndex, dataSet);
            mediaSearch.deleteMediaFromDatabase(mediaID);

            refreshSearchResults();
            System.out.printf("%s[SearchViewController][DeleteMedia] the selected media has been deleted%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }
        else {
            System.out.printf("%s[SearchViewController][DeleteMedia] No media has been selected%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
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
     * method for clearing the list view for all items.
     */
    private void clearListView(){
        LWSearchResult.getItems().clear();
    }

    private void editMedia() {
        // get selected medias mediaID.
        if (getSelectedItemIndex()!= -1) {
            EditMediaViewController.selectedMediaID = MediaSearchUtil.getMediaIDFromDataset(getSelectedItemIndex(), dataSet);
            switchToEditView();
        }

        else {
            System.out.printf("%s[SearchViewController][editMedia] no media has been picked.%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
            new AlertPopup("No media selected", "No media has been selected! pleas select a media to edit.").showInformation();
        }

    }

}
