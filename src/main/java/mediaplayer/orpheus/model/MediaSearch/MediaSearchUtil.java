package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import mediaplayer.orpheus.model.Playlist.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MediaSearchUtil {

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
