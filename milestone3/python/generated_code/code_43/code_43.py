def set_many(self, new_values):
        # type: (Iterable[B]) -> Callable[[S], T]
        '''Set many foci to values taken by iterating over `new_values`.

            >>> from lenses import lens
            >>> lens.Each().set_many(range(4, 7))([0, 1, 2])
            [4, 5, 6]
        '''

        def setter_many(state):
            return self._optic.iterate(state, new_values)

        return setter_many