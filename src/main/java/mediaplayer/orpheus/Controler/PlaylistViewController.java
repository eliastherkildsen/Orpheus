package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.model.Database.DatabaseDelete;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Media.MediaObj;
import mediaplayer.orpheus.model.MediaSearch.MediaSearch;
import mediaplayer.orpheus.model.MediaSearch.MediaSearchUtil;
import mediaplayer.orpheus.model.Playlist.PlaylistHandler;
import mediaplayer.orpheus.model.Playlist.PlaylistObj;
import mediaplayer.orpheus.model.Service.FileChooser;
import mediaplayer.orpheus.util.AlertPopup;

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
    private ListView<String> LWPlaylistDisplay, LWPlaylistMedia;

    private final SceneController sceneController = new SceneController();
    private ArrayList<PlaylistObj> playlistObjs = new ArrayList<>();
    private ArrayList<MediaObj> mediaObjs = new ArrayList<>();
    public static PlaylistObj selectedPlaylist = null;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        loadlistViewPlaylist();

        if (selectedPlaylist != null){
            loadListViewMedia(selectedPlaylist);
        }

    }

    private void loadListViewMedia(PlaylistObj playlistObj){

        mediaObjs.clear();
        LWPlaylistMedia.getItems().clear();

        try (ResultSet resultSet = DatabaseRead.getMediaIDFromPlaylistName(playlistObj.getPLAYLIST_NAME()).executeQuery()){

            while (resultSet.next()){
                mediaObjs.add(new MediaObj(resultSet.getInt("fldMediaID")));
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        for (MediaObj mediaObj : mediaObjs) {
            addItemToListView(LWPlaylistMedia, mediaObj.getPRESENTED_MEDIA_TITLE());
        }

    }

    private void loadlistViewPlaylist() {

        try (ResultSet resultSet = new MediaSearch().searchMediaForPlaylist(""); ){

            while (resultSet.next()){
                playlistObjs.add(new PlaylistObj(resultSet.getString("fldPlaylistName")));
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        for (PlaylistObj playlistObj : playlistObjs) {
            addItemToListView(LWPlaylistDisplay, playlistObj.getPRESENTED_PLAYLIST_NAME());
        }

    }

    private void addItemToListView(ListView listView, String item){listView.getItems().add(item);}

    @FXML
    private void onActionbtnCreateClick(){

        PlaylistHandler.createPlaylist(playlistCreateBar.getText());
        load();

    }

    @FXML
    private void onActionbtnCancelClick(){
        sceneController.switchToHomeScene();
    }

    @FXML
    private  void onActionbtnDeletePlaylistClick(){

        AlertPopup confirmDelete = new AlertPopup("Delete Playlist"
                , "Are you sure you want to delete the playlist?");

        if(confirmDelete.showConfirmation("Yes", "No")){

            try{
                if (LWPlaylistDisplay.getSelectionModel().getSelectedIndex() != -1) {
                    PlaylistHandler.deletePlaylist(LWPlaylistDisplay.getSelectionModel().getSelectedItem());
                }

                if (LWPlaylistMedia.getSelectionModel().getSelectedIndex() != -1){
                    try {
                        DatabaseDelete.deleteChosenMediaFromPlaylist(selectedPlaylist.getPLAYLIST_NAME(),
                                mediaObjs.get(LWPlaylistMedia.getSelectionModel().getSelectedIndex()).getMEDIA_ID()).executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }

                load();
                loadListViewMedia(selectedPlaylist);

            }catch (IndexOutOfBoundsException e){
                throw new IndexOutOfBoundsException();
            }

        }

    }

    @FXML
    private void onBtnEditClick(){
        LWPlaylistMedia.getItems().clear();
        selectedPlaylist = playlistObjs.get(LWPlaylistDisplay.getSelectionModel().getSelectedIndex());
        loadListViewMedia(playlistObjs.get(LWPlaylistDisplay.getSelectionModel().getSelectedIndex()));
    }



    public void onActionbtnPlayPlaylistClick(){

        PlaylistHandler.createMediaArray(playlistObjs.get(LWPlaylistDisplay.getSelectionModel().getSelectedIndex()).getPLAYLIST_NAME());
        if(!HomeViewController.mediaObjQue.isEmpty()){
            switchToHomeView();
        }
    }

    @FXML
    public void switchToPlaylistView() {
        sceneController.switchToPlaylistScene();
    }
    @FXML

    public void switchToSearchView() {
        sceneController.switchToSearchScene();
    }
    @FXML

    public void switchToHomeView() {
        sceneController.switchToHomeScene();
    }
    @FXML

    public void onActionbtnImportClick(){
        FileChooser.fileChooser();
    }

    private void load(){

        playlistObjs.clear();
        LWPlaylistDisplay.getItems().clear();


        loadlistViewPlaylist();
    }



}