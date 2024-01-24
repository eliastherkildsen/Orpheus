package mediaplayer.orpheus.model.Service;

import mediaplayer.orpheus.util.debugMessage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


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

        String absolutePath = file.getAbsolutePath();
        String fileName = new File(absolutePath).getName();

        if (fileName.contains(".") && !fileName.endsWith(".")) {
            return fileName.substring(fileName.lastIndexOf('.') + 1);
        }
        return "";
    }

    /**
     * A method to determine what extension the class has, then returns that in a desired format.
     * Includes the ability to call a error when the extension isn't supported.
     * TODO: Correct error Handling
     * @return String mp3 or mp4
     */
    public String mp3OrMp4(){
        String extension = this.returnFileExtension();
        debugMessage.debug(this,"Extension found as: " + extension);
        return switch (extension) {
            case "mp3" -> "mp3";
            case "mp4" -> "mp4";
            default -> "error";
        };
    }

    /**
     * Takes a path and only returns the filename.
     * @return String filename.
     */
    public String getFileNameWithoutExtension() {
        Path path = Paths.get(getFilePath());
        String fileName = path.getFileName().toString();

        int dotIndex = fileName.lastIndexOf('.');

        // If there's a dot in the filename, extract the substring without extension
        if (dotIndex != -1) {
            return fileName.substring(0, dotIndex);
        } else {
            // If there's no dot, the entire filename is the result
            return fileName;
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
