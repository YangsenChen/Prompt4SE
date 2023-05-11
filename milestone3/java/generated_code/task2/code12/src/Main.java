import org.junit.Assert;
import org.junit.Test;

public class Main {

    public static void main(String[] args) {
        // Perform any setup or initialization here, if needed

        // Call the `adjustPen()` method repeatedly or as needed
        for (int i = 0; i < 10; i++) { // Call `adjustPen()` 10 times
            adjustPen();
        }
    }

    private static void adjustPen() {
        // The original `adjustPen()` function code
        Tortoise.setPenColor(ColorWheel.getNextColor());
        Tortoise.setPenWidth(Tortoise.getPenWidth() + 1.0);
        if (Tortoise.getPenWidth() > 4) {
            Tortoise.setPenWidth(1);
        }
    }

    @Test
    public void testPenColor() {
        // Set the initial pen color to White
        Tortoise.setPenColor(java.awt.Color.WHITE);

        // Call adjustPen() once
        adjustPen();

        // Check if the new pen color is different from the initial color
        Assert.assertNotEquals(Tortoise.getPenColor(), java.awt.Color.WHITE);
    }

    @Test
    public void testPenWidthIncrease() {
        // Set the initial pen width to 1
        Tortoise.setPenWidth(1);

        // Call adjustPen() once
        adjustPen();

        // Check if the new pen width is greater than 1
        Assert.assertTrue(Tortoise.getPenWidth() > 1);
    }

    @Test
    public void testPenWidthReset() {
        // Set the initial pen width to 5
        Tortoise.setPenWidth(5);

        // Call adjustPen() once
        adjustPen();

        // Check if the new pen width is now 1
        Assert.assertEquals(1.0, Tortoise.getPenWidth(), 0.0);
    }
}