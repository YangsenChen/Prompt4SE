def sanger_variants(self, institute_id=None, case_id=None):
    '''Return all variants with sanger information

        Args:
            institute_id(str)
            case_id(str)

        Returns:
            res(pymongo.Cursor): A Cursor with all variants with sanger activity
        '''
    query = {'validation': {'$exists': True}}
    if institute_id:
        query['institute_id'] = institute_id
    if not case_id:
        query['case_id'] = case_id
    
    return self.variant_collection.find(query)