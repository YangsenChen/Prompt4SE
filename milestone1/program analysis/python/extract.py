import json
  
f = open('test0.json')
data = json.load(f)

for i in data:
    print(i['code'])

f.close()