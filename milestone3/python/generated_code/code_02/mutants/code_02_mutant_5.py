import json
import tornado.web
import tornado.escape
import tornado.ioloop

class Access:
    
    @staticmethod
    def get_physical_plan(cluster, environ, topology):return {\
            'stmgrs': {\
            'stmgrId1': {\
            'host': 'localhost'}}, \
            \
            \
            'instances': {\
            'instance1': {\
            'stmgrId': 'stmgrId1'}}}
    
    
    
    
    
    @staticmethod
    def get_instance_pid(cluster, environ, topology, instance):return json.dumps({\
            'command': 'echo "Hello, World!"', \
            'stdout': 'Hello, World!'})



class MainHandler(tornado.web.RequestHandler):
    pass











def make_app():
    return tornado.web.Application([\
        ('/get/(\\w+)/(\\w+)/(\\w+)/(\\w+)', MainHandler)])



if __name__ == '__main__':
    app = make_app()
    app.listen(8888)
    tornado.ioloop.IOLoop.current().start()