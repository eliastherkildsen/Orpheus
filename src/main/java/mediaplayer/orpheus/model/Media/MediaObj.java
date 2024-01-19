package mediaplayer.orpheus.model.Media;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Database.DatabaseUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaObj {

    private int mediaID;
    private String mediaPath;
    private String mediaTitle;
    private String mediaArtist;
    private String mediaGenre;
    private String mediaYear;
    private int trackLength;
    private String imagePath = "src/main/resources/css/images/audio-lines.png";

    private static final Connection connection = OrpheusApp.jdbc.getConnection();

    public MediaObj(int mediaID) throws SQLException {
        this.mediaID = mediaID;
        generateObject();
    }

    private void generateObject() {

        this.mediaPath = generateMediaPath();
        this.mediaTitle = generateMediaTitle();
        this.mediaArtist = generateMediaArtist();
        this.trackLength = generateMediaTrackLength();
        this.imagePath = generateImagePath();
        this.mediaGenre = loadMediaGenre();
        this.mediaYear = loadMediaYear();

    }

    private String generateImagePath() {

        String query = DatabaseRead.getMediaImagePathFromMediaID(mediaID);

        try(ResultSet rsMediaImagePath = OrpheusApp.jdbc.executeQuary(query)){
            while (rsMediaImagePath.next()){
                return rsMediaImagePath.getString("fldImagePath");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;

    }

    private int generateMediaTrackLength() {

        String query = DatabaseRead.getMediaLength(mediaID);

        try(ResultSet rsMediaTrackLength = OrpheusApp.jdbc.executeQuary(query)){
            while (rsMediaTrackLength.next()){
                return rsMediaTrackLength.getInt("fldTrackLength");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }

    private String generateMediaArtist(){

        String query = DatabaseRead.getMediaArtistArtName(mediaID);

        try(ResultSet rsMediaArtist = OrpheusApp.jdbc.executeQuary(query)){
            while (rsMediaArtist.next()){
                return rsMediaArtist.getString("fldArtistName");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    private String generateMediaPath() {

        String query = DatabaseRead.getMediaPath(mediaID);

        try(ResultSet rsMediaPath = OrpheusApp.jdbc.executeQuary(query)){
            while (rsMediaPath.next()){
                return rsMediaPath.getString("fldFilePath");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    private String generateMediaTitle()  {

        String query = DatabaseRead.getMediaTitle(mediaID);

        try(ResultSet rsMediaTitle = OrpheusApp.jdbc.executeQuary(query)){
            while (rsMediaTitle.next()){
                return rsMediaTitle.getString("fldMediaTitle");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    private String loadMediaGenre(){

        String quary = DatabaseRead.getMediaGenre(mediaID);

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                return resultSet.getString("fldGenre");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private String loadArtistName(){

        String quary = DatabaseRead.getMediaArtistArtName(mediaID);

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                return resultSet.getString("fldArtistName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private String loadMediaYear(){

        String quary = DatabaseRead.getMediaYear(mediaID);

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                return resultSet.getString("fldMediaYear");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }


    private void updateMediaTitle(){
        String quary = DatabaseUpdate.setMediaTitle(this.mediaTitle, mediaID);
        OrpheusApp.jdbc.executeUpdate(quary);
    }

    private void updateArtistName(){

        // get person id from person name.
        String fldPersonID = getPersonIDFromPersonName();

        String quary = DatabaseUpdate.setMediaArtist(fldPersonID, mediaID);
        OrpheusApp.jdbc.executeUpdate(quary);
    }

    private void updateMediaGenre(){
        String quary = DatabaseUpdate.setMediaGenre(this.mediaGenre, mediaID);
        OrpheusApp.jdbc.executeUpdate(quary);
    }

    private void updateMediaYear(){
        String quary = DatabaseUpdate.setMediaYear(this.mediaYear, mediaID);
        OrpheusApp.jdbc.executeUpdate(quary);
    }

    private void updateMediaImagePath(){
        String query = DatabaseUpdate.setMediaImagePath(this.imagePath, mediaID);
        OrpheusApp.jdbc.executeUpdate(query);
    }

    // region / getter & setter

    private String getPersonIDFromPersonName(){
        String quary = DatabaseRead.getMediaArtistIDFromName(this.mediaArtist);

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                return resultSet.getString("fldPersonID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public String getMediaArtist() {
        return mediaArtist;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public String getImagePath() {
        return imagePath;
    }
    public String getMediaYear() {
        return mediaYear;
    }

    public String getMediaGenre() {
        return mediaGenre;
    }

    public int getMediaID() {
        return mediaID;
    }

    public void setMediaTitle(String mediaTitel) {
        this.mediaTitle = mediaTitel;
        updateMediaTitle();

    }

    public void setMediaGenre(String mediaGenre) {
        this.mediaGenre = mediaGenre;
        updateMediaGenre();

    }

    public void setMediaArtistName(String artistName) {
        this.mediaArtist = artistName;
        updateArtistName();
    }

    public void setMediaYear(String mediaYear) {
        this.mediaYear = mediaYear;
        updateMediaYear();

    }

    public void setMediaImagePath(String imagePath) {
        this.imagePath = imagePath;
        updateMediaImagePath();
    }


    // endregion

}
