package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MediaSearchUtil {

    /**
     * Method for parsing the position of the selected item from a string to an integer.
     * and the retrieving the path for the String at the index og the integer retrieved.
     *
     * @return String filepath to selected media.
     */
    public static String getMediaPathFromDataset(int mediaPicked, ArrayList<String[]> dataSet) {
        // gets the String array from the arraylist,

        if (mediaPicked != -1) {
            String[] temp = dataSet.get(mediaPicked);
            // check if the array has enough elements
            if (temp.length > 5) {
                // returns the String at index 5, which refers to the index where the filePath exists.
                // check if temp[5] is null
                if (!(temp[5] == null)) {
                    return temp[5];
                }
            }
        }

        return "";

    }

    public static int getMediaIDFromDataset(int mediaPicked, ArrayList<String[]> dataSet) {
        String[] temp = dataSet.get(mediaPicked);

        int mediaID = Integer.parseInt(temp[8]);
        return mediaID;
    }


    /**
     * Method for creating a formatted string output for a quarry result.
     *
     * @param dataset String array.
     * @return String.
     */
    public static String formatedQuarryResultString(String[] dataset) {
        // initializing StringBuilder
        StringBuilder sb = new StringBuilder();

        // appending to the presented String.
        sb.append(formatString(dataset[3], false)); // media title
        sb.append(formatString(dataset[0], true)); // artist name
        sb.append(formatString(dataset[7], true)); // media genre

        return sb.toString();
    }

    /**
     * method for checking if a string contains "NULL" or is null
     *
     * @param string string to format.
     * @return boolean
     */
    private static boolean checkNull(String string) {
        if (string == null) {
            return false;
        } else return !string.equalsIgnoreCase("NULL");
    }

    /**
     * method for returning a formatted string, with null check.
     *
     * @param data   String field contents
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

    public static ArrayList<String> getAllPlaylist(){
    ArrayList<String> playListNamesArr = new ArrayList<>();
    String query = DatabaseRead.getAllPlaylistNames();

        try(
    ResultSet resultSet = OrpheusApp.jdbc.executeQuary(query))

    {

        while (resultSet.next()) {
            playListNamesArr.add(resultSet.getString("fldPlaylistName"));
        }

    }catch(
    SQLException e)

    {
        throw new RuntimeException(e);
    }
        return playListNamesArr;
    }

}
