package org.example;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
    public static Logger logger;

    public static void main(String[] args) {
        String dsn = "your-dsn-goes-here";
        Optional<String> tags = Optional.of("your-tags-go-here");
        boolean cleanRootLogger = true;

    }

    public static void bootstrap(
            final String dsn,
            Optional<String> tags,
            Optional<String> otherOptional1,
            Optional<String> otherOptional2,
            boolean cleanRootLogger
    ) {
        // Initialize our logger
        logger = Logger.getLogger(dsn);
        logger.setLevel(cleanRootLogger ? Level.SEVERE : Level.INFO);

        // Print out the parameters
        logger.info("Tags: " + (tags.isPresent() ? tags.get() : "No tags provided"));
        logger.info("Other Optional 1: " + (otherOptional1.isPresent() ? otherOptional1.get() : "No data for Other Optional 1"));
        logger.info("Other Optional 2: " + (otherOptional2.isPresent() ? otherOptional2.get() : "No data for Other Optional 2"));
        logger.info("Clean Root Logger: " + cleanRootLogger);


        // Some println statements
        System.out.println("DSN: " + dsn);
        System.out.println("Tags: " + (tags.isPresent() ? tags.get() : "No tags provided"));
        System.out.println("Clean Root Logger: " + cleanRootLogger);
    }

    public static void bootstrap(final String dsn, Optional<String> tags, boolean cleanRootLogger) {
        bootstrap(dsn, tags, Optional.empty(), Optional.empty(), cleanRootLogger);
    }

    // New function added
}
