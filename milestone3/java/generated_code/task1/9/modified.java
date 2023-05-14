// New method to handle native buffer creation
private static DoubleBuffer createNativeDoubleBuffer(int length) {
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(length * 8);
    byteBuffer.order(ByteOrder.nativeOrder());
    return byteBuffer.asDoubleBuffer();
}

public static int getCuBlasMatrix(int rows, int cols, Pointer A, int lda, cuDoubleComplex[] B, int offsetB, int ldb) {
    int bufferLength = B.length * 2; // Each complex number takes two double bytes
    int bufferIndex;
    double[] tempB = new double[bufferLength];

    // Call the native method to get the matrix and store it in the direct buffer
    DoubleBuffer directBuffer = createNativeDoubleBuffer(bufferLength);
    int status = getCuBlasMatrixNative(rows, cols, 16, A, lda, Pointer.to(directBuffer).withByteOffset(offsetB * 8 * 2), ldb, bufferLength);

    // Iterate over the direct buffer to extract data
    if (status == CUBLAS_SUCCESS) {
        directBuffer.rewind();
        bufferIndex = 0;
        while (directBuffer.remaining() >= 2) {
            tempB[bufferIndex] = directBuffer.get();
            tempB[bufferIndex + 1] = directBuffer.get();
            bufferIndex += 2;
        }
    }

    // Move the data from the temporary array to the return array
    bufferIndex = offsetB;
    for (int c = 0; c < cols; c++) {
        for (int r = 0; r < rows; r++) {
            B[bufferIndex].x = tempB[bufferIndex * 2];
            B[bufferIndex].y = tempB[bufferIndex * 2 + 1];
            bufferIndex++;
        }
        bufferIndex += ldb - rows; // Skip to the start of the next column
    }

    return checkResult(status);
}