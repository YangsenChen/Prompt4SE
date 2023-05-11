public int[] peakNextBits(int[] buffer, int startIndex, int n) throws IOException {
    int endIndex = startIndex + n;
    if (endIndex > buffer.length)
        throw new IllegalArgumentException("End index should be less than the buffer length");

    int[] bits = new int[n];

    int currentByteIndex = startIndex / 8;
    int currentByteOffset = startIndex % 8;
    int remainingBits = n;

    while (remainingBits > 0) {
        int bitsToReadFromCurrentByte = Math.min(8 - currentByteOffset, remainingBits);
        int mask = (1 << bitsToReadFromCurrentByte) - 1;
        int bitShift = 8 - currentByteOffset - bitsToReadFromCurrentByte;

        int value = (buffer[currentByteIndex] >> bitShift) & mask;
        bits[n - remainingBits] = value;

        remainingBits -= bitsToReadFromCurrentByte;
        currentByteIndex++;
        currentByteOffset = 0;

        if (currentByteIndex < endIndex / 8) {
            mask = (1 << remainingBits) - 1;
            bitShift = 8 - remainingBits;

            value = buffer[currentByteIndex] >> bitShift & mask;
            bits[n - remainingBits - 1] |= value;

            remainingBits = 0;
            break;
        }
    }

    return bits;
}