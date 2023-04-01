

## user: 


Write a set of unit tests for the given Python code snippet to ensure that it is functioning correctly. The unit tests should verify that the output produced by the function matches the expected output, without producing false negatives.
```python
def _is_iterable(item):
    """ Checks if an item is iterable (list, tuple, generator), but not string """
    return isinstance(item, collections.Iterable) and not isinstance(item, six.string_types)
```


## chatgpt: 

Here are some unit tests to verify the functionality of the `_is_iterable` function:
```python
import collections
import six

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
```
These tests cover a variety of input types and should be sufficient to test that the `_is_iterable` function is working as expected.