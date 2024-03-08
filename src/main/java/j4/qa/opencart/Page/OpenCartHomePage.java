package j4.qa.opencart.Page;

import com.microsoft.playwright.Page;

public class OpenCartHomePage {

	private Page page;

	private String headerLogo = "#logo a img[title='naveenopencart']";
	private String searchBox = "#search input[name='search']";
	private String clickOnSearchbtn = "#search button[type='button']";
	private String searchPageHeader = "div#content h1";
	private String resisterTab= "li a:has-text('Register')";
	private String myAccountTab= "//a[@title='My Account']";
	

	public OpenCartHomePage(Page page) {
		this.page = page;
	}

	public String verifyHeaderLogo() {

		return page.locator(headerLogo).getAttribute("class");
		
	}

	public String verifySearchBoxPlaceholderText() {
		// verify placeholder text
		return page.locator("#search input[name='search']").getAttribute("placeholder");
		//System.out.println(getPlaceholderText);
		
	}

	public String searchForAnyComponent(String component) {
		page.locator(searchBox).fill(component);
		page.click(clickOnSearchbtn);

		return page.locator(searchPageHeader).textContent();
	}

	public String getTitle() {
//		System.out.println("Title is- "+ page.title());
		return page.title();
	}

	public String getUrl() {
//		System.out.println("Url is- "+ page.url());
		return page.url();
	}
	
	public OpenCartResisterPage navigateToRegisterPage() {
		page.locator(myAccountTab).first().isVisible();
		page.hover(myAccountTab);
		page.click(myAccountTab);
		
		page.locator(resisterTab).first().isVisible();
		page.locator(resisterTab).first().click();
		
		return new OpenCartResisterPage(page);
	}
}
