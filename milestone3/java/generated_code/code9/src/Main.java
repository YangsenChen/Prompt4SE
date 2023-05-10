import jdk.incubator.foreign.MemoryHandles;
import jdk.incubator.foreign.MemorySegment;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    static {
        System.loadLibrary("cublas");
    }

    @Test
    public void testGetMatrixSuccess() {
        int rows = 2;
        int cols = 2;
        int lda = 2;
        int ldb = 2;
        int offsetB = 0;
        cuDoubleComplex[] B = {new cuDoubleComplex(), new cuDoubleComplex(), new cuDoubleComplex(), new cuDoubleComplex()};
        Pointer A = new Pointer();
        int result = cublasGetMatrix(rows, cols, A, lda, B, offsetB, ldb);
        if (result == cublasStatus.CUBLAS_STATUS_SUCCESS) {
            cuDoubleComplex[] expected = {new cuDoubleComplex(1, 2), new cuDoubleComplex(3, 4),
                    new cuDoubleComplex(5, 6), new cuDoubleComplex(7, 8)};
            for (int i = 0; i < B.length; i++) {
                assertEquals(expected[i].x, B[i].x);
                assertEquals(expected[i].y, B[i].y);
            }
        } else {
            assertEquals(cublasStatus.CUBLAS_STATUS_SUCCESS, result);
        }
    }

    @Test
    public void testGetMatrixFail() {
        int rows = 2;
        int cols = 2;
        int lda = 2;
        int ldb = 2;
        int offsetB = 0;
        cuDoubleComplex[] B = {new cuDoubleComplex(), new cuDoubleComplex(), new cuDoubleComplex(), new cuDoubleComplex()};
        Pointer A = new Pointer();
        // Simulate failed cublasGetMatrixNative call by passing invalid arguments
        int status = cublasGetMatrixNative(rows, cols, 16, A, lda, Pointer.NULL, ldb);
        int result = cublasStatus.CUBLAS_STATUS_SUCCESS;
        // The following block is similar to `checkResult` function
        if (status != cublasStatus.CUBLAS_STATUS_SUCCESS) {
            result = status;
        }
        if (status == cublasStatus.CUBLAS_STATUS_SUCCESS && B.length == 0) {
            result = cublasStatus.CUBLAS_STATUS_INVALID_VALUE;
        }
        assertEquals(result, cublasStatus.CUBLAS_STATUS_INVALID_VALUE);
    }

    @Test
    public void testGetMatrixEmpty() {
        int rows = 0;
        int cols = 0;
        int lda = 0;
        int ldb = 0;
        int offsetB = 0;
        cuDoubleComplex[] B = {};
        Pointer A = new Pointer();
        int result = cublasGetMatrix(rows, cols, A, lda, B, offsetB, ldb);
        assertEquals(result, cublasStatus.CUBLAS_STATUS_INVALID_VALUE);
    }

    public static int cublasGetMatrix(int rows, int cols, Pointer A, int lda, cuDoubleComplex[] B, int offsetB, int ldb) {
        ByteBuffer byteBufferB = ByteBuffer.allocateDirect(B.length * 8 * 2);
        byteBufferB.order(ByteOrder.nativeOrder());
        DoubleBuffer doubleBufferB = byteBufferB.asDoubleBuffer();
        MemorySegment dBuffer = MemorySegment.ofByteBuffer(byteBufferB);
        Pointer dBufferPointer = Pointer.create(MemoryHandles.getBaseAddress(dBuffer), new Pointer.Deallocator() {
            @Override
            public void deallocate(Pointer p) {
                dBuffer.close();
            }
        });
        int status = cublasGetMatrix(rows, cols, A, lda, dBufferPointer, offsetB, ldb);
        if (status == cublasStatus.CUBLAS_STATUS_SUCCESS) {
            doubleBufferB.rewind();
            for (int c = 0; c < cols; c++) {
                for (int r = 0; r < rows; r++) {
                    int index = c * ldb + r + offsetB;
                    B[index].x = doubleBufferB.get(index * 2 + 0);
                    B[index].y = doubleBufferB.get(index * 2 + 1);
                }
            }
        }
        return checkResult(status);
    }
}