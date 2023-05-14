# def _string(self):
#         """:return: the string from a :class:`io.StringIO`"""
#         file = StringIO()
#         self.__dump_to_file(file)
#         file.seek(0)
#         return file.read()


from io import StringIO

class CustomStringIO:
    def __init__(self):
        self.content = "Some text that will be dumped into StringIO."

    def _string(self):
        """:return: the string from a :class:`io.StringIO`"""
        file = StringIO()
        self.__dump_to_file(file)
        file.seek(0)
        return file.read()

    def __dump_to_file(self, file):
        """Dumps the content to a file-like object."""
        file.write(self.content)

def main():
    custom_string_io = CustomStringIO()
    result = custom_string_io._string()
    print("String from StringIO:", result)

if __name__ == "__main__":
    main()