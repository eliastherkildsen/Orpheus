package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.model.Database.DatabaseDelete;
import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;

import java.io.File;
import java.sql.SQLException;

public class DeleteMedia {

    /**
     * Method for deleting a media from the database.
     * @param mediaId the mediaId associated to the media.
     */
    public static void deleteMediaFromDatabase(int mediaId){

        try {

            DatabaseDelete.deleteMediaQuarry(mediaId).executeUpdate();

            debugMessage.success(DeleteMedia.class,"DeleteFromDB: Media with " + mediaId + " ID was DELETED.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method for deleting a file from the computer, add a specific path.
     * @param filePath the file path to the file intended to be deleted.
     */
    public static void deleteMediaFileFromDir(String filePath){

        File file = new File(filePath);

        if (file.delete()) {
            debugMessage.success(DeleteMedia.class,"DeleteMediaFileFromDir: Deleted the file: " + file.getName());
        } else {
            debugMessage.error(DeleteMedia.class,"DeletedMediaFileFromDir: Failed to find the file. " + file.getName());
        }

    }

}
