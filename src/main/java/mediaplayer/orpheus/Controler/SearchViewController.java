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
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {
    @FXML
    private Button btnSearch, btnPlaylist, btnDelete;
    @FXML
    private TextField FldSearch;
    @FXML
    private ListView<String> LWSearchResult;
    private DatabaseSearch databaseSearch;
    private ResultSet resultSet;
    private DatabaseSearch dbs = new DatabaseSearch();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseSearch = new DatabaseSearch();
        LWSearchResult.getItems().add("item1");
        databaseSearch.searchMedia("USSEL");
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