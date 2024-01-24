package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseCreate;
import mediaplayer.orpheus.util.AnsiColorCode;

public class AddGenre {

    /**
     * Method for creating a new genre, and adding the genre to the database.
     * @param genre String The genre name
     */
    public static void createGenre(String genre){

        String quary = DatabaseCreate.insertGenre(genre);
        JDBC.instance.executeUpdate(quary);
        System.out.printf("%s[AddGenre][createGenre] A new media genre has been added to the database: %s%s",
                AnsiColorCode.ANSI_YELLOW, genre, AnsiColorCode.ANSI_RESET);

    }

}
