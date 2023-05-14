def create_submission(self, user_id, institute_id):
        """Create an open clinvar submission for a user and an institute
           Args:
                user_id(str): a user ID
                institute_id(str): an institute ID

           returns:
                submission(obj): an open clinvar submission object
        """

        submission_obj = {
            'status' : 'open',
            'created_at' : datetime.now(),
            'user_id' : user_id,
            'institute_id' : institute_id
        }
        LOG.info("Creating a new clinvar submission for user '%s' and institute %s", user_id, institute_id)
        result = self.clinvar_submission_collection.insert_one(submission_obj)
        return result.inserted_id