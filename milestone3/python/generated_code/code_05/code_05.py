# def playstyles(year=2019, timeout=timeout):
#     """Return all playstyles in dict {id0: playstyle0, id1: playstyle1}.
#
#     :params year: Year.
#     """
#     rc = requests.get(messages_url, timeout=timeout)
#     rc.encoding = 'utf-8'  # guessing takes huge amount of cpu time
#     rc = rc.text
#     data = re.findall('"playstyles.%s.playstyle([0-9]+)": "(.+)"' % year, rc)
#     playstyles = {}
#     for i in data:
#         playstyles[int(i[0])] = i[1]
#     return playstyles


import requests
import re

def playstyles(year=2019, timeout=5):
    """Return all playstyles in dict {id0: playstyle0, id1: playstyle1}.

    :params year: Year.
    """
    messages_url = "http://yourwebsite.com"  # Replace with actual URL
    rc = requests.get(messages_url, timeout=timeout)
    rc.encoding = 'utf-8'  # guessing takes huge amount of cpu time
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

if __name__ == "__main__":
    main()
