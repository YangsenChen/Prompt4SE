package org.example;


import org.junit.Test;

import java.io.IOException;

import static org.example.App.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testWErrorSize() throws IOException {
        testWithErrorSize(10);
    }

//    @Test
//    public void testWDataSize() throws IOException {
//        testWithDataSize(16);
//    }
//
//    @Test
//    public void testWBitCount() throws IOException {
//        testWithBitCount(4);
//    }
}
