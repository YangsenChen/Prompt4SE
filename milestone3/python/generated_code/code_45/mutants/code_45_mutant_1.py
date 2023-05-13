def tag_to_text(tag):
    '''
    :param tag: Beautiful soup tag
    :return: Flattened text
    '''
    out = []
    for item in tag.contents:
        
        if not (item.name):
            out.append(tag_to_text(item))
        else:
            
            out.append(item)
    
    return ' '.join(out)