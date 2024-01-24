package mediaplayer.orpheus.model.Media;

public class MediaUtil {

    /**
     * Method for converting a duration time in seconds to the HH:MM:SS format
     *
     * @param durationTime time in seconds.
     * @return String
     */
    public static String secondsFormattedToTime(double durationTime) {
        int hours = (int) durationTime / 3600;
        int secondsLeft = (int) durationTime % 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;

        // formats the time into HH:MM:SS
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
