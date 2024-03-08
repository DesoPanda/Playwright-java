package j4.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import j4.qa.opencart.AppConstants.AppConstant;
import j4.qa.opencart.Base.BaseTest;

public class RegisterPageTest extends BaseTest {

	@Test(priority = 1)
	public void verifyRegisterPageTitle() {
		resisterPage= homePage.navigateToRegisterPage();
		
		String header= resisterPage.verifyRegisterPageHeaderText();
		Assert.assertEquals(header, AppConstant.RESISTER_PAGE_HEADERTEXT);
	}
	
	@Test(priority = 2)
	public void registerNewAccount() {
		successPage= resisterPage.resisterNewAccount();
		String successPageHeader= successPage.verifyHeaderText();
		Assert.assertEquals(successPageHeader, AppConstant.SUCCESS_PAGE_HEADERTEXT);
	}
}
