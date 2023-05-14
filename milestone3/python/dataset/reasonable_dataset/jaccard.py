def __jaccard(int_a, int_b):  # pragma: no cover
    '''Jaccard similarity between two intervals

    Parameters
    ----------
    int_a, int_b : np.ndarrays, shape=(2,)

    Returns
    -------
    Jaccard similarity between intervals
    '''
    ends = [int_a[1], int_b[1]]
    if ends[1] < ends[0]:
        ends.reverse()

    starts = [int_a[0], int_b[0]]
    if starts[1] < starts[0]:
        starts.reverse()

    intersection = ends[0] - starts[1]
    if intersection < 0:
        intersection = 0.

    union = ends[1] - starts[0]

    if union > 0:
        return intersection / union

    return 0.0

# ROR
def buggy_jaccard(int_a, int_b):  # pragma: no cover
    '''Jaccard similarity between two intervals

    Parameters
    ----------
    int_a, int_b : np.ndarrays, shape=(2,)

    Returns
    -------
    Jaccard similarity between intervals
    '''
    ends = [int_a[1], int_b[1]]
    if ends[1] < ends[0]:
        ends.reverse()

    starts = [int_a[0], int_b[0]]
    if starts[1] < starts[0]:
        starts.reverse()

    intersection = ends[0] - starts[1]
    if intersection < 0:
        intersection = 0.

    union = ends[1] - starts[0]

    # equal
    if union >= 0:
        return intersection / union

    return 0.0