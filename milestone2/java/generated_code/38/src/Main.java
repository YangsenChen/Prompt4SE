import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class Main {
    public static void main(String[] args) {
        String message = "An error occurred. Error code: %d";
        int errorCode = 404;
        String errorLog = log(message, errorCode);

        System.out.println("Error log: " + errorLog);
    }

    private static String log(String message, Object... args) {
        message = String.format(message, args);
        java.util.logging.Logger.getLogger(Server.class.getCanonicalName()).log(Level.SEVERE, message);
        return message;
    }

//    // chatgpt generated  semantically equivalent code: test pass 3/3
//    // chatgpt made the following change: The only change made here is to store the Logger instance in a variable for better readability and maintainability.
//    private static String log(String message, Object... args) {
//        message = String.format(message, args);
//        Logger logger = Logger.getLogger(Server.class.getCanonicalName());
//        logger.severe(message);
//        return message;
//    }


    @Test
    public void testLog() {
        String message = "An error occurred. Error code: %d";
        int errorCode = 500;
        String errorLog = log(message, errorCode);
        assertEquals("An error occurred. Error code: 500", errorLog);
    }

    @Test
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
}

class Server {
    // This class is needed to retrieve the logger
}