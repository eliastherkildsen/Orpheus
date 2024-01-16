package mediaplayer.orpheus.model.Database;

public class DatabaseUpdate {
    public static String setMediaTitle(String newTitle, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldMediaTitle = '")
                .append(newTitle)
                .append("' WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    /**
     * Note, this method does not check if the mediaType is valid for the DB. It assumes you know what you are doing.
     * @param fileType file extension, mp3 or mp4
     * @param mediaId mediaid from DB
     * @return String query
     */
    public static String setMediaType(String fileType, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldFileType = '")
                .append(fileType)
                .append("' WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String setMediaAlbum(String albumName, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldAlbum = '")
                .append(albumName)
                .append("' WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String setMediaYear(String year, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldMediaYear = ")
                .append(year)
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String setMediaTrack(int trackNumber, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldMediaTrack = ")
                .append(trackNumber)
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String setMediaLength(int LengthInSeconds, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldTrackLength = ")
                .append(LengthInSeconds)
                .append(" WHERE fldMediaId = ")
                .append(mediaId)
                .toString();
    }
    public static String setFilePath(String filepath, int mediaId){
        return new StringBuilder()
                .append("Update tblMedia")
                .append(" SET fldFilePath = '")
                .append(filepath)
                .append("' WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }



    /**
     * Setting the Artist name for the person.
     * @param artistName String
     * @param personId Int
     * @return String query
     */
    public static String setArtistName(String artistName,int personId){
        return new StringBuilder()
                .append("Update tblPerson")
                .append(" SET fldArtistName = '")
                .append(artistName)
                .append("' WHERE fldPersonID = ")
                .append(personId)
                .toString();
    }
    public static String setPersonFirstName(String firstName, int personId){
        return new StringBuilder()
                .append("Update tblPerson")
                .append(" SET fldFirstName = '")
                .append(firstName)
                .append("' WHERE fldPersonID = ")
                .append(personId)
                .toString();
    }
    public static String setPersonLastName(String lastName, int personId){
        return new StringBuilder()
                .append("Update tblPerson")
                .append(" SET fldLastName = '")
                .append(lastName)
                .append("' WHERE fldPersonID = ")
                .append(personId)
                .toString();
    }
    /**
     * Setting the PersonId in relation to the Media
     * @param personId Int
     * @param mediaId Int
     * @return String query
     */
    public static String setMediaArtist(int personId, String mediaId){
        return new StringBuilder()
                .append("Update tblMediaPerson")
                .append(" SET fldPersonID = ")
                .append(personId)
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
        /**
     * Setting the genre in relation to the Media
     * @param genre Int
     * @param mediaId Int
     * @return String query
     */
    public static String setMediaGenre(String genre, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMediaGenre")
                .append(" SET fldGenre = '")
                .append(genre)
                .append("' WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }




    /**
     * Updates the Genre for a specific song. Obviously the Genre needs to match a Genre in the tblGenre.
     * @param updatedGenre String
     * @param mediaId int
     * @return String query
     */
    public static String updateMediaGenre(String updatedGenre, String mediaId){
        return new StringBuilder()
                .append("UPDATE tblMediaGenre")
                .append(" SET fldGenre = '")
                .append(updatedGenre)
                .append("' WHERE = ")
                .append(mediaId)
                .toString();
    }


}








