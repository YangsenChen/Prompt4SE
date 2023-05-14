import collections
import six
from IsIterabl import _is_iterable

def test_is_iterable():
    assert _is_iterable([]) == True
    assert _is_iterable(()) == True
    assert _is_iterable((x for x in range(3))) == True
    assert _is_iterable("") == False
    assert _is_iterable("abc") == False
    assert _is_iterable("1,2,3") == False
    assert _is_iterable(123) == False
    assert _is_iterable(True) == False
    assert _is_iterable(False) == False