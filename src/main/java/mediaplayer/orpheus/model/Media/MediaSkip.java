package mediaplayer.orpheus.model.Media;

import javafx.util.Duration;

public class MediaSkip {

    private static final int SKIP_BY_SECONDS = 15;

    /**
     * Method for skipping media 15 seconds forward
     *
     * @param currentTrackTime of the media
     * @param mediaLength the length of the media.
     * @return media time after skip forward.
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
     * @param currentTrackTime of the media.
     * @return track time after skip back.
     */
    public static int mediaSkipBackward(double currentTrackTime){

        if (currentTrackTime < SKIP_BY_SECONDS) {
            return (int) Duration.ZERO.toSeconds();
        }

        // if the current track time is greater than the first 14 seconds of the media
        return (int) currentTrackTime - SKIP_BY_SECONDS;
    }

}
