public double dot(MAVector v) {

    if (nelems != v.getNelems())
        throw new IllegalArgumentException("MAVector.dot "+nelems+" != "+ v.getNelems());

    // Create a DoubleStream of elementwise products using the zip() method
    DoubleStream elementwiseProductStream = DoubleStreamEx
        .zip(Arrays.stream(getElements()), Arrays.stream(v.getElements()), 
                (a, b) -> a * b);

    // Calculate the sum of all products using the sum() method
    double sum = elementwiseProductStream.sum();

    // Return the final dot product
    return sum;
}