package mediaplayer.orpheus.Controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Database.DatabaseUtil;
import mediaplayer.orpheus.model.Service.FileChooser;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditMediaViewController implements Initializable {

    @FXML
    private Button btnSearch, btnPlaylist, btnImport, btnDelete, btnUpdate, btnCancel;
    private SceneController sceneController = new SceneController();
    @FXML
    private TextField fldMediaTitle, fldYear;
    @FXML
    private ChoiceBox cbArtist, cbGenre;
    @FXML
    private Label labMedia;
    public static int selectedMediaID;
    private ArrayList<String> genre = new ArrayList<>();
    private ArrayList<String> artist = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();
    }
    @FXML
    private void onBtnUpdataClick(){}
    @FXML
    public void onBtnCancelClick() {}

    // populate genre.
    // get all genre in array list to keep track of indecies.

    /**
     * method for adding a String to a choice box
     *
     * @param choiceBox
     * @param item String to be added to the choice box.
     */
    private void addItemToChoiceBox(ChoiceBox choiceBox, String item){
        choiceBox.getItems().add(item);
    }

    /**
     * method for clearing a choiceBox
     * @param choiceBox
     */
    private void clearChoiceBox(ChoiceBox choiceBox){
        choiceBox.getItems().clear();
    }

    /**
     * Method for getting the selected items index in a choice box
     * returns -1 if no item is selected.
     * @param choiceBox
     * @return the selected items index, -1 if no item is selected.
     */
    private int getSelectedItemIndex(ChoiceBox choiceBox){
        return choiceBox.getSelectionModel().getSelectedIndex();
    }

    private void load(){
        labMedia.setText("MEDIA TITLE");

        // load all genres in to cbGenre
        loadGenre();

        // load all artists in to cbArtist
        loadArtist();

    }

    private void loadGenre(){
        String quary = DatabaseRead.getAllGenres();

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                genre.add(resultSet.getString("fldGenre")); // Replace "columnName" with the actual column name
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String genre : genre){
            addItemToChoiceBox(cbGenre, genre);
        }

    }

    private void loadArtist(){
        String quary = DatabaseRead.getAllArtists();

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                artist.add(resultSet.getString("fldArtistName")); // Replace "columnName" with the actual column name
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String artist : artist){
            addItemToChoiceBox(cbArtist, artist);
        }

    }







    @FXML
    public void switchToPlaylistView() {
        try {
            sceneController.switchToPlaylistScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void switchToSearchView() {
        try {
            sceneController.switchToSearchScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void switchToHomeView() {
        try {
            sceneController.switchToHomeScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onActionbtnImportClick(){

        FileChooser.fileChooser();
    }

}
