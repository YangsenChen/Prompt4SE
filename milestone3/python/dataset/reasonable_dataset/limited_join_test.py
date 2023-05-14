from unittest import TestCase
from LimitedJoin import limited_join

def test_limited_join():
    # Test basic functionality
    assert limited_join(", ", ["apple", "banana", "cherry"]) == "apple, banana, cherry"

    # Test max_chars limit
    assert limited_join(", ", ["apple", "banana", "cherry"], max_chars=10) == "apple, ba..."

    # Test custom overflow marker
    assert limited_join(", ", ["apple", "banana", "cherry"], max_chars=10, overflow_marker="!!!") == "apple, ba!!!"

    # Test edge case where max_chars is smaller than the shortest item
    assert limited_join(", ", ["a", "bb", "ccc"], max_chars=1) == "..."

    # Test edge case where max_chars is equal to the length of the first item
    assert limited_join(": ", ["apple", "banana", "cherry"], max_chars=5) == "apple"

    # Test edge case where max_chars is equal to the length of the overflow marker
    assert limited_join(": ", ["apple", "banana", "cherry"], max_chars=3, overflow_marker=".") == "a."

    # Test edge case where the input list is empty
    assert limited_join(", ", []) == ""

