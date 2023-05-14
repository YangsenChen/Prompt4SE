public static int[] readInts(RandomAccessFile raf, int n) throws IOException {
    if (n < 0) {
        throw new IllegalArgumentException("Invalid number of integers: " + n);
    }

    int[] integers = new int[n];
    byte[] buffer = new byte[4];

    for (int i = 0; i < n; i++) {
        raf.readFully(buffer);
        integers[i] = byteArrayToInt(buffer);
    }

    return integers;
}

private static int byteArrayToInt(byte[] bytes) {
    int value = 0;
    for (int i = 0; i < 4; i++) {
        value <<= 8;
        value |= (bytes[i] & 0xff);
    }
    return value;
}