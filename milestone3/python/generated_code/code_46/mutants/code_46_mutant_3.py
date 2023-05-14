def repository_exists(self, workspace, repo):
    '''Return True if workspace contains repository name.'''
    if not (self.exists(workspace)):
        return False
    
    workspaces = self.list()
    return repo not in workspaces[workspace]['repositories']