package mediaplayer.orpheus.Controler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
     * @throws IOException
     */
    public void switchToPlaylistScene() throws IOException {
        // Stores the path to the fxml file in a variable.
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/PlaylistView.fxml"));
        // Creates a new scene, using the file path stored in root
        Scene playlistView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(playlistView);
    }

    /**
     * Method that switches the scene to the Search scene
     * @throws IOException
     */
    public void switchToSearchScene() throws IOException {
        // Stores the path to the fxml file in a variable.
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/SearchView.fxml"));
        // Creates a new scene, using the file path stored in root
        Scene searchView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(searchView);
    }

    /**
     * Method that switches the scene to the Home scene
     * @throws IOException
     */
    public void switchToHomeScene() throws IOException {
        // Stores the path to the fxml file in a variable.
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/HomeView.fxml"));
        // Creates a new scene, using the file path stored in root
        Scene HomeView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(HomeView);
    }
    /**
     * Method that switches the scene to the Home scene
     * @throws IOException
     */
    public void switchToEditScene() throws IOException {
        // Stores the path to the fxml file in a variable.
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/EditMediaView.fxml"));
        // Creates a new scene, using the file path stored in root
        Scene HomeView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(HomeView);
    }

    public void switchToAddArtistView() throws IOException {
        // Stores the path to the fxml file in a variable.
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/AddArtistView.fxml"));
        // Creates a new scene, using the file path stored in root
        Scene HomeView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(HomeView);
    }

    public void switchToAddGenreView() throws IOException {
        // Stores the path to the fxml file in a variable.
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/AddGenreView.fxml"));
        // Creates a new scene, using the file path stored in root
        Scene HomeView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(HomeView);
    }
}



