# def do_GEOHASHMEMBERS(self, geoh):
#         """Return members of a geohash and its neighbors.
#         GEOHASHMEMBERS u09vej04 [NEIGHBORS 0]"""
#         geoh, with_neighbors = self._match_option('NEIGHBORS', geoh)
#         key = compute_geohash_key(geoh, with_neighbors != '0')
#         if key:
#             for id_ in DB.smembers(key):
#                 r = Result(id_)
#                 print('{} {}'.format(white(r), blue(r._id)))

import redis
# from geohash import expand_bbox, neighbors, decode_exactly
from pretty import white, blue

DB = redis.StrictRedis(db=0, decode_responses=True)


class GeoHashMembers:
    def __init__(self):
        self.db = DB

    def _match_option(self, option_string, arg_list):
        if option_string in arg_list:
            idx = arg_list.index(option_string)
            value = arg_list[idx + 1]
            arg_list = arg_list[:idx] + arg_list[idx + 2:]
            return arg_list, value
        else:
            return arg_list, None

    def compute_geohash_key(self, geoh, with_neighbors):
        set_key = f'geo:{geoh}'
        if with_neighbors:
            geohs = neighbors(geoh) + [geoh]
        else:
            geohs = [geoh]
        return set_key, geohs

    def do_GEOHASHMEMBERS(self, geoh):
        """Return members of a geohash and its neighbors.
        GEOHASHMEMBERS u09vej04 [NEIGHBORS 0]"""
        geoh, with_neighbors = self._match_option('NEIGHBORS', geoh.split())
        key, geohs = self.compute_geohash_key(geoh, with_neighbors != '0')

        for geoh in geohs:
            for id_ in self.db.smembers(key.format(geoh=geoh)):
                r = Result(id_)
                print('{} {}'.format(white(r), blue(r._id)))


class Result:
    def __init__(self, _id):
        self._id = _id

    def __str__(self):
        return f'Result(ID={self._id})'


def main():
    geo_hash_members = GeoHashMembers()
    geohash = 'u09vej04'
    geo_hash_members.do_GEOHASHMEMBERS(geohash)


if __name__ == '__main__':
    main()