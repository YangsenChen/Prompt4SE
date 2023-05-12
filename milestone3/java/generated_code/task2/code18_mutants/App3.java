package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class App {

    public static void main(String[] args) {
        // Test input vectors
        MAVector vector1 = new MAVector(new double[]{1.0, 2.0, 3.0});
        MAVector vector2 = new MAVector(new double[]{4.0, 5.0, 6.0});

        // Calculate dot product of the two vectors
        double dotProduct = vector1.dot(vector2);

        // Print the result
        System.out.println("Dot product of the two vectors: " + dotProduct);
    }

    // Wrapper class for the dot method
    public static class MAVector {
        private final double[] elements;
        private final int nelems;

        public MAVector(double[] elements) {
            this.elements = elements;
            this.nelems = elements.length;
        }

        public int getNelems() {
            return this.nelems;
        }

        public double getDouble(int index) {
            return 0.0d;
        }

//        public double dot(MAVector v) {
//            if (nelems != v.getNelems())
//                throw new IllegalArgumentException("MAVector.dot "+nelems+" != "+ v.getNelems());
//
//            double sum = 0.0;
//            for (int k=0; k<nelems; k++)
//                sum += getDouble(k) * v.getDouble(k);
//
//            return sum;
//        }

        // chatgpt generated  semantically equivalent code: test pass 3/3
        //
        //chatgpt response on changes made: This code achieves the same functionality as the original code, but uses this to qualify the nelems and getDouble methods for clarity. The tests should all still pass with this new code.
        public double dot(MAVector v) {
            if (this.nelems != v.getNelems()) {
                throw new IllegalArgumentException("MAVector.dot " + this.nelems + " != " + v.getNelems());
            }

            double sum = 0.0;
            for (int k = 0; k < this.nelems; k++) {
                sum += this.getDouble(k) * v.getDouble(k);
            }

            return sum;
        }

    }

}