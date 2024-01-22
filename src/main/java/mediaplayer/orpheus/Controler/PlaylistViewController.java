package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Playlist.PlaylistHandler;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.util.AlertPopup;

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
        clearListViewDisplay();
        String query = DatabaseRead.getAllPlaylistNames();

        try (ResultSet resultSet = JDBC.instance.executeQuary(query)){

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
        clearListViewDisplay();
        loadListView();

    }

    @FXML
    private void onActionbtnCancelClick(){
        try {
            sceneController.switchToHomeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private  void onActionbtnDeletePlaylistClick(){

        AlertPopup confirmDelete = new AlertPopup("Delete Playlist"
                , "Are you sure you want to delete the playlist?");

        if(confirmDelete.showConfirmation("Yes", "No")){

            try{

                PlaylistHandler.deletePlaylist(LWPlaylistDisplay.getSelectionModel().getSelectedItem());

                clearListViewDisplay();
                loadListView();

            }catch (IndexOutOfBoundsException e){
                throw new IndexOutOfBoundsException();
            }

        }

    }

    private void clearListViewDisplay() {

        playlistNameArr.clear();
        LWPlaylistDisplay.getItems().clear();

    }

    public void onActionbtnPlayPlaylistClick(){

        PlaylistHandler.createMediaArray(LWPlaylistDisplay.getSelectionModel().getSelectedItem());
        if(!HomeViewController.mediaObjQue.isEmpty()){
            switchToHomeView();
        }


    }

}