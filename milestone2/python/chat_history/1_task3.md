

## user: 

Now with the following code
    ```python
    def limited_join(sep, items, max_chars=30, overflow_marker="..."):
    """Join a number of strings to one, limiting the length to *max_chars*.

    If the string overflows this limit, replace the last fitting item by
    *overflow_marker*.

    Returns: joined_string
    """
    full_str = sep.join(items)
    if len(full_str) < max_chars:
        return full_str

    n_chars = 0
    n_items = 0
    for j, item in enumerate(items):
        n_chars += len(item) + len(sep)
        if n_chars < max_chars - len(overflow_marker):
            n_items += 1
        else:
            break

    return sep.join(list(items[:n_items]) + [overflow_marker])
    ```
    Can you generate another code that is different from this one, but produces the same output given the same input?

## chatgpt: 

Yes, one possible alternative implementation for `limited_join` is:

```python
def limited_join(sep, items, max_chars=30, overflow_marker="..."):
    joined_items = sep.join(items)

    if len(joined_items) <= max_chars:
        return joined_items

    truncated_items = []
    curr_len = 0
    overflow = False

    for item in items:
        if curr_len + len(item) + len(sep) <= max_chars - len(overflow_marker):
            curr_len += len(item) + len(sep)
            truncated_items.append(item)
        else:
            overflow = True
            break

    if overflow:
        truncated_items[-1] = overflow_marker

    return sep.join(truncated_items)
```

This implementation uses a slightly different approach where it iteratively adds each item to a list of truncated items as long as the current length of the joined string does not exceed the maximum length minus the length of the overflow marker. Once that limit is reached, if overflow is True, the last item in the truncated_items list is replaced with the overflow marker. Finally, the truncated items are joined together using the separator.