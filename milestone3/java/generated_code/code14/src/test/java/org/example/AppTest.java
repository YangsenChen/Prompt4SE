package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.App.forInt;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    @org.junit.jupiter.api.Test
    public void testForIntValidInput() {
        Profile profile = forInt(2);
        Assertions.assertEquals("Profile 2", profile.getName());
    }

    @org.junit.jupiter.api.Test
    public void testForIntInvalidInput() {
        Profile profile = forInt(5);
        Assertions.assertEquals("UNKNOWN", profile.getName());
    }

    @Test
    public void testForIntNegativeInput() {
        Profile profile = forInt(-3);
        Assertions.assertEquals("UNKNOWN", profile.getName());
    }
}
