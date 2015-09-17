#Browser Data-Driven Testing#
By embedding control codes in the data the script reads, we direct and control the test script behavior with respect to the application under test (AUT). Furthermore when the application data changes, we only have to change a control code in the test data. We do not have to maintain the code in an interpreter script. Our approach is more economical in terms of test script maintenance.

We developed a test script function with TestNG DataProvider that read the MS Excel spreadsheets and directly pull the data into the AUT.

##TestNG DataProvider##
"Specifying parameters in testng.xml might not be sufficient if you need to pass complex parameters, or parameters that need to be created from Java (complex objects, objects read from a property file or a database, etc...). In this case, you can use a Data Provider to supply the values you need to test.  A Data Provider is a method on your class that returns an array of array of objects.  This method is annotated with @DataProvider"

You can read more at: http://testng.org/doc/documentation-main.html#parameters-dataproviders

##The advantages of data-driven test scripts##
1.	It is not necessary to modify the test script when the test data require changes.
2.	It is not necessary to modify the test script when it is necessary to add additional test data as the test data is appended to the existing text file.
3.	It is not necessary to modify the test script when the application under test is modified because you merely reset the control data values by changing or adding new ones.
4.	It is easy to modify the data records with Microsoft Excel.
5.	Multiple input data files can be created and used when required.

##MS Excel Test Data##
The test data file is located in a spreadsheet in the 'testdata' folder.

##MS Excel Test Result##
The listed test cases and theirs results are located in a separate spreadsheet in the 'testresult' folder.

##Technology Stack##
-	Java 1.8
-	Maven 3.2.5
-	TestNG 6.9.4
-	ReportNG 1.1.4
-	Apache POI 3.12
-	WebDriver [latest]
-	MS Excel
-	Eclipse IDE Mars
