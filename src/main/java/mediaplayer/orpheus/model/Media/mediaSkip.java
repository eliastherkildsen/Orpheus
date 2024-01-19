package mediaplayer.orpheus.model.Media;

import javafx.util.Duration;

public class mediaSkip {

    private static final int SKIP_BY_SECONDS = 15;

    /**
     * Method for skipping media 15 seconds forward
     *
     * @param currentTrackTime
     * @param mediaLength
     * @return
     */
    public static int mediaSkipForward(double currentTrackTime, double mediaLength){

        if (currentTrackTime < mediaLength - SKIP_BY_SECONDS) {
            return (int) currentTrackTime + SKIP_BY_SECONDS;
        }

        return (int) mediaLength;
    }


    /**
     * Method for skipping media 15 seconds backward
     *
     * @param currentTrackTime
     * @param mediaLength
     * @return
     */
    public static int mediaSkipBackward(double currentTrackTime, double mediaLength){

        // checks if the current track time is greater than the first 14 seconds of the media
        if (currentTrackTime > 14) {
            return (int) currentTrackTime - SKIP_BY_SECONDS;
        }

        // if the current track time is less than 15 seconds (less than the skip backward time)
        return (int) Duration.ZERO.toSeconds();
    }

}
