## user:
def _at_block_start(tc, line):
        """
        Improve QTextCursor.atBlockStart to ignore spaces
        """
        if tc.atBlockStart():
            return True
        column = tc.columnNumber()
        indentation = len(line) - len(line.lstrip())
        return column <= indentation
for this code, your task is to make it runnable without bugs  give me the full code where the main function calling this function
## assistant:
To make the provided code snippet runnable with a main function that calls the `_at_block_start` function, you can create a simple code example using PyQt5's QTextCursor and QTextDocument classes. Here's a full code example for that:

```python
from PyQt5.QtGui import QTextCursor, QTextDocument

def _at_block_start(tc, line):
    """
    Improve QTextCursor.atBlockStart to ignore spaces
    """
    if tc.atBlockStart():
        return True
    column = tc.columnNumber()
    indentation = len(line) - len(line.lstrip())
    return column <= indentation


def main():
    document = QTextDocument()
    document.setPlainText("""This is a sample text with
    multiple lines, some of which
        are indented.""")

    cursor = QTextCursor(document)

    while not cursor.atEnd():
        block_start = _at_block_start(cursor, cursor.block().text())
        print(f'At block start ({cursor.blockNumber()}, {cursor.columnNumber()}): {block_start}')
        cursor.movePosition(QTextCursor.Down)

if __name__ == '__main__':
    main()
```

Note that PyQt5 must be installed for this to work. You can install PyQt5 using pip:

```
pip install PyQt5
```

When you run the code, you should see the output depending on whether the cursor position is at the beginning of a text block or not, considering spaces as well.
## user:
then write a class to test every line of the code you just generated
## assistant:
Here's a class with test methods to test each line of the provided code snippet:

```python
import unittest
from PyQt5.QtGui import QTextCursor, QTextDocument

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

    def test_at_indented_block_start(self):
        cursor = self._create_cursor(46)
        block_start = _at_block_start(cursor, cursor.block().text())
        self.assertEqual(block_start, True)

    def test_not_at_indented_block_start(self):
        cursor = self._create_cursor(49)
        block_start = _at_block_start(cursor, cursor.block().text())
        self.assertEqual(block_start, False)


if __name__ == '__main__':
    unittest.main()
```

This class (`TestTextCursor`) contains four test methods to test the `_at_block_start` function for different cursor positions. It utilizes Python's built-in `unittest` module, which orchestrates running the tests when the script is executed. The `setUp` method initializes a common QTextDocument object with the required text to be used across test cases. The `_create_cursor` method is a helper method to create a QTextCursor object with the specified position.

To run the tests, save the script in a file and execute it. The output should show the status of each performed test.
