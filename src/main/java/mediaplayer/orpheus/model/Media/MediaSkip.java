package mediaplayer.orpheus.model.Media;

import javafx.util.Duration;

public class MediaSkip {

    private static final int SKIP_BY_SECONDS = 15;

    /**
     * Method for skipping media 15 seconds forward.
     *
     * This method takes the current track time of the media and the total length of the media, and returns
     * the media time after skipping forward by 15 seconds. If the current track time is within the last 15 seconds
     * of the media, the method returns the total media length.
     *
     * @param currentTrackTime of the media
     * @param mediaLength the length of the media
     * @return media time after skip forward
     */
    public static int mediaSkipForward(double currentTrackTime, double mediaLength){

        // if negative currentTrackTime || negative mediaLength
        if (currentTrackTime < 0 || mediaLength < 0) {
            currentTrackTime = 0;
            mediaLength = 0;
        }


        if (currentTrackTime < mediaLength - SKIP_BY_SECONDS) {
            return (int) currentTrackTime + SKIP_BY_SECONDS;
        }

        return (int) mediaLength;
    }


    /**
     * Method for skipping media 15 seconds backward.
     *
     * his method takes the current track time of the media, and returns
     * the media time after skipping backward by 15 seconds. If the current track time is within the first 15 seconds
     * of the media, the method returns the starting time of the media (0 seconds).
     *
     * @param currentTrackTime of the media
     * @return track time after skip back
     */
    public static int mediaSkipBackward(double currentTrackTime){

        // if negative currentTrackTime || negative mediaLength
        if (currentTrackTime < 0) {
            currentTrackTime = 0;
        }

        if (currentTrackTime < SKIP_BY_SECONDS) {
            return (int) Duration.ZERO.toSeconds();
        }

        // if the current track time is greater than the first 14 seconds of the media
        return (int) currentTrackTime - SKIP_BY_SECONDS;
    }

}
