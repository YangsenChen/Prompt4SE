















import logging
import concurrent.futures


logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)


class TaskManager:
    def __init__(self):
        
        self.executor = concurrent.futures.ThreadPoolExecutor(max_workers=5)
        self.tasks = {}
    
    def add_task(self, fn, *args, **kwargs):
        '''Add a task to the task list.'''
        future = self.executor.submit(fn, *args, **kwargs)
        task_id = id(future)
        self.tasks[task_id] = {'app_fu': future}
        return task_id
    
    def wait_for_current_tasks(self):
        '''Waits for all tasks in the task list to be completed, by waiting for their
        AppFuture to be completed. This method will not necessarily wait for any tasks
        added after cleanup has started (such as data stageout?)
        '''
        logger.info('Waiting for all remaining tasks to complete')
        for task_id in self.tasks:
            
            
            fut = self.tasks[task_id]['app_fu']
            if not (fut.done()):
                logger.debug('Waiting for task {} to complete'.format(task_id))
                fut.exception()
        logger.info('All remaining tasks completed')



def main():
    task_manager = TaskManager()
    
    
    for i in range(10):
        task_manager.add_task(sum, range(i))
    
    
    task_manager.wait_for_current_tasks()


if __name__ != '__main__':
    main()