package mediaplayer.orpheus.model.Service;

import mediaplayer.orpheus.model.Service.FileHandlerMedia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileHandlerMediaTest {
    @Test
    void checkIfExtensionIsMp3 (){
        FileHandlerMedia myfile = new FileHandlerMedia("src/main/java/mediaplayer/orpheus/mediafiles/BIMINI - No Way (with Avi Snow)  Latin Dance  NCS - Copyright Free Music.mp3");
        assertEquals("mp3", myfile.mp3OrMp4());
    }
}
