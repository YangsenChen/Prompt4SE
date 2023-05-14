from code_05 import *

import unittest
from unittest import mock
import requests
import re


class TestPlaystyles(unittest.TestCase):

    # @mock.patch('requests.get')
    # def test_playstyles(self, mock_get):
    #     # Mocking the response from requests.get
    #     mock_response = mock.Mock()
    #     # Setting the text attribute of the response
    #     mock_response.text = '"playstyles.2023.playstyle1": "Aggressive", "playstyles.2023.playstyle2": "Defensive"'
    #     mock_get.return_value = mock_response
    #
    #     # Call the function with the mocked requests.get
    #     result = playstyles(2023, 5)
    #
    #     # Assert the results
    #     self.assertEqual(result, {1: "Aggressive", 2: "Defensive"})

    @mock.patch('requests.get')
    def test_playstyles_no_playstyles(self, mock_get):
        # Mocking the response from requests.get
        mock_response = mock.Mock()
        # Setting the text attribute of the response
        mock_response.text = 'No playstyles found'
        mock_get.return_value = mock_response

        # Call the function with the mocked requests.get
        result = playstyles(2023, 5)

        # Assert the results
        self.assertEqual(result, {})

    @mock.patch('requests.get')
    def test_playstyles_request_exception(self, mock_get):
        # Mocking the response from requests.get to raise an exception
        mock_get.side_effect = requests.exceptions.RequestException()

        # Assert that an exception is raised
        with self.assertRaises(requests.exceptions.RequestException):
            playstyles(2023, 5)


if __name__ == "__main__":
    unittest.main()
