package ru.anarbek;

import org.junit.Test;
import ru.anarbek.helper.Util;

import static org.junit.Assert.assertEquals;

public class UtilTest {

    @Test
    public void testRemoveStripLeadingHyphens() {
        assertEquals("create", Util.stripLeadingHyphens("--create"));
        assertEquals("create", Util.stripLeadingHyphens("-create"));
        assertEquals("create", Util.stripLeadingHyphens("create"));
        assertEquals("create--", Util.stripLeadingHyphens("create--"));
        assertEquals("c--reate", Util.stripLeadingHyphens("c--reate"));
    }
}
