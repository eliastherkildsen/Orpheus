package mediaplayer.orpheus.model.metadata;

public class Metadata {
    private String filePath;
    private String mediaTitle;
    private String fileType;
    private String album;
    private String artist;
    int mediaYear;
    int mediaTrack;
    int trackLength;


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

    public int getMediaYear() {
        return mediaYear;
    }

    public void setMediaYear(int mediaYear) {
        this.mediaYear = mediaYear;
    }

    public int getMediaTrack() {
        return mediaTrack;
    }

    public void setMediaTrack(int mediaTrack) {
        this.mediaTrack = mediaTrack;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(int trackLength) {
        this.trackLength = trackLength;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
