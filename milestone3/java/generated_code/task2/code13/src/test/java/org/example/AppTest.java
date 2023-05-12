package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.testng.annotations.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Test that write() sends the correct data to the output stream
     */
    @Test
    public void testWrite() throws IOException {
        // Create a mock HttpServletResponse object for testing purposes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HttpServletResponse response = new MockHttpServletResponse(outputStream);

        // Create a StreamHandler object containing some data to send to the client
        StreamHandler<String> handler = new StreamHandler<>("Hello world!");

        // Write the data in the StreamHandler to the response output stream
        App main = new App();
        main.write(response, handler);

        // Check that the output stream contains the expected data
        assertEquals("Hello world!", outputStream.toString());
    }

    /**
     * Test that write() throws an IOException if the response output stream throws an exception
     */
//    @Test(expected = IOException.class)
//    public void testWriteIOException() throws IOException {
//        // Create a mock HttpServletResponse object for testing purposes
//        ServletOutputStream outputStream = new ErrorOutputStream();
//
//        HttpServletResponse response = new MockHttpServletResponse(outputStream);
//
//        // Create a StreamHandler object containing some data to send to the client
//        StreamHandler<String> handler = new StreamHandler<>("Hello world!");
//
//        // Write the data in the StreamHandler to the response output stream
//        App main = new App();
//        main.write(response, handler);
//    }
//
//    /**
//     * Test that write() throws a ClassCastException if the value passed to it is not a StreamHandler object
//     */
//    @Test(expected = ClassCastException.class)
//    public void testWriteClassCastException() throws IOException {
//        // Create a mock HttpServletResponse object for testing purposes
//        HttpServletResponse response = new MockHttpServletResponse();
//
//        // Attempt to write a non-StreamHandler object to the response output stream
//        Main main = new Main();
//        main.write(response, "Hello world!");
//    }

    @Test
    public void testWrite1() throws IOException {
        // Create a mock HttpServletResponse object for testing purposes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HttpServletResponse response = new MockHttpServletResponse(outputStream);

        // Create a StreamHandler object containing some data to send to the client
        StreamHandler<String> handler = new StreamHandler<>("Hello world!");

        // Write the data in the StreamHandler to the response output stream
        App main = new App();
        main.write1(response, handler);

        // Check that the output stream contains the expected data
        assertEquals("Hello world!", outputStream.toString());
    }
}
