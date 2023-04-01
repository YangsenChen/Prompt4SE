import com.sun.source.tree.Scope;
import org.junit.Assert;
import org.junit.Test;

public class Main {

    public static void main(String[] args) {
        String input = "SYSTEM";
        Scope output = stringToScope(input);

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
    }

    static public Scope stringToScope(String scopeStr) {
        if(Scope.SYSTEM.toString().equalsIgnoreCase(scopeStr))
            return Scope.SYSTEM;
        if(Scope.CONTEXT.toString().equalsIgnoreCase(scopeStr))
            return Scope.CONTEXT;

        return Scope.LOCAL;
    }

    @Test
    public void stringToScope_System_ReturnsSystem() {
        Scope expected = Scope.SYSTEM;
        Scope actual = stringToScope("SYSTEM");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringToScope_Context_ReturnsContext() {
        Scope expected = Scope.CONTEXT;
        Scope actual = stringToScope("CONTEXT");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringToScope_Other_ReturnsLocal() {
        Scope expected = Scope.LOCAL;
        Scope actual = stringToScope("OTHER");
        Assert.assertEquals(expected, actual);
    }
}