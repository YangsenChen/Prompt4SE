from code_04 import *

import unittest
import time

class TestTaskManager(unittest.TestCase):
    def test_add_task(self):
        task_manager = TaskManager()
        task_id = task_manager.add_task(sum, range(10))
        self.assertIn(task_id, task_manager.tasks)

    def test_task_result(self):
        task_manager = TaskManager()
        task_id = task_manager.add_task(sum, range(10))
        task_result = task_manager.tasks[task_id]['app_fu'].result()
        self.assertEqual(task_result, 45)  # sum of range(10)

    def test_wait_for_current_tasks(self):
        task_manager = TaskManager()

        for i in range(5):
            task_manager.add_task(time.sleep, 1)  # task that takes 1 second

        start_time = time.time()
        task_manager.wait_for_current_tasks()
        end_time = time.time()

        # The total time should be roughly 1 second since tasks run concurrently
        self.assertAlmostEqual(end_time - start_time, 1, delta=0.5)

if __name__ == '__main__':
    unittest.main()
