# def plot_boundaries(all_boundaries, est_file, algo_ids=None, title=None,
#                     output_file=None):
#     """Plots all the boundaries.
#
#     Parameters
#     ----------
#     all_boundaries: list
#         A list of np.arrays containing the times of the boundaries, one array
#         for each algorithm.
#     est_file: str
#         Path to the estimated file (JSON file)
#     algo_ids : list
#         List of algorithm ids to to read boundaries from.
#         If None, all algorithm ids are read.
#     title : str
#         Title of the plot. If None, the name of the file is printed instead.
#     """
#     import matplotlib.pyplot as plt
#     N = len(all_boundaries)  # Number of lists of boundaries
#     if algo_ids is None:
#         algo_ids = io.get_algo_ids(est_file)
#
#     # Translate ids
#     for i, algo_id in enumerate(algo_ids):
#         algo_ids[i] = translate_ids[algo_id]
#     algo_ids = ["GT"] + algo_ids
#
#     figsize = (6, 4)
#     plt.figure(1, figsize=figsize, dpi=120, facecolor='w', edgecolor='k')
#     for i, boundaries in enumerate(all_boundaries):
#         color = "b"
#         if i == 0:
#             color = "g"
#         for b in boundaries:
#             plt.axvline(b, i / float(N), (i + 1) / float(N), color=color)
#         plt.axhline(i / float(N), color="k", linewidth=1)
#
#     # Format plot
#     _plot_formatting(title, est_file, algo_ids, all_boundaries[0][-1], N,
#                      output_file)


import numpy as np
import matplotlib.pyplot as plt

# Mock data and functions
def get_mock_boundaries():
    return [np.array([1, 3, 5]), np.array([2, 4, 6])]

def get_mock_algo_ids():
    return ["A1", "A2"]

def get_mock_est_file():
    return "path/to/file.json"

# Assuming translate_ids is a dictionary from ids to their translated versions
# Please adjust as needed based on your actual ids
translate_ids = {"A1": "Algorithm 1", "A2": "Algorithm 2"}

# Adjust the plot formatting
def _plot_formatting(title, est_file, algo_ids, max_x, N, output_file):
    plt.title(title if title else est_file)
    plt.ylim(0, N)
    plt.xlim(0, max_x + 1)
    plt.yticks(np.linspace(0.5 / float(N), 1 - 1.5 / float(N), N), algo_ids)
    plt.xlabel('Time')
    plt.tight_layout()

    if output_file:
        plt.savefig(output_file)
    else:
        plt.show()

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
        import io

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

def main():
    all_boundaries = get_mock_boundaries()
    est_file = get_mock_est_file()
    algo_ids = get_mock_algo_ids()
    plot_boundaries(all_boundaries, est_file, algo_ids, title="Example Plot")

if __name__ == "__main__":
    main()