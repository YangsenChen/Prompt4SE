import random
import ijson
import json

python_data = []
count = 0
with open("../../../python.json",'r',encoding='utf-8') as f:
    for record in ijson.items(f,'item'):
        for r in record:
            count += 1
print(count)

rnumbers = []
for _ in range(50):
    temp = random.randint(0,count)
    rnumbers.append(temp)
print(rnumbers)

count = 0
with open("../../../python.json",'r',encoding='utf-8') as f:
    for record in ijson.items(f,'item'):
        for r in record:
            count += 1
            if count in rnumbers:
                python_data.append(r['original_string'])

json_data = {}
for i in range(len(python_data)):
    json_data[i] = python_data[i]
    print(python_data[i])
with open("dataset.json", 'w') as file:
    json.dump(json_data, file)