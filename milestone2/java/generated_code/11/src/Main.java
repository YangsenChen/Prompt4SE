import org.junit.Assert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Main {
    private long lastPacketTimestampNs; // example field
    private long imageLivenessTimeoutNs; // example field
    private boolean isEndOfStream; // example field
    // Declare and initialize hwmPosition and rebuildPosition volatile variables, as appropriate

    public boolean hasActivityAndNotEndOfStream(final long nowNs) {
        boolean isActive = true;

        if (((lastPacketTimestampNs + imageLivenessTimeoutNs) - nowNs < 0) ||
                (isEndOfStream && rebuildPosition.getVolatile() >= hwmPosition.get())) {
            isActive = false;
        }

        return isActive;
    }

    public static void main(String[] args) {
        Main main = new Main();
        // Set values for example fields, as appropriate
        long nowNs = System.nanoTime();
        boolean isActive = main.hasActivityAndNotEndOfStream(nowNs);
        System.out.println("Activity is " + (isActive ? "active" : "inactive"));
    }

    @Test
    public void testHasActivityAndNotEndOfStream_whenActive_shouldReturnTrue() {
        // Set current time to be within imageLivenessTimeoutNs and not at the end of the stream
        long nowNs = lastPacketTimestampNs + imageLivenessTimeoutNs / 2;

        boolean isActive = hasActivityAndNotEndOfStream(nowNs);

        Assert.assertEquals("isActive should be true when activity is in progress and the stream has not ended",
                true, String.valueOf(isActive));
    }

    @Test
    public void testHasActivityAndNotEndOfStream_whenInactive_shouldReturnFalse() {
        // Set current time to be greater than imageLivenessTimeoutNs and not at the end of the stream
        long nowNs = lastPacketTimestampNs + imageLivenessTimeoutNs * 2;

        boolean isActive = hasActivityAndNotEndOfStream(nowNs);

        Assert.assertEquals("isActive should be false when activity is not in progress and the stream has not ended",
                false, String.valueOf(isActive));
    }

    @Test
    public void testHasActivityAndNotEndOfStream_whenStreamEnded_shouldReturnFalse() {
        // Set current time to be within imageLivenessTimeoutNs but at the end of the stream
        long nowNs = lastPacketTimestampNs + imageLivenessTimeoutNs / 2;
        isEndOfStream = true;
        hwmPosition.set(100); // example value for hwmPosition
        rebuildPosition.set(200); // example value for rebuildPosition

        boolean isActive = hasActivityAndNotEndOfStream(nowNs);

        Assert.assertEquals("isActive should be false when the stream has ended", false, isActive);
    }
}