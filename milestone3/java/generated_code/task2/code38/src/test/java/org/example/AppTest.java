package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import static org.example.App.log;
import static org.example.App.log1;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    @org.junit.Test
    public void testLog() {
        String message = "An error occurred. Error code: %d";
        int errorCode = 500;
        String errorLog = log(message, errorCode);
        assertEquals("An error occurred. Error code: 500", errorLog);
    }

    @org.junit.Test
    public void testLogWithoutArgs() {
        String message = "An error occurred.";
        String errorLog = log(message);
        assertEquals("An error occurred.", errorLog);
    }

    @Test
    public void testLogWithMultipleArgs() {
        String message = "An error occurred. Error code: %d. Error message: %s";
        int errorCode = 403;
        String errorMessage = "Access denied";
        String errorLog = log(message, errorCode, errorMessage);
        assertEquals("An error occurred. Error code: 403. Error message: Access denied", errorLog);
    }

    @org.junit.Test
    public void testLog1() {
        String message = "An error occurred. Error code: %d";
        int errorCode = 500;
        String errorLog = log1(message, errorCode);
        assertEquals("An error occurred. Error code: 500", errorLog);
    }

    @org.junit.Test
    public void testLogWithoutArgs1() {
        String message = "An error occurred.";
        String errorLog = log1(message);
        assertEquals("An error occurred.", errorLog);
    }

    @Test
    public void testLogWithMultipleArgs1() {
        String message = "An error occurred. Error code: %d. Error message: %s";
        int errorCode = 403;
        String errorMessage = "Access denied";
        String errorLog = log1(message, errorCode, errorMessage);
        assertEquals("An error occurred. Error code: 403. Error message: Access denied", errorLog);
    }
}
