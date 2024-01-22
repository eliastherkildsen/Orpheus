package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseRead;
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


    public static ArrayList<String> getAllPlaylist(){
    ArrayList<String> playListNamesArr = new ArrayList<>();
    String query = DatabaseRead.getAllPlaylistNames();

        try(
    ResultSet resultSet = JDBC.instance.executeQuary(query))

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
