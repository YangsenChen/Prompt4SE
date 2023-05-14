public double dot(MAVector v) {

    if (nelems != v.getNelems())
      throw new IllegalArgumentException("MAVector.dot "+nelems+" != "+ v.getNelems());

    double sum = 0.0;
    for (int k=0; k<nelems; k++)
      sum += getDouble(k) * v.getDouble(k);

    return sum;
  }