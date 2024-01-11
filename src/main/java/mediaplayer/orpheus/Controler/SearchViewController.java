package mediaplayer.orpheus.Controler;
import mediaplayer.orpheus.model.MediaSearch.DatabaseSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
        String mediaPicked = LWSearchResult.getSelectionModel().getSelectedItems().toString();
        // retrieves the path for the selected media.
        String filePath = parseLWSearchResult(mediaPicked);
        // switches to home view, and sets the media at home view to the selected media.
        switchMedia(filePath);
        // debug logging.
        System.out.printf("%s[MEDIA-SEARCH] Media picked: %s %s%n", AnsiColorCode.ANSI_YELLOW, mediaPicked, AnsiColorCode.ANSI_RESET);
        System.out.println(filePath);

    }


    /**
     * Method for updating the mediaPath in Homeview, and switching scene view.
     * @param filePath
     */
    private void switchMedia(String filePath) {
        // switches the filepath for the media view to the user selected filepath
        HomeViewController.mediaPath = filePath;

        // switing sceens.
        try {
            viewControler.switchToHomeScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for parsing the position of the selected item from a string to an integer.
     * and the retriving the path for the String at the index og the integer retrived.
     * @param mediaPicked
     * @return String filepath to selected media.
     */
    private String parseLWSearchResult(String mediaPicked) {

        // the returend value from getSelectedIndices() is a text, therefor we need to parse it to an int, and exclude
        // the start char '[' and the last char ']'
        String mediaIndex = LWSearchResult.getSelectionModel().getSelectedIndices().toString();

        mediaIndex = mediaIndex.substring(1,mediaIndex.length()-1);
        int mediaIndexFormatted = Integer.parseInt(mediaIndex);
        String[] temp = dataSet.get(mediaIndexFormatted);
        // returns the String at index 5, witch referees to the index where the filePath exists.
        return temp[5];
    }

    @FXML
    private void onActionbtnAddToPlaylistClick(){

    }

    @FXML
    private void onActionbtnDeleteMediaClick(){

    }
    @FXML
    private void onActionbtnSearchBarClick(){

        dataSet = new ArrayList<>();
        ResultSet res = databaseSearch.searchMedia(FldSearch.getText());
        dataSet = databaseSearch.processResultSet(res);
        LWSearchResult.getItems().clear();



        for (String[] strings : dataSet) {

            StringBuilder sb = new StringBuilder();

            if(strings[3] != "NULL" || strings[3] != "null"){
                sb.append(strings[3]);
                sb.append(" ");
            }
            if(strings[0] != "NULL" || strings[0] != "null"){
                sb.append(strings[0]);
                sb.append(" ");
            }
            if(strings[1] != "NULL" || strings[1] != "null"){
                sb.append(strings[1]);
                sb.append(" ");
            }
            if(strings[2] != "NULL" || strings[2] != "null"){
                sb.append(strings[2]);
                sb.append(" ");
            }
            if(strings[6] != "NULL" || strings[6] != "null"){
                sb.append(strings[6]);
                sb.append(" ");
            }
            if(strings[7] != "NULL" || strings[7] != "null"){
                sb.append(strings[7]);
                sb.append(" ");
            }

            LWSearchResult.getItems().add(sb.toString());
        }


    }

    public void onActionbtnImportClick(){

        FileHandlerMedia.fileChooser();

    }

    private ViewControler viewControler = new ViewControler();

    public void switchToPlaylistView(ActionEvent event) {
        try {
            viewControler.switchToPlaylistScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSearchView(ActionEvent event) {
        try {
            viewControler.switchToSearchScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHomeView(ActionEvent event) {
        try {
            viewControler.switchToHomeScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}