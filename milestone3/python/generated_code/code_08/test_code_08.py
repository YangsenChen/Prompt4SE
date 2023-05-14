from code_08 import *

import unittest




class TestUCIOutputCleaner(unittest.TestCase):
    def setUp(self):
        self.cleaner = UCIOutputCleaner()

    # def test_correct_indentation(self):
    #     input_str = "    option value\nlist values\n    option other_value"
    #     expected_output = "\toption value\n\tlist values\n\toption other_value\n"
    #     self.assertEqual(self.cleaner.cleanup(input_str), expected_output)
    #
    # def test_boolean_conversion(self):
    #     input_str = "option value True\noption other_value False"
    #     expected_output = "option value 1\noption other_value 0\n"
    #     self.assertEqual(self.cleaner.cleanup(input_str), expected_output)
    #
    # def test_delimiter_limit(self):
    #     input_str = "option value\n\n\noption other_value"
    #     expected_output = "option value\n\noption other_value\n"
    #     self.assertEqual(self.cleaner.cleanup(input_str), expected_output)

    def test_new_line_handling(self):
        input_str = "option value\n\n"
        expected_output = "option value\n"
        self.assertEqual(self.cleaner.cleanup(input_str), expected_output)

        input_str = "option value\n"
        expected_output = "option value\n"
        self.assertEqual(self.cleaner.cleanup(input_str), expected_output)


if __name__ == '__main__':
    unittest.main()