package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Playlist.PlaylistHandler;
import mediaplayer.orpheus.model.Service.FileChooser;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlaylistViewController implements Initializable {

    @FXML
    private Button btnSearch, btnPlaylist, btnImport, btnDelete;

    @FXML
    private TextField playlistCreateBar;

    @FXML
    private ListView<String> LWPlaylistDisplay;

    private SceneController sceneController = new SceneController();
    private static ArrayList<String> playlistNameArr = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        loadListView();

    }

    private void loadListView() {

        String query = DatabaseRead.getAllPlaylistNames();

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(query)){

            while (resultSet.next()){
                playlistNameArr.add(resultSet.getString("fldPlaylistName"));
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        for (String playlistName : playlistNameArr) {
            addItemToListView(LWPlaylistDisplay, playlistName);
        }

    }

    private void addItemToListView(ListView listView, String item){listView.getItems().add(item);}

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

    public void onActionbtnImportClick(){

        FileChooser.fileChooser();
    }

    @FXML
    private void onActionbtnCreateClick(){

        PlaylistHandler.createPlaylist(playlistCreateBar.getText());

    }

    @FXML
    private void onActionbtnCancelClick(){
        try {
            sceneController.switchToHomeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}