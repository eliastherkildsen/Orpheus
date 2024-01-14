package mediaplayer.orpheus.Controler;
import javafx.fxml.Initializable;
import mediaplayer.orpheus.model.MediaSearch.DatabaseSearch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.MediaSearch.MediaSearchUtil;
import mediaplayer.orpheus.model.Service.FileHandlerMedia;

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
    private final DatabaseSearch databaseSearch = new DatabaseSearch();
    private ArrayList<String[]> dataSet = new ArrayList<>();
    private final SceneController sceneController = new SceneController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // to auto-populate the search results.
        refreshSearchResults();
    }

    @FXML
    private void onActionbtnEditClick(){

    }

    @FXML
    private void onActionbtnListenClick(){

        // retrieves the path for the selected media.
        String filePath = MediaSearchUtil.getMediaPathFromDataset(getSelectedItemIndex(), dataSet);

        // switches to home view, and sets the media at home view to the selected media.
        if (!filePath.equals("")){
            switchMedia(filePath);
        }

        // debug logging.
        System.out.printf("%s[MEDIA-SEARCH] Media picked: %s %s%n", AnsiColorCode.ANSI_YELLOW, getSelectedItemIndex(), AnsiColorCode.ANSI_RESET);
        System.out.println(filePath);

    }

    @FXML
    private void onActionbtnAddToPlaylistClick(){

    }

    @FXML
    private void onActionbtnDeleteMediaClick(){
        deleteMedia(getSelectedItemIndex());
    }

    /**
     * method for getting the selected item in LW
     * @return the index of the selected item. return -1 if no item is selected.
     */
    private int getSelectedItemIndex() {
        return LWSearchResult.getSelectionModel().getSelectedIndex();
    }


    @FXML
    private void onActionbtnSearchBarClick(){

        /**
         * TODO: move into separate method.
         */

        // initializes a new arraylist for storing the quarry result, to make sure that it is empty.
        dataSet = new ArrayList<>();
        String search = FldSearch.getText();
        // quarry's the users search input.
        ResultSet res = databaseSearch.searchMedia(search);
        // parses the result of the quarry to a String array for each row.
        dataSet = databaseSearch.processResultSet(res);
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

    public void onActionbtnImportClick(){
        FileHandlerMedia.fileChooser();
    }

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
    /**
     * Method for updating the mediaPath in Home view, and switching scene view.
     * @param filePath
     */
    private void switchMedia(String filePath) {
        // switches the filepath for the media view to the user selected filepath
        HomeViewController.mediaPath = filePath;
        switchToHomeView();
    }

    /**
     * method for clearing the list view for all items.
     */
    private void clearListView(){
        LWSearchResult.getItems().clear();
    }

    /**
     * method for deleting a Media in the database.
     * @param mediaID
     */
    private void deleteMedia(int mediaID) {

        // checks if an item in LW has been selected.
        if (getSelectedItemIndex() != -1){
            databaseSearch.deleteMediaFromDatabase(mediaID);
            refreshSearchResults();
            System.out.printf("%s[SearchViewController][DeleteMedia] the selected media has been deleted%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        }
        else {
            System.out.printf("%s[SearchViewController][DeleteMedia] No media has been selected%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }

    }

    private void refreshSearchResults() {
        onActionbtnSearchBarClick();
    }

}