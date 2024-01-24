package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseRead;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MediaSearchUtil {

    public static ArrayList<String> getAllPlaylist(){
    ArrayList<String> playListNamesArr = new ArrayList<>();

        try(
    ResultSet resultSet = JDBC.instance.executeQuary(DatabaseRead.getAllPlaylistNames()))

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
