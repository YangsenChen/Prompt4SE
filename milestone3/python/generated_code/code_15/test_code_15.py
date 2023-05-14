from code_15 import *

import unittest
from PyQt5.QtGui import QTextCursor, QTextDocument

from milestone3.python.generated_code.code_15.code_15 import _at_block_start


class TestTextCursor(unittest.TestCase):
    def setUp(self):
        self.document = QTextDocument()
        self.document.setPlainText("""This is a sample text with
        multiple lines, some of which
            are indented.""")

    def _create_cursor(self, position):
        cursor = QTextCursor(self.document)
        cursor.setPosition(position)
        return cursor

    def test_at_block_start(self):
        cursor = self._create_cursor(0)
        block_start = _at_block_start(cursor, cursor.block().text())
        self.assertEqual(block_start, True)

    def test_not_at_block_start(self):
        cursor = self._create_cursor(15)
        block_start = _at_block_start(cursor, cursor.block().text())
        self.assertEqual(block_start, False)

    # def test_at_indented_block_start(self):
    #     cursor = self._create_cursor(46)
    #     block_start = _at_block_start(cursor, cursor.block().text())
    #     self.assertEqual(block_start, True)

    def test_not_at_indented_block_start(self):
        cursor = self._create_cursor(49)
        block_start = _at_block_start(cursor, cursor.block().text())

        self.assertEqual(block_start, False)


if __name__ == '__main__':
    unittest.main()