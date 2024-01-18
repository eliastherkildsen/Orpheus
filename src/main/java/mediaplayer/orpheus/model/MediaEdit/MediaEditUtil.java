package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.OrpheusApp;
import mediaplayer.orpheus.model.Database.DatabaseRead;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MediaEditUtil {

    private static ArrayList<String> genre = new ArrayList<>();
    private static ArrayList<String> artist = new ArrayList<>();

    public static ArrayList<String> getAllGenre(){

        String quary = DatabaseRead.getAllGenres();

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
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

        try (ResultSet resultSet = OrpheusApp.jdbc.executeQuary(quary)) {
            while (resultSet.next()) {
                artist.add(resultSet.getString("fldArtistName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return artist;

    }

}