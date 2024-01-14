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

        // appending to the presented String.
        sb.append(formatString(dataset[3], false));
        sb.append(formatString(dataset[1], true ));
        sb.append(formatString(dataset[2], true ));
        sb.append(formatString(dataset[6], true ));
        sb.append(formatString(dataset[7], true ));

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
        } else return !string.equalsIgnoreCase("NULL");
    }

    /**
     * method for returning a formatted string, with null check.
     * @param data String field contents
     * @param prefix boolean, boolean value for
     *               specifying if a prefix needs to be used
     * @return String formatted string of field contents
     */
    private static String formatString(String data, boolean prefix) {
        String returnString = "";
        if (checkNull(data)) {
            returnString = prefix ? String.format(" - %s", data) : data;
        }
        return returnString;
    }
}
