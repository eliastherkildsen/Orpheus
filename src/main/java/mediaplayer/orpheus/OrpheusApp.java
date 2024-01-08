package mediaplayer.orpheus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OrpheusApp extends Application {

    private final int SCREEN_WITH = 800;
    private final int SCREEN_HIGHT = 800;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OrpheusApp.class.getResource("SearchView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SCREEN_WITH, SCREEN_HIGHT);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}