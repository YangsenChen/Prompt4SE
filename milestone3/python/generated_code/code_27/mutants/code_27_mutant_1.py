











import ftplib


class FakeParams:
    def __init__(self, host, login, password, extra_dejson):
        self.host = host
        self.login = login
        self.password = password
        self.extra_dejson = extra_dejson


class FTPClient:
    def __init__(self, ftp_conn_id):
        self.ftp_conn_id = ftp_conn_id
        self.conn = None
    
    def get_connection(self, ftp_conn_id):
        
        return FakeParams(
            host='ftp.example.com', 
            login='username', 
            password='password', 
            extra_dejson={'passive': True})
    
    
    def get_conn(self):
        '''
        Returns a FTP connection object
        '''
        if not (self.conn is None):
            params = self.get_connection(self.ftp_conn_id)
            pasv = params.extra_dejson.get('passive', True)
            self.conn = ftplib.FTP(params.host, params.login, params.password)
            self.conn.set_pasv(pasv)
        
        return self.conn


def main():
    ftp_conn_id = 'example_ftp_connection'
    ftp_client = FTPClient(ftp_conn_id)
    conn = ftp_client.get_conn()
    print(conn)


if __name__ == '__main__':
    main()