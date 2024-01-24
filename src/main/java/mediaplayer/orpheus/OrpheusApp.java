package mediaplayer.orpheus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mediaplayer.orpheus.Controler.SceneController;
import mediaplayer.orpheus.model.Database.JDBC;
import java.io.IOException;
import java.util.Objects;

public class OrpheusApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // creates a new database obj.
        JDBC.get();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeView.fxml")));
        Scene scene = new Scene(root);
        SceneController.setStage(stage);
        stage.setScene(scene);
        stage.setTitle("Orpheus");
        stage.getIcons().add(new Image("file:src/main/resources/css/images/audio-lines.png"));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
        // closing database.
        JDBC.instance.databaseClose();
    }
}