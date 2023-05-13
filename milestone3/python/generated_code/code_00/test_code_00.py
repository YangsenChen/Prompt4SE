
import unittest
from code_00 import *

class TestMyClass(unittest.TestCase):
    def test_GetAnnotationObjects_returns_empty_list(self):
        # Setup MyClass with a pattern that returns an empty ElementArray
        class EmptyPattern:
            def GetCurrentAnnotationObjects(self):
                return ElementArray(length=0)
        my_instance = MyClass()
        my_instance.pattern = EmptyPattern()

        # Call GetAnnotationObjects and assert that it returns an empty list
        self.assertEqual(my_instance.GetAnnotationObjects(), [])

    def test_GetAnnotationObjects_returns_list_of_controls(self):
        # Setup MyClass with a pattern that returns an ElementArray of length 5
        class FiveElementPattern:
            def GetCurrentAnnotationObjects(self):
                return ElementArray(length=5)
        my_instance = MyClass()
        my_instance.pattern = FiveElementPattern()

        # Call GetAnnotationObjects and assert that it returns a list of five Controls
        result = my_instance.GetAnnotationObjects()
        self.assertEqual(len(result), 5)
        self.assertTrue(all(isinstance(x, Control) for x in result))

    def test_GetAnnotationObjects_calls_GetCurrentAnnotationObjects(self):
        # Setup MyClass with a pattern that has a mock GetCurrentAnnotationObjects method
        class MockPattern:
            def __init__(self):
                self.call_count = 0

            def GetCurrentAnnotationObjects(self):
                self.call_count += 1
                return ElementArray(length=0)
        mock_pattern = MockPattern()
        my_instance = MyClass()
        my_instance.pattern = mock_pattern

        # Call GetAnnotationObjects and assert that it calls GetCurrentAnnotationObjects once
        my_instance.GetAnnotationObjects()
        self.assertEqual(mock_pattern.call_count, 1)


# if __name__ == '__main__':
#     unittest.main()
