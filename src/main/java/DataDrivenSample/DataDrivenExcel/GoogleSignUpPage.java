package DataDrivenSample.DataDrivenExcel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Factory for the Google Sign Up page.
 * 
 * @author Annamaria_Szegedi
 *
 */
public class GoogleSignUpPage {
	@FindBy(id = "LastName")
	private WebElement lastNameBox;
	@FindBy(id = "FirstName")
	private WebElement firstNameBox;
	@FindBy(id = "GmailAddress")
	private WebElement gmailAddressBox;
	@FindBy(id = "Passwd")
	private WebElement passwdBox;
	@FindBy(id = "PasswdAgain")
	private WebElement passwdAgainBox;
	@FindBy(id = "errormsg_0_GmailAddress")
	private WebElement emailWarning;
	
	/**
	 * Constructor for the Data Search Page Factory.
	 * @param driver
	 */
	public GoogleSignUpPage() {
		System.out.println("Google Sign Up page is initialized.");
	}
	
	/**
	 * Fill out the Last Name text box.
	 * 
	 * @param name
	 */
	public void setLastName(String name) {
		lastNameBox.clear();
		lastNameBox.sendKeys(name);
	}

	/**
 	 * Get the Last Name text box value.
 	 * 
	 * @return The value of the Last Name text box or null.
	 */
	public String getLastName() {
		try {
			return lastNameBox.getText();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Fill out the First Name text box.
	 * 
	 * @param name
	 */
	public void setFirstName(String name) {
		firstNameBox.clear();
		firstNameBox.sendKeys(name);
	}

	/**
	 * Fill out the email address text box.
	 * 
	 * @param address
	 */
	public void setGmailAddress(String address) {
		gmailAddressBox.clear();
		gmailAddressBox.sendKeys(address);
	}

	/**
	 * Get the email address warning.
	 */
	public boolean getGmailWarning() {
		return emailWarning.isDisplayed();
	}

	/**
	 * Fill out the Password text box.
	 * 
	 * @param password
	 */
	public void setPasswd(String password) {
		passwdBox.clear();
		passwdBox.sendKeys(password);
	}

	/**
	 * Fill out the Confirm Password text box.
	 * 
	 * @param password
	 */
	public void setPasswdAgain(String password) {
		passwdAgainBox.clear();
		passwdAgainBox.sendKeys(password);
	}

	/**
 	 * Get the Confirm Password text box value.
 	 * 
	 * @return The value of the Confirm Password text box or null.
	 */
	public String getPasswdAgain() {
		try {
			return passwdAgainBox.getText();
		} catch (Exception e) {
			return null;
		}
	}
}
