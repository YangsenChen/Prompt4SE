from code_30 import *

import unittest
# from main import Workspace, RestClient, Experiment, DatasetReader

class TestDatasetReader(unittest.TestCase):

    def test_workspace(self):
        workspace = Workspace()
        self.assertIsNotNone(workspace)
        self.assertEqual(workspace.workspace_id, 1)

    def test_rest_client(self):
        rest_client = RestClient()
        self.assertIsNotNone(rest_client)

    def test_experiment(self):
        experiment_id = 0
        experiment = Experiment(experiment_id)
        self.assertIsNotNone(experiment)
        self.assertEqual(experiment.experiment_id, experiment_id)

    def test_dataset_reader(self):
        workspace = Workspace()
        experiment = Experiment(0)
        node_id = 42
        port_name = "output"
        dataset_reader = DatasetReader(workspace, experiment, node_id, port_name)
        self.assertIsNotNone(dataset_reader)
        self.assertEqual(dataset_reader.node_id, node_id)
        self.assertEqual(dataset_reader.port_name, port_name)

    # def test_read_as_text(self):
    #     workspace = Workspace()
    #     experiment = Experiment(0)
    #     node_id = 42
    #     port_name = "output"
    #     dataset_reader = DatasetReader(workspace, experiment, node_id, port_name)
    #     dataset_text = dataset_reader.read_as_text()
    #     expected_text = f"Data from workspace_id: 1, experiment_id: 0, node_id: {node_id}, and port_name: {port_name}"
    #     self.assertEqual(dataset_text, expected_text)

if __name__ == '__main__':
    unittest.main()