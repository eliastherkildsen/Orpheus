package mediaplayer.orpheus.model.MediaSearch;

import java.util.ArrayList;

public class MediaSearchUtil {

    /**
     * Method for parsing the position of the selected item from a string to an integer.
     * and the retrieving the path for the String at the index og the integer retrieved.
     * @return String filepath to selected media.
     */
    public static String parseLWSearchResult(String mediaPicked, ArrayList<String[]> dataSet) {

        // the returned value from getSelectedIndices() is a text, therefor we need to parse it to an int, and exclude
        // the start char '[' and the last char ']'
        String mediaIndex = mediaPicked;
        mediaIndex = mediaIndex.substring(1,mediaIndex.length()-1);

        int mediaIndexFormatted = Integer.parseInt(mediaIndex);

        // gets the String array from the arraylist,
        String[] temp = dataSet.get(mediaIndexFormatted);

        // returns the String at index 5, witch referees to the index where the filePath exists.
        return temp[5];
    }

    /**
     * Method for creating a formatted string output for a quarry result.
     * @param dataset String array.
     * @return String.
     */
    public static String formatedQuarryResultString(String[] dataset){
        // initializing StringBuilder
        StringBuilder sb = new StringBuilder();

        /*
        dataset[0] = fldArtistName
        dataset[1] = fldFirstName
        dataset[2] = fldLastName
        dataset[3] = fldMediaTitle
        dataset[6] = fldTrackLength
        dataset[7] = fldGenre
         */

        sb.append(formatString(dataset[3], true));
        sb.append(formatString(dataset[1]));
        sb.append(formatString(dataset[2]));
        sb.append(formatString(dataset[6]));
        sb.append(formatString(dataset[7]));

        return sb.toString();
    }

    /**
     * method for checking if a string contains "NULL" or is null
     * @param string string to format.
     * @return boolean
     */
    private static boolean checkNull(String string){
        if (string == null){
            return false;
        } else return !string.toUpperCase().equals("NULL");
    }

    /**
     * method for returning a formated string, with null check.
     * @param data field contents
     * @return String formatted string of field contents
     */
    private static String formatString(String data) {
        String returnString = "";
        if (checkNull(data)) {
            returnString = String.format(" - %s", data);

        }

        return returnString;
    }

    /**
     * method for returning a formated string, with null check. overloaded from
     * formatString(String data), to make able to remove prefix " - "
     * @param data String field contents
     * @param prefix boolean, boolean value ignored.
     * @return String formatted string of field contents
     */
    private static String formatString(String data, boolean prefix) {
        String returnString = "";
        if (checkNull(data)) {
            return data;
        }

        return returnString;
    }

}
