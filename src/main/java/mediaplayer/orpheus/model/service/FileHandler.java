package mediaplayer.orpheus.model.service;

import java.io.File;

public class FileHandler {
    private String filePath;
    public FileHandler(String filePath) {
        setFilePath(filePath);
    }

    /**
     *
     * @return
     */
    private String fileTypeCheck() {

        File file = new File(filePath);
        if (file.exists()) { //Skal det her check overhoved være her?
            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

            System.out.println("File type: " + fileExtension);

            return fileExtension;
        } else {
            return ""; //Prøve igen? Gør ingenting? ErrorReport?
        }
    };

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
