package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseCreate;

public class AddGenre {
    public static void createGenre(String genre){

        String quary = DatabaseCreate.insertGenre(genre);
        OrpheusApp.jdbc.executeUpdate(quary);

    }

}
