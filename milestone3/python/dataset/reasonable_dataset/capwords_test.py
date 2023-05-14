from capwords import capwords

def test_capwords():
    assert capwords("hello world") == "Hello World"
    assert capwords(" foo bar baz ") == "Foo Bar Baz"
    assert capwords("camelCase") == "Camelcase"
    assert capwords("1 2 3 4 5") == "1 2 3 4 5"
    assert capwords("one,two,three", sep=",") == "One,Two,Three"
    assert capwords(" speak to me ", sep="to") == " Speak To Me "