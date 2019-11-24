package ru.anarbek;

import org.junit.Before;
import org.junit.Test;
import ru.anarbek.cli.LoadFileException;
import ru.anarbek.cli.Options;
import ru.anarbek.cli.OptionsBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class OptionsBuilderTest {

    private Method m;

    @Before
    public void setUp() throws Exception {
        m = OptionsBuilder.class.getDeclaredMethod("loadFromXML", String.class);
        m.setAccessible(true);
    }

    @Test
    public void testBuild() {
        try {
            Options options = OptionsBuilder.build();
            assertEquals(Options.class, options.getClass());
        } catch (LoadFileException e) {
            fail("Error load options file");
        }
    }

    @Test
    public void validLoadXML() {
        String validPath = "src/main/resources/options.xml";

        try {
            m.invoke(null, validPath);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail("Error load file");
        }
    }

    @Test
    public void testInvalidLoadXML() {
        String invalidPath = "src/main/options.xml";

        try {
            m.invoke(null, invalidPath);
        }catch (IllegalAccessException | InvocationTargetException e) {
            assertTrue(e.getCause() instanceof LoadFileException);
        }
    }
}
