package mediaplayer.orpheus.Controler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mediaplayer.orpheus.util.AnsiColorCode;

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

            System.out.printf("%s[SceneController][switchToPlaylistScene] An error occurred while switching " +
                    "to the playlistView %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);

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

            System.out.printf("%s[SceneController][switchToSearchScene] An error occurred while switching " +
                    "to the searchView %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);

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
            System.out.printf("%s[SceneController][switchToHomeScene] An error occurred while switching " +
                    "to the HomeView %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);

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

            System.out.printf("%s[SceneController][switchToEditScene] An error occurred while switching " +
                    "to the addEditView %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);

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
            System.out.printf("%s[SceneController][switchToAddArtistView] An error occurred while switching " +
                    "to the addArtistView %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);

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

            System.out.printf("%s[SceneController][switchToAddGenreView] An error occurred while switching " +
                    "to the addGenreView %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);

            throw new RuntimeException(e);

        }
        // Creates a new scene, using the file path stored in root
        Scene HomeView = new Scene(root);
        // Sets the stage to the new scene
        stage.setScene(HomeView);
    }
}



