package mediaplayer.orpheus.util.Database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JDBCTest {

    @Test
    void databaseClose() {
        JDBC db = new JDBC();
        db.databaseClose();
        assertNull(db.getConnection());
    }

    @Test
    void getConnection() {
        JDBC db = new JDBC();
        assertNotNull(db.getConnection());
    }

}