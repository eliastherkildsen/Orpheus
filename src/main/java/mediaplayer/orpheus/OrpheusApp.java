package mediaplayer.orpheus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mediaplayer.orpheus.Controler.ViewControler;

import java.io.IOException;
import java.util.Objects;

public class OrpheusApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeView.fxml")));
            Scene scene = new Scene(root);
            ViewControler.setStage(stage);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}