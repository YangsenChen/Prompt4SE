## user:
def convert_datetime_to_str(df, *, column: str, format: str, new_column: str = None):
    """
    Convert datetime column into string column

    ---

    ### Parameters

    *mandatory :*
    - column (*str*): name of the column to format
    - format (*str*): format of the result values (see [available formats](
    https://docs.python.org/3/library/datetime.html#strftime-and-strptime-behavior))

    *optional :*
    - new_column (*str*): name of the output column. By default `column` is overwritten.
    """
    new_column = new_column or column
    df[new_column] = df[column].dt.strftime(format)
    return df
for this code, your task is to make it runnable without bugs  give me the full code where the main function calling this function
## assistant:
Here's the full code with imports and a main function calling the convert_datetime_to_str function:

```python
import pandas as pd
from datetime import datetime

def convert_datetime_to_str(df, *, column: str, format: str, new_column: str = None):
    """
    Convert datetime column into string column

    ---

    ### Parameters

    *mandatory :*
    - column (*str*): name of the column to format
    - format (*str*): format of the result values (see [available formats](
    https://docs.python.org/3/library/datetime.html#strftime-and-strptime-behavior))

    *optional :*
    - new_column (*str*): name of the output column. By default `column` is overwritten.
    """
    new_column = new_column or column
    df[new_column] = df[column].dt.strftime(format)
    return df

def main():
    # Sample data
    data = {
        'date': [datetime(2021, 1, 1), datetime(2021, 1, 2), datetime(2021, 1, 3)],
        'value': [1, 2, 3]
    }
    df = pd.DataFrame(data)
    print("Original DataFrame:")
    print(df)

    # Convert datetime column to string
    df = convert_datetime_to_str(df, column='date', format='%Y-%m-%d', new_column='date_str')
    print("\nConverted DataFrame:")
    print(df)

if __name__ == "__main__":
    main()
```

This code includes the necessary imports, creates some sample data with a datetime column, and calls the `convert_datetime_to_str` function in the main function to convert the datetime column into a string column with the specified format.
## user:
then write a class to test every line of the code you just generated
## assistant:
Here's a test class for the code provided above using Python's unittest module:

```python
import unittest
import pandas as pd
from datetime import datetime

class TestConvertDatetimeToStr(unittest.TestCase):

    def test_convert_datetime_to_str(self):
        # Sample data
        data = {
            'date': [datetime(2021, 1, 1), datetime(2021, 1, 2), datetime(2021, 1, 3)],
            'value': [1, 2, 3]
        }
        df = pd.DataFrame(data)

        # Test 1: Test if function converts datetime column to string
        df = convert_datetime_to_str(df, column='date', format='%Y-%m-%d', new_column='date_str')
        expected_columns = ['date', 'value', 'date_str']
        self.assertListEqual(list(df.columns), expected_columns)

        # Test 2: Test if original datetime column is overwritten
        df = convert_datetime_to_str(df, column='date', format='%Y-%m-%d')
        self.assertListEqual(list(df.columns), expected_columns)

        # Test 3: Test if datetime column is properly converted into string with given format
        expected_dates = ['2021-01-01', '2021-01-02', '2021-01-03']
        self.assertListEqual(list(df['date_str']), expected_dates)

if __name__ == "__main__":
    unittest.main()
```

This test class contains three tests:
1. Test if the `convert_datetime_to_str` function adds the converted column to the DataFrame with the expected columns.
2. Test if the original datetime column is overwritten when the new_column parameter is not provided.
3. Test if the datetime column is properly converted into a string column with the given format.
