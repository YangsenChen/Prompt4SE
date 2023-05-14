
from code_02 import MainHandler, Access

import json
import unittest
import tornado.testing
from tornado.web import Application

# Assuming the Access and MainHandler classes are defined here
# from your_application import Access, MainHandler

class MainHandler(tornado.web.RequestHandler):
    async def get(self, cluster, environ, topology, instance):
        pplan = await Access.get_physical_plan(cluster, environ, topology)
        if not pplan or 'stmgrs' not in pplan or 'instances' not in pplan or instance not in pplan['instances']:
            self.set_status(400)
            return
        host = pplan['stmgrs'][pplan['instances'][instance]['stmgrId']]['host']
        result = json.loads(await Access.get_instance_pid(
            cluster, environ, topology, instance))
        if not result or 'command' not in result or 'stdout' not in result:
            self.set_status(400)
            return
        self.write('<pre><br/>$%s>: %s<br/><br/>%s</pre>' % (
            host,
            tornado.escape.xhtml_escape(result['command']),
            tornado.escape.xhtml_escape(result['stdout'])))

class TestMainHandler(tornado.testing.AsyncHTTPTestCase):
    def get_app(self):
        return Application([
            (r"/(?P<cluster>[^\/]+)/(?P<environ>[^\/]+)/(?P<topology>[^\/]+)/(?P<instance>[^\/]+)", MainHandler)
        ])

    @tornado.testing.gen_test
    async def test_get(self):
        # Mocking the Access class methods
        Access.get_physical_plan = async_mock(return_value={
            'stmgrs': {'stmgrId1': {'host': 'localhost'}},
            'instances': {'test_instance': {'stmgrId': 'stmgrId1'}}
        })
        Access.get_instance_pid = async_mock(return_value=json.dumps({
            'command': 'command',
            'stdout': 'output'
        }))

        response = await self.http_client.fetch(self.get_url('/test_cluster/test_environ/test_topology/test_instance'))
        # check the response code
        self.assertEqual(response.code, 200)
        # check the response body
        self.assertIn(b'<pre><br/>$localhost>: command<br/><br/>output</pre>', response.body)

def async_mock(*, return_value=None):
    async def mock(*args, **kwargs):
        return return_value
    return mock

if __name__ == '__main__':
    unittest.main()
