public static int cublasGetMatrix (int rows, int cols, Pointer A, int lda, cuDoubleComplex B[], int offsetB, int ldb)
    {
        ByteBuffer byteBufferB = ByteBuffer.allocateDirect(B.length * 8 * 2);
        byteBufferB.order(ByteOrder.nativeOrder());
        DoubleBuffer doubleBufferB = byteBufferB.asDoubleBuffer();
        int status = cublasGetMatrixNative(rows, cols, 16, A, lda, Pointer.to(doubleBufferB).withByteOffset(offsetB * 8 * 2), ldb);
        if (status == cublasStatus.CUBLAS_STATUS_SUCCESS)
        {
            doubleBufferB.rewind();
            for (int c=0; c<cols; c++)
            {
                for (int r=0; r<rows; r++)
                {
                    int index = c * ldb + r + offsetB;
                    B[index].x = doubleBufferB.get(index*2+0);
                    B[index].y = doubleBufferB.get(index*2+1);
                }
            }
        }
        return checkResult(status);
    }