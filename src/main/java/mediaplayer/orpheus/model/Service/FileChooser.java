package mediaplayer.orpheus.model.Service;

import javafx.stage.Stage;
import mediaplayer.orpheus.util.AnsiColorCode;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class FileChooser {

    /**
     * Method for choosing a file from file explore
     */
    public static void fileChooser(){
        Stage fileStage = new Stage();

        // Creates a FileChooser object
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        // Makes a filter for the FileChooser, so when using FileChooser file explore only shows mp3 and mp4 files
        javafx.stage.FileChooser.ExtensionFilter extensionFilter = new javafx.stage.FileChooser.ExtensionFilter("mp4 files", "*.mp4", "*.mp3");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(fileStage);

        // Checks if a valid file has been chosen
        if (file != null){
            processSelectedFile(file);
        }else {
            // Debug line
            System.out.printf("%s[File Chooser] File path not found%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
        }
    }

    /**
     * Method for processing the selected file
     * @param file This is the file selected from the FileChooser
     */
    private static void processSelectedFile(File file){
        String mediaPath = file.getAbsolutePath();
        // Debug line
        System.out.printf("%s[File Chooser] File path found%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        MetadataService metadataHandler = new MetadataService(mediaPath);

        // Trys to extract all the metadata from the file as it can
        try {
            metadataHandler.insertAndGatherMediaMp3();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
