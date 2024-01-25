package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseCreate;
import mediaplayer.orpheus.util.AnsiColorCode;
import mediaplayer.orpheus.util.debugMessage;

public class AddArtist {

    /**
     * Method for creating a new artist, and adding the artist to the database.
     *
     * @param artistFirstname String
     * @param artistLastname String
     * @param artistName String referees to the artists stage name.
     */
    public static void createArtist(String artistFirstname, String artistLastname, String artistName){

        JDBC.instance.executeUpdate(DatabaseCreate.insertPerson(artistFirstname, artistLastname, artistName));

        debugMessage.success(AddArtist.class,"CreateArtist: A new Media Artist has been added to the DB.");
    }

}
