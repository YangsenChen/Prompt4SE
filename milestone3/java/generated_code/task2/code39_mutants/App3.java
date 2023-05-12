package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        if (args.length < 1) {
            // System.out.println("Usage: java Main <file>");
            return;
        }

        try {
            RandomAccessFile raf = new RandomAccessFile(args[0], "r");
            int value = int3(raf);
            System.out.println(value);
            value = int3(raf);
            System.out.println(value);
            raf.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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


}
