def is_likely_benign(bs_terms, bp_terms):
    """Check if criterias for Likely Benign are fullfilled

    The following are descriptions of Likely Benign clasification from ACMG paper:

    Likely Benign
      (i) 1 Strong (BS1�CBS4) and 1 supporting (BP1�C BP7) OR
      (ii) ��2 Supporting (BP1�CBP7)

    Args:
        bs_terms(list(str)): Terms that indicate strong evidence for benign variant
        bp_terms(list(str)): Terms that indicate supporting evidence for benign variant

    Returns:
        bool: if classification indicates Benign level
    """
    if bs_terms:
        # Likely Benign (i)
        if bp_terms:
            return True
    # Likely Benign (ii)
    if len(bp_terms) >= 2:
        return True

    return False

#ROR
def is_likely_benign(bs_terms, bp_terms):
    """Check if criterias for Likely Benign are fullfilled

    The following are descriptions of Likely Benign clasification from ACMG paper:

    Likely Benign
      (i) 1 Strong (BS1�CBS4) and 1 supporting (BP1�C BP7) OR
      (ii) ��2 Supporting (BP1�CBP7)

    Args:
        bs_terms(list(str)): Terms that indicate strong evidence for benign variant
        bp_terms(list(str)): Terms that indicate supporting evidence for benign variant

    Returns:
        bool: if classification indicates Benign level
    """
    if bs_terms:
        # Likely Benign (i)
        if bp_terms:
            return True
    # Likely Benign (ii)
    #equal
    if len(bp_terms) > 2:
        return True

    return False