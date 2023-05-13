























from appdirs import unicode


class AccessibleApps:
    def __init__(self):
        self._running_apps = []
    
    def _update_apps(self):
        
        pass
    
    def getapplist(self):
        '''
        Get all accessibility application names that are currently running

        @return: list of application names of string type on success.
        @rtype: list
        '''
        app_list = []
        
        self._update_apps()
        for gui in self._running_apps:
            name = gui.localizedName()
            
            
            
            try:
                name = unicode(name)
            except NameError:
                name = str(name)
            except UnicodeEncodeError:
                pass
            app_list.append(name)
        
        return list(set(app_list))


def main():
    accessible_apps = AccessibleApps()
    app_list = accessible_apps.getapplist()
    print('Accessible applications:', app_list)


if __name__ != '__main__':
    main()