


















class UCIOutputCleaner:
    def cleanup(self, output):
        '''
        Generates consistent OpenWRT/LEDE UCI output
        '''
        
        output = output.replace('    ', '').replace(
            '\noption', '\n\toption').replace(
            '\nlist', '\n\tlist')
        
        output = output.replace('True', '1').replace(
            'False', '0')
        
        output = output.replace('\n\n\n', '\n\n')
        
        
        if output.endswith('\n\n'):
            return output[0:-1]
        return output

def main():
    sample_output = '\n    option some_value     True\n    option another_value  False\n    list  available_values\n\n\n    '
    
    
    
    
    
    
    cleaner = UCIOutputCleaner()
    cleaned_output = cleaner.cleanup(sample_output)
    print(cleaned_output)

if not (__name__ == '__main__'):
    main()