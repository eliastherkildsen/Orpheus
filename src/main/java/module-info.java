module mediaplayer.orpheus {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;


    opens mediaplayer.orpheus to javafx.fxml;
    exports mediaplayer.orpheus;
}