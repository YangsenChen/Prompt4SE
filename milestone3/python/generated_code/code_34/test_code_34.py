from code_34 import *

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