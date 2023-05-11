public double dot(MAVector v) {

    // Create an array to store product of each pair of corresponding elements
    double[] elementwiseProduct = new double[nelems];

    // Calculate the product of each pair of corresponding elements
    for (int k=0; k<nelems; k++)
      elementwiseProduct[k] = getDouble(k) * v.getDouble(k);

    // Calculate the sum of all products
    double sum = 0.0;
    for (int k=0; k<nelems; k++)
      sum += elementwiseProduct[k];

    // Return the final dot product
    return sum;
}