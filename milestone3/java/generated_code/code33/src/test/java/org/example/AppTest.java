package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

//    @org.junit.jupiter.api.Test
//    public void testInitSuccess() {
//        FileProcessor fp = new FileProcessor();
//        try {
//            assertTrue(fp.init(true));
//        } catch (IOException e) {
//            fail("IOException occurred: " + e.getMessage());
//        }
//    }

    @Test
    public void testInitFileNotSet() {
        FileProcessor fp = new FileProcessor();
        try {
            assertFalse(fp.init(true));
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            assertEquals(e.getMessage(), "file has not been set");
        }
    }

//    @Test
//    public void testInitLabelNotOk() {
//        FileProcessor fp = new FileProcessor();
//        fp.setRf(new Object());
//        fp.setDmLabel(new DMLabel(false));
//        try {
//            assertFalse(fp.init(true));
//            verify(fp).logError(eq("not a GEMPAK file"));
//        } catch (IOException e) {
//            fail("IOException occurred: " + e.getMessage());
//        }
//    }
}
