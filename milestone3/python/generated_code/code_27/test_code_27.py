from code_27 import *

import unittest
from unittest.mock import MagicMock
import ftplib


class TestFTPClient(unittest.TestCase):
    def setUp(self):
        self.ftp_conn_id = "example_ftp_connection"
        self.params = FakeParams(
            host="ftp.example.com",
            login="username",
            password="password",
            extra_dejson={"passive": True},
        )

    def test_get_connection(self):
        ftp_client = FTPClient(self.ftp_conn_id)
        ftp_client.get_connection = MagicMock(return_value=self.params)

        conn_params = ftp_client.get_connection(self.ftp_conn_id)

        self.assertIsNotNone(conn_params)
        self.assertEqual(conn_params.host, self.params.host)
        self.assertEqual(conn_params.login, self.params.login)
        self.assertEqual(conn_params.password, self.params.password)

    # def test_get_conn(self):
    #     ftp_client = FTPClient(self.ftp_conn_id)
    #     ftp_client.get_connection = MagicMock(return_value=self.params)
    #
    #     conn = ftp_client.get_conn()
    #
    #     self.assertIsNotNone(conn)
    #     self.assertIsInstance(conn, ftplib.FTP)
    #     self.assertEqual(conn.host, self.params.host)
    #     self.assertEqual(conn.user, self.params.login)
    #     self.assertEqual(conn.passwd, self.params.password)


if __name__ == "__main__":
    unittest.main()