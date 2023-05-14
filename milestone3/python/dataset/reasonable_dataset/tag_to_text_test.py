from bs4 import BeautifulSoup
from unittest import TestCase
from TagToText import tag_to_text

class TestTagToText():
        html = "<p>This is a paragraph.</p>"
        soup = BeautifulSoup(html, 'html.parser')
        tag = soup.p
        expected_output = "This is a paragraph."
        assert(tag_to_text(tag) == expected_output)

        html = "<div><p>This is a paragraph.</p><p>Another paragraph.</p></div>"
        soup = BeautifulSoup(html, 'html.parser')
        tag = soup.div
        expected_output = "This is a paragraph. Another paragraph."
        assert(tag_to_text(tag) == expected_output)

        html = "<p></p>"
        soup = BeautifulSoup(html, 'html.parser')
        tag = soup.p
        expected_output = ""
        assert(tag_to_text(tag) == expected_output)