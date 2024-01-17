package mediaplayer.orpheus.model.Playlist;

public class Media {

    private String mediaPath;
    private String mediaTitle;
    private String mediaArtist;
    private int trackLength;
    private String imagePath;
    private String logoPath = "src/main/resources/css/images/audio-lines.png";

    public Media(String mediaPath, String mediaTitle, String mediaArtist, int trackLength, String imagePath) {
        this.mediaPath = mediaPath;
        this.mediaTitle = mediaTitle;
        this.mediaArtist = mediaArtist;
        this.trackLength = trackLength;
        this.imagePath = imagePath;
    }

    public Media(String mediaPath, String mediaTitle, String mediaArtist, int trackLength){

        this.mediaPath = mediaPath;
        this.mediaTitle = mediaTitle;
        this.mediaArtist = mediaArtist;
        this.trackLength = trackLength;
        this.imagePath = logoPath;

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

    public String getLogoPath() {
        return logoPath;
    }
}
