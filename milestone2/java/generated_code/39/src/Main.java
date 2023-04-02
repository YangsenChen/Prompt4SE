import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <file>");
            return;
        }

        try {
            RandomAccessFile raf = new RandomAccessFile(args[0], "r");
            int value = int3(raf);
            System.out.println(value);
            value = int3(raf);
            System.out.println(value);
            raf.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int int3(RandomAccessFile raf) throws IOException {
        int a = raf.read();
        int b = raf.read();
        int c = raf.read();

        return int3(a, b, c);
    }

//    // chatgpt generated  semantically equivalent code: test pass 1/3
//    // chatgpt made the following change: Instead of reading one byte at a time, we read all three bytes into a byte array using the readFully method of RandomAccessFile. We then pass the three bytes to the existing int3 method, with a bit-wise AND operation to convert each byte to an unsigned integer.
//    public static int int3(RandomAccessFile raf) throws IOException {
//        byte[] bytes = new byte[3];
//        raf.readFully(bytes);
//        return int3(bytes[0] & 0xFF, bytes[1] & 0xFF, bytes[2] & 0xFF);
//    }

    public static int int3(int a, int b, int c) {
        return ((a & 0xFF) << 16) | ((b & 0xFF) << 8) | (c & 0xFF);
    }

    @Test
    public void testInt3() {
        byte[] bytes = {(byte) 0xFF, (byte) 0x7F, 0x00};
        RandomAccessFile raf = null;
        try {
            File file = File.createTempFile("test", ".bin");
            raf = new RandomAccessFile(file, "rw");
            raf.write(bytes);
            raf.seek(0);
            int value = int3(raf);
            assertEquals(2147483647, value);
        } catch (IOException e) {
            fail("Unexpected exception: " + e.getMessage());
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    @Test
    public void testInt3Zero() {
        byte[] bytes = {0, 0, 0};
        RandomAccessFile raf = null;
        try {
            File file = File.createTempFile("test", ".bin");
            raf = new RandomAccessFile(file, "rw");
            raf.write(bytes);
            raf.seek(0);
            int value = int3(raf);
            assertEquals(0, value);
        } catch (IOException e) {
            fail("Unexpected exception: " + e.getMessage());
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    @Test
    public void testInt3Negative() {
        byte[] bytes = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        RandomAccessFile raf = null;
        try {
            File file = File.createTempFile("test", ".bin");
            raf = new RandomAccessFile(file, "rw");
            raf.write(bytes);
            raf.seek(0);
            int value = int3(raf);
            assertEquals(-1, value);
        } catch (IOException e) {
            fail("Unexpected exception: " + e.getMessage());
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                // Ignore
            }
        }
    }
}