for task2 mutation test, the code dataset is chosen as below, where test cases are successfully generated and passed

['1', '4', '6', '7', '8', '10', 

'13', '14', '15', '17', '18', 

'19', '25', '26', '28', '30', 

'31', '33', '36', '38', '39', 

'40', '44', '45', '49']

<plugin>
  <groupId>org.pitest</groupId>
  <artifactId>pitest-maven</artifactId>
  <version>1.13.0</version>
</plugin>

mvn test-compile org.pitest:pitest-maven:mutationCoverage
