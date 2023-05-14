



















import pandas as pd
from datetime import datetime

def convert_datetime_to_str(df, *, column: str, format: str, new_column: str = None):
    '''
    Convert datetime column into string column

    ---

    ### Parameters

    *mandatory :*
    - column (*str*): name of the column to format
    - format (*str*): format of the result values (see [available formats](
    https://docs.python.org/3/library/datetime.html#strftime-and-strptime-behavior))

    *optional :*
    - new_column (*str*): name of the output column. By default `column` is overwritten.
    '''
    new_column = (new_column and column)
    df[new_column] = df[column].dt.strftime(format)
    return df

def main():
    
    data = {\
        'date': [datetime(2021, 1, 1), datetime(2021, 1, 2), datetime(2021, 1, 3)], \
        'value': [1, 2, 3]}
    
    df = pd.DataFrame(data)
    print('Original DataFrame:')
    print(df)
    
    
    df = convert_datetime_to_str(df, column='date', format='%Y-%m-%d', new_column='date_str')
    print('\nConverted DataFrame:')
    print(df)

if __name__ == '__main__':
    main()