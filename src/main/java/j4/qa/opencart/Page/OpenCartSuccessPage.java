package j4.qa.opencart.Page;

import com.microsoft.playwright.Page;

public class OpenCartSuccessPage {

	private String successPageHeader = "#content h1";
	private String successPagetext1 = "//div[@id='content']/p[1]";
	private String successPagetext2 = "//div[@id='content']/p[4]";
	private String continueBtn = "a:has-text('Continue')";

	private Page page;

	public OpenCartSuccessPage(Page page) {
		this.page = page;
	}

	// 3 Action/ methods on success page
	public String verifyHeaderText() {
		return page.locator(successPageHeader).innerText();

	}
	
	public String verifyPageText1() {
		return page.locator(successPagetext1).innerText();

	}

	public String verifyPageText2() {
		return page.locator(successPagetext2).innerText();
	}
	
	public OpenCartHomePage navigateToSuccessPage() {
		//click on continue
		page.locator(continueBtn).click();
		page.waitForLoadState();
		return new OpenCartHomePage(page);
	}
	
}
