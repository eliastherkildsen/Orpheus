package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseCreate;
import mediaplayer.orpheus.util.AnsiColorCode;

public class AddArtist {

    /**
     * Method for creating a new artist, and adding the artist to the database.
     *
     * @param artistFirstname String
     * @param artistLastname String
     * @param artistName String referees to the artists stage name.
     */
    public static void createArtist(String artistFirstname, String artistLastname, String artistName){

        String query = DatabaseCreate.insertPerson(artistFirstname, artistLastname, artistName);

        JDBC.instance.executeUpdate(query);

        System.out.printf("%s[AddArtist][createArtist] A new media Artist has been added to the database: %s%s",
                AnsiColorCode.ANSI_YELLOW, artistName, AnsiColorCode.ANSI_RESET);


    }

}
