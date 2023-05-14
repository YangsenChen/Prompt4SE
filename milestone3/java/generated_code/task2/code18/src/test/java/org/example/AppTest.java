package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    @org.junit.Test
    public void testDotProduct() {
        // Test input vectors
        App.MAVector vector1 = new App.MAVector(new double[]{1.0, 2.0, 3.0});
        App.MAVector vector2 = new App.MAVector(new double[]{4.0, 5.0, 6.0});

        // Expected result
        double expectedDotProduct = 32.0;

        // Calculate dot product of the two vectors
        double dotProduct = vector1.dot(vector2);

        // Check the result against the expected value
        assertEquals(expectedDotProduct, dotProduct, 0.0001);
    }

//    @org.junit.Test(expected = IllegalArgumentException.class)
//    public void testInvalidVectorLength() {
//        // Test input vectors with different lengths
//        App.MAVector vector1 = new App.MAVector(new double[]{1.0, 2.0, 3.0});
//        App.MAVector vector2 = new App.MAVector(new double[]{4.0, 5.0});
//
//        // This should throw an IllegalArgumentException
//        vector1.dot(vector2);
//    }

    @Test
    public void testZeroDotProduct() {
        // Test input vectors
        App.MAVector vector1 = new App.MAVector(new double[]{1.0, 2.0, 3.0});
        App.MAVector vector2 = new App.MAVector(new double[]{0.0, 0.0, 0.0});

        // Expected result
        double expectedDotProduct = 0.0;

        // Calculate dot product of the two vectors
        double dotProduct = vector1.dot(vector2);

        // Check the result against the expected value
        assertEquals(expectedDotProduct, dotProduct, 0.0001);
    }
}
