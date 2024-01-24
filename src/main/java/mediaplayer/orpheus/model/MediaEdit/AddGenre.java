package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseCreate;
import mediaplayer.orpheus.util.debugMessage;

public class AddGenre {

    /**
     * Method for creating a new genre, and adding the genre to the database.
     * @param genre String The genre name
     */
    public static void createGenre(String genre){

        JDBC.instance.executeUpdate(DatabaseCreate.insertGenre(genre));
        debugMessage.debug(AddGenre.class,"CreateGenre: A new media genre has been added to the DB.");
    }

}
