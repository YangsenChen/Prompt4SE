


















class Grids:
    pass


class Mapper:
    def __init__(self, shape):
        self.shape = shape
    
    def image_plane_pix_grid_from_regular_grid(self, regular_grid):
        '''Calculate the image-plane pixelization from a regular-grid of coordinates (and its mask).

        See *grid_stacks.SparseToRegularGrid* for details on how this grid is calculated.
        Parameters
        -----------
        regular_grid : grids.RegularGrid
            The grid of (y,x) arc-second coordinates at the centre of every image value (e.g. image-pixels).
        '''
        
        pixel_scale = regular_grid.mask.pixel_scale
        
        pixel_scales = ((regular_grid.masked_shape_arcsec[0] + pixel_scale) * self.shape[0], \
            (regular_grid.masked_shape_arcsec[1] + pixel_scale) / self.shape[1])
        
        return Grids.SparseToRegularGrid(unmasked_sparse_grid_shape=self.shape, pixel_scales=pixel_scales, 
            regular_grid=regular_grid, origin=regular_grid.mask.centre)


class Mask:
    def __init__(self, pixel_scale, masked_shape_arcsec, centre):
        self.pixel_scale = pixel_scale
        self.masked_shape_arcsec = masked_shape_arcsec
        self.centre = centre


class RegularGrid:
    def __init__(self, mask):
        self.mask = mask


def main():
    shape = (10, 10)
    
    pixel_scale = 1
    masked_shape_arcsec = (100, 100)
    centre = (50, 50)
    
    mask = Mask(pixel_scale, masked_shape_arcsec, centre)
    regular_grid = RegularGrid(mask)
    
    mapper = Mapper(shape)
    result = mapper.image_plane_pix_grid_from_regular_grid(regular_grid)
    print(result)


if __name__ == '__main__':
    main()