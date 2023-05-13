from code_25 import *

import unittest
import numpy as np
import h5py
import os


class TestHDF5Manager(unittest.TestCase):

    def setUp(self):
        self.filename = 'test.h5'
        self.hdf5_manager = HDF5Manager(self.filename)

    def tearDown(self):
        self.hdf5_manager.close()
        if os.path.exists(self.filename):
            os.remove(self.filename)

    def test_init(self):
        self.assertIsInstance(self.hdf5_manager._file, h5py.File)
        self.assertIsInstance(self.hdf5_manager._group, h5py.Group)
        self.assertDictEqual(self.hdf5_manager._datasets, {})

    def test_create_empty_dataset(self):
        empty_dataset = self.hdf5_manager.create_empty_dataset('my_dataset', dtype=np.float64)

        self.assertTrue('my_dataset' in self.hdf5_manager._datasets)
        self.assertIsInstance(empty_dataset, h5py.Dataset)
        self.assertEqual(empty_dataset.dtype, np.float64)
        self.assertEqual(empty_dataset.shape, (1, 1))
        self.assertEqual(empty_dataset.maxshape, (None, None))

    def test_create_empty_dataset_existing(self):
        empty_dataset_1 = self.hdf5_manager.create_empty_dataset('my_dataset', dtype=np.float64)
        empty_dataset_2 = self.hdf5_manager.create_empty_dataset('my_dataset', dtype=np.float64)

        self.assertEqual(empty_dataset_1, empty_dataset_2)

    # def test_close(self):
    #     self.hdf5_manager.close()
    #     self.assertTrue(self.hdf5_manager._file.closed)


if __name__ == '__main__':
    unittest.main()