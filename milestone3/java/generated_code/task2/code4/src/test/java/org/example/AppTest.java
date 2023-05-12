package org.example;

import junit.framework.TestCase;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.App.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     */
    @org.testng.annotations.Test
    public void testGetLogger() {
        Logger logger1 = createLog();
        Logger logger2 = createLog();
        assertNotNull(logger1);
        assertNotNull(logger2);
        assertEquals(logger1, logger2);
    }

    @org.testng.annotations.Test
    public void testLogLevel() {
        Logger logger = createLog();
        assertEquals(Level.ALL, logger.getLevel());
    }

    @Test
    public void testLogMessage() {
        Logger logger = createLog();
        logger.severe("This is an error message");
        logger.warning("This is a warning");
        logger.info("This is an informational message");
        logger.config("This is a configuration message");
        logger.fine("This is a fine message");
        logger.finer("This is a finer message");
        logger.finest("This is a finest message");
    }

    @org.testng.annotations.Test
    public void testGetLogger1() {
        Logger logger1 = createLog1();
        Logger logger2 = createLog1();
        assertNotNull(logger1);
        assertNotNull(logger2);
        assertEquals(logger1, logger2);
    }

    @org.testng.annotations.Test
    public void testLogLevel1() {
        Logger logger = createLog1();
        assertEquals(Level.ALL, logger.getLevel());
    }

    @Test
    public void testLogMessage1() {
        Logger logger = createLog1();
        logger.severe("This is an error message");
        logger.warning("This is a warning");
        logger.info("This is an informational message");
        logger.config("This is a configuration message");
        logger.fine("This is a fine message");
        logger.finer("This is a finer message");
        logger.finest("This is a finest message");
    }

    @org.testng.annotations.Test
    public void testGetLogger2() {
        Logger logger1 = createLog2();
        Logger logger2 = createLog2();
        assertNotNull(logger1);
        assertNotNull(logger2);
        assertEquals(logger1, logger2);
    }

    @org.testng.annotations.Test
    public void testLogLevel2() {
        Logger logger = createLog2();
        assertEquals(Level.ALL, logger.getLevel());
    }

    @Test
    public void testLogMessage2() {
        Logger logger = createLog2();
        logger.severe("This is an error message");
        logger.warning("This is a warning");
        logger.info("This is an informational message");
        logger.config("This is a configuration message");
        logger.fine("This is a fine message");
        logger.finer("This is a finer message");
        logger.finest("This is a finest message");
    }
}
