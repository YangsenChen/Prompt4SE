from code_32 import *

import unittest
import responses

# from manage_webhooks import check_type, API_ENDPOINT, WebhooksManager


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