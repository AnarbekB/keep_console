package ru.anarbek;

import org.junit.Test;
import ru.anarbek.db.SQLiteConnection;

import static org.junit.Assert.*;

public class SQLiteConnectionTest {

    @Test
    public void createRunFirstMigrations() {
        try {
            SQLiteConnection.connect();
        } catch (Exception e) {
            fail("Fail connection to DB");
        }
    }
}
