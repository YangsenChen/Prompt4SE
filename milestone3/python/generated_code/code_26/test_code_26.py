from code_26 import *

import unittest
from unittest.mock import MagicMock, patch


# Import your classes and functions here
# from your_module import update, api, Course, Exercise, download_exercise

class TestUpdateFunction(unittest.TestCase):
    def test_update_courses(self):
        api.get_courses = MagicMock(
            return_value=[{"id": 1, "name": "Course 1", "details_url": "https://example.com/course1"}])
        Course.get = MagicMock(side_effect=peewee.DoesNotExist)
        Course.create = MagicMock()

        update(course=True)

        api.get_courses.assert_called_once()
        Course.create.assert_called_once_with(tid=1, name="Course 1", details_url="https://example.com/course1")

    # def test_update_exercises(self):
    #     api.get_exercises = MagicMock(return_value=[{
    #         "id": 1,
    #         "name": "Exercise 1",
    #         "attempted": False,
    #         "completed": False,
    #         "deadline": "2022-12-31",
    #         "return_url": "https://example.com/exercise1_return",
    #         "zip_url": "https://example.com/exercise1_zip",
    #         "exercise_submissions_url": "https://example.com/submissions1"
    #     }])
    #     Course.get_selected = MagicMock(return_value=DummyCourse(id=1))
    #     Exercise.byid = MagicMock(side_effect=peewee.DoesNotExist)
    #     Exercise.create = MagicMock()
    #
    #     with patch("os.path.isdir", return_value=False):
    #         with patch("your_module.download_exercise") as mock_download_exercise:
    #             update(course=False)
    #
    #     api.get_exercises.assert_called_once_with(DummyCourse(id=1))
    #     Exercise.create.assert_called_once_with(tid=1,
    #                                             name="Exercise 1",
    #                                             course=1,
    #                                             is_attempted=False,
    #                                             is_completed=False,
    #                                             deadline="2022-12-31",
    #                                             return_url="https://example.com/exercise1_return",
    #                                             zip_url="https://example.com/exercise1_zip",
    #                                             submissions_url="https://example.com/submissions1")
    #     mock_download_exercise.assert_called_once()


if __name__ == '__main__':
    unittest.main()