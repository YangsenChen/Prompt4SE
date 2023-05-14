from SplitNamespace import split_namespace

def test_split_namespace():
    assert split_namespace("{DAV:}foo") == ("DAV:", "foo")
    assert split_namespace("bar") == ("", "bar")
    assert split_namespace("{http://www.example.com/}sample") == ("http://www.example.com/", "sample")
    assert split_namespace("{http://www.w3.org/XML/1998/namespace}base") == ("http://www.w3.org/XML/1998/namespace", "base")
    assert split_namespace("{}spam") == ("", "spam")