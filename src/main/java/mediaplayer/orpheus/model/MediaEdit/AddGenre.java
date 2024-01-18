package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseCreate;
import mediaplayer.orpheus.util.AnsiColorCode;

public class AddGenre {
    public static void createGenre(String genre){

        String quary = DatabaseCreate.insertGenre(genre);
        OrpheusApp.jdbc.executeUpdate(quary);
        System.out.printf("%s[AddGenre][createGenre] A new media genre has been added to the database: %s%s", AnsiColorCode.ANSI_YELLOW, genre, AnsiColorCode.ANSI_RESET);

    }

}
