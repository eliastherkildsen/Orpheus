package mediaplayer.orpheus.model.Database;

public class DatabaseUpdate {
    public static String setMediaTitle(String newTitle, int mediaId){
        return "UPDATE tblMedia" +
                " SET fldMediaTitle = '" +
                newTitle +
                "' WHERE fldMediaID = " +
                mediaId;
    }
    /**
     * Note, this method does not check if the mediaType is valid for the DB. It assumes you know what you are doing.
     * @param fileType file extension, mp3 or mp4
     * @param mediaId mediaid from DB
     * @return String query
     */
    public static String setMediaType(String fileType, int mediaId){
        return "UPDATE tblMedia" +
                " SET fldFileType = '" +
                fileType +
                "' WHERE fldMediaID = " +
                mediaId;
    }
    public static String setMediaAlbum(String albumName, int mediaId){
        return "UPDATE tblMedia" +
                " SET fldAlbum = '" +
                albumName +
                "' WHERE fldMediaID = " +
                mediaId;
    }
    public static String setMediaYear(String year, int mediaId){
        return "UPDATE tblMedia" +
                " SET fldMediaYear = " +
                year +
                " WHERE fldMediaID = " +
                mediaId;
    }
    public static String setMediaTrack(int trackNumber, int mediaId){
        return "UPDATE tblMedia" +
                " SET fldMediaTrack = " +
                trackNumber +
                " WHERE fldMediaID = " +
                mediaId;
    }
    public static String setMediaLength(int LengthInSeconds, int mediaId){
        return "UPDATE tblMedia" +
                " SET fldTrackLength = " +
                LengthInSeconds +
                " WHERE fldMediaId = " +
                mediaId;
    }
    public static String setMediaImagePath(String imagePath, int mediaId){
        return "UPDATE tblMedia" +
                " SET fldImagePath = '" +
                imagePath +
                "' WHERE fldMediaID = " +
                mediaId;
    }
    public static String setFilePath(String filepath, int mediaId){
        return "Update tblMedia" +
                " SET fldFilePath = '" +
                filepath +
                "' WHERE fldMediaID = " +
                mediaId;
    }



    /**
     * Setting the Artist name for the person.
     * @param artistName String
     * @param personId Int
     * @return String query
     */
    public static String setArtistName(String artistName,int personId){
        return "Update tblPerson" +
                " SET fldArtistName = '" +
                artistName +
                "' WHERE fldPersonID = " +
                personId;
    }
    public static String setPersonFirstName(String firstName, int personId){
        return "Update tblPerson" +
                " SET fldFirstName = '" +
                firstName +
                "' WHERE fldPersonID = " +
                personId;
    }
    public static String setPersonLastName(String lastName, int personId){
        return "Update tblPerson" +
                " SET fldLastName = '" +
                lastName +
                "' WHERE fldPersonID = " +
                personId;
    }
    /**
     * Setting the PersonId in relation to the MediaObj
     * @param personId Int
     * @param mediaId Int
     * @return String query
     */
    public static String setMediaArtist(String personId, int mediaId){
        return "Update tblMediaPerson" +
                " SET fldPersonID = " +
                personId +
                " WHERE fldMediaID = " +
                mediaId;
    }
        /**
     * Setting the genre in relation to the MediaObj
     * @param genre Int
     * @param mediaId Int
     * @return String query
     */
    public static String setMediaGenre(String genre, int mediaId){
        return "UPDATE tblMediaGenre" +
                " SET fldGenre = '" +
                genre +
                "' WHERE fldMediaID = " +
                mediaId;
    }




    /**
     * Updates the Genre for a specific song. Obviously the Genre needs to match a Genre in the tblGenre.
     * @param updatedGenre String
     * @param mediaId int
     * @return String query
     */
    public static String updateMediaGenre(String updatedGenre, int mediaId){
        return "UPDATE tblMediaGenre" +
                " SET fldGenre = '" +
                updatedGenre +
                "' WHERE = " +
                mediaId;
    }


}








