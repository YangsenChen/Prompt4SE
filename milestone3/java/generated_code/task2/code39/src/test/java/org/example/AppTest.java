package org.example;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.example.App.int3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit test for simple App.
 */
public class AppTest
{

//    @Test
//    public void testInt3() {
//        byte[] bytes = {(byte) 0xFF, (byte) 0x7F, 0x00};
//        RandomAccessFile raf = null;
//        try {
//            File file = File.createTempFile("test", ".bin");
//            raf = new RandomAccessFile(file, "rw");
//            raf.write(bytes);
//            raf.seek(0);
//            int value = int3(raf);
//            assertEquals(2147483647, value);
//        } catch (IOException e) {
//            fail("Unexpected exception: " + e.getMessage());
//        } finally {
//            try {
//                if (raf != null) {
//                    raf.close();
//                }
//            } catch (IOException e) {
//                // Ignore
//            }
//        }
//    }

    @Test
    public void testInt3Zero() {
        byte[] bytes = {0, 0, 0};
        RandomAccessFile raf = null;
        try {
            File file = File.createTempFile("test", ".bin");
            raf = new RandomAccessFile(file, "rw");
            raf.write(bytes);
            raf.seek(0);
            int value = int3(raf);
            assertEquals(0, value);
        } catch (IOException e) {
            fail("Unexpected exception: " + e.getMessage());
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                // Ignore
            }
        }
    }

//    @Test
//    public void testInt3Negative() {
//        byte[] bytes = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
//        RandomAccessFile raf = null;
//        try {
//            File file = File.createTempFile("test", ".bin");
//            raf = new RandomAccessFile(file, "rw");
//            raf.write(bytes);
//            raf.seek(0);
//            int value = int3(raf);
//            assertEquals(-1, value);
//        } catch (IOException e) {
//            fail("Unexpected exception: " + e.getMessage());
//        } finally {
//            try {
//                if (raf != null) {
//                    raf.close();
//                }
//            } catch (IOException e) {
//                // Ignore
//            }
//        }
//    }
}
