from IsLikelyBenigh import is_likely_benign

def test_is_likely_benign():
    # Test 1: No BS or BP terms
    bs_terms = []
    bp_terms = []
    assert is_likely_benign(bs_terms, bp_terms) == False

    # Test 2: 1 Strong and 1 Supporting BP term
    bs_terms = ["BS1"]
    bp_terms = ["BP1"]
    assert is_likely_benign(bs_terms, bp_terms) == True

    # Test 3: 1 Strong and 2 Supporting BP terms
    bs_terms = ["BS1"]
    bp_terms = ["BP1", "BP2"]
    assert is_likely_benign(bs_terms, bp_terms) == True

    # Test 4: 2 Supporting BP terms
    bs_terms = []
    bp_terms = ["BP3", "BP4"]
    assert is_likely_benign(bs_terms, bp_terms) == True

    # Test 5: 1 Strong BP term
    bs_terms = []
    bp_terms = ["BP5"]
    assert is_likely_benign(bs_terms, bp_terms) == False

    # Test 6: 2 Strong BP terms
    bs_terms = ["BS2"]
    bp_terms = ["BP6", "BP7"]
    assert is_likely_benign(bs_terms, bp_terms) == False