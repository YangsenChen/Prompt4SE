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