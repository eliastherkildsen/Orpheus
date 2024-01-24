package mediaplayer.orpheus.model.Database;

import javafx.beans.property.MapProperty;

import java.nio.MappedByteBuffer;

public class DatabaseRead {
    public static String getMediaIdFromTitle(String mediaTitle){
        return "SELECT fldMediaID" +
                " FROM tblMedia" +
                " WHERE fldMediaTitle = '" +
                mediaTitle +
                "'";
    }
    public static String getMediaTitle(int mediaId) {
        return "SELECT fldMediaTitle" +
                " FROM tblMedia" +
                " WHERE fldMediaID = " +
                mediaId;
    }
    public static String getMediaType(int mediaId){
        return "SELECT fldFileType" +
                " FROM tblMedia" +
                " WHERE fldMediaID = " +
                mediaId;
    }
    public static String getMediaAlbum(int mediaId){
        return "SELECT fldFilePath" +
                " FROM tblMedia" +
                " WHERE fldMediaID = " +
                mediaId;
    }
    public static String getMediaYear(int mediaId){
        return "SELECT fldMediaYear" +
                " FROM tblMedia" +
                " WHERE fldMediaID = " +
                mediaId;
    }
    public static String getMediaTrack(int mediaId){
        return "SELECT fldMediaTrack" +
                " FROM tblMedia" +
                " WHERE fldMediaID = " +
                mediaId;
    }
    public static String getMediaLength(int mediaId){
        return "SELECT fldTrackLength" +
                " FROM tblMedia" +
                " WHERE fldMediaID = " +
                mediaId;
    }
    public static String getMediaPath(int mediaId){
        return "SELECT fldFilePath" +
                " FROM tblMedia" +
                " WHERE fldMediaID = " +
                mediaId;
    }
    public static String getMediaArtistArtName(int mediaId){
        return "SELECT DISTINCT p.fldArtistName" +
                " FROM tblPerson p" +
                " JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID" +
                " WHERE mp.fldMediaID = " +
                mediaId;
    }
    public static String getMediaArtistFirstName(int mediaId){
        return "SELECT DISTINCT p.fldFirstName" +
                " FROM tblPerson p" +
                " JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID" +
                " WHERE mp.fldMediaID = " +
                mediaId;
    }
    public static String getMediaArtistLastName(int mediaId){
        return "SELECT DISTINCT p.fldLastName" +
                " FROM tblPerson p" +
                " JOIN tblMediaPerson mp ON p.fldPersonID = mp.fldPersonID" +
                " WHERE mp.fldMediaID = " +
                mediaId;
    }

    public static String getAllArtists (){
        return "SELECT DISTINCT fldArtistName FROM tblPerson";
    }
    public static String getMediaGenre(int mediaId){

        return "SELECT DISTINCT mg.fldGenre" +
                " FROM tblMedia m" +
                " JOIN tblMediaGenre mg ON m.fldMediaID = mg.fldMediaID" +
                " WHERE mg.fldMediaID = " +
                mediaId;

    }
    public static String getAllGenres (){
        return "SELECT * FROM tblGenre";
    }

    public static String getAllPlaylistNames() {return "SELECT fldPlaylistName FROM tblPlaylist";}

    public static String getMaxTrackOrder(String playlistName){
        return "SELECT fldTrackOrder FROM tblMediaPlaylist WHERE fldTrackOrder = " +
                "(SELECT MAX(fldTrackOrder) FROM tblMediaPlaylist WHERE fldPlaylistName = '" +
                playlistName +
                "')";
    }
    public static String getMediaArtistIDFromName(String mediaArtistName) {

        return "SELECT fldPersonID FROM tblPerson WHERE fldArtistName = '" +
                mediaArtistName +
                "'";

    }
    public static String getMediaIDFromPlaylistName(String playlistName){

        return "SELECT fldMediaID FROM tblMediaPlaylist WHERE fldPlaylistName = '" +
                playlistName +
                "'";

    }
    public static String getMediaImagePathFromMediaID(int mediaID){

        return "SELECT fldImagePath FROM tblMedia WHERE fldMediaID = '" +
                mediaID +
                "'";

    }

    public static String getMediaExstentionFromMediaID(int mediaID){

        return "SELECT fldFileType FROM tblMedia WHERE fldMediaID = '" +
                mediaID +
                "'";

    }



}