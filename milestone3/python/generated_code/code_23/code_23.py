# def stop(self):
#         """
#         Stops monitoring the predefined directory.
#         """
#         with self._status_lock:
#             if self._running:
#                 assert self._observer is not None
#                 self._observer.stop()
#                 self._running = False
#                 self._origin_mapped_data = dict()

from threading import Lock
import time
from watchdog.observers import Observer

class DirectoryMonitor:

    def __init__(self):
        self._observer = Observer()
        self._running = False
        self._status_lock = Lock()
        self._origin_mapped_data = dict()

    def stop(self):
        """
        Stops monitoring the predefined directory.
        """
        with self._status_lock:
            if self._running:
                assert self._observer is not None
                self._observer.stop()
                self._running = False
                self._origin_mapped_data = dict()

    def start(self):
        """
        Starts monitoring the predefined directory.
        """
        with self._status_lock:
            if not self._running:
                self._observer.start()
                self._running = True

    def setup_observer(self, directory, event_handler):
        """
        Sets up the observer to monitor the directory with the given event handler.
        """
        self._observer.schedule(event_handler, directory, recursive=True)

def main():
    import sys
    from watchdog.events import FileSystemEventHandler

    if len(sys.argv) < 2:
        print("Usage: python script.py [directory_to_monitor]")
        return

    directory_to_monitor = sys.argv[1]

    monitor = DirectoryMonitor()
    event_handler = FileSystemEventHandler()
    monitor.setup_observer(directory_to_monitor, event_handler)
    monitor.start()

    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        pass
    finally:
        monitor.stop()

if __name__ == "__main__":
    main()