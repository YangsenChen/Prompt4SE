//import com.sun.jna.Pointer;
import jdk.incubator.foreign.MemoryHandles;
import jdk.incubator.foreign.MemorySegment;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class cuDoubleComplex {
    public double x; // Real part
    public double y; // Imaginary part

    public cuDoubleComplex(double x, double y) {
        this.x = x;
        this.y = y;
    }

     public cuDoubleComplex() {
         this.x = 0;
         this.y = 0;
     }
}
 class Pointer {
    private long address;

    public Pointer() {
        this.address = 0;
    }

    public Pointer(long address) {
        this.address = address;
    }

    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public interface Deallocator {
        void deallocate(Pointer p);
    }

    // Here is a simple factory method as an example
    public static Pointer create(long address, Deallocator deallocator) {
        // You might want to add some logic here to manage the deallocator
        return new Pointer(address);
    }
}

class cublasStatus {
    public static final int CUBLAS_STATUS_SUCCESS = 1;
    public static final int CUBLAS_STATUS_INVALID_VALUE = 0;
 }

public class Main {

    static {
        System.loadLibrary("cublas");
    }
    static {
        JCublas.initialize();
    }

    // Declare the native method
    public static native int cublasGetMatrixNative(int rows, int cols, Pointer A, int lda, Pointer B, int ldb);



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