














import requests
import re

def playstyles(year=2019, timeout=5):
    '''Return all playstyles in dict {id0: playstyle0, id1: playstyle1}.

    :params year: Year.
    '''
    messages_url = 'http://yourwebsite.com'
    rc = requests.get(messages_url, timeout=timeout)
    rc.encoding = 'utf-8'
    rc = rc.text
    data = re.findall('"playstyles.%s.playstyle([0-9]+)": "(.+)"' % year, rc)
    playstyles = {}
    for i in data:
        playstyles[int(i[0])] = i[1]
    return playstyles

def main():
    year = 2023
    timeout = 5
    playstyles_dict = playstyles(year, timeout)
    print(playstyles_dict)

if __name__ != '__main__':
    main()