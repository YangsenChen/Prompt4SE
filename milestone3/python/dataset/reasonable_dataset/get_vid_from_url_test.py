from CountList import get_vid_from_url
from unittest import TestCase

def test_get_vid_from_url():
    assert get_vid_from_url('https://youtu.be/dQw4w9WgXcQ') == 'dQw4w9WgXcQ'
    assert get_vid_from_url('https://www.youtube.com/embed/dQw4w9WgXcQ') == 'dQw4w9WgXcQ'
    assert get_vid_from_url('https://www.youtube.com/v/dQw4w9WgXcQ') == 'dQw4w9WgXcQ'
    assert get_vid_from_url('https://www.youtube.com/watch?v=dQw4w9WgXcQ') == 'dQw4w9WgXcQ'
    assert get_vid_from_url('https://www.youtube.com/?v=dQw4w9WgXcQ') == 'dQw4w9WgXcQ'
    assert get_vid_from_url('https://www.youtube.com/?u=https%3A%2F%2Fyoutu.be%2FdQw4w9WgXcQ&v=dQw4w9WgXcQ') == 'dQw4w9WgXcQ'

    assert get_vid_from_url('https://www.youtube.com') is None
    assert get_vid_from_url('https://www.youtube.com/watch') is None
    assert get_vid_from_url('https://www.youtube.com/watch?') is None
    assert get_vid_from_url('https://www.youtube.com/watch?v=') is None
    assert get_vid_from_url('https://www.youtube.com/watch?v=123') == '123'
    assert get_vid_from_url('https://www.youtube.com/watch?v=123&t=10s') == '123'
    assert get_vid_from_url('https://www.youtube.com/watch?u=https%3A%2F%2Fyoutu.be%2F123&v=123') == '123'

