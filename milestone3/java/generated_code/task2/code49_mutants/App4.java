package org.example;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final byte[] DATA = { (byte) 0b10101110, (byte) 0b00011010 };
    private static int nBit = 0;
    private static int curByte = 0;
    private static int nextByte = 0;

    public static void main(String[] args) throws IOException {
        runTests();
    }

    private static void runTests() throws IOException {
        testWithErrorSize(10);
        testWithDataSize(16);
        // testWithBitCount(4);
    }

    public static void testWithErrorSize(int size) throws IOException {
        try {
            peakNextBits(size);
        } catch (IllegalArgumentException e) {
            System.out.println("Test with error size passed: " + e.getMessage());
            return;
        }
        System.err.println("Test with error size failed");
    }

    public static void testWithDataSize(int size) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(DATA);
        int bitCount = 8;

        int count = 0;
        while (peakNextBits(bitCount) >= 0) {
            count++;
        }

        if (count == size) {
            System.out.println("Test with data size passed: " + count);
            return;
        }
        System.err.println("Test with data size failed. Count: " + count);
    }

    public static void testWithBitCount(int bitCount) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(DATA);

        int count = 0;
        while (peakNextBits(bitCount) >= 0) {
            count++;
        }

        if (count == 4) {
            System.out.println("Test with bit count passed: " + count);
            return;
        }
        System.err.println("Test with bit count failed. Count: " + count);
    }

    public static void advance() throws IOException {
        if ((nextByte = System.in.read()) == -1) {
            curByte = -1;
        } else {
            curByte = nextByte;
            nBit = 0;
        }
    }

    public static int peakNextBits(int n) throws IOException {
        if (n > 8)
            throw new IllegalArgumentException("N should be less then 8");
        if (nBit == 8) {
            advance();
            if (curByte == -1) {
                return -1;
            }
        }
        int[] bits = new int[16 - nBit];

        int cnt = 0;
        for (int i = nBit; i < 8; i++) {
            bits[cnt++] = (curByte >> (7 - i)) & 0x1;
        }

        for (int i = 0; i < 8; i++) {
            bits[cnt++] = (nextByte >> (7 - i)) & 0x1;
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result <<= 1;
            result |= bits[i];
        }

        nBit += n;
        if (nBit == 8) {
            advance();
        }

        return result;
    }
}
