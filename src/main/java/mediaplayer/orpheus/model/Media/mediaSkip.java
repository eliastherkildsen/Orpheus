package mediaplayer.orpheus.model.Media;

import javafx.util.Duration;

public class mediaSkip {

    private static final int SKIP_BY_SECONDS = 15;

    private static int mediaSkipForward(double currentTrackTime, double mediaLen) {

        if (currentTrackTime < mediaLen - SKIP_BY_SECONDS) {
            return (int) currentTrackTime + SKIP_BY_SECONDS;
        }

        return (int) mediaLen;

    }



}
