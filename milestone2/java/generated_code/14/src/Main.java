import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Main {
    private static final Profile UNKNOWN = new Profile("UNKNOWN");
    private static final Profile[] ALL = {
            new Profile("Profile 1"),
            new Profile("Profile 2"),
            new Profile("Profile 3")
    };

    public static void main(String[] args) {
        // Call forInt method with input value
        Profile profile = forInt(2);

        // Print the result
        System.out.println(profile.getName());
    }

    public static Profile forInt(int i) {
        Profile p;
        if (i <= 0 || i > ALL.length)
            p = UNKNOWN;
        else
            p = ALL[i-1];

        return p;
    }

    @Test
    public void testForIntValidInput() {
        Profile profile = forInt(2);
        Assertions.assertEquals("Profile 2", profile.getName());
    }

    @Test
    public void testForIntInvalidInput() {
        Profile profile = forInt(5);
        Assertions.assertEquals("UNKNOWN", profile.getName());
    }

    @Test
    public void testForIntNegativeInput() {
        Profile profile = forInt(-3);
        Assertions.assertEquals("UNKNOWN", profile.getName());
    }
}

class Profile {
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}