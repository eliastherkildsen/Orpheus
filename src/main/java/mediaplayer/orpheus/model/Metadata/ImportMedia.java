package mediaplayer.orpheus.model.Metadata;

public class ImportMedia {
    private String filePath;
    private String mediaTitle;
    private String fileType;
    private String album;
    private String artist;
    private Integer mediaYear;
    private Integer mediaTrack;
    private Integer trackLength;

    public ImportMedia(String filePath, String mediaTitle, String fileType, String album, String artist, Integer mediaYear, Integer mediaTrack, Integer trackLength) {
        this.filePath = filePath;
        this.mediaTitle = mediaTitle;
        this.fileType = fileType;
        this.album = album;
        this.artist = artist;
        this.mediaYear = mediaYear;
        this.mediaTrack = mediaTrack;
        this.trackLength = trackLength;
    }

    public ImportMedia(String filePath, String fileType, int trackLength) {
        this.filePath = filePath;
        this.mediaTitle = null;
        this.fileType = fileType;
        this.album = null;
        this.artist = null;
        this.mediaYear = null;
        this.mediaTrack = null;
        this.trackLength = trackLength;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getMediaYear() {
        return mediaYear;
    }

    public void setMediaYear(Integer mediaYear) {
        this.mediaYear = mediaYear;
    }

    public Integer getMediaTrack() {
        return mediaTrack;
    }

    public void setMediaTrack(Integer mediaTrack) {
        this.mediaTrack = mediaTrack;
    }

    public Integer getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(Integer trackLength) {
        this.trackLength = trackLength;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
