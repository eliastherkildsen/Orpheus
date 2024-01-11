package mediaplayer.orpheus.model.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
//region Getter and Setter
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
//endregion
}
