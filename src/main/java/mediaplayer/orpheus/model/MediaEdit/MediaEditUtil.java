package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseRead;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MediaEditUtil {

    private static ArrayList<String> genre;
    private static ArrayList<String> artist;

    public static ArrayList<String> getAllGenre(){

        String quary = DatabaseRead.getAllGenres();

        genre = new ArrayList<>();

        try (ResultSet resultSet = JDBC.instance.executeQuary(quary)) {
            while (resultSet.next()) {
                genre.add(resultSet.getString("fldGenre"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return genre;

    }
    public static ArrayList<String> getAllArtist(){

        String quary = DatabaseRead.getAllArtists();

        artist = new ArrayList<>();

        try (ResultSet resultSet = JDBC.instance.executeQuary(quary)) {
            while (resultSet.next()) {
                artist.add(resultSet.getString("fldArtistName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return artist;

    }

}
