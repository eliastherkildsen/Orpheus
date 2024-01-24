package mediaplayer.orpheus.model.Media;

import java.util.ArrayList;
import java.util.Collections;

public class MediaShuffle {

    public static ArrayList<MediaObj> shufflePlaylist (ArrayList<MediaObj> playlist){

        System.out.println(playlist.size());

        Collections.shuffle(playlist);

        for (MediaObj obj : playlist){

        System.out.println("\nShuffled List : \n" + obj.getMediaTitle());
        }

        return playlist;
    }
}
