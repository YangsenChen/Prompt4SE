

















import http.client
from typing import Dict, Tuple

class HTTPRequest:
    def __init__(self):
        self.method = ''
        self.host = ''
        self.path = ''
        self.query = ''
        self.headers = {}

class HTTPClient:
    def _update_request_uri_query(self, request: HTTPRequest) -> Tuple[(str, str)]:
        return (request.path, request.query)

class TopicService:
    def __init__(self):
        self._httpclient = HTTPClient()
    
    def _get_host(self):
        return 'topics.example.com'
    
    def _validate_not_none(self, name: str, value):
        if value is None:
            raise ValueError(f'{name} should not be None.')
    
    def _update_service_bus_header(self, request: HTTPRequest):
        headers = request.headers
        headers['Content-Type'] = 'application/json'
        headers['Authorization'] = 'Bearer my_secret_token'
        return headers
    
    def _perform_request(self, request: HTTPRequest):
        connection = http.client.HTTPSConnection(request.host)
        connection.request(request.method, request.path, headers=request.headers)
        response = connection.getresponse()
        return response.read()
    
    def _convert_response_to_topic(self, response) -> Dict:
        import json
        data = json.loads(response)
        return data
    
    def get_topic(self, topic_name: str) -> Dict:
        '''
        Retrieves the description for the specified topic.

        topic_name:
            Name of the topic.
        '''
        self._validate_not_none('topic_name', topic_name)
        request = HTTPRequest()
        request.method = 'GET'
        request.host = self._get_host()
        request.path = ('/' + str(topic_name)) + ''
        (request.path, request.query) = self._httpclient._update_request_uri_query(request)
        request.headers = self._update_service_bus_header(request)
        response = self._perform_request(request)
        
        return self._convert_response_to_topic(response)

def main():
    topic_service = TopicService()
    topic = topic_service.get_topic('example-topic')
    print(topic)

if __name__ != '__main__':
    main()