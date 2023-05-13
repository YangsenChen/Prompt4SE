from code_28 import *

import unittest
from unittest.mock import MagicMock, patch

class TestTopicService(unittest.TestCase):

    def setUp(self):
        self.topic_service = TopicService()

    def test_validate_not_none(self):
        self.assertRaises(ValueError, self.topic_service._validate_not_none, 'test_value', None)
        self.assertIsNone(self.topic_service._validate_not_none('test_value', 'not none'))

    def test_get_host(self):
        self.assertEqual(self.topic_service._get_host(), 'topics.example.com')

    def test_update_service_bus_header(self):
        request = HTTPRequest()
        request.headers = {}
        updated_headers = self.topic_service._update_service_bus_header(request)

        self.assertIn('Content-Type', updated_headers)
        self.assertEqual(updated_headers['Content-Type'], 'application/json')
        self.assertIn('Authorization', updated_headers)
        self.assertEqual(updated_headers['Authorization'], 'Bearer my_secret_token')

    @patch('http.client.HTTPSConnection')
    def test_perform_request(self, mock_https_connection):
        mock_response = MagicMock()
        mock_response.read.return_value = '{"status": "ok"}'
        mock_https_connection.return_value.getresponse.return_value = mock_response

        request = HTTPRequest()
        request.method = 'GET'
        request.host = 'topics.example.com'
        request.path = '/example-topic'
        request.headers = self.topic_service._update_service_bus_header(request)

        response_data = self.topic_service._perform_request(request)

        mock_https_connection.return_value.request.assert_called_once_with(request.method, request.path, headers=request.headers)
        self.assertEqual(response_data, '{"status": "ok"}')

    def test_convert_response_to_topic(self):
        test_response = '{"status": "ok", "description": "A test topic description"}'
        expected_result = {"status": "ok", "description": "A test topic description"}

        topic_dict = self.topic_service._convert_response_to_topic(test_response)

        self.assertIsInstance(topic_dict, dict)
        self.assertEqual(topic_dict, expected_result)

    @patch.object(TopicService, '_perform_request')
    def test_get_topic(self, mock_perform_request):
        test_response = '{"status": "ok", "description": "A test topic description"}'
        mock_perform_request.return_value = test_response
        expected_result = {"status": "ok", "description": "A test topic description"}

        topic_dict = self.topic_service.get_topic("example-topic")

        self.assertIsInstance(topic_dict, dict)
        self.assertEqual(topic_dict, expected_result)


if __name__ == '__main__':
    unittest.main()