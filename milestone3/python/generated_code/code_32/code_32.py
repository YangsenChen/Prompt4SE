# def delete(self, webhookId):
#         """Delete a webhook, by ID.
#
#         Args:
#             webhookId(basestring): The ID of the webhook to be deleted.
#
#         Raises:
#             TypeError: If the parameter types are incorrect.
#             ApiError: If the Webex Teams cloud returns an error.
#
#         """
#         check_type(webhookId, basestring, may_be_none=False)
#
#         # API request
#         self._session.delete(API_ENDPOINT + '/' + webhookId)

import requests


def check_type(value, expected_type, may_be_none=True):
    if not isinstance(value, expected_type):
        raise TypeError(f"Expected type {expected_type}, but got {type(value)}")
    if not may_be_none and value is None:
        raise TypeError(f"Value must not be None")


API_ENDPOINT = 'https://api.example-webhooks.com/v1/webhooks'


class WebhooksManager:
    def __init__(self):
        self._session = requests.Session()

    def delete(self, webhookId):
        """Delete a webhook, by ID.

        Args:
            webhookId(basestring): The ID of the webhook to be deleted.

        Raises:
            TypeError: If the parameter types are incorrect.
            ApiError: If the Webex Teams cloud returns an error.

        """
        check_type(webhookId, str, may_be_none=False)

        # API request
        self._session.delete(API_ENDPOINT + '/' + webhookId)


def main():
    webhook_manager = WebhooksManager()
    webhook_id = "<your_webhook_id_here>"
    webhook_manager.delete(webhook_id)


if __name__ == "__main__":
    main()