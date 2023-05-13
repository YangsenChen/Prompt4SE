from code_20 import *

import unittest
from types import FunctionType


class TestAccessibleApps(unittest.TestCase):
    # def test_initialization(self):
    #     access_apps = AccessibleApps()
    #     self.assertIsNotNone(access_apps)
    #     self.assertIs(type(access_apps._update_apps), FunctionType, "_update_apps should be a function")

    def test_getapplist_type(self):
        access_apps = AccessibleApps()
        app_list = access_apps.getapplist()
        self.assertIs(type(app_list), list, "The result of getapplist() should be a list")

    def test_getapplist_elements(self):
        access_apps = AccessibleApps()
        app_list = access_apps.getapplist()
        for app_name in app_list:
            self.assertIs(type(app_name), str, "Each element of the list returned by getapplist() should be a string")

    def test_getapplist_uniqueness(self):
        access_apps = AccessibleApps()
        app_list = access_apps.getapplist()
        unique_app_list = list(set(app_list))
        self.assertListEqual(app_list, unique_app_list, "The application list should contain unique elements")


if __name__ == "__main__":
    unittest.main()