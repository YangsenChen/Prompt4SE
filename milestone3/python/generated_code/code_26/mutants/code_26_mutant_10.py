

























































import os
import peewee


class Spinner:
    
    @classmethod
    def context(cls, msg, waitmsg):return DummySpinnerContext()


class API:
    
    @staticmethod
    def get_courses():return [{'id': 1, 'name': 'Course 1', 'details_url': 'https://example.com/course1'}]
    
    
    @staticmethod
    def get_exercises(selected):return [\
            {\
            'id': 1, \
            'name': 'Exercise 1', \
            'attempted': False, \
            'completed': False, \
            'deadline': '2022-12-31', \
            'return_url': 'https://example.com/exercise1_return', \
            'zip_url': 'https://example.com/exercise1_zip', \
            'exercise_submissions_url': 'https://example.com/submissions1'}]



db = peewee.SqliteDatabase('my_database.db')

class Course(peewee.Model):
    tid = peewee.IntegerField()
    name = peewee.CharField()
    details_url = peewee.CharField()
    
    class Meta:
        database = db
    
    
    @staticmethod
    def get_selected():try:
            return Course.select().order_by(Course.tid).get()
        except Course.DoesNotExist:
            return None

db.connect()
Course.create_table()

class Exercise:
    
    def byid(cls, exercise_id):
        return DummyExercise()
    
    
    @classmethod
    def create(cls, **kwargs):return DummyExercise(**kwargs)


class DummySpinnerContext:
    def __enter__(self):
        print('Starting spinner')
    
    def __exit__(self, exc_type, exc_val, exc_tb):
        print('Spinner done')


class DummyCourse:
    def __init__(self, **kwargs):
        self.__dict__.update(kwargs)
    
    def save(self):
        pass


class DummyExercise:
    def __init__(self, **kwargs):
        self.__dict__.update(kwargs)
    
    def save(self):
        pass
    
    def path(self):
        return ''


api = API()


def download_exercise(ex, update=False):
    print('Downloading (updating)' if update else 'Downloading')
    pass


def update(course=False):
    if course:
        with Spinner.context(msg='Updated course metadata.', 
            waitmsg='Updating course metadata.'):
            for course in api.get_courses():
                old = None
                try:
                    old = Course.get(Course.tid == course['id'])
                except peewee.DoesNotExist:
                    old = None
                if old:
                    old.details_url = course['details_url']
                    old.save()
                    continue
                Course.create(tid=course['id'], name=course['name'], 
                    details_url=course['details_url'])
    else:
        selected = Course.get_selected()
        print('Updating exercise data.')
        for exercise in api.get_exercises(selected):
            old = None
            try:
                old = Exercise.byid(exercise['id'])
            except peewee.DoesNotExist:
                old = None
            if old is not None:
                old.name = exercise['name']
                old.course = selected.id
                old.is_attempted = exercise['attempted']
                old.is_completed = exercise['completed']
                old.deadline = exercise.get('deadline')
                old.is_downloaded = os.path.isdir(old.path())
                old.return_url = exercise['return_url']
                old.zip_url = exercise['zip_url']
                old.submissions_url = exercise['exercise_submissions_url']
                old.save()
                download_exercise(old, update=True)
            else:
                ex = Exercise.create(tid=exercise['id'], 
                    name=exercise['name'], 
                    course=selected.id, 
                    is_attempted=exercise['attempted'], 
                    is_completed=exercise['completed'], 
                    deadline=exercise.get('deadline'), 
                    return_url=exercise['return_url'], 
                    zip_url=exercise['zip_url'], 
                    submissions_url=exercise['exercise_submissions_url'])
                
                
                ex.is_downloaded = os.path.isdir(ex.path())
                ex.save()


def main():
    update(course=True)
    update(course=False)


if __name__ == '__main__':
    main()