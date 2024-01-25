package mediaplayer.orpheus.model.Service;

import javafx.stage.Stage;
import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;

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
        // Adds extension filter to the fil chooser
        fileChooser.getExtensionFilters().add(extensionFilter);
        // Opens the fil choosers dialog and if any file is selected then set file to that
        File file = fileChooser.showOpenDialog(fileStage);

        // Checks if a valid file has been chosen
        if (file != null){
            processSelectedFile(file);
        }else {
            // Debug line
            debugMessage.error(FileChooser.class,"File path not found.");
        }

    }

    /**
     * Method for processing the selected file
     * @param file This is the file selected from the FileChooser
     */
    private static void processSelectedFile(File file){
        String mediaPath = file.getAbsolutePath();
        // Debug line
        debugMessage.success(FileChooser.class,"File path found.");
        MetadataService metadataHandler = new MetadataService(mediaPath);


        // Trys to extract all the metadata from the file as it can
        try {
            metadataHandler.mp3OrMp4();

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for choosing an image path for thumbnail
     * @return This is the file path for the selected image
     */
    public static String imageChooser(){

        Stage imageStage = new Stage();

        // Creates a FileChooser object
        javafx.stage.FileChooser imageChooser = new javafx.stage.FileChooser();
        // Makes a filter for the FileChooser, so when using FileChooser file explore only shows mp3 and mp4 files
        javafx.stage.FileChooser.ExtensionFilter extensionFilter = new javafx.stage.FileChooser.ExtensionFilter("PNG files", "*.png");
        imageChooser.getExtensionFilters().add(extensionFilter);
        // Opens the image choosers dialog and if any file is chosen set that to file
        File file = imageChooser.showOpenDialog(imageStage);

        // Checks if a valid file has been chosen
        try{
            // Debug line
            debugMessage.success(FileChooser.class,"Image File path found.");
            return file.toString();
        }catch (NullPointerException e){
            // Debug line
            debugMessage.error(FileChooser.class,"Image file path not found.");
        }
        return null;
    }

}
