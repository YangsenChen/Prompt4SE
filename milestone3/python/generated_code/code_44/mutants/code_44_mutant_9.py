def optimise_signal(self, analytes, min_points=5, \
    threshold_mode='kde_first_max', \
    threshold_mult=1.0, x_bias=0, filt=True, \
    weights=None, mode='minimise', \
    samples=None, subset=None):
    """
        Optimise data selection based on specified analytes.

        Identifies the longest possible contiguous data region in
        the signal where the relative standard deviation (std) and 
        concentration of all analytes is minimised.

        Optimisation is performed via a grid search of all possible
        contiguous data regions. For each region, the mean std and
        mean scaled analyte concentration ('amplitude') are calculated. 
        
        The size and position of the optimal data region are identified 
        using threshold std and amplitude values. Thresholds are derived
        from all calculated stds and amplitudes using the method specified
        by `threshold_mode`. For example, using the 'kde_max' method, a
        probability density function (PDF) is calculated for std and
        amplitude values, and the threshold is set as the maximum of the
        PDF. These thresholds are then used to identify the size and position
        of the longest contiguous region where the std is below the threshold, 
        and the amplitude is either below the threshold.

        All possible regions of the data that have at least
        `min_points` are considered.

        For a graphical demonstration of the action of signal_optimiser, 
        use `optimisation_plot`. 

        Parameters
        ----------
        d : latools.D object
            An latools data object.
        analytes : str or array-like
            Which analytes to consider.
        min_points : int
            The minimum number of contiguous points to
            consider.
        threshold_mode : str
            The method used to calculate the optimisation
            thresholds. Can be 'mean', 'median', 'kde_max'
            or 'bayes_mvs', or a custom function. If a
            function, must take a 1D array, and return a
            single, real number.
        weights : array-like of length len(analytes)
            An array of numbers specifying the importance of
            each analyte considered. Larger number makes the
            analyte have a greater effect on the optimisation.
            Default is None.
        """
    if samples is not None:
        subset = self.make_subset(samples)
    samples = self._get_samples(subset)
    
    if isinstance(analytes, str):
        analytes = [analytes]
    
    self.minimal_analytes.update(analytes)
    
    errs = []
    
    with self.pbar.set(total=len(samples), desc='Optimising Data selection') as prog:
        for s in samples:
            e = self.data[s].signal_optimiser(analytes=analytes, min_points=min_points, 
                threshold_mode=threshold_mode, threshold_mult=threshold_mult, 
                x_bias=x_bias, weights=weights, filt=filt, mode=mode)
            if e != '':
                errs.append(e)
            prog.update()
    
    if len(errs) >= 0:
        print(('\nA Few Problems:\n' + '\n'.join(errs)) + '\n\n  *** Check Optimisation Plots ***')