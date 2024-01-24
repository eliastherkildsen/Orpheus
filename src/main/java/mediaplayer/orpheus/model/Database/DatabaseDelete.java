package mediaplayer.orpheus.model.Database;

public class DatabaseDelete {

    public static String deleteMediaQuarry(int mediaID){


        return "DELETE FROM tblMediaGenre WHERE fldMediaID= " +
                mediaID +
                "DELETE FROM tblMediaPerson WHERE fldMediaID= " +
                mediaID +
                "DELETE FROM tblMediaPlaylist WHERE fldMediaID= " +
                mediaID +
                "DELETE FROM tblMedia WHERE fldMediaID= " +
                mediaID;

    }

}
