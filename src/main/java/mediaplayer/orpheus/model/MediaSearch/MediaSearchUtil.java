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
        StringBuilder sb = new StringBuilder();

        /*
        dataset[0] = fldArtistName
        dataset[1] = fldFirstName
        dataset[2] = fldLastName
        dataset[3] = fldMediaTitle
        dataset[6] = fldTrackLength
        dataset[7] = fldGenre
         */

        if (checkNull(dataset[3])){
            sb.append("[");
            sb.append(dataset[3]);
            sb.append("] ");
        }

        if (checkNull(dataset[0])){
            sb.append(" - ");
            sb.append(dataset[0]);
        }

        if (checkNull(dataset[1])){
            sb.append(" - ");
            sb.append(dataset[1]);
        }

        if (checkNull(dataset[2])){
            sb.append(" - ");
            sb.append(dataset[2]);
        }

        if (checkNull(dataset[6])){
            sb.append(" - ");
            sb.append(dataset[6]);
        }

        if (checkNull(dataset[7])){
            sb.append(" - ");
            sb.append(dataset[7]);
        }

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
}
