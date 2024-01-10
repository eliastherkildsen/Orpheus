package mediaplayer.orpheus.Controler;
import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.MediaSearch.DatabaseSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {

    @FXML
    private Button btnSearch, btnPlaylist, btnDelete, btnEdit, btnListen, btnAddToPlaylist, btnDeleteMedia;
    @FXML
    private TextField FldSearch;
    @FXML
    private ListView<String> LWSearchResult;
    private ResultSet resultSet;
    private DatabaseSearch databaseSearch = new DatabaseSearch();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LWSearchResult.getItems().add("Item 1");

    }
    @FXML
    private void onActionbtnEditClick(){

    }

    @FXML
    private void onActionbtnListenClick(){

    }

    @FXML
    private void onActionbtnAddToPlaylistClick(){

    }

    @FXML
    private void onActionbtnDeleteMediaClick(){

    }
    @FXML
    private void onActionbtnSearchBarClick(){

        ArrayList<String[]> dataSet = new ArrayList<>();
        databaseSearch.processResultSet(databaseSearch.searchMedia("a"));

        LWSearchResult.getItems().clear();

        for (String[] strings : dataSet) {
            LWSearchResult.getItems().add(strings[3] + " " + strings[0] + " " + strings[1] + " " + strings[2]);
        }


    }


    public void OnFldSearchKeyTyped() {





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