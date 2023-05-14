import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Main {

    public static void main(String[] args) {
        // Call the getInstance method to get an instance of WebsockifySslContext
        String keystore = "/path/to/keystore";
        String password = "keystore_password";
        String keyPassword = "key_password";
        WebsockifySslContext context = WebsockifySslContext.getInstance(keystore, password, keyPassword);

        // Use the returned context object as desired
        // For example, print its hashCode value as a way to verify that it's a singleton
        System.out.println(context.hashCode());
    }

    // Unit test method to test the Singleton pattern
    @Test
    public void testSingleton() {
        // Call getInstance method multiple times with the same keystore path
        String keystore1 = "/path/to/keystore";
        String keystore2 = "/path/to/keystore";  // same as keystore1
        String password = "keystore_password";
        String keyPassword = "key_password";

        WebsockifySslContext context1 = WebsockifySslContext.getInstance(keystore1, password, keyPassword);
        WebsockifySslContext context2 = WebsockifySslContext.getInstance(keystore2, password, keyPassword);

        // Assert that both instances are the same object
        assertSame(context1, context2);
    }

    // Unit test method to test creating the object with a new keystore path
    @Test
    public void testCreateNew() {
        // Call getInstance method with a new keystore path
        String keystore1 = "/path/to/keystore1";  // new keystore path
        String password = "keystore_password";
        String keyPassword = "key_password";

        WebsockifySslContext context1 = WebsockifySslContext.getInstance(keystore1, password, keyPassword);

        // Assert that context1 is not null
        assertNotNull(context1);
    }

    // Unit test method to test handling null keystore path
    @Test
    public void testNullKeystore() {
        // Call getInstance method with a null keystore path
        String keystore1 = null; // null keystore path
        String password = "keystore_password";
        String keyPassword = "key_password";

        // Assert that getInstance method throws IllegalArgumentException when keystore path is null
        assertThrows(IllegalArgumentException.class, () -> WebsockifySslContext.getInstance(keystore1, password, keyPassword));
    }
}

// SingletonHolder class to hold the INSTANCE_MAP
class SingletonHolder {
    // Map to store instances of WebsockifySslContext
    static final Map<String, WebsockifySslContext> INSTANCE_MAP = new HashMap<>();
}