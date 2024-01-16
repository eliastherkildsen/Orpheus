package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Database.DatabaseUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaEdit {
    private final int MEDIA_ID;
    private String mediaTitle;
    private String mediaGenre;
    private String mediaArtistName;
    private String mediaYear;

    public MediaEdit(int mediaId) {
        this.MEDIA_ID = mediaId;
        this.mediaTitle = loadMediaTitle();
        this.mediaGenre = loadMediaGenre();
        this.mediaArtistName = loadArtistName();
        this.mediaYear = loadYear();
    }

    private String loadMediaTitle(){

        String quary = DatabaseRead.getMediaTitle(MEDIA_ID);

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                return resultSet.getString("fldMediaTitle");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private String loadMediaGenre(){

        String quary = DatabaseRead.getMediaGenre(MEDIA_ID);

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

        String quary = DatabaseRead.getMediaArtistArtName(MEDIA_ID);

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                return resultSet.getString("fldArtistName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private String loadYear(){

        String quary = DatabaseRead.getMediaYear(MEDIA_ID);

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
        String quary = DatabaseUpdate.setMediaTitle(this.mediaTitle, MEDIA_ID);
        OrpheusApp.jdbc.executeUpdate(quary);
    }

    private void updateArtistName(){

        // get person id from person name.
        String fldPersonID = getPersonIDFromPersonName();

        String quary = DatabaseUpdate.setMediaArtist(fldPersonID, MEDIA_ID);
        OrpheusApp.jdbc.executeUpdate(quary);
    }

    private void updateMediaGenre(){
        String quary = DatabaseUpdate.setMediaGenre(this.mediaGenre, MEDIA_ID);
        OrpheusApp.jdbc.executeUpdate(quary);
    }

    private void updateMediaYear(){
        String quary = DatabaseUpdate.setMediaYear(this.mediaYear, MEDIA_ID);
        OrpheusApp.jdbc.executeUpdate(quary);
    }


    public String getMediaYear() {
        return mediaYear;
    }

    public int getMEDIA_ID() {
        return MEDIA_ID;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public String getMediaGenre() {
        return mediaGenre;
    }

    public String getMediaArtistName() {
        return mediaArtistName;
    }

    public void setMediaTitle(String mediaTitel) {
        this.mediaTitle = mediaTitel;
        updateMediaTitle();
        System.out.println("updated mediaTitel");

    }

    public void setMediaGenre(String mediaGenre) {
        this.mediaGenre = mediaGenre;
        updateMediaGenre();
        System.out.println("updated media Genre");

    }

    public void setMediaArtistName(String artistName) {
        this.mediaArtistName = artistName;
        updateArtistName();
        System.out.println("updated artistname");
    }

    public void setMediaYear(String mediaYear) {
        this.mediaYear = mediaYear;
        updateMediaYear();
        System.out.println("updated mediaYear");

    }

    private String getPersonIDFromPersonName(){
        String quary = DatabaseRead.getMediaArtistIDFromName(this.mediaArtistName);

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                return resultSet.getString("fldPersonID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
