

## user: 

Now with the following code
    ```python
    def tag_to_text(tag):
    """
    :param tag: Beautiful soup tag
    :return: Flattened text
    """
    out = []
    for item in tag.contents:
        # If it has a name, it is a tag
        if item.name:
            out.append(tag_to_text(item))
        else:
            # Just text!
            out.append(item)

    return ' '.join(out)
    ```
    Can you generate another code that is different from this one, but produces the same output given the same input?

## chatgpt: 

Certainly! Here's an alternative implementation of `tag_to_text` that should produce the same output as the original function:

```python
def tag_to_text(tag):
    """
    :param tag: Beautiful soup tag
    :return: Flattened text
    """
    if not tag.contents:
        # Base case
        return tag.string.strip() if tag.string else ""

    # Recursive case
    out = [tag_to_text(child) for child in tag.contents if hasattr(child, "contents")]
    return " ".join(filter(None, out))
```

This implementation recursively flattens the contents of a given tag, returning a single string of text with all whitespace characters removed. It uses the `hasattr` function to check if a child tag has contents (i.e. is not a NavigableString object). If a child tag does have contents, it is recursively passed to the `tag_to_text` function to be flattened. Finally, the function returns a joined string of all non-empty child text nodes with any leading/trailing whitespace removed.