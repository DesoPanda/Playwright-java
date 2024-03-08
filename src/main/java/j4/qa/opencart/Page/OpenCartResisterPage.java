package j4.qa.opencart.Page;

import java.util.Properties;
import java.util.Random;

import com.microsoft.playwright.Page;

import j4.qa.opencart.Factory.BrowserFactory;

public class OpenCartResisterPage {

	private String headerText = "#content h1";
	private String firstName = "#input-firstname";
	private String lastName = "#input-lastname";
	private String email = "#input-email";
	private String phone = "#input-telephone";
	private String password = "#input-password";
	private String confirmPassword = "#input-confirm";
	private String privacyPolicyCheckbox = "input[name='agree']";
	private String continueBtn = "input[value='Continue']";
	private String subscribeRadioBtn = "input[name='newsletter']";

	private Page page;
	BrowserFactory factory;
	Properties prop;

	// 2. resisterPage constructor
	public OpenCartResisterPage(Page page) {
		this.page = page;
	}

	// 3. Page Action/ Methods

	public String verifyRegisterPageHeaderText() {
		String header = page.locator(headerText).textContent();
		return header;
		
	}
	

	public OpenCartSuccessPage resisterNewAccount() {

		factory= new BrowserFactory();
		prop = factory.initProperties();
		
		page.locator(firstName).isVisible();
		page.locator(firstName).fill(prop.getProperty("FirstName"));

		page.locator(lastName).fill(prop.getProperty("LastName"));

		page.locator(email).fill("qa"+new Random().nextInt(40)+"@auto"+new Random().nextInt(60)+".com");
		
//		page.locator(email).fill(prop.getProperty("Email"));

		page.locator(phone).fill(prop.getProperty("Phone"));

		page.locator(password).fill(prop.getProperty("Password"));

		page.locator(confirmPassword).fill(prop.getProperty("ConfirmPassword"));

		// Verify bydefault subscribe "no" btn is selected
		page.locator(subscribeRadioBtn).first().isChecked();

		// check the policy agreement
		page.locator(privacyPolicyCheckbox).check();

		// click on continue to resister successfully
		page.locator(continueBtn).click();

		return new OpenCartSuccessPage(page);
	}

}
