from code_16 import *

import unittest
from io import StringIO



class TestCustomStringIO(unittest.TestCase):
    def test_init(self):
        custom_string_io = CustomStringIO()
        self.assertEqual(custom_string_io.content, "Some text that will be dumped into StringIO.")

    def test_string(self):
        custom_string_io = CustomStringIO()
        result = custom_string_io._string()
        self.assertEqual(result, "Some text that will be dumped into StringIO.")

    def test_dump_to_file(self):
        file = StringIO()
        custom_string_io = CustomStringIO()
        custom_string_io._CustomStringIO__dump_to_file(file)  # Accessing the private method for testing purposes
        file.seek(0)
        content = file.read()
        self.assertEqual(content, "Some text that will be dumped into StringIO.")


if __name__ == '__main__':
    unittest.main()