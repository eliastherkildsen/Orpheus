package mediaplayer.orpheus.model.Playlist;

public class PlaylistObj {

    private String playlistName;
    private String prestenedPlaylist;

    public PlaylistObj(String playlistName){

        this.playlistName = playlistName;
        this.prestenedPlaylist = "[Playlist] - " + this.playlistName;

    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getPrestenedPlaylist() {
        return prestenedPlaylist;
    }
}
