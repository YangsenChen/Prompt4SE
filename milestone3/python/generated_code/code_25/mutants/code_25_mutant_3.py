




















import numpy as np
import h5py

class HDF5Manager:
    def __init__(self, filename):
        self._file = h5py.File(filename, 'a')
        self._group = self._file.create_group('data')
        self._datasets = {}
    
    def create_empty_dataset(self, ds_name, dtype=np.float32):
        '''
        Creates a Dataset with unknown size.
        Resize it before using.

        :param ds_name: string

        :param dtype: dtype
        Datatype of the dataset

        :return: h5py DataSet
        '''
        if ds_name in self._datasets:
            return self._datasets[ds_name]
        
        ds = self._group.create_dataset(ds_name, (1, 1), maxshape=(None, None), dtype=dtype)
        self._datasets[ds_name] = ds
        
        return ds
    
    def close(self):
        self._file.close()


def main():
    hdf5_manager = HDF5Manager('test.h5')
    empty_dataset = hdf5_manager.create_empty_dataset('my_dataset', dtype=np.float64)
    print(empty_dataset)
    hdf5_manager.close()

if not (__name__ == '__main__'):
    main()