package mediaplayer.orpheus.Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mediaplayer.orpheus.OrpheusApp;

import java.io.IOException;
import java.util.Objects;

public class ViewControler {

    private static Stage stage;

    public static void setStage(Stage stage) {
        ViewControler.stage = stage;
    }

    public void switchToPlaylistScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/PlaylistView.fxml"));
        Scene playlistView = new Scene(root);
        stage.setScene(playlistView);
        stage.show();
    }
    public void switchToSearchScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/SearchView.fxml"));
        Scene searchView = new Scene(root);
        stage.setScene(searchView);
        stage.show();
    }
    public void switchToHomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/HomeView.fxml"));
        Scene HomeView = new Scene(root);
        stage.setScene(HomeView);
        stage.show();
    }
    public void switchToHomeScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mediaplayer/orpheus/HomeView.fxml"));
        Scene HomeView = new Scene(root);
        stage.setScene(HomeView);
        stage.show();
    }

    // Other methods...
}



