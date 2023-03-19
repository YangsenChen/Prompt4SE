def sina_download_by_vid(vid, title=None, output_dir='.', merge=True, info_only=False):
    """Downloads a Sina video by its unique vid.
    http://video.sina.com.cn/
    """
    xml = api_req(vid)
    urls, name, size = video_info(xml)
    if urls is None:
        log.wtf(name)
    title = name
    print_info(site_info, title, 'flv', size)
    if not info_only:
        download_urls(urls, title, 'flv', size, output_dir = output_dir, merge = merge)

