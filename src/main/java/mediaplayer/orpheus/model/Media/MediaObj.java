package mediaplayer.orpheus.model.Media;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Database.DatabaseUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaObj {

    private int mediaID;
    private String mediaPath;
    private String mediaTitle;
    private String presentetedMedia;
    private String mediaArtist;
    private String mediaGenre;
    private String mediaYear;
    private int trackLength;
    private String imagePath = "src/main/resources/css/images/audio-lines.png";


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

        this.presentetedMedia = String.format("[MEDIA] - " + mediaTitle);


    }

    /**
     *  Method for getting the image path from the database
     * @return Returns the image path
     */
    private String generateImagePath() {

        String query = DatabaseRead.getMediaImagePathFromMediaID(mediaID);

        try(ResultSet rsMediaImagePath = JDBC.instance.executeQuary(query)){
            while (rsMediaImagePath.next()){
                return rsMediaImagePath.getString("fldImagePath");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;

    }

    /**
     * Method for getting Track length from the database
     * @return Returns the track length
     */
    private int generateMediaTrackLength() {

        String query = DatabaseRead.getMediaLength(mediaID);

        try(ResultSet rsMediaTrackLength = JDBC.instance.executeQuary(query)){
            while (rsMediaTrackLength.next()){
                return rsMediaTrackLength.getInt("fldTrackLength");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * Method for getting the artist name from the database
     * @return Returns the artist name
     */
    private String generateMediaArtist(){

        String query = DatabaseRead.getMediaArtistArtName(mediaID);

        try(ResultSet rsMediaArtist = JDBC.instance.executeQuary(query)){
            while (rsMediaArtist.next()){
                return rsMediaArtist.getString("fldArtistName");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Method for getting the file path from the database
     * @return Returns the file path
     */
    private String generateMediaPath() {

        String query = DatabaseRead.getMediaPath(mediaID);

        try(ResultSet rsMediaPath = JDBC.instance.executeQuary(query)){
            while (rsMediaPath.next()){
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

        String query = DatabaseRead.getMediaTitle(mediaID);

        try(ResultSet rsMediaTitle = JDBC.instance.executeQuary(query)){
            while (rsMediaTitle.next()){
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

        String quary = DatabaseRead.getMediaGenre(mediaID);

        try (ResultSet resultSet = JDBC.instance.executeQuary(quary)) {
            while (resultSet.next()) {
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

        String quary = DatabaseRead.getMediaYear(mediaID);

        try (ResultSet resultSet = JDBC.instance.executeQuary(quary)) {
            while (resultSet.next()) {
                return resultSet.getString("fldMediaYear");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    /**
     * Method for updating the media title in the database
     */
    private void updateMediaTitle(){
        String quary = DatabaseUpdate.setMediaTitle(this.mediaTitle, mediaID);
        JDBC.instance.executeUpdate(quary);
    }

    /**
     * Method for updating the artist name in the database
     */
    private void updateArtistName(){

        // get person id from person name.
        String fldPersonID = getPersonIDFromPersonName();

        String quary = DatabaseUpdate.setMediaArtist(fldPersonID, mediaID);
        JDBC.instance.executeUpdate(quary);
    }

    /**
     * Method for updating the media genre in the database
     */
    private void updateMediaGenre(){
        String quary = DatabaseUpdate.setMediaGenre(this.mediaGenre, mediaID);
        JDBC.instance.executeUpdate(quary);
    }

    /**
     * Method for updating the media year in the database
     */
    private void updateMediaYear(){
        String quary = DatabaseUpdate.setMediaYear(this.mediaYear, mediaID);
        JDBC.instance.executeUpdate(quary);
    }

    /**
     * Method for updating the image path in the database
     */
    private void updateMediaImagePath(){
        String query = DatabaseUpdate.setMediaImagePath(this.imagePath, mediaID);
        JDBC.instance.executeUpdate(query);
    }

    // region / getter & setter

    /**
     * Method for getting PersonID from the database using the artist name
     * @return Returns the PersonID
     */
    private String getPersonIDFromPersonName(){
        String quary = DatabaseRead.getMediaArtistIDFromName(this.mediaArtist);

        try (ResultSet resultSet = JDBC.instance.executeQuary(quary)) {
            while (resultSet.next()) {
                return resultSet.getString("fldPersonID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    /**
     * Method for getting the objects file path
     * @return Returns the file path
     */
    public String getMediaPath() {
        return mediaPath;
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
    public String getMediaArtist() {
        return mediaArtist;
    }

    /**
     * Method for getting the objects track length
     * @return Returns the track length
     */
    public int getTrackLength() {
        return trackLength;
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
    public int getMediaID() {
        return mediaID;
    }

    /**
     * Method for setting the objects media title
     */
    public void setMediaTitle(String mediaTitel) {
        this.mediaTitle = mediaTitel;
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
        this.mediaArtist = artistName;
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

    public String getPresentetedMedia() {
        return presentetedMedia;
    }


    // endregion

}
