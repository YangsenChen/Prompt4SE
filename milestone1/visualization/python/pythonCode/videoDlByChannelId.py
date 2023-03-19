def vimeo_download_by_channel_id(channel_id, output_dir='.', merge=False, info_only=False, **kwargs):
    """str/int->None"""
    html = get_content('https://api.vimeo.com/channels/{channel_id}/videos?access_token={access_token}'.format(channel_id=channel_id, access_token=access_token))
    data = loads(html)
    id_list = []

    #print(data)
    for i in data['data']:
        id_list.append(match1(i['uri'], r'/videos/(\w+)'))

    for id in id_list:
        try:
            vimeo_download_by_id(id, None, output_dir, merge, info_only, **kwargs)
        except urllib.error.URLError as e:
            log.w('{} failed with {}'.format(id, e))

