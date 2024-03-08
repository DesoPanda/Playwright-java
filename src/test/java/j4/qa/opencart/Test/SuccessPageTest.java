package j4.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import j4.qa.opencart.AppConstants.AppConstant;
import j4.qa.opencart.Base.BaseTest;

public class SuccessPageTest extends BaseTest {

	Page page;

	@Test(priority = 1)
	public void navigateToSuccessPage() {
		resisterPage= homePage.navigateToRegisterPage();
		successPage= resisterPage.resisterNewAccount();
		
	}

	@Test(priority = 2)
	public void verifyHeader() {
		String headerText = successPage.verifyHeaderText();
		Assert.assertEquals(headerText, AppConstant.SUCCESS_PAGE_HEADERTEXT);
	}

	@Test(priority = 3)
	public void verifyPageText() {
		String paraText1 = successPage.verifyPageText1();
		Assert.assertEquals(paraText1, AppConstant.SUCCESS_PAGE_PAGETEXT1);

		String paraText2 = successPage.verifyPageText2();
		Assert.assertEquals(paraText2, AppConstant.SUCCESS_PAGE_PAGETEXT2);
	}

	@Test(priority = 4)
	public void clickOnContinue() {
		homePage = successPage.navigateToSuccessPage();
		String header = homePage.verifyHeaderLogo();
		Assert.assertEquals(header, AppConstant.HOME_PAGE_HEADERlOGOTEXT);
	}
}
