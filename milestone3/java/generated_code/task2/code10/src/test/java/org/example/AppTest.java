package org.example;



import org.junit.Test;

import java.util.Optional;
import java.util.logging.Level;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testBootstrapWithTags() {
        App.bootstrap("test-dsn", Optional.of("test-tags"), true);
        assertEquals("Logger level should be SEVERE", Level.SEVERE, App.logger.getLevel());
    }

    @Test
    public void testBootstrapWithoutTags() {
        App.bootstrap("test-dsn", Optional.empty(), true);
        assertEquals("Logger level should be SEVERE", Level.SEVERE, App.logger.getLevel());
    }

    @Test
    public void testBootstrapWithFalseCleanRootLogger() {
        App.bootstrap("test-dsn", Optional.of("test-tags"), false);
        assertEquals("Logger level should be INFO", Level.INFO, App.logger.getLevel());
    }


//    @Test
//    public void testBootstrapWithFalseCleanRootLogger1() {
//        App.bootstrap1("test-dsn", Optional.of("test-tags"), false);
//        assertEquals("Logger level should be INFO", Level.INFO, App.logger.getLevel());
//    }
}
