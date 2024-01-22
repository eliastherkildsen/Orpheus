package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseCreate;
import mediaplayer.orpheus.util.AnsiColorCode;

public class AddArtist {

    public static void createArtist(String artistFirstname, String artistLastname, String artistName){

        String quary = DatabaseCreate.insertPerson(artistFirstname, artistLastname, artistName);
        JDBC.instance.executeUpdate(quary);
        System.out.printf("%s[AddArtist][createArtist] A new media Artist has been added to the database: %s%s", AnsiColorCode.ANSI_YELLOW, artistName, AnsiColorCode.ANSI_RESET);


    }

}
