package mediaplayer.orpheus.model.Database;

public class DatabaseUpdate {
    public static String setMediaTitle(String newTitle, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldMediaTitle = ")
                .append(newTitle)
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String setMediaType(String mediaType, int mediaId){
        return new StringBuilder()
                .append("UPDATE tblMedia")
                .append(" SET fldMediaType = ")
                .append(mediaType)
                .append(" WHERE fldMediaId = ")
                .append(mediaId)
                .toString();
    }
}
