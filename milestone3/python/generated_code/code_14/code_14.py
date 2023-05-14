# def join(self, password=None, history_maxchars = None,
#             history_maxstanzas = None, history_seconds = None, history_since = None):
#         """
#         Send a join request for the room.
#
#         :Parameters:
#             - `password`: password to the room.
#             - `history_maxchars`: limit of the total number of characters in
#               history.
#             - `history_maxstanzas`: limit of the total number of messages in
#               history.
#             - `history_seconds`: send only messages received in the last
#               `history_seconds` seconds.
#             - `history_since`: Send only the messages received since the
#               dateTime specified (UTC).
#         :Types:
#             - `password`: `unicode`
#             - `history_maxchars`: `int`
#             - `history_maxstanzas`: `int`
#             - `history_seconds`: `int`
#             - `history_since`: `datetime.datetime`
#         """
#         if self.joined:
#             raise RuntimeError("Room is already joined")
#         p=MucPresence(to_jid=self.room_jid)
#         p.make_join_request(password, history_maxchars, history_maxstanzas,
#                 history_seconds, history_since)
#         self.manager.stream.send(p)

import datetime

class MucPresence:
    def __init__(self, to_jid=None):
        self.to_jid = to_jid

    def make_join_request(self, password, history_maxchars, history_maxstanzas,
                            history_seconds, history_since):
        pass  # add implementation to make join request for the room

class MucRoom:
    def __init__(self, room_jid, manager):
        self.room_jid = room_jid
        self.manager = manager
        self.joined = False

    def join(self, password=None, history_maxchars=None,
             history_maxstanzas=None, history_seconds=None, history_since=None):
        """
        Send a join request for the room.

        :Parameters:
            ...
        """
        if self.joined:
            raise RuntimeError("Room is already joined")
        p = MucPresence(to_jid=self.room_jid)
        p.make_join_request(password, history_maxchars, history_maxstanzas,
                            history_seconds, history_since)
        self.manager.stream.send(p)

class Manager:
    def __init__(self):
        self.stream = Stream()

class Stream:
    def send(self, p):
        pass  # add implementation to send MucPresence on the stream

def main():
    manager = Manager()
    room_jid = "example_room@example.com"
    muc_room = MucRoom(room_jid, manager)
    muc_room.join(password="example_password", history_maxchars=1000)

if __name__ == "__main__":
    main()