package mediaplayer.orpheus.model.Database;

public class DatabaseCreate {
    //INSERT INTO tblMediaGenre (fldGenre, fldMediaID) VALUES ('Pop', 29)
    public static String insertMediaGenre(String genre, String mediaId){
        return new StringBuilder()
                .append("INSERT INTO tblMediaGenre (fldGenre, fldMediaID)")
                .append(" VALUES ('")
                .append(genre)
                .append("',")
                .append(mediaId)
                .append(")")
                .toString();
    }
    //INSERT INTO tblMediaPerson(fldMediaID, fldPersonID) VALUES (29, '16')
    public static String insertMediaPerson(int person, String mediaId){
        return new StringBuilder()
                .append("INSERT INTO tblMediaPerson (fldPersonID, fldMediaID)")
                .append(" VALUES ('")
                .append(person)
                .append("',")
                .append(mediaId)
                .append(")")
                .toString();
    }


}
