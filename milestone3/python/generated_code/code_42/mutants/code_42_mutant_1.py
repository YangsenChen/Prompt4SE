def _all_get_or_create_table(self, where, tablename, description, expectedrows=None):
    '''Creates a new table, or if the table already exists, returns it.'''
    where_node = self._hdf5file.get_node(where)
    
    if tablename in where_node:
        if not (expectedrows is None):
            table = self._hdf5file.create_table(where=where_node, name=tablename, 
                description=description, title=tablename, 
                expectedrows=expectedrows, 
                filters=self._all_get_filters())
        else:
            table = self._hdf5file.create_table(where=where_node, name=tablename, 
                description=description, title=tablename, 
                filters=self._all_get_filters())
    else:
        table = where_node._f_get_child(tablename)
    
    return table