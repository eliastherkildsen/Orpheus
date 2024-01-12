package mediaplayer.orpheus.model.Service;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mediaplayer.orpheus.util.AnsiColorCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Objects;

/**
 * TODO: ERROR HANDLING
 */
public class FileHandlerMedia {
    private String filePath;

    /**
     * FileHandler currently only deals with .mp3 and .mp4
     * @param filePath as String, the path for the file you want the extension for.
     */
    public FileHandlerMedia(String filePath) {
        setFilePath(filePath);
    }

    /**
     * Gets the file extension as a string for use elsewhere.
     * @return the file extension, everything after the last .
     */
    private String returnFileExtension() {
        File file = new File(filePath);
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    };

    /**
     *
     * @return String mp3 or mp4
     */
    public String mp3OrMp4(){
        String extension = this.returnFileExtension();
        if (Objects.equals(extension, "mp3")){
            return "mp3";
        } else if (Objects.equals(extension, "mp4")){
            return "mp4";
        } else {
            return "ERROR"; //SKIFT MIG TIL ERROR HANDLING
        }
    }
    public String getFileNameWithoutExtension() {
        Path path = Paths.get(getFilePath());
        // Get the filename from the path
        String fileName = path.getFileName().toString();

        // Find the last occurrence of '.' to separate the extension
        int dotIndex = fileName.lastIndexOf('.');

        // If there's a dot in the filename, extract the substring without extension
        if (dotIndex != -1) {
            return fileName.substring(0, dotIndex);
        } else {
            // If there's no dot, the entire filename is the result
            return fileName;
        }
    }

    /**
     * Method for choosing a file from file explore
     */
    public static void fileChooser(){
        Stage fileStage = new Stage();

        // Creates a FileChooser object
        FileChooser fileChooser = new FileChooser();
        // Makes a filter for the FileChooser, so when using FileChooser file explore only shows mp3 and mp4 files
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("mp4 files", "*.mp4", "*.mp3");
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
            metadataHandler.insertAndGatherMedia();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

//region Getter and Setter
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

//endregion
}
