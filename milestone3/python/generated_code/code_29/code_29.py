# def resolve_expression(self, *args, **kwargs):
#         """Resolves expressions inside the dictionary."""
#
#         result = dict()
#         for key, value in self.value.items():
#             if hasattr(value, 'resolve_expression'):
#                 result[key] = value.resolve_expression(
#                     *args, **kwargs)
#             else:
#                 result[key] = value
#
#         return HStoreValue(result)

class HStoreValue:
    def __init__(self, value):
        self.value = value

    def __str__(self):
        return str(self.value)

    def resolve_expression(self, *args, **kwargs):
        """Resolves expressions inside the dictionary."""

        result = dict()
        for key, value in self.value.items():
            if hasattr(value, 'resolve_expression'):
                result[key] = value.resolve_expression(
                    *args, **kwargs)
            else:
                result[key] = value

        return HStoreValue(result)


def main():
    some_data = {
        'integer': 42,
        'string': 'Hello, world!',
        'nested_hstore': HStoreValue({'key': 'value'})
    }

    hstore = HStoreValue(some_data)
    print(f"Original HStoreValue: {hstore}")

    resolved_hstore = hstore.resolve_expression()
    print(f"Resolved HStoreValue: {resolved_hstore}")


if __name__ == '__main__':
    main()