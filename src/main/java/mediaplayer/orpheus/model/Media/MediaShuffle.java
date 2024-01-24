package mediaplayer.orpheus.model.Media;

import java.util.ArrayList;
import java.util.Collections;

public class MediaShuffle {

    /**
     * Method for shuffle the chosen playlist.
     * This method takes a chosen playlist, shuffles its order, and returns the shuffled playlist.
     *
     * @param playlist the chosen playlist to be shuffled
     * @return the shuffled playlist
     */
    public static ArrayList<MediaObj> shufflePlaylist (ArrayList<MediaObj> playlist){
        // checks the size of the playlist
        //System.out.println(playlist.size());

        // shuffles the playlist
        Collections.shuffle(playlist);

        // prints the shuffled playlist
        /*for (MediaObj obj : playlist){
        System.out.println("\nShuffled List : \n" + obj.getMediaTitle());
        }*/

        return playlist;
    }
}
