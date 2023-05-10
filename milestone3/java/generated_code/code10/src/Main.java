import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertThrows;

public class Main {

    public static void main(String[] args) {
        String dsn = "your-dsn-goes-here";
        Optional<String> tags = Optional.of("your-tags-go-here");
        boolean cleanRootLogger = true;

        bootstrap(dsn, tags, cleanRootLogger);
    }

    public static void bootstrap(final String dsn, Optional<String> tags, boolean cleanRootLogger) {
        bootstrap(dsn, tags, Optional.empty(), Optional.empty(), cleanRootLogger);
    }

    @Test
    public void bootstrap_withNonEmptyDSNAndTags_shouldNotThrowException() {
        String dsn = "non-empty-dsn";
        Optional<String> tags = Optional.of("tag1");

        assertDoesNotThrow(() -> bootstrap(dsn, tags, false));
    }

    @Test
    public void bootstrap_withEmptyDSN_shouldThrowIllegalArgumentException() {
        String dsn = "";
        Optional<String> tags = Optional.of("tag1");

        assertThrows(IllegalArgumentException.class, () -> bootstrap(dsn, tags, false));
    }

    @Test
    public void bootstrap_withEmptyTags_shouldNotThrowException() {
        String dsn = "non-empty-dsn";
        Optional<String> tags = Optional.empty();

        assertDoesNotThrow(() -> bootstrap(dsn, tags, false));
    }
}