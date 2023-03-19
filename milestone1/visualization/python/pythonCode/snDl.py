def sina_download(url, output_dir='.', merge=True, info_only=False, **kwargs):
    """Downloads Sina videos by URL.
    """
    if 'news.sina.com.cn/zxt' in url:
        sina_zxt(url, output_dir=output_dir, merge=merge, info_only=info_only, **kwargs)
        return

    vid = match1(url, r'vid=(\d+)')
    if vid is None:
        video_page = get_content(url)
        vid = hd_vid = match1(video_page, r'hd_vid\s*:\s*\'([^\']+)\'')
        if hd_vid == '0':
            vids = match1(video_page, r'[^\w]vid\s*:\s*\'([^\']+)\'').split('|')
            vid = vids[-1]

    if vid is None:
        vid = match1(video_page, r'vid:"?(\d+)"?')
    if vid:
        #title = match1(video_page, r'title\s*:\s*\'([^\']+)\'')
        sina_download_by_vid(vid, output_dir=output_dir, merge=merge, info_only=info_only)
    else:
        vkey = match1(video_page, r'vkey\s*:\s*"([^"]+)"')
        if vkey is None:
            vid = match1(url, r'#(\d+)')
            sina_download_by_vid(vid, output_dir=output_dir, merge=merge, info_only=info_only)
            return
        title = match1(video_page, r'title\s*:\s*"([^"]+)"')
        sina_download_by_vkey(vkey, title=title, output_dir=output_dir, merge=merge, info_only=info_only)

