package mediaplayer.orpheus.model.Playlist;

public class PlaylistObj {

    private final String PLAYLIST_NAME;
    private final String PRESENTED_PLAYLIST_NAME;

    public PlaylistObj(String playlistName){

        this.PLAYLIST_NAME = playlistName;
        this.PRESENTED_PLAYLIST_NAME = "[Playlist] - " + this.PLAYLIST_NAME;

    }

    public String getPLAYLIST_NAME() {
        return PLAYLIST_NAME;
    }

    public String getPRESENTED_PLAYLIST_NAME() {
        return PRESENTED_PLAYLIST_NAME;
    }
}
