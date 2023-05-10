import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "path/to/your/file.txt";
        String encoding = "UTF-8";
        byte[] result = readFromFile(filePath, encoding);

        // Do something with the result
        System.out.println(new String(result, encoding));
    }

    public static byte[] readFromFile(String filePath, String encoding) throws IOException {
        Reader reader = new FileReader(filePath);
        try {
            return toByteArray(reader, encoding);
        } finally {
            reader.close();
        }
    }

    public static byte[] toByteArray(Reader input, String encoding) throws IOException {
        return toByteArray(input, Charsets.toCharset(encoding));
    }

    @Test
    public void testToByteArray() throws IOException {
        String inputString = "Hello, World!";
        String encoding = "UTF-8";
        Reader reader = new StringReader(inputString);
        byte[] expectedOutput = inputString.getBytes(encoding);
        byte[] actualOutput = toByteArray(reader, encoding);
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testReadFromFile() throws IOException {
        String filePath = "src/main/resources/test.txt";
        String encoding = "UTF-8";
        byte[] expectedOutput = "Test file contents.\n".getBytes(encoding);
        byte[] actualOutput = readFromFile(filePath, encoding);
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testMain() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, "UTF-8");
        System.setOut(ps);
        String[] args = {};
        Main.main(args);
        String expectedOutput = "Hello, World!\n";
        String actualOutput = new String(baos.toByteArray(), "UTF-8");
        assertEquals(expectedOutput, actualOutput);
        System.setOut(System.out);
    }
}