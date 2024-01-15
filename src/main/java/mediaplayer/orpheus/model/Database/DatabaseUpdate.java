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
                .append("' WHERE fldMediaId = ")
                .append(mediaId)
                .toString();
    }
    public static String setMediaAlbum(String albumName, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldAlbum = '")
                .append(albumName)
                .append("' WHERE fldMediaId = ")
                .append(mediaId)
                .toString();
    }
    public static String setMediaYear(int year, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldMediaYear = ")
                .append(year)
                .append(" WHERE fldMediaId = ")
                .append(mediaId)
                .toString();
    }
    public static String setMediaTrack(int trackNumber, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldMediaTrack = ")
                .append(trackNumber)
                .append(" WHERE fldMediaId = ")
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
                .append("' WHERE fldMediaId = ")
                .append(mediaId)
                .toString();
    }

}
