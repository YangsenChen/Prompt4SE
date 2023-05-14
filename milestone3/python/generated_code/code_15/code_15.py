# def _at_block_start(tc, line):
#         """
#         Improve QTextCursor.atBlockStart to ignore spaces
#         """
#         if tc.atBlockStart():
#             return True
#         column = tc.columnNumber()
#         indentation = len(line) - len(line.lstrip())
#         return column <= indentation

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