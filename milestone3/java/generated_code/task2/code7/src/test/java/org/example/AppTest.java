package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    // Unit Test 1 - Test headerInfoDump() function output
    @org.junit.Test
    public void testHeaderInfoDump() {
        App a = new App();
        String expectedOutput = "Header1:::::Value1:::::\nHeader2:::::Value2:::::\nHeader3:::::Value3:::::\n";
        assertEquals(expectedOutput, a.headerInfoDump());
    }

    // Unit Test 2 - Test headerInfoDump() function with empty hashmap
    @org.junit.Test
    public void testHeaderInfoDumpWithEmptyHashMap() {
        App a = new App();
        a.headerInfo = new HashMap<>();
        String expectedOutput = "";
        assertEquals(expectedOutput, a.headerInfoDump());
    }

    // Unit Test 3 - Test headerInfoDump() function with new header added
    @Test
    public void testHeaderInfoDumpWithNewHeaderAdded() {
        App a = new App();
        a.headerInfo.put("Header4", "Value4");
        String expectedOutput = "Header1:::::Value1:::::\nHeader2:::::Value2:::::\nHeader3:::::Value3:::::\nHeader4:::::Value4:::::\n";
        assertEquals(expectedOutput, a.headerInfoDump());
    }
}
