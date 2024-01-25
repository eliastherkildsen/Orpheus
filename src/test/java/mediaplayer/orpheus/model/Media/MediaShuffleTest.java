package mediaplayer.orpheus.model.Media;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MediaShuffleTest {

    @Test
    void validShufflePlaylist() throws SQLException {

        // Arrange
        ArrayList<MediaObj> regularPlaylist = new ArrayList<>(Arrays.asList(
                new MediaObj(1),
                new MediaObj(2),
                new MediaObj(3)
        ));

        ArrayList<MediaObj> byComparison = new ArrayList<>(regularPlaylist);

        // Act
        ArrayList<MediaObj> shuffledPlaylist = new ArrayList<>(MediaShuffle.shufflePlaylist(regularPlaylist));

        // Assert
        assertNotEquals(byComparison, shuffledPlaylist);
    }

}