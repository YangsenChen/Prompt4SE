for task 1, the dataset is the same 50 as in milestone2 as required.


for task2 mutation test, the sequence number of code in the dataset is chosen as below, totally 25 codes where test cases are successfully generated and passed

['1', '4', '6', '7', '8', '10', 

'13', '14', '15', '17', '18', 

'19', '25', '26', '28', '30', 

'31', '33', '36', '38', '39', 

'40', '44', '45', '49']

to run the mutation test, run the following command in the root directory of each code project
mvn test-compile org.pitest:pitest-maven:mutationCoverage

the report is already generated in the target\pit-reports\ folder and can be viewed in the browser