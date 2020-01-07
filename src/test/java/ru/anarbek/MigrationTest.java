package ru.anarbek;

import org.junit.Test;
import ru.anarbek.db.Migration;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MigrationTest {

    @Test
    public void testGetAllMigrations() {
        try {
            Migration migration = new Migration("src/main/resources/migrations");
            ArrayList<File> filesForMigrate = migration.getMigrate(null);

            assertTrue(filesForMigrate.size() > 0);
        } catch (Exception e) {
            fail("Fail migration");
        }
    }

    @Test
    public void testGetSQLFromFile() throws NoSuchMethodException {
        Migration migration = null;
        try {
            migration = new Migration("src/main/resources/migrations");
        } catch (Exception e) {
            fail("Fail migration");
        }
        Method m = Migration.class.getDeclaredMethod("getSQL", File.class);
        m.setAccessible(true);

        ArrayList<File> filesForMigrate = migration.getMigrate(null);

        try {
            Object sql = m.invoke(migration, filesForMigrate.get(0));

            assertTrue(sql instanceof String);
        } catch (Exception e) {
            fail("Migration error");
        }
    }

    @Test
    public void testMigrate() {
        try {
            Migration migration = new Migration("src/main/resources/migrations");
            migration.migrate();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
