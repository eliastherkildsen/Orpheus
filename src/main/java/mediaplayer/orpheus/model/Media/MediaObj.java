package mediaplayer.orpheus.model.Media;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Database.DatabaseUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaObj {

    private final int MEDIA_ID;
    private final String MEDIA_PATH;
    private String mediaTitle;
    private final String PRESENTED_MEDIA_TITLE;
    private String mediaArtistStageName;
    private String mediaGenre;
    private String mediaYear;
    private String imagePath;

    public MediaObj(int mediaID) throws SQLException {

        this.MEDIA_ID = mediaID;
        this.MEDIA_PATH = generateMediaPath();
        this.mediaTitle = generateMediaTitle();
        this.mediaArtistStageName = generateMediaArtist();
        this.imagePath = generateImagePath();
        this.mediaGenre = loadMediaGenre();
        this.mediaYear = loadMediaYear();

        this.PRESENTED_MEDIA_TITLE = String.format("[%s] - %s", getMediaType(), mediaTitle);

    }


    /**
     *  Method for getting the image path from the database
     * @return Returns the image path
     */
    private String generateImagePath() {

        try(ResultSet rsMediaImagePath = JDBC.instance.executeQuary(DatabaseRead.getMediaImagePathFromMediaID(MEDIA_ID))){

            if (rsMediaImagePath.next()){
                return rsMediaImagePath.getString("fldImagePath");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return "src/main/resources/css/images/audio-lines.png";
    }


    /**
     * Method for getting the artist name from the database
     * @return Returns the artist name
     */
    private String generateMediaArtist(){

        try(ResultSet rsMediaArtist = JDBC.instance.executeQuary(DatabaseRead.getMediaArtistArtName(MEDIA_ID))){
            if (rsMediaArtist.next()){
                return rsMediaArtist.getString("fldArtistName");
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return "Unknown";
    }

    /**
     * Method for getting the file path from the database
     * @return Returns the file path
     */
    private String generateMediaPath() {

        try(ResultSet rsMediaPath = JDBC.instance.executeQuary(DatabaseRead.getMediaPath(MEDIA_ID))){
            if (rsMediaPath.next()){
                return rsMediaPath.getString("fldFilePath");
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Method for getting the media title from the database
     * @return Returns the title
     */
    private String generateMediaTitle()  {

        try(ResultSet rsMediaTitle = JDBC.instance.executeQuary(DatabaseRead.getMediaTitle(MEDIA_ID))){
            if (rsMediaTitle.next()){
                return rsMediaTitle.getString("fldMediaTitle");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Method for getting the medias genre from the database
     * @return Returns the genre
     */
    private String loadMediaGenre(){

        try (ResultSet resultSet = JDBC.instance.executeQuary(DatabaseRead.getMediaGenre(MEDIA_ID))) {
            if (resultSet.next()){
                return resultSet.getString("fldGenre");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Method for getting the media year from the database
     * @return Returns the year
     */
    private String loadMediaYear(){

        try (ResultSet resultSet = JDBC.instance.executeQuary(DatabaseRead.getMediaYear(MEDIA_ID))) {
            if (resultSet.next()){
                return resultSet.getString("fldMediaYear");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**
     * Method for updating the media title in the database
     */
    private void updateMediaTitle(){
        JDBC.instance.executeUpdate(DatabaseUpdate.setMediaTitle(this.mediaTitle, MEDIA_ID));
    }

    /**
     * Method for updating the artist name in the database
     */
    private void updateArtistName(){

        // get person id from person name.
        String fldPersonID = getPersonIDFromPersonName();

        JDBC.instance.executeUpdate(DatabaseUpdate.setMediaArtist(fldPersonID, MEDIA_ID));
    }

    /**
     * Method for updating the media genre in the database
     */
    private void updateMediaGenre(){
        JDBC.instance.executeUpdate(DatabaseUpdate.setMediaGenre(this.mediaGenre, MEDIA_ID));
    }

    /**
     * Method for updating the media year in the database
     */
    private void updateMediaYear(){
        JDBC.instance.executeUpdate(DatabaseUpdate.setMediaYear(this.mediaYear, MEDIA_ID));
    }

    /**
     * Method for updating the image path in the database
     */
    private void updateMediaImagePath(){
        JDBC.instance.executeUpdate(DatabaseUpdate.setMediaImagePath(this.imagePath, MEDIA_ID));
    }

    // region / getter & setter

    /**
     * Method for getting PersonID from the database using the artist name
     * @return Returns the PersonID
     */
    private String getPersonIDFromPersonName(){

        try (ResultSet resultSet = JDBC.instance.executeQuary(DatabaseRead.getMediaArtistIDFromName(this.mediaArtistStageName))) {
            if (resultSet.next()){
                return resultSet.getString("fldPersonID");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Method for getting PersonID from the database using the artist name
     * @return Returns the PersonID
     */
    private String getMediaType(){

        try (ResultSet resultSet = JDBC.instance.executeQuary(DatabaseRead.getMediaExtensionFromMediaID(this.MEDIA_ID))) {
            if (resultSet.next()){
                return resultSet.getString("fldFileType");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }



    /**
     * Method for getting the objects file path
     * @return Returns the file path
     */
    public String getMediaPath() {
        return MEDIA_PATH;
    }

    /**
     * Method for getting the objects title
     * @return Returns the title
     */
    public String getMediaTitle() {
        return mediaTitle;
    }

    /**
     * Method for getting the objects artist name
     * @return Returns the artist name
     */
    public String getMediaArtistStageName() {
        return mediaArtistStageName;
    }


    /**
     * Method for getting the objects image path
     * @return Returns the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Method for getting the objects media year
     * @return Returns the media year
     */
    public String getMediaYear() {
        return mediaYear;
    }

    /**
     * Method for getting the objects media genre
     * @return Returns the media genre
     */
    public String getMediaGenre() {
        return mediaGenre;
    }

    /**
     * Method for getting the objects MediaID
     * @return Returns the MediaID
     */
    public int getMEDIA_ID() {
        return MEDIA_ID;
    }

    /**
     * Method for setting the objects media title
     */
    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
        updateMediaTitle();

    }

    /**
     * Method for setting the objects media genre
     */
    public void setMediaGenre(String mediaGenre) {
        this.mediaGenre = mediaGenre;
        updateMediaGenre();

    }

    /**
     * Method for setting the objects artist name
     */
    public void setMediaArtistName(String artistName) {
        this.mediaArtistStageName = artistName;
        updateArtistName();
    }

    /**
     * Method for setting the objects media year
     */
    public void setMediaYear(String mediaYear) {
        this.mediaYear = mediaYear;
        updateMediaYear();

    }

    /**
     * Method for setting the objects image path
     */
    public void setMediaImagePath(String imagePath) {
        this.imagePath = imagePath;
        updateMediaImagePath();
    }

    public String getPRESENTED_MEDIA_TITLE() {
        return PRESENTED_MEDIA_TITLE;
    }


    // endregion

}
