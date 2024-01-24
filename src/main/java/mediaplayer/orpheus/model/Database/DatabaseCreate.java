package mediaplayer.orpheus.model.Database;

public class DatabaseCreate {
    //INSERT INTO tblMediaGenre (fldGenre, fldMediaID) VALUES ('Pop', 29)
    public static String insertMediaGenre(String genre, String mediaId){
        return "INSERT INTO tblMediaGenre (fldGenre, fldMediaID)" +
                " VALUES ('" +
                genre +
                "'," +
                mediaId +
                ")";
    }
    //INSERT INTO tblMediaPerson(fldMediaID, fldPersonID) VALUES (29, '16')
    public static String insertMediaPerson(int person, String mediaId){
        return "INSERT INTO tblMediaPerson (fldPersonID, fldMediaID)" +
                " VALUES ('" +
                person +
                "'," +
                mediaId +
                ")";
    }
    //INSERT INTO tblPerson (fldFirstName, fldLastName, fldArtistName) VALUES ()
    public static String insertPerson(String firstName, String lastName, String artistName){
        return "INSERT INTO tblPerson (fldFirstName, fldLastName, fldArtistName)" +
                " VALUES ('" +
                firstName +
                "','" +
                artistName +
                "','" +
                artistName +
                "')";
    }


    public static String insertGenre(String genre) {
        return "INSERT INTO tblGenre (fldGenre) VALUES (' " +
                genre +
                "')";
    }
}
