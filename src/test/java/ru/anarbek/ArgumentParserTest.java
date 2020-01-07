package ru.anarbek;

import org.junit.Test;
import ru.anarbek.cli.*;
import ru.anarbek.constant.Argument;

import static org.junit.Assert.*;

public class ArgumentParserTest {

    @Test
    public void testParseCreate() {
        String[] args = {"--create"};

        try {
            Options options = ArgumentParser.parse(args);

            assertNotNull(options);
            assertEquals(1, options.getOptions().size());
            assertNotNull(options.getOption("c"));
            assertEquals(3, options.getOption("c").getChildren().getOptions().size());
            assertNull(options.getOption("c").getChildren().getOption("d").getValue());
            assertNull(options.getOption("c").getChildren().getOption("t").getValue());
        } catch (LoadFileException e) {
            fail("Load file options.xml error");
        } catch (InputArgumentsException e) {
            fail("Invalid arguments");
        }
    }

    @Test
    public void testParseCreateWithArguments() {
        String[] args = {"--create", "--title", "Test",  "title", "--data", "Test", "data", "text"};

        try {
            Options options = ArgumentParser.parse(args);

            assertNotNull(options);
            assertEquals(3, options.getOption("c").getChildren().getOptions().size());

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
    public void testParseCreateWithArgumentsErrors() {
        String[] args = {"--create", "--title", "--data", "Test", "title", "Test", "data", "text"};

        try {
            ArgumentParser.parse(args);
        } catch (LoadFileException e) {
            fail("Load file options.xml error");
        } catch (InputArgumentsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testParseListColor() {
        String[] args = {"--list", "--color"};

        try {
            Options options = ArgumentParser.parse(args);

            assertNotNull(options);
            boolean isOptionPresent = options.getOption(Argument.COLOR_OUTPUT).isOptionPresent();
            assertTrue(isOptionPresent);
        } catch (LoadFileException e) {
            fail("Load file options.xml error");
        } catch (InputArgumentsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testParseCreateWithColor() {
        String[] args = {"--create", "--title", "--data", "Test", "title", "Test", "data", "text", "--color"};

        try {
            Options options = ArgumentParser.parse(args);

            assertNotNull(options);
            boolean isOptionPresent = options.getOption(Argument.COLOR_OUTPUT).isOptionPresent();
            assertTrue(isOptionPresent);
        } catch (LoadFileException e) {
            fail("Load file options.xml error");
        } catch (InputArgumentsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testParseCreateWithSilence() {
        String[] args = {"--create", "--title", "Test", "title", "title", "--data", "Test", "data", "--silence"};

        try {
            Options options = ArgumentParser.parse(args);

            assertNotNull(options);
            boolean isOptionPresent = options.getOption(Argument.CREATE_KEEP).getChildren()
                    .getOption(Argument.SILENCE_CREATE).isOptionPresent();
            assertTrue(isOptionPresent);

        } catch (LoadFileException e) {
            fail("Load file options.xml error");
        } catch (InputArgumentsException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testParseCreateWithOneDataWorld() {
        String[] args = {"--create", "--title", "Test", "title", "--data", "data"};

        try {
            ArgumentParser.parse(args);
        } catch (LoadFileException e) {
            fail("Load file options.xml error");
        } catch (InputArgumentsException e) {
            assertNotNull(e);
        }
    }
}
