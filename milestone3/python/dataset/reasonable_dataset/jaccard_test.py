import numpy as np
import unittest
from jaccard import __jaccard

def TestJaccard():

    int_a = np.array([1, 5])
    int_b = np.array([3, 6])
    assert(__jaccard(int_a, int_b) == 0.25)

    int_a = np.array([1, 5])
    int_b = np.array([6, 10])
    assert(__jaccard(int_a, int_b) == 0.0)

    int_a = np.array([1, 5])
    int_b = np.array([1, 5])
    assert(__jaccard(int_a, int_b) == 1.0)

    int_a = np.array([6, 10])
    int_b = np.array([1, 5])
    assert(__jaccard(int_a, int_b) == 0.0)

    int_a = np.array([1, 2])
    int_b = np.array([2, 3])
    assert(__jaccard(int_a, int_b) == 0.0)