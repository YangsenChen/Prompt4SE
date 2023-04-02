

## user: 

Now with the following code
    ```python
    def _is_iterable(item):
    """ Checks if an item is iterable (list, tuple, generator), but not string """
    return isinstance(item, collections.Iterable) and not isinstance(item, six.string_types)
    ```
    Can you generate another code that is different from this one, but produces the same output given the same input?

## chatgpt: 

Yes, here's another way to implement the same `is_iterable` function.

```python
def is_iterable(item):
    """ Checks if an item is iterable (list, tuple, generator), but not string """
    try:
        iter(item)
        return not isinstance(item, str)
    except TypeError:
        return False
```

This implementation uses a `try-except` block instead of `isinstance` to check if an object is iterable. The `iter()` function is called on the `item` and if it throws a `TypeError`, we know the object is not iterable. If it is successful, we check whether the object is a string before returning the boolean value. This function should work the same way as the original implementation.