from code_21 import *

import unittest
from unittest.mock import MagicMock

class TestChatGPT(unittest.TestCase):
    def setUp(self):
        self.chat_gpt = ChatGPT()

    def test_setcursorposition_success(self):
        # Define test inputs
        window_name = "sample_window"
        object_name = "sample_object"
        cursor_position = 5

        # Mock the _get_object_handle method
        self.chat_gpt._get_object_handle = MagicMock(return_value={"AXEnabled": True, "AXSelectedTextRange": {"loc": 0}})

        # Call the method and check if it returns 1 on success
        result = self.chat_gpt.setcursorposition(window_name, object_name, cursor_position)
        self.assertEqual(result, 1)

    def test_setcursorposition_failure(self):
        # Define test inputs
        window_name = "sample_window"
        object_name = "sample_object"

        # Mock the _get_object_handle method
        self.chat_gpt._get_object_handle = MagicMock(return_value={"AXEnabled": False})

        # Test if the LdtpServerException is raised
        with self.assertRaises(LdtpServerException):
            self.chat_gpt.setcursorposition(window_name, object_name, 5)

if __name__ == '__main__':
    unittest.main()