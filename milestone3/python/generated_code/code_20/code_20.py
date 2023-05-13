# def getapplist(self):
#         """
#         Get all accessibility application name that are currently running
#
#         @return: list of appliction name of string type on success.
#         @rtype: list
#         """
#         app_list = []
#         # Update apps list, before parsing the list
#         self._update_apps()
#         for gui in self._running_apps:
#             name = gui.localizedName()
#             # default type was objc.pyobjc_unicode
#             # convert to Unicode, else exception is thrown
#             # TypeError: "cannot marshal <type 'objc.pyobjc_unicode'> objects"
#             try:
#                 name = unicode(name)
#             except NameError:
#                 name = str(name)
#             except UnicodeEncodeError:
#                 pass
#             app_list.append(name)
#         # Return unique application list
#         return list(set(app_list))
from appdirs import unicode


class AccessibleApps:
    def __init__(self):
        self._running_apps = []

    def _update_apps(self):
        # This function should include the logic to update the list of running applications
        pass

    def getapplist(self):
        """
        Get all accessibility application names that are currently running

        @return: list of application names of string type on success.
        @rtype: list
        """
        app_list = []
        # Update apps list, before parsing the list
        self._update_apps()
        for gui in self._running_apps:
            name = gui.localizedName()
            # default type was objc.pyobjc_unicode
            # convert to Unicode, else exception is thrown
            # TypeError: "cannot marshal <type 'objc.pyobjc_unicode'> objects"
            try:
                name = unicode(name)
            except NameError:
                name = str(name)
            except UnicodeEncodeError:
                pass
            app_list.append(name)
        # Return unique application list
        return list(set(app_list))


def main():
    accessible_apps = AccessibleApps()
    app_list = accessible_apps.getapplist()
    print("Accessible applications:", app_list)


if __name__ == "__main__":
    main()