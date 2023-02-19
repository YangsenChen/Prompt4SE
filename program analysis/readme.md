# Workspace

## python code instruction

### create codeql database
move to the directory with python code (in .py file) and use command
```
codeql database create --language=python --source-root <ur python code> <new codeql db>
```

After running the code a new folder will appear named "new codeql db" with all required files


### run and visualize control flow
copy the py_control_flow.ql to the directory with your codeql database and run
```
codeql database analyze <new codeql db> ./py_control_flow.ql --format=dot --output=<py control visualization...>
```
to rerun the ql file use this one:
```
codeql database analyze --rerun <new codeql db> ./py_control_flow.ql --format=dot --output=<py control visualization...>
```
without --rerun option codeql won't automatically rerun


A "py control visualization..." folder containing a null.dot file will appear if you do it correct. To visualize the result, simply:
```
dot -Tpng ./<py control visualization...>/null.dot -o <your png graph here..> 
```

And a png file with name "your png graph here.." will appear in your working directory.


### run and visualize data flow
copy the py_data_flow.ql to the directory with your codeql database and run
```
codeql database analyze <new codeql db> ./py_data_flow.ql --format=dot --output=<py data visualization...>
```
to rerun the ql file use this one:
```
codeql database analyze --rerun <new codeql db> ./py_data_flow.ql --format=dot --output=<py data visualization...>
```
without --rerun option codeql won't automatically rerun


A "py data visualization..." folder containing a null.dot file will appear if you do it correct. To visualize the result, simply:
```
dot -Tpng ./<py data visualization...>/null.dot -o <your png graph here..> 
```

And a png file with name "your png graph here.." will appear in your working directory.

