package mediaplayer.orpheus.model.Database;

public class DatabaseRead {
    public static String getMediaIdFromTitle(String mediaTitle){
        return new StringBuilder()
                .append("SELECT fldMediaID")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaTitle = '")
                .append(mediaTitle)
                .append("'")
                .toString();
    }
    public static String getMediaTitle(int mediaId) {
        return new StringBuilder()
                .append("SELECT fldMediaTitle")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getMediaType(int mediaId){
        return new StringBuilder()
                .append("SELECT fldFileType")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getMediaAlbum(int mediaId){
        return new StringBuilder()
                .append("SELECT fldFilePath")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getMediaYear(int mediaId){
        return new StringBuilder()
                .append("SELECT fldFilePath")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getMediaTrack(int mediaId){
        return new StringBuilder()
                .append("SELECT fldMediaTrack")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getMediaLength(int mediaId){
        return new StringBuilder()
                .append("SELECT fldTrackLength")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getMediaPath(int mediaId){
        return new StringBuilder()
                .append("SELECT fldFilePath")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getMediaArtistArtName(int mediaId){
        return new StringBuilder()
                .append("SELECT DISTINCT p.fldArtistName")
                .append(" FROM tblPerson p")
                .append(" JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID")
                .append(" WHERE mp.fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getMediaArtistFirstName(int mediaId){
        return new StringBuilder()
                .append("SELECT DISTINCT p.fldFirstName")
                .append(" FROM tblPerson p")
                .append(" JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID")
                .append(" WHERE mp.fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getMediaArtistLastName(int mediaId){
        return new StringBuilder()
                .append("SELECT DISTINCT p.fldLastName")
                .append(" FROM tblPerson p")
                .append(" JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID")
                .append(" WHERE mp.fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getAllArtists (){
        return "SELECT DISTINCT fldArtistName FROM tblPerson";
    }
    public static String getMediaGenre(int mediaId){
        return new StringBuilder()
                .append("SELECT DISTINCT mg.fldGenre")
                .append(" FROM tblMedia m")
                .append(" JOIN tblMediaGenre mg ON m.fldMediaID = mg.fldMediaID")
                .append(" WHERE mg.fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public static String getAllGenres (){
        return "SELECT * FROM tblGenre";
    }

    public static String getAllPlaylistNames() {return "SELECT fldPlaylistName FROM tblPlaylist";}

    //SELECT fldTrackOrder FROM tblMediaPlaylist WHERE fldTrackOrder = " +
    //                    "(SELECT MAX(fldTrackOrder) FROM tblMediaPlaylist WHERE fldPlaylistName = 'Hello')
    public static String getMaxTrackOrder(String playlistName){
        return new StringBuilder()
                .append("SELECT fldTrackOrder FROM tblMediaPlaylist WHERE fldTrackOrder = ")
                .append("(SELECT MAX(fldTrackOrder) FROM tblMediaPlaylist WHERE fldPlaylistName = '")
                .append(playlistName)
                .append("')")
                .toString();
    }
}