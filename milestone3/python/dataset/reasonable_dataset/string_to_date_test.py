import unittest
from datetime import datetime
from StringToDate import string_to_date

class TestStringToDate():

        date_str = "--0531"
        expected_date = datetime(1900, 5, 31)
        assert(string_to_date(date_str) == expected_date)

        date_str = "--12-25"
        expected_date = datetime(1900, 12, 25)
        assert(string_to_date(date_str) == expected_date)

        date_str = "20220130"
        expected_date = datetime(2022, 1, 30)
        assert(string_to_date(date_str) == expected_date)

        date_str = "2022-02-01"
        expected_date = datetime(2022, 2, 1)
        assert(string_to_date(date_str) == expected_date)

        date_str = "20220130T132045"
        expected_date = datetime(2022, 1, 30, 13, 20, 45)
        assert(string_to_date(date_str) == expected_date)

        date_str = "2022-02-01T13:20:45"
        expected_date = datetime(2022, 2, 1, 13, 20, 45)
        assert(string_to_date(date_str) == expected_date)

        date_str = "20220130T132045Z"
        expected_date = datetime(2022, 1, 30, 13, 20, 45)
        assert(string_to_date(date_str) == expected_date)

        date_str = "2022-02-01T13:20:45Z"
        expected_date = datetime(2022, 2, 1, 13, 20, 45)
        assert(string_to_date(date_str) == expected_date)
