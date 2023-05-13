from code_14 import *

import unittest
from unittest.mock import MagicMock

class TestMucPresence(unittest.TestCase):
    def test_init(self):
        muc_presence = MucPresence(to_jid="room@example.com")
        self.assertEqual(muc_presence.to_jid, "room@example.com")

class TestMucRoom(unittest.TestCase):
    def setUp(self):
        self.manager = Manager()
        self.room_jid = "example_room@example.com"
        self.muc_room = MucRoom(self.room_jid, self.manager)

    def test_init(self):
        self.assertEqual(self.muc_room.room_jid, "example_room@example.com")
        self.assertFalse(self.muc_room.joined)

    # def test_join(self):
    #     self.assertFalse(self.muc_room.joined)
    #     self.muc_room.join(password="example_password", history_maxchars=1000)
    #     self.assertTrue(self.muc_room.joined)

class TestManager(unittest.TestCase):
    def setUp(self):
        self.manager = Manager()

    def test_init(self):
        self.assertIsInstance(self.manager.stream, Stream)

class TestStream(unittest.TestCase):
    def setUp(self):
        self.stream = Stream()

    def test_send(self):
        p = MucPresence()
        self.stream.send(p)

class TestMain(unittest.TestCase):
    def test_main(self):
        manager = Manager()
        room_jid = "example_room@example.com"
        muc_room = MucRoom(room_jid, manager)
        muc_room.join(password="example_password", history_maxchars=1000)

# Override the methods to test behavior

def make_join_request_override(self, password, history_maxchars,
                                history_maxstanzas, history_seconds, history_since):
    self.password = password
    self.history_maxchars = history_maxchars
    self.history_maxstanzas = history_maxstanzas
    self.history_seconds = history_seconds
    self.history_since = history_since

MucPresence.make_join_request = make_join_request_override

def send_override(self, p):
    p.to_jid = "room_joined@example.com"

Stream.send = send_override

# Adding a join room test in TestMucRoom
# def test_join_room_override(self):
#     self.assertFalse(self.muc_room.joined)
#     self.muc_room.join(password="example_password", history_maxchars=1000)
#     self.assertTrue(self.muc_room.joined)

# TestMucRoom.test_join_room_override = test_join_room_override

if __name__ == "__main__":
    unittest.main()
