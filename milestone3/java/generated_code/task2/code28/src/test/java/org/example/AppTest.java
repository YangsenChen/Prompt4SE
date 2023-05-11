package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.example.App.getDecimalSeparatorByDefaultLocale;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    @org.junit.jupiter.api.Test
    public void testDecimalSeparatorForUSLocale() {
        Locale.setDefault(Locale.US);
        String expected = ".";
        String actual = getDecimalSeparatorByDefaultLocale();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    public void testDecimalSeparatorForFrenchLocale() {
        Locale.setDefault(Locale.FRANCE);
        String expected = ",";
        String actual = getDecimalSeparatorByDefaultLocale();
        assertEquals(expected, actual);
    }

    @Test
    public void testDecimalSeparatorForJapaneseLocale() {
        Locale.setDefault(Locale.JAPAN);
        String expected = ".";
        String actual = getDecimalSeparatorByDefaultLocale();
        assertEquals(expected, actual);
    }
}
