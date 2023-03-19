def veoh_download(url, output_dir = '.', merge = False, info_only = False, **kwargs):
    '''Get item_id'''
    if re.match(r'http://www.veoh.com/watch/\w+', url):
        item_id = match1(url, r'http://www.veoh.com/watch/(\w+)')
    elif re.match(r'http://www.veoh.com/m/watch.php\?v=\.*', url):
        item_id = match1(url, r'http://www.veoh.com/m/watch.php\?v=(\w+)')
    else:
        raise NotImplementedError('Cannot find item ID')
    veoh_download_by_id(item_id, output_dir = '.', merge = False, info_only = info_only, **kwargs)

