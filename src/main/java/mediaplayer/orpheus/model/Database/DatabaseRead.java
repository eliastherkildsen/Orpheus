package mediaplayer.orpheus.model.Database;

public class DatabaseRead {
    public String getMediaTitle(int mediaId) {
        return new StringBuilder()
                .append("SELECT fldFilePath")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();

    }
    public String getMediaType(int mediaId){
        return new StringBuilder()
                .append("SELECT fldFilePath")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getMediaAlbum(int mediaId){
        return new StringBuilder()
                .append("SELECT fldFilePath")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getMediaYear(int mediaId){
        return new StringBuilder()
                .append("SELECT fldFilePath")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getMediaTrack(int mediaId){
        return new StringBuilder()
                .append("SELECT fldMediaTrack")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getMediaLength(int mediaId){
        return new StringBuilder()
                .append("SELECT fldTrackLength")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getMediaPath(int mediaId){
        return new StringBuilder()
                .append("SELECT fldFilePath")
                .append(" FROM tblMedia")
                .append(" WHERE fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getMediaArtistArtName(int mediaId){
        return new StringBuilder()
                .append("SELECT DISTINCT p.fldArtistName")
                .append(" FROM tblPerson p")
                .append(" JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID")
                .append(" WHERE mp.fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getMediaArtistFirstName(int mediaId){
        return new StringBuilder()
                .append("SELECT DISTINCT p.fldFirstName")
                .append(" FROM tblPerson p")
                .append(" JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID")
                .append(" WHERE mp.fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getMediaArtistLastName(int mediaId){
        return new StringBuilder()
                .append("SELECT DISTINCT p.fldLastName")
                .append(" FROM tblPerson p")
                .append(" JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID")
                .append(" WHERE mp.fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getAllArtists (){
        return "SELECT DISTINCT fldArtistName FROM tblPerson";
    }
    public String getMediaGenre(int mediaId){
        return new StringBuilder()
                .append("SELECT DISTINCT mg.fldGenre")
                .append(" FROM tblMedia m")
                .append(" JOIN tblMediaGenre mg ON m.fldMediaID = mg.fldMediaID")
                .append(" WHERE mg.fldMediaID = ")
                .append(mediaId)
                .toString();
    }
    public String getAllGenres (){
        return "SELECT * FROM tblGenre";
    }
}