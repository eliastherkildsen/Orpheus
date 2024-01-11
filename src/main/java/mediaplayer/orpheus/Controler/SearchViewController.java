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