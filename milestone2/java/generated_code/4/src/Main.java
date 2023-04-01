import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class Main {
    private static final Map<Class<?>, Logger> loggers = new HashMap<>();

    private static Logger createLog() {
        Class<?> clazz = new Object() {}.getClass().getEnclosingClass(); // use anonymous class to get enclosing class
        Logger logger = loggers.get(clazz);
        if (logger == null) {
            logger = Logger.getLogger(clazz.getName());
            logger.setLevel(Level.ALL); // set log level to ALL
            loggers.put(clazz, logger);
        }
        return logger;
    }


    public static void main(String[] args) {
        Logger logger = createLog();
        logger.info("Hello World!"); // log message
    }

    @Test
    public void testGetLogger() {
        Logger logger1 = createLog();
        Logger logger2 = createLog();
        assertNotNull(logger1);
        assertNotNull(logger2);
        assertEquals(logger1, logger2);
    }

    @Test
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
}