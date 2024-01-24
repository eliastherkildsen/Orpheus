package mediaplayer.orpheus.Controler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;

import java.io.IOException;

/**
 * This class handles the switching between the different scenes.
 */

public class SceneController {

    private static Stage stage;

    /**
     * Method that sets the passed stage to the static stage variable
     * @param stage Passed stage
     */
    public static void setStage(Stage stage) {
        SceneController.stage = stage;
    }

    /**
     * Method that switches the scene to the Playlist scene
     */
    public void switchToPlaylistScene() {
        // Stores the path to the fxml file in a variable.
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/PlaylistView.fxml"));
        } catch (IOException e) {

            debugMessage.error(this,"SwitchToPlayList: An Error occurred while switching.");

            throw new RuntimeException(e);
        }
        // Creates a new scene, using the file path stored in root
        Scene playlistView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(playlistView);
    }

    /**
     * Method that switches the scene to the Search scene
     */
    public void switchToSearchScene() {
        // Stores the path to the fxml file in a variable.
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/SearchView.fxml"));
        } catch (IOException e) {

            debugMessage.error(this,"SwitchToSearch: An Error occurred while switching.");

            throw new RuntimeException(e);
        }
        // Creates a new scene, using the file path stored in root
        Scene searchView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(searchView);
    }

    /**
     * Method that switches the scene to the Home scene
     */
    public void switchToHomeScene() {
        // Stores the path to the fxml file in a variable.
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/HomeView.fxml"));
        } catch (IOException e) {
            debugMessage.error(this,"SwitchToHome: An Error occurred while switching.");


            throw new RuntimeException(e);
        }
        // Creates a new scene, using the file path stored in root
        Scene HomeView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(HomeView);
    }


    /**
     * Method that switches the scene to the Home scene
     */
    public void switchToEditScene() {
        // Stores the path to the fxml file in a variable.
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/EditMediaView.fxml"));
        } catch (IOException e) {

            debugMessage.error(this,"SwitchToEdit: An Error occurred while switching.");


            throw new RuntimeException(e);

        }
        // Creates a new scene, using the file path stored in root
        Scene HomeView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(HomeView);
    }

    public void switchToAddArtistView() {
        // Stores the path to the fxml file in a variable.
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/AddArtistView.fxml"));
        } catch (IOException e) {
            debugMessage.error(this,"SwitchToArtist: An Error occurred while switching.");


            throw new RuntimeException(e);

        }
        // Creates a new scene, using the file path stored in root
        Scene HomeView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(HomeView);
    }

    public void switchToAddGenreView(){
        // Stores the path to the fxml file in a variable.
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/AddGenreView.fxml"));
        } catch (IOException e) {

            debugMessage.error(this,"SwitchToGenre: An Error occurred while switching.");


            throw new RuntimeException(e);

        }
        // Creates a new scene, using the file path stored in root
        Scene HomeView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(HomeView);
    }
}



