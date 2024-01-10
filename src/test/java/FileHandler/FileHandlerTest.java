package FileHandler;

import mediaplayer.orpheus.model.service.FileHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileHandlerTest {
    @Test
    void checkIfExtensionIsMp3 (){
        FileHandler myfile = new FileHandler("src/main/java/mediaplayer/orpheus/mediafiles/BIMINI - No Way (with Avi Snow)  Latin Dance  NCS - Copyright Free Music.mp3");
        assertEquals("mp3", myfile.mp3OrMp4());
    }
}
