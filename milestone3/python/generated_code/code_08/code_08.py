# def cleanup(self, output):
#         """
#         Generates consistent OpenWRT/LEDE UCI output
#         """
#         # correct indentation
#         output = output.replace('    ', '')\
#                        .replace('\noption', '\n\toption')\
#                        .replace('\nlist', '\n\tlist')
#         # convert True to 1 and False to 0
#         output = output.replace('True', '1')\
#                        .replace('False', '0')
#         # max 2 consecutive \n delimiters
#         output = output.replace('\n\n\n', '\n\n')
#         # if output is present
#         # ensure it always ends with 1 new line
#         if output.endswith('\n\n'):
#             return output[0:-1]
#         return output

class UCIOutputCleaner:
    def cleanup(self, output):
        """
        Generates consistent OpenWRT/LEDE UCI output
        """
        # correct indentation
        output = output.replace('    ', '')\
                       .replace('\noption', '\n\toption')\
                       .replace('\nlist', '\n\tlist')
        # convert True to 1 and False to 0
        output = output.replace('True', '1')\
                       .replace('False', '0')
        # max 2 consecutive \n delimiters
        output = output.replace('\n\n\n', '\n\n')
        # if output is present
        # ensure it always ends with 1 new line
        if output.endswith('\n\n'):
            return output[0:-1]
        return output

def main():
    sample_output = '''
    option some_value     True
    option another_value  False
    list  available_values
\n
    '''

    cleaner = UCIOutputCleaner()
    cleaned_output = cleaner.cleanup(sample_output)
    print(cleaned_output)

if __name__ == '__main__':
    main()