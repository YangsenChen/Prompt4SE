def tag_to_text(tag):
    """
    :param tag: Beautiful soup tag
    :return: Flattened text
    """
    out = []
    for item in tag.contents:
        # If it has a name, it is a tag
        if item.name:
            out.append(tag_to_text(item))
        else:
            # Just text!
            out.append(item)

    return ' '.join(out)

#COI
def tag_to_text(tag):
    """
    :param tag: Beautiful soup tag
    :return: Flattened text
    """
    out = []
    for item in tag.contents:
        # If it has a name, it is a tag
        #opposite add not
        if not item.name:
            out.append(tag_to_text(item))
        else:
            # Just text!
            out.append(item)

    return ' '.join(out)