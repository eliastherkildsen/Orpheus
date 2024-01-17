package mediaplayer.orpheus.model.Playlist;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Database.DatabaseUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Media {

    private int mediaID;
    private String mediaPath;
    private String mediaTitle;
    private String mediaArtist;
    private String mediaGenre;
    private String mediaYear;
    private int trackLength;
    private String imagePath = "src/main/resources/css/images/audio-lines.png";

    private static final Connection connection = OrpheusApp.jdbc.getConnection();

    public Media(int mediaID) throws SQLException {
        this.mediaID = mediaID;
        this.imagePath = imagePath;
        generateObject(mediaID);
    }

    private void generateObject(int mediaID) throws SQLException {

        this.mediaPath = generateMediaPath(mediaID);
        this.mediaTitle = generateMediaTitle(mediaID);
        this.mediaArtist = generateMediaArtist(mediaID);
        this.trackLength = generateMediaTrackLength(mediaID);
        this.mediaGenre = loadMediaGenre();
        this.mediaYear = loadMediaYear();

    }

    private int generateMediaTrackLength(int mediaID) throws SQLException {

        PreparedStatement psMediaTackLength;

        String query = DatabaseRead.getMediaLength(mediaID);
        psMediaTackLength = connection.prepareCall(query);

        try(ResultSet rsMediaTrackLength = psMediaTackLength.executeQuery()){
            while (rsMediaTrackLength.next()){
                return rsMediaTrackLength.getInt("fldTrackLength");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }

    private String generateMediaArtist(int mediaID) throws SQLException {

        PreparedStatement psMediaArtist;

        String query = DatabaseRead.getMediaArtistArtName(mediaID);
        psMediaArtist = connection.prepareCall(query);

        try(ResultSet rsMediaArtist = psMediaArtist.executeQuery()){
            while (rsMediaArtist.next()){
                return rsMediaArtist.getString("fldArtistName");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    private static String generateMediaPath(int mediaID) throws SQLException {

        PreparedStatement psMediaPath;

        String query = DatabaseRead.getMediaPath(mediaID);
        psMediaPath = connection.prepareCall(query);

        try(ResultSet rsMediaPath = psMediaPath.executeQuery()){
            while (rsMediaPath.next()){
                return rsMediaPath.getString("fldFilePath");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    private String generateMediaTitle(int mediaID) throws SQLException {

        String query = DatabaseRead.getMediaTitle(mediaID);
        PreparedStatement psMediaTitle = connection.prepareCall(query);

        try(ResultSet rsMediaTitle = psMediaTitle.executeQuery()){
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


    // endregion

}
