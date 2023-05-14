## user:
def delete(self, webhookId):
        """Delete a webhook, by ID.

        Args:
            webhookId(basestring): The ID of the webhook to be deleted.

        Raises:
            TypeError: If the parameter types are incorrect.
            ApiError: If the Webex Teams cloud returns an error.

        """
        check_type(webhookId, basestring, may_be_none=False)

        # API request
        self._session.delete(API_ENDPOINT + '/' + webhookId)
for this code, your task is to make it runnable without bugs  give me the full code where the main function calling this function
## assistant:
To make the code runnable without bugs, follow these steps:

1. Define the missing variables and functions: `check_type`, `API_ENDPOINT`, and `_session`.
2. Create a class to contain the `delete` function.
3. Create a main function that will instantiate the class and call the `delete` function.

Here's the full code:

```python
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
```

Replace `<your_webhook_id_here>` with the actual webhook ID you want to delete. Note that you might need to provide authentication for the `_session` object if the API requires it.
## user:
then write a class to test every line of the code you just generated
## assistant:
You can use the `unittest` module in Python to create a test class to test the code. Following is a test class that tests the `check_type` function, the `delete` function, and simulates an API call using the `responses` library.

First, install `responses` library:

```bash
pip install responses
```

Now, create your test class:

```python
import unittest
import responses

from manage_webhooks import check_type, API_ENDPOINT, WebhooksManager

class TestWebhooksManager(unittest.TestCase):

    def test_check_type(self):
        check_type("test", str)
        check_type(1, int, may_be_none=False)

        with self.assertRaises(TypeError):
            check_type(None, str, may_be_none=False)

        with self.assertRaises(TypeError):
            check_type({}, str)

    @responses.activate
    def test_delete(self):
        # Register a fake delete URL for testing
        webhook_id = "test_webhook_id"
        delete_url = f"{API_ENDPOINT}/{webhook_id}"
        responses.add(responses.DELETE, delete_url, status=204)

        # Test delete with correct webhook ID
        webhook_manager = WebhooksManager()
        webhook_manager.delete(webhook_id)

        with self.assertRaises(TypeError):
            # Test delete with incorrect webhook ID type
            webhook_manager.delete(1)

        
if __name__ == "__main__":
    unittest.main()
```

This test class contains two test functions: `test_check_type` and `test_delete`. The `test_check_type` function tests the behavior of the `check_type` function, ensuring it produces the expected outcomes. The `test_delete` function tests the `delete` function and simulates the API call using the `responses` library for mocking. Make sure to place this code in a separate Python script (e.g., `test_manage_webhooks.py`) and run the test script using `python test_manage_webhooks.py`.
