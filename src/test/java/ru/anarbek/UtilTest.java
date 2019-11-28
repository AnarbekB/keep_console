package ru.anarbek;

import org.junit.Test;
import ru.anarbek.helper.Util;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void testRemoveStripLeadingHyphens() {
        assertEquals("create", Util.stripLeadingHyphens("--create"));
        assertEquals("create", Util.stripLeadingHyphens("-create"));
        assertEquals("create", Util.stripLeadingHyphens("create"));
        assertEquals("create--", Util.stripLeadingHyphens("create--"));
        assertEquals("c--reate", Util.stripLeadingHyphens("c--reate"));
    }

    @Test
    public void testIsOption() {
        assertTrue(Util.isOption("--test"));
        assertTrue(Util.isOption("-test"));
        assertFalse(Util.isOption("test"));
        assertFalse(Util.isOption("test--"));
        assertFalse(Util.isOption("te--st"));
        assertFalse(Util.isOption("__test"));
    }
}
