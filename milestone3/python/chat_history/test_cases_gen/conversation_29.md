## user:
def resolve_expression(self, *args, **kwargs):
        """Resolves expressions inside the dictionary."""

        result = dict()
        for key, value in self.value.items():
            if hasattr(value, 'resolve_expression'):
                result[key] = value.resolve_expression(
                    *args, **kwargs)
            else:
                result[key] = value

        return HStoreValue(result)
for this code, your task is to make it runnable without bugs  give me the full code where the main function calling this function
## assistant:
Here's the updated code, including a main function and all required classes and methods for it to run successfully:

```
class HStoreValue:
    def __init__(self, value):
        self.value = value

    def __str__(self):
        return str(self.value)

    def resolve_expression(self, *args, **kwargs):
        """Resolves expressions inside the dictionary."""

        result = dict()
        for key, value in self.value.items():
            if hasattr(value, 'resolve_expression'):
                result[key] = value.resolve_expression(
                    *args, **kwargs)
            else:
                result[key] = value

        return HStoreValue(result)


def main():
    some_data = {
        'integer': 42,
        'string': 'Hello, world!',
        'nested_hstore': HStoreValue({'key': 'value'})
    }

    hstore = HStoreValue(some_data)
    print(f"Original HStoreValue: {hstore}")
    
    resolved_hstore = hstore.resolve_expression()
    print(f"Resolved HStoreValue: {resolved_hstore}")


if __name__ == '__main__':
    main()
```

In this code, I've defined the `HStoreValue` class that takes a dictionary-like `value` parameter. An `__init__` method initializes the class instance with the given `value`. The `__str__` method enables a readable string representation when printing the HStoreValue object.

The `main()` function tests the `HStoreValue` class and the `resolve_expression()` method. It creates an `HStoreValue` object with sample data and then calls the `resolve_expression()` method on it. Finally, it prints both the original and resolved `HStoreValue` objects.
## user:
then write a class to test every line of the code you just generated
## assistant:
Here's a test class to thoroughly test the provided `HStoreValue` class using the `unittest` framework:

```python
import unittest


class TestHStoreValue(unittest.TestCase):

    def test_init(self):
        value = {'a': 1, 'b': 2}
        hstore = HStoreValue(value)
        self.assertEqual(hstore.value, value)

    def test_str(self):
        value = {'a': 1, 'b': 2}
        hstore = HStoreValue(value)
        self.assertEqual(str(hstore), str(value))

    def test_resolve_expression_plain_value(self):
        value = {'a': 1, 'b': 2}
        hstore = HStoreValue(value)
        resolved_hstore = hstore.resolve_expression()
        self.assertEqual(resolved_hstore.value, value)

    def test_resolve_expression_hstore_value(self):
        nested_value = {'x': 'y'}
        value = {'a': 1, 'b': HStoreValue(nested_value)}
        hstore = HStoreValue(value)
        resolved_hstore = hstore.resolve_expression()
        expected_value = {'a': 1, 'b': HStoreValue(nested_value)}
        self.assertEqual(resolved_hstore.value, expected_value)

    def test_resolve_expression_nested_hstore_value(self):
        nested_value = {'x': HStoreValue({'key': 'value'})}
        value = {'a': 1, 'b': HStoreValue(nested_value)}
        hstore = HStoreValue(value)
        resolved_hstore = hstore.resolve_expression()
        expected_value = {'a': 1, 'b': HStoreValue({'x': HStoreValue({'key': 'value'})})}
        self.assertEqual(resolved_hstore.value, expected_value)


if __name__ == '__main__':
    unittest.main()
```

In the `TestHStoreValue` class, five unit tests have been added to test the behavior of the `HStoreValue` class:

1. `test_init` tests the proper initialization of the HStoreValue object.
2. `test_str` tests the string representation of the HStoreValue object.
3. `test_resolve_expression_plain_value` tests resolution of plain values (non-HStoreValue items) in the HStoreValue object.
4. `test_resolve_expression_hstore_value` tests resolution of HStoreValue items at the top level of the HStoreValue object.
5. `test_resolve_expression_nested_hstore_value` tests resolution of nested HStoreValue items in the HStoreValue object.
