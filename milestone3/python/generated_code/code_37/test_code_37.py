from code_37 import *

import unittest
import numpy as np
import os
import tempfile

# from your_module import get_mock_boundaries, get_mock_algo_ids, \
#     get_mock_est_file, translate_ids, _plot_formatting, plot_boundaries

# This assumes your_module contains the functions provided in the previous answer
class TestBoundaryPlot(unittest.TestCase):
    def test_get_mock_boundaries(self):
        boundaries = get_mock_boundaries()
        self.assertEqual(len(boundaries), 2)
        self.assertTrue(isinstance(boundaries[0], np.ndarray))
        self.assertTrue(isinstance(boundaries[1], np.ndarray))

    def test_get_mock_algo_ids(self):
        algo_ids = get_mock_algo_ids()
        self.assertEqual(len(algo_ids), 2)
        self.assertEqual(algo_ids, ["A1", "A2"])

    def test_get_mock_est_file(self):
        est_file = get_mock_est_file()
        self.assertEqual(est_file, "path/to/file.json")

    def test_translate_ids(self):
        self.assertEqual(translate_ids["A1"], "Algorithm 1")
        self.assertEqual(translate_ids["A2"], "Algorithm 2")

    def test_plot_formatting(self):
        import matplotlib.pyplot as plt
        title = "Test Title"
        est_file = "path/to/file.json"
        algo_ids = ["A1", "A2"]
        max_x = 10
        N = 2
        output_file = tempfile.NamedTemporaryFile(delete=False).name

        try:
            # Test with output_file
            plt.figure()
            _plot_formatting(title, est_file, algo_ids, max_x, N, output_file)
            plt.close()
            self.assertTrue(os.path.isfile(output_file))

            # Test without output_file
            plt.figure()
            _plot_formatting(title, est_file, algo_ids, max_x, N, output_file=None)
            plt.close()

        finally:
            os.remove(output_file)

    def test_plot_boundaries(self):
        all_boundaries = get_mock_boundaries()
        est_file = get_mock_est_file()
        algo_ids = get_mock_algo_ids()
        title = "Test Title"
        output_file = tempfile.NamedTemporaryFile(delete=False).name

        try:
            # Test with output_file
            plot_boundaries(all_boundaries, est_file, algo_ids, title, output_file)
            self.assertTrue(os.path.isfile(output_file))

            # Test without output_file
            plot_boundaries(all_boundaries, est_file, algo_ids, title, output_file=None)

        finally:
            os.remove(output_file)


if __name__ == "__main__":
    unittest.main()