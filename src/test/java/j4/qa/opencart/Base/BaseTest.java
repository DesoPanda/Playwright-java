package j4.qa.opencart.Base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;

import j4.qa.opencart.Factory.BrowserFactory;
import j4.qa.opencart.Page.OpenCartHomePage;
import j4.qa.opencart.Page.OpenCartResisterPage;
import j4.qa.opencart.Page.OpenCartSuccessPage;

public class BaseTest {

	Page page;
	protected OpenCartHomePage homePage;
	protected BrowserFactory factory;
	protected Properties properties;
	protected OpenCartResisterPage resisterPage;
	protected OpenCartSuccessPage successPage;
	
	
	
	@BeforeTest
	public void setUp() {
		factory = new BrowserFactory();
		properties= factory.initProperties();
		page = factory.initBrowser(properties);
		homePage = new OpenCartHomePage(page);
	}
	
	@AfterTest
	public void tearDown() {
//		factory.closeBrowser();
		page.context().browser().close();
	}
}
