package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class AppTest1 {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testBootstrap() {
        String dsn = "your-dsn-goes-here";
        Optional<String> tags = Optional.of("your-tags-go-here");
        boolean cleanRootLogger = true;

        App.bootstrap(dsn, tags, Optional.empty(), Optional.empty(), cleanRootLogger);

        String expectedOutput = "DSN: " + dsn + "\r\n" +
                "Tags: " + (tags.isPresent() ? tags.get() : "No tags provided") + "\r\n" +
                "Clean Root Logger: " + cleanRootLogger + "\r\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
