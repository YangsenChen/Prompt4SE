from code_22 import *

import unittest
import numpy as np
# from validator import Validator


class TestValidatorMethods(unittest.TestCase):
    def setUp(self):
        self.validator = Validator()

    def test_not_tuple(self):
        value = ["compound", 1.2, 3.0]
        result = self.validator._is_compound_mfr_temperature_tuple(value)
        self.assertFalse(result)

    def test_wrong_len(self):
        value = ("compound", 1.2)
        result = self.validator._is_compound_mfr_temperature_tuple(value)
        self.assertFalse(result)

    def test_compound_not_str(self):
        value = (1, 1.2, 3.0)
        result = self.validator._is_compound_mfr_temperature_tuple(value)
        self.assertFalse(result)

    def test_mfr_not_float(self):
        value = ("compound", "mfr", 3.0)
        result = self.validator._is_compound_mfr_temperature_tuple(value)
        self.assertFalse(result)

    def test_temperature_not_float(self):
        value = ("compound", 1.2, "temp")
        result = self.validator._is_compound_mfr_temperature_tuple(value)
        self.assertFalse(result)

    def test_valid_parameters(self):
        value = ("compound", 1.2, 3.0)
        result = self.validator._is_compound_mfr_temperature_tuple(value)
        self.assertTrue(result)

    def test_valid_parameters_numpy_float32(self):
        value = ("compound", np.float32(1.2), np.float32(3.0))
        result = self.validator._is_compound_mfr_temperature_tuple(value)
        self.assertTrue(result)

    def test_valid_parameters_numpy_float64(self):
        value = ("compound", np.float64(1.2), np.float64(3.0))
        result = self.validator._is_compound_mfr_temperature_tuple(value)
        self.assertTrue(result)


if __name__ == '__main__':
    unittest.main()