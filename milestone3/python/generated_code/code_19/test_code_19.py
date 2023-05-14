from code_19 import *

import os
import unittest
import tempfile
import json

from flask_security.utils import hash_password

class TestDepositCreation(unittest.TestCase):

    def setUp(self):
        self.db_fd, app.config["DATABASE"] = tempfile.mkstemp()
        app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///" + app.config["DATABASE"]
        app.config["TESTING"] = True
        app.config["WTF_CSRF_ENABLED"] = False
        self.app = app.test_client()
        with app.app_context():
            db.create_all()
            self.create_test_user()

    def tearDown(self):
        os.close(self.db_fd)
        os.unlink(app.config["DATABASE"])

    def test_create_deposit_unauthenticated(self):
        data = {
            "title": "My Test Deposit",
            "description": "A description of the test deposit",
        }

        deposit = Deposit.create(data)
        self.assertIsNotNone(deposit)
        self.assertIsNotNone(deposit.data['$schema'])
        self.assertIsNotNone(deposit.data['_deposit'])
        self.assertEqual(deposit.data['_deposit'].get('status'), 'draft')
        self.assertIn(0, deposit.data['_deposit'].get('owners'))
        self.assertIsNone(deposit.data['_deposit'].get('created_by'))

    def test_create_deposit_authenticated(self):
        with self.app.session_transaction() as sess:
            sess["user_id"] = 1

        data = {
            "title": "My Authenticated Test Deposit",
            "description": "A description for authenticated test deposit",
        }

        deposit = Deposit.create(data)
        self.assertIsNotNone(deposit)
        self.assertIsNotNone(deposit.data['$schema'])
        self.assertIsNotNone(deposit.data['_deposit'])
        self.assertEqual(deposit.data['_deposit'].get('status'), 'draft')
        self.assertIn(1, deposit.data['_deposit'].get('owners'))
        self.assertEqual(deposit.data['_deposit'].get('created_by'), 1)

    def create_test_user(self):
        test_user = {
            "email": "test@example.com",
            "password": "testpassword",
            "active": True
        }

        user_datastore.create_user(**test_user)
        db.session.commit()


if __name__ == '__main__':
    unittest.main()