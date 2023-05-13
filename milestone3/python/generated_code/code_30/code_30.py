def read_as_text(self):
        '''Read and return the dataset contents as text.'''
        return self.workspace._rest.read_intermediate_dataset_contents_text(
            self.workspace.workspace_id,
            self.experiment.experiment_id,
            self.node_id,
            self.port_name
        )