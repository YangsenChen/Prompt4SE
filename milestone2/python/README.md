## Code Instruction
### Data
Python data for this milestone consists of randomly generated 50 python methods from CodeSearchNet, which is under the ../dataset directory.
But most functions under CodeSearchNet come from a bigger Class/context that without relevant information we are not able to validate. And considering task 2 and task 3 require generating unit tests & equivalent code. 
And for us to validate the result, I pick 10 functions from CodeSearchNet that can be executed independently. That's why we have a reasonable_dataset folder and a separate reasonable.json file containing 10 functions. 

### Task 1
Manual generation, all chatting history under reasoning_all.md

### Task 2
scripy_to_all.py can be used to reproduce the unit_test_all.md, which is the chatting history of 50 random methods for task2.
Simply run
```
python scripy_to_all.py
```
under the current directory

script_to_reasonable.py is used to reproduce the unit test results for data under reasonable_dataset, and the results are used to count the incorrectly failed numbers.
Run
```
python script_to_reasonable.py
```
under the current directory

### Task 3
equivalent_code_all.py can be used to reproduce the task3_all.md, which is the semantically equivalent code of 50 random methods
Run
```
python equivalent_code_all.py
```
under the current directory

equivalent_code_reasonable.py is used to produce the equivalent code for data under reasonable_dataset. I use the result to compute how many tests produced by task2 get the same results as the original code.
Run
```
python equivalent_code_reasonable.py
```
under the current directory
