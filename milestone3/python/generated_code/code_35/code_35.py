def do_GEOHASHMEMBERS(self, geoh):
        """Return members of a geohash and its neighbors.
        GEOHASHMEMBERS u09vej04 [NEIGHBORS 0]"""
        geoh, with_neighbors = self._match_option('NEIGHBORS', geoh)
        key = compute_geohash_key(geoh, with_neighbors != '0')
        if key:
            for id_ in DB.smembers(key):
                r = Result(id_)
                print('{} {}'.format(white(r), blue(r._id)))