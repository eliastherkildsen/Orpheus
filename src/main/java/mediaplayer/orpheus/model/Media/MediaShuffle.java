package mediaplayer.orpheus.model.Media;

import mediaplayer.orpheus.Controler.HomeViewController;

import java.util.ArrayList;
import java.util.Collections;

public class MediaShuffle {

    public static ArrayList<MediaObj> shufflePlaylist (ArrayList<MediaObj> playlist){

        Collections.shuffle(playlist);
        System.out.println("\nShuffled List : \n" + playlist);


        return playlist;
    }
}
