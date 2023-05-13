def plot_boundaries(all_boundaries, est_file, algo_ids=None, title=None,
                    output_file=None):
    """Plots all the boundaries.

    Parameters
    ----------
    all_boundaries: list
        A list of np.arrays containing the times of the boundaries, one array
        for each algorithm.
    est_file: str
        Path to the estimated file (JSON file)
    algo_ids : list
        List of algorithm ids to to read boundaries from.
        If None, all algorithm ids are read.
    title : str
        Title of the plot. If None, the name of the file is printed instead.
    """
    import matplotlib.pyplot as plt
    N = len(all_boundaries)  # Number of lists of boundaries
    if algo_ids is None:
        algo_ids = io.get_algo_ids(est_file)

    # Translate ids
    for i, algo_id in enumerate(algo_ids):
        algo_ids[i] = translate_ids[algo_id]
    algo_ids = ["GT"] + algo_ids

    figsize = (6, 4)
    plt.figure(1, figsize=figsize, dpi=120, facecolor='w', edgecolor='k')
    for i, boundaries in enumerate(all_boundaries):
        color = "b"
        if i == 0:
            color = "g"
        for b in boundaries:
            plt.axvline(b, i / float(N), (i + 1) / float(N), color=color)
        plt.axhline(i / float(N), color="k", linewidth=1)

    # Format plot
    _plot_formatting(title, est_file, algo_ids, all_boundaries[0][-1], N,
                     output_file)