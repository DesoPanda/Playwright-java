package j4.qa.opencart.Test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import j4.qa.opencart.AppConstants.AppConstant;
import j4.qa.opencart.Base.BaseTest;
import j4.qa.opencart.Factory.BrowserFactory;

public class HomePageTest extends BaseTest {

//	HomePageTest homePageTest;
//	OpenCartHomePage homePage;

//	public HomePageTest(Page page) {
//		super(page);
//	}

	Page page;
	BrowserFactory factory = new BrowserFactory();
//	BrowserFactory factory = new BrowserFactory(page);

	

	@Test(priority = 1)
	public void homePageTitleTest() {
		String title = homePage.getTitle();
		System.out.println("Title is- " + title);
		Assert.assertEquals(title, AppConstant.HOME_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void homePageUrlTest() {
		String url = homePage.getUrl();
		System.out.println("Url is- " + url);
		Assert.assertEquals(url, properties.getProperty("Url"));
	}

	@Test(priority = 3)
	public void verifyHomePageHeaderTest() {
//		PlaywrightAssertions.assertThat(homePage.verifyHeaderLogo()).hasClass("img-responsive");
		Assert.assertEquals(homePage.verifyHeaderLogo(), AppConstant.HOME_PAGE_HEADERlOGOTEXT);
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			
			{"Iphone"},
			{"iMac"},
			{"Samasung"}
		};
	}
	
	
	@Test(priority = 4, dataProvider = "getProductData")
	public void doSearch(String prodName) {
		// Verify placeholder text
		String placeHolderText = homePage.verifySearchBoxPlaceholderText();
		System.out.println("SearchBox Placeholder is- " + placeHolderText);
		Assert.assertEquals(placeHolderText, AppConstant.HOME_PAGE_PLACEHOLDERTEXT);

		// verify searchProduct
		String seachHeader = homePage.searchForAnyComponent(prodName);
		System.out.println("Search product is- " + seachHeader);
		assertEquals(seachHeader, "Search - "+ prodName);
	}

	
}
