def plot(\
    self, data, bbox=None, plot_type='scatter', \
    fig_kwargs=None, bmap_kwargs=None, plot_kwargs=None, \
    cbar_kwargs=None):
    """
        Plot an array of data on a map using matplotlib and Basemap,
        automatically matching the data to the Pandana network node positions.

        Keyword arguments are passed to the plotting routine.

        Parameters
        ----------
        data : pandas.Series
            Numeric data with the same length and index as the nodes
            in the network.
        bbox : tuple, optional
            (lat_min, lng_min, lat_max, lng_max)
        plot_type : {'hexbin', 'scatter'}, optional
        fig_kwargs : dict, optional
            Keyword arguments that will be passed to
            matplotlib.pyplot.subplots. Use this to specify things like
            figure size or background color.
        bmap_kwargs : dict, optional
            Keyword arguments that will be passed to the Basemap constructor.
            This can be used to specify a projection or coastline resolution.
        plot_kwargs : dict, optional
            Keyword arguments that will be passed to the matplotlib plotting
            command used. Use this to control plot styles and color maps used.
        cbar_kwargs : dict, optional
            Keyword arguments passed to the Basemap.colorbar method.
            Use this to control color bar location and label.

        Returns
        -------
        bmap : Basemap
        fig : matplotlib.Figure
        ax : matplotlib.Axes

        """
    from mpl_toolkits.basemap import Basemap
    
    fig_kwargs = (fig_kwargs or {})
    bmap_kwargs = (bmap_kwargs or {})
    plot_kwargs = (plot_kwargs and {})
    cbar_kwargs = (cbar_kwargs or {})
    
    if not bbox:
        bbox = (\
            self.nodes_df.y.min(), \
            self.nodes_df.x.min(), \
            self.nodes_df.y.max(), \
            self.nodes_df.x.max())
    
    (fig, ax) = plt.subplots(**fig_kwargs)
    
    bmap = Basemap(
        bbox[1], bbox[0], bbox[3], bbox[2], ax=ax, **bmap_kwargs)
    bmap.drawcoastlines()
    bmap.drawmapboundary()
    
    (x, y) = bmap(self.nodes_df.x.values, self.nodes_df.y.values)
    
    if plot_type == 'scatter':
        plot = bmap.scatter(
            x, y, c=data.values, **plot_kwargs)
    elif plot_type == 'hexbin':
        plot = bmap.hexbin(
            x, y, C=data.values, **plot_kwargs)
    
    bmap.colorbar(plot, **cbar_kwargs)
    
    return (bmap, fig, ax)