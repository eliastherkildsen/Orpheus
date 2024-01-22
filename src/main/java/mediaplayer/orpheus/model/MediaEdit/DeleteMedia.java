package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseDelete;
import mediaplayer.orpheus.util.AnsiColorCode;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMedia {

    private static Connection connection = JDBC.instance.getConnection();

    public static void deleteMediaFromDatabase(int mediaId){

        PreparedStatement preparedStatement;
        String quarry = DatabaseDelete.deleteMediaQuarry(mediaId);

        try {

            preparedStatement = connection.prepareCall(quarry);
            preparedStatement.executeUpdate();

            System.out.printf("%s[DeleteMedia][deleteMediaFromDatabase] media with %s as media id was deleted%s%n", AnsiColorCode.ANSI_YELLOW, mediaId, AnsiColorCode.ANSI_RESET);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteMediaFileFromDir(String filePath){
        File file = new File(filePath);
        if (file.delete()) {
            System.out.printf("%s[DeleteMedia][deleteMediaFileFromDir] Deleted the file: %s%s", AnsiColorCode.ANSI_YELLOW, file.getName(), AnsiColorCode.ANSI_RESET);
        } else {
            System.out.printf("%s[DeleteMedia][deleteMediaFileFromDir] Failed the file: %s%s", AnsiColorCode.ANSI_RED, file.getName(), AnsiColorCode.ANSI_RESET);
        }

    }

}
