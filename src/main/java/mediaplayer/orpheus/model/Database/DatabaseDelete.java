package mediaplayer.orpheus.model.Database;

public class DatabaseDelete {

    public static String deleteMediaQuarry(int mediaID){


        return new StringBuilder()
                .append("DELETE FROM tblMediaGenre WHERE fldMediaID= ")
                .append(mediaID)

                .append("DELETE FROM tblMediaPerson WHERE fldMediaID= ")
                .append(mediaID)

                .append("DELETE FROM tblMediaPlaylist WHERE fldMediaID= ")
                .append(mediaID)

                .append("DELETE FROM tblMedia WHERE fldMediaID= ")
                .append(mediaID)

                .toString();

    }

}
