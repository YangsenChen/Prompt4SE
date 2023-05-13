def get_topic(self, topic_name):
        '''
        Retrieves the description for the specified topic.

        topic_name:
            Name of the topic.
        '''
        _validate_not_none('topic_name', topic_name)
        request = HTTPRequest()
        request.method = 'GET'
        request.host = self._get_host()
        request.path = '/' + _str(topic_name) + ''
        request.path, request.query = self._httpclient._update_request_uri_query(request)  # pylint: disable=protected-access
        request.headers = self._update_service_bus_header(request)
        response = self._perform_request(request)

        return _convert_response_to_topic(response)