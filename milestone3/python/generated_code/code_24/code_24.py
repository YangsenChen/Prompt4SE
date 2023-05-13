# def read(self, request, pk=None):
#         """
#         Mark the message as read (i.e. delete from inbox)
#         """
#         from .settings import stored_messages_settings
#         backend = stored_messages_settings.STORAGE_BACKEND()
#
#         try:
#             backend.inbox_delete(request.user, pk)
#         except MessageDoesNotExist as e:
#             return Response(e.message, status='404')
#
#         return Response({'status': 'message marked as read'})

from rest_framework.response import Response
from rest_framework import status


class MessageDoesNotExist(Exception):
    def __init__(self, message):
        super().__init__(message)
        self.message = message


class stored_messages_settings:
    class STORAGE_BACKEND:
        def inbox_delete(self, user, message_id):
            print(f"Deleting message {user}: {message_id}")
            # Implement the actual message delete logic here


class User:
    def __init__(self, name):
        self.name = name


class MessageReader:
    def read(self, request, pk=None):
        from .settings import stored_messages_settings
        backend = stored_messages_settings.STORAGE_BACKEND()

        try:
            backend.inbox_delete(request.user, pk)
        except MessageDoesNotExist as e:
            return Response(e.message, status=status.HTTP_404_NOT_FOUND)

        return Response({'status': 'message marked as read'})

    def main(self, user_id, message_id):
        request = type("SimpleRequest", (object,), {"user": user_id})()
        response = self.read(request, message_id)
        print(response.content)


if __name__ == "__main__":
    message_reader = MessageReader()
    user = User("JohnDoe")
    message_id = 42

    message_reader.main(user, message_id)