package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseCreate;

public class AddArtist {

    public static void createArtist(String artistFirstname, String artistLastname, String artistName){

        String quary = DatabaseCreate.insertPerson(artistFirstname, artistLastname, artistName);
        OrpheusApp.jdbc.executeUpdate(quary);

    }

}
