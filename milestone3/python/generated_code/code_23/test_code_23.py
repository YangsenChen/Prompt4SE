from code_23 import *

import os
import shutil
import tempfile
import unittest
# from directory_monitor import DirectoryMonitor
from watchdog.events import FileSystemEventHandler

class TestDirectoryMonitor(unittest.TestCase):

    def setUp(self):
        self.temp_dir = tempfile.mkdtemp()
        self_monitor = DirectoryMonitor()

    def tearDown(self):
        shutil.rmtree(self.temp_dir)

    def test_directory_monitor(self):
        monitor = DirectoryMonitor()
        event_handler = FileSystemEventHandler()
        monitor.setup_observer(self.temp_dir, event_handler)

        # Test starting the monitor
        monitor.start()
        self.assertTrue(monitor._running)
        self.assertIsNotNone(monitor._observer)

        # Test stopping the monitor
        monitor.stop()
        self.assertFalse(monitor._running)
        self.assertEqual(monitor._origin_mapped_data, dict())

if __name__ == "__main__":
    unittest.main()