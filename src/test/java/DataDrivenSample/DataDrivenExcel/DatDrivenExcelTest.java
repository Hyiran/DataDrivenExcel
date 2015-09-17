package DataDrivenSample.DataDrivenExcel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DataDrivenSample.DataDrivenExcel.ParsingExcel;

/**
 * This class was created based on the A. K. Sahu Blog
 * http://aksahu.blogspot.hu/
 * 2014/08/data-driven-testing-in-selenium-webdriver-with
 * -testng-using-excel.html
 * 
 */
public class DatDrivenExcelTest {
	WebDriver driver;
	/**
	 * Collection of the test results.
	 */
	Map<String, Object[]> testResults;

	/**
	 * TestNG Data Provider for the testSignup test case.
	 * 
	 * @return Provides the test users details in a two-dimensional array (row
	 *         and column)
	 *
	 * @throws Exception
	 */
	@DataProvider(name = "Users")
	public Object[][] getLoginDataForAllRoles() throws Exception {

		/**
		 * The file path should be the test data file full path. The sheet name
		 * should be the test sheet name in the test data file.
		 */
		ParsingExcel userData = new ParsingExcel("testdata/SampleExcel.xlsx", "Users");
		 
		/**
		 * Temporary array of the user details.
		 */
		ArrayList<Object[]> dataList = new ArrayList<Object[]>();

		int i = 1;// excluding header row
		int totalRows = 4;// the row number of data in the sheet
		while (i < totalRows) {
			System.out.println("Users : line : " + i);

			Object[] dataLine = new Object[6];
			dataLine[0] = userData.getCell(i, 0);// Test No
			dataLine[1] = userData.getCell(i, 1);// LastName
			dataLine[2] = userData.getCell(i, 2);// FirstName
			dataLine[3] = userData.getCell(i, 3);// GmailAddress
			dataLine[4] = userData.getCell(i, 4);// Passwd
			dataLine[5] = userData.getCell(i, 5);// PasswdAgain

			/**
			 * Fill out all the user details in the temporary array.
			 */
			dataList.add(dataLine);

			i++;
		}

		/**
		 * Iterating through on the user details in the temporary array.
		 */
		Object[][] data = new Object[dataList.size()][]; // 2D array of objects
		for (i = 0; i < dataList.size(); i++)
			data[i] = dataList.get(i);

		return data;// REturn back in the test case with the actual user
					// details.
	}

	/**
	 * Data driven test sample for creating Google account based on Excel
	 * spreadsheet test data set.
	 * 
	 * @param LastName
	 * @param FirstName
	 * @param GmailAddress
	 * @param Passwd
	 * @param PasswdAgain
	 */
	@Test(dataProvider = "Users", description = "Sign up for Google")
	public void testSignUp(String TestCaseNO, String LastName, String FirstName,
			String GmailAddress, String Passwd, String PasswdAgain) {
		/**
		 * Initialize page factory for the Google Sign Up page.
		 */
		GoogleSignUpPage googlesignup = PageFactory.initElements(driver,
				GoogleSignUpPage.class);

		/**
		 * The following lines contains the test steps for this test case.
		 * 
		 * Very Important! The proper way if the tester build a PageFactory
		 * class for every tested page with PageObjects for test steps.
		 * https://code.google.com/p/selenium/wiki/PageFactory
		 */
		Reporter.log("The Last Name is:" + LastName, true);
		googlesignup.setLastName(LastName);
		Reporter.log("The First Name is:" + FirstName, true);
		googlesignup.setFirstName(FirstName);
		Reporter.log("The Gmail Address is:" + GmailAddress, true);
		googlesignup.setGmailAddress(GmailAddress);
		Reporter.log("The Password is:" + Passwd, true);
		googlesignup.setPasswd(Passwd);
		Reporter.log("The Password Again is:" + PasswdAgain, true);
		googlesignup.setPasswdAgain(PasswdAgain);
		
		if (googlesignup.getGmailWarning()) {
			testResults.put(TestCaseNO, new Object[] { TestCaseNO, 1d, 
					"navigate to site and fill out the sign in details",
					"site opens and fill out success", "Pass" });
		} else {
			testResults.put(TestCaseNO, new Object[] { TestCaseNO, 1d,
					"navigate to site and fill out the sign in details",
					"site opens and fill out success", "Fail" });
			Assert.assertTrue(googlesignup.getGmailWarning(),
					"The Form is done successfully!");
		}
	}

	@BeforeClass(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
		testResults = new LinkedHashMap<String, Object[]>();
		testResults.put("1", new Object[] { "Test Case Id", "Test Step Id", "Action",
				"Expected Result", "Actual Result" });

		/**
		 * Create a new instance of the Firefox driver. Notice that the
		 * remainder of the code relies on the interface, not the
		 * implementation.
		 */
		driver = new FirefoxDriver();
		/**
		 * Open the Google Accounts Sign Up page.
		 */
		driver.get("https://accounts.google.com/signup");
	}

	@AfterClass(alwaysRun = true)
	public void testResults() {
		/**
		 * Close the browser window that the driver has been focused.
		 */
		driver.close(); 
		/**
		 * Close the Browser.
		 */
		driver.quit();
		/**
		 * The file path should be the test data file full path. The sheet name
		 * should be the test sheet name in the test data file.
		 */
		WritingExcel userData = new WritingExcel("testresult/SampleResult.xlsx ", "TestResults");

		
		Set<String> results = testResults.keySet();

		int rowIndex = 0;
		int columnIndex;
		for (String result : results) {
			Object[] resultValues = testResults.get(result);
			userData.createRow(rowIndex);
			columnIndex = 0;
			for (Object resultValue : resultValues) {
				userData.setCell(rowIndex, columnIndex, resultValue.toString());
				columnIndex++;
			}
			rowIndex++;
		}

		try {
			userData.closeExcel();
			System.out.println("Excel written successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
