package mediaplayer.orpheus.model.MediaEdit;

import mediaplayer.orpheus.model.Database.JDBC;
import mediaplayer.orpheus.model.Database.DatabaseRead;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MediaEditUtil {


    /**
     * Method for getting all genres in the database
     * @return an arraylist of all genres in the database.
     */
    public static ArrayList<String> getAllGenre(){

        ArrayList<String> genre = new ArrayList<>();

        try (ResultSet resultSet = JDBC.instance.executeQuary(DatabaseRead.getAllGenres())) {
            while (resultSet.next()) {
                genre.add(resultSet.getString("fldGenre"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return genre;

    }

    /**
     * Method for getting all artists in the database
     * @return an arraylist of all artists in the database.
     */
    public static ArrayList<String> getAllArtist(){

        ArrayList<String> artist = new ArrayList<>();

        try (ResultSet resultSet = JDBC.instance.executeQuary(DatabaseRead.getAllArtists())) {
            while (resultSet.next()) {
                artist.add(resultSet.getString("fldArtistName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return artist;

    }

}
