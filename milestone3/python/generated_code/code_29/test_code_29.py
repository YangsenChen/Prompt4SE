from code_29 import *

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

    # def test_resolve_expression_hstore_value(self):
    #     nested_value = {'x': 'y'}
    #     value = {'a': 1, 'b': HStoreValue(nested_value)}
    #     hstore = HStoreValue(value)
    #     resolved_hstore = hstore.resolve_expression()
    #     expected_value = {'a': 1, 'b': HStoreValue(nested_value)}
    #     self.assertEqual(resolved_hstore.value, expected_value)

    # def test_resolve_expression_nested_hstore_value(self):
    #     nested_value = {'x': HStoreValue({'key': 'value'})}
    #     value = {'a': 1, 'b': HStoreValue(nested_value)}
    #     hstore = HStoreValue(value)
    #     resolved_hstore = hstore.resolve_expression()
    #     expected_value = {'a': 1, 'b': HStoreValue({'x': HStoreValue({'key': 'value'})})}
    #     self.assertEqual(resolved_hstore.value, expected_value)


if __name__ == '__main__':
    unittest.main()