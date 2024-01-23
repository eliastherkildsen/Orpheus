package mediaplayer.orpheus.model.Media;

import mediaplayer.orpheus.model.Playlist.PlaylistObj;

public class GeneralMediaObject {

    private MediaObj mediaObj = null;
    private PlaylistObj playlistObj = null;

    public GeneralMediaObject(MediaObj mediaObj){
        this.mediaObj = mediaObj;
    }


    public GeneralMediaObject(PlaylistObj playlistObj){
        this.playlistObj = playlistObj;
    }

    public MediaObj getMediaObj(){
        return this.mediaObj;
    }

    public PlaylistObj getPlaylistObj(){
        return this.playlistObj;
    }

}
