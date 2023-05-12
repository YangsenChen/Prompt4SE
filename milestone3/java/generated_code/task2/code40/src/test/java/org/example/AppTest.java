package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AppTest {

    @Test
    public void testReadFromFile() throws IOException {
        String testFilePath = "./testFile.txt";
        String testContent = "Test content";

        // Create a test file
        FileWriter writer = new FileWriter(testFilePath);
        writer.write(testContent);
        writer.close();

        byte[] result = App.readFromFile(testFilePath, "UTF-8");
        assertEquals(testContent, new String(result, StandardCharsets.UTF_8));

        // Clean up the test file
        new File(testFilePath).delete();
    }

    @Test(expected = IOException.class)
    public void testReadFromNonexistentFile() throws IOException {
        App.readFromFile("./nonexistentFile.txt", "UTF-8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadFromFileWithInvalidEncoding() throws IOException {
        String testFilePath = "./testFile.txt";
        String testContent = "Test content";

        // Create a test file
        FileWriter writer = new FileWriter(testFilePath);
        writer.write(testContent);
        writer.close();

        App.readFromFile(testFilePath, "INVALID_ENCODING");

        // Clean up the test file
        new File(testFilePath).delete();
    }
}
