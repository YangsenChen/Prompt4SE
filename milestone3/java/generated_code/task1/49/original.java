public int peakNextBits(int n) throws IOException {
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

        return result;
    }