def repository_exists(self, workspace, repo):
        """Return True if workspace contains repository name."""
        if not self.exists(workspace):
            return False

        workspaces = self.list()
        return repo in workspaces[workspace]["repositories"]