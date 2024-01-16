package mediaplayer.orpheus.model.Media;

public class Media {

    private final String MEDIA_PATH;
    private String MEDIA_IMG_PATH = "file:src/main/resources/css/images/audio-lines.png";
    private int TRACK_ORDER;
    private final String MEDIA_TITEL;




    // overloaded constructor(s)
    public Media(String path, String mediaImgPath, int trackOrder, String mediaTitel) {
        MEDIA_PATH = path;
        MEDIA_IMG_PATH = mediaImgPath;
        TRACK_ORDER = trackOrder;
        MEDIA_TITEL = mediaTitel;
    }
    public Media(String path, int trackOrder, String mediaTitel) {
        MEDIA_PATH = path;
        TRACK_ORDER = trackOrder;
        MEDIA_TITEL = mediaTitel;
    }

    public Media(String path, String mediaTitel, String mediaImgPath) {
        MEDIA_PATH = path;
        MEDIA_TITEL = mediaTitel;
        MEDIA_IMG_PATH = mediaImgPath;
    }

    public Media(String path, String mediaTitel) {
        MEDIA_PATH = path;
        MEDIA_TITEL = mediaTitel;
    }

    // end of constructor(s)

    public String getMEDIA_PATH() {
        return MEDIA_PATH;
    }

    public String getMEDIA_IMG_PATH() {
        return MEDIA_IMG_PATH;
    }

    public int getTRACK_ORDER() {
        return TRACK_ORDER;
    }

    public String getMEDIA_TITEL() {
        return MEDIA_TITEL;
    }
}
