import random
import ijson
import json

java_data = []
count = 0
with open("../../../java.json",'r',encoding='utf-8') as f:
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
with open("../../../java.json",'r',encoding='utf-8') as f:
    for record in ijson.items(f,'item'):
        for r in record:
            count += 1
            if count in rnumbers:
                java_data.append(r['original_string'])

json_data = {}
for i in range(len(java_data)):
    json_data[i] = java_data[i]
    print(java_data[i])
with open("java_all.json", 'w') as file:
    json.dump(json_data, file)