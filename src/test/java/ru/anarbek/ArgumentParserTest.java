package ru.anarbek;

import org.junit.Test;
import ru.anarbek.cli.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ArgumentParserTest {

    @Test
    public void testParseCreate() {
        String[] args = {"--create"};

        try {
            Options options = ArgumentParser.parse(args);

            assertNotNull(options);
            assertEquals(1, options.getOptions().size());
            assertNotNull(options.getOption("c"));
            assertEquals(2, options.getOption("c").getChildren().getOptions().size());
            assertNull(options.getOption("c").getChildren().getOption("d").getValue());
            assertNull(options.getOption("c").getChildren().getOption("t").getValue());
        } catch (LoadFileException e) {
            fail("Load file options.xml error");
        } catch (InputArgumentsException e) {
            fail("Invalid arguments");
        }
    }

    @Test
    public void testParseCrateWithArguments() {
        String[] args = {"--create", "--title", "Test",  "title", "--data", "Test", "data", "text"};

        try {
            Options options = ArgumentParser.parse(args);

            assertNotNull(options);
            assertEquals(2, options.getOption("c").getChildren().getOptions().size());

            Option title = options.getOption("c").getChildren().getOption("t");
            assertNotNull(title);
            Option data = options.getOption("c").getChildren().getOption("d");
            assertNotNull(data);

            assertNotNull(data.getValue());
            assertNotNull(title.getValue());

            assertEquals(args[2] + " " + args[3], title.getValue());
            assertEquals(args[5] + " " + args[6] + " " + args[7], data.getValue());
        } catch (LoadFileException e) {
            fail("Load file options.xml error");
        } catch (InputArgumentsException e) {
            fail("Invalid arguments");
        }
    }

    @Test
    public void testParseCrateWithArgumentsErrors() {
        String[] args = {"--create", "--title", "--data", "Test title", "Test data text"};

        try {
            ArgumentParser.parse(args);
        } catch (LoadFileException e) {
            fail("Load file options.xml error");
        } catch (InputArgumentsException e) {
            assertNotNull(e);
        }
    }
}
