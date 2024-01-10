module mediaplayer.orpheus {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jaudiotagger;


    opens mediaplayer.orpheus to javafx.fxml;
    exports mediaplayer.orpheus;
    exports mediaplayer.orpheus.Controler;
    opens mediaplayer.orpheus.Controler to javafx.fxml;
}