public static int int3(RandomAccessFile raf) throws IOException {
    byte[] bytes = new byte[3];
    raf.readFully(bytes);
    int a = bytes[0] & 0xff;
    int b = bytes[1] & 0xff;
    int c = bytes[2] & 0xff;
    return int3(a, b, c);
}

private static int int3(int a, int b, int c) {
    return (a << 16) | (b << 8) | c;
}