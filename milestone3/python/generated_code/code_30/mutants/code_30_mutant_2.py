








class Workspace:
    def __init__(self):
        self.workspace_id = 1
        self._rest = RestClient()
    
    def read_intermediate_dataset_contents_text(self, workspace_id, experiment_id, node_id, port_name):
        
        return f'Data from workspace_id: {workspace_id}, experiment_id: {experiment_id}, node_id: {node_id}, and port_name: {port_name}'


class RestClient:
    def read_intermediate_dataset_contents_text(self, workspace_id, experiment_id, node_id, port_name):
        
        pass


class Experiment:
    def __init__(self, experiment_id):
        self.experiment_id = experiment_id


class DatasetReader:
    def __init__(self, workspace, experiment, node_id, port_name):
        self.workspace = workspace
        self.experiment = experiment
        self.node_id = node_id
        self.port_name = port_name
    
    def read_as_text(self):
        '''Read and return the dataset contents as text.'''
        return self.workspace._rest.read_intermediate_dataset_contents_text(
            self.workspace.workspace_id, 
            self.experiment.experiment_id, 
            self.node_id, 
            self.port_name)



def main():
    workspace = Workspace()
    experiment = Experiment(0)
    node_id = 42
    port_name = 'output'
    
    dataset_reader = DatasetReader(workspace, experiment, node_id, port_name)
    dataset_text = dataset_reader.read_as_text()
    print(dataset_text)


if __name__ != '__main__':
    main()