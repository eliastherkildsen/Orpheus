package mediaplayer.orpheus.Controler;
import mediaplayer.orpheus.model.MediaSearch.DatabaseSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.MediaSearch.MediaSearchUtil;
import mediaplayer.orpheus.model.Service.FileHandlerMedia;

import mediaplayer.orpheus.util.AnsiColorCode;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchViewController {

    @FXML
    private Button btnSearch, btnPlaylist, btnImport, btnDelete, btnEdit, btnListen, btnAddToPlaylist, btnDeleteMedia;
    @FXML
    private TextField FldSearch;
    @FXML
    private ListView<String> LWSearchResult;
    private ResultSet resultSet;
    private final DatabaseSearch databaseSearch = new DatabaseSearch();
    private ArrayList<String[]> dataSet = new ArrayList<>();

    @FXML
    private void onActionbtnEditClick(){

    }

    @FXML
    private void onActionbtnListenClick(){



        // gets the selected media.
        String mediaPickedIndex = LWSearchResult.getSelectionModel().getSelectedIndices().toString();
        String mediaPicked = LWSearchResult.getSelectionModel().toString();

        // retrieves the path for the selected media.
        String filePath = MediaSearchUtil.parseLWSearchResult(mediaPickedIndex, dataSet);

        // debug logging.
        System.out.printf("%s[MEDIA-SEARCH] Media picked: %s %s%n", AnsiColorCode.ANSI_YELLOW, mediaPicked, AnsiColorCode.ANSI_RESET);
        System.out.println(filePath);

        // switches to home view, and sets the media at home view to the selected media.
        switchMedia(filePath);

    }

    @FXML
    private void onActionbtnAddToPlaylistClick(){

    }

    @FXML
    private void onActionbtnDeleteMediaClick(){

    }
    @FXML
    private void onActionbtnSearchBarClick(){

        /**
         * TODO: move into seperet method.
         */

        // initializes a new arraylist for storing the quarry result.
        dataSet = new ArrayList<>();

        // quarry's the users search input.
        ResultSet res = databaseSearch.searchMedia(FldSearch.getText());

        // parses the result of the quarry to a String array for each row.
        dataSet = databaseSearch.processResultSet(res);
        LWSearchResult.getItems().clear();

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
    /**
     * Method for updating the mediaPath in Homebrew, and switching scene view.
     * @param filePath
     */
    private void switchMedia(String filePath) {
        // switches the filepath for the media view to the user selected filepath
        HomeViewController.mediaPath = filePath;

        // switching screens.
        try {
            sceneController.switchToHomeScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}