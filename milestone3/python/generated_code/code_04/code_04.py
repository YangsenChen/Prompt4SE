# def wait_for_current_tasks(self):
#         """Waits for all tasks in the task list to be completed, by waiting for their
#         AppFuture to be completed. This method will not necessarily wait for any tasks
#         added after cleanup has started (such as data stageout?)
#         """
#
#         logger.info("Waiting for all remaining tasks to complete")
#         for task_id in self.tasks:
#             # .exception() is a less exception throwing way of
#             # waiting for completion than .result()
#             fut = self.tasks[task_id]['app_fu']
#             if not fut.done():
#                 logger.debug("Waiting for task {} to complete".format(task_id))
#                 fut.exception()
#         logger.info("All remaining tasks completed")

import logging
import concurrent.futures

# set up logging
logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)


class TaskManager:
    def __init__(self):
        # Initialize an executor and a task dictionary
        self.executor = concurrent.futures.ThreadPoolExecutor(max_workers=5)
        self.tasks = {}

    def add_task(self, fn, *args, **kwargs):
        """Add a task to the task list."""
        future = self.executor.submit(fn, *args, **kwargs)
        task_id = id(future)
        self.tasks[task_id] = {'app_fu': future}
        return task_id

    def wait_for_current_tasks(self):
        """Waits for all tasks in the task list to be completed, by waiting for their
        AppFuture to be completed. This method will not necessarily wait for any tasks
        added after cleanup has started (such as data stageout?)
        """
        logger.info("Waiting for all remaining tasks to complete")
        for task_id in self.tasks:
            # .exception() is a less exception throwing way of
            # waiting for completion than .result()
            fut = self.tasks[task_id]['app_fu']
            if not fut.done():
                logger.debug("Waiting for task {} to complete".format(task_id))
                fut.exception()
        logger.info("All remaining tasks completed")


# Main function
def main():
    task_manager = TaskManager()

    # Add some tasks
    for i in range(10):
        task_manager.add_task(sum, range(i))

    # Wait for all tasks to complete
    task_manager.wait_for_current_tasks()


if __name__ == "__main__":
    main()
