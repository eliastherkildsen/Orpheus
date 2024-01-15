package mediaplayer.orpheus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mediaplayer.orpheus.Controler.SceneController;
import mediaplayer.orpheus.model.Database.JDBC;

import java.util.Objects;

public class OrpheusApp extends Application {

    public static JDBC jdbc;
    @Override
    public void start(Stage stage) {
        jdbc = new JDBC();
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeView.fxml")));
            Scene scene = new Scene(root);
            SceneController.setStage(stage);
            stage.setScene(scene);
            stage.setTitle("Orpheus");
            stage.getIcons().add(new Image("file:src/main/resources/css/images/trash-2.png"));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
        // closing database.
        jdbc.databaseClose();
    }
}