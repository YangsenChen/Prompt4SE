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

    @org.junit.Test
    public void testHeaderInfoDump1() {
        App a = new App();
        String expectedOutput = "Header1:::::Value1:::::\nHeader2:::::Value2:::::\nHeader3:::::Value3:::::\n";
        assertEquals(expectedOutput, a.headerInfoDump1());
    }

    // Unit Test 2 - Test headerInfoDump() function with empty hashmap
    @org.junit.Test
    public void testHeaderInfoDumpWithEmptyHashMap1() {
        App a = new App();
        a.headerInfo = new HashMap<>();
        String expectedOutput = "";
        assertEquals(expectedOutput, a.headerInfoDump1());
    }

    // Unit Test 3 - Test headerInfoDump() function with new header added
    @Test
    public void testHeaderInfoDumpWithNewHeaderAdded1() {
        App a = new App();
        a.headerInfo.put("Header4", "Value4");
        String expectedOutput = "Header1:::::Value1:::::\nHeader2:::::Value2:::::\nHeader3:::::Value3:::::\nHeader4:::::Value4:::::\n";
        assertEquals(expectedOutput, a.headerInfoDump1());
    }

    @org.junit.Test
    public void testHeaderInfoDump2() {
        App a = new App();
        String expectedOutput = "Header1:::::Value1:::::\nHeader2:::::Value2:::::\nHeader3:::::Value3:::::\n";
        assertEquals(expectedOutput, a.headerInfoDump2());
    }

    // Unit Test 2 - Test headerInfoDump() function with empty hashmap
    @org.junit.Test
    public void testHeaderInfoDumpWithEmptyHashMap2() {
        App a = new App();
        a.headerInfo = new HashMap<>();
        String expectedOutput = "";
        assertEquals(expectedOutput, a.headerInfoDump2());
    }

    // Unit Test 3 - Test headerInfoDump() function with new header added
    @Test
    public void testHeaderInfoDumpWithNewHeaderAdded2() {
        App a = new App();
        a.headerInfo.put("Header4", "Value4");
        String expectedOutput = "Header1:::::Value1:::::\nHeader2:::::Value2:::::\nHeader3:::::Value3:::::\nHeader4:::::Value4:::::\n";
        assertEquals(expectedOutput, a.headerInfoDump2());
    }
}
