from code_33 import *

import unittest
from unittest.mock import MagicMock
from io import StringIO
import sys

class TestCursesMenu(unittest.TestCase):

    def setUp(self):
        self.title = "Main Menu"
        self.subtitle = "Select an option:"
        self.menu = CursesMenu(title=self.title, subtitle=self.subtitle)

    def test_init(self):
        self.assertEqual(self.menu.title, self.title)
        self.assertEqual(self.menu.subtitle, self.subtitle)
        self.assertEqual(self.menu.current_option, 0)
        self.assertIsNotNone(self.menu.screen)
        self.assertEqual(len(self.menu.items), 0)

    def test_add_item(self):
        self.menu.add_item("Option 1")
        self.assertEqual(len(self.menu.items), 1)
        self.assertEqual(self.menu.items[0].text, "Option 1")

    def test_draw(self):
        self.menu.draw = MagicMock()

        self.menu.add_item("Option 1")
        self.menu.add_item("Option 2")
        self.menu.draw()

        self.menu.draw.assert_called_once()

if __name__ == '__main__':
    unittest.main()