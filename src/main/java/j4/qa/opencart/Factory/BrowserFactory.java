package j4.qa.opencart.Factory;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

//	Playwright playwright;
//	BrowserType browserType;
//	Browser browser;
//	BrowserContext browserContext;
//	Page page;
	Properties properties;

	/**
	 * Introducing ThreadLocal Concept......
	 */

	private static ThreadLocal<BrowserType> tlocalBrowserType = new ThreadLocal<>();
	private static ThreadLocal<Browser> tlocalBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlocalBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlocalPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlocalPlaywright = new ThreadLocal<>();

	/**
	 * Defining ThreadLocal get() Concept......
	 */

	public static Playwright getPlaywright() {
		return tlocalPlaywright.get();
	}

	public static BrowserType getBrowserType() {
		return tlocalBrowserType.get();

	}

	public static Browser getBrowser() {
		return tlocalBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tlocalBrowserContext.get();
	}

	public static Page getPage() {
		return tlocalPage.get();

	}

	public Page initBrowser(Properties properties) {

		String browserName = properties.getProperty("Browser").trim();
		Boolean headLess = Boolean.valueOf(properties.getProperty("Headless"));
		int width = BrowserFactory.setViewPortWidthSize();
		int height = BrowserFactory.setViewPortHeightSize();

		System.out.println("Application opened with " + browserName + " browser...");
		System.out.println("Headless browser mode is set to " + headLess);

//		playwright = Playwright.create();
		tlocalPlaywright.set(Playwright.create());

		switch (browserName.toLowerCase()) {

		case "chromium":
//			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headLess));
			tlocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headLess)));
			break;

		case "firefox":
//			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headLess));
			tlocalBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headLess)));
			break;

		case "safari":
//			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headLess));
			tlocalBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headLess)));
			break;

		case "chrome":
//			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headLess));
			tlocalBrowser.set(getPlaywright().chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headLess)));
			break;

		case "edge":
//			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headLess));
			tlocalBrowser.set(getPlaywright().chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headLess)));
			break;

		default:
			System.out.println("Please select a browser to be initialize...");
			break;
		}

//		browserContext = browser.newContext(
//				new Browser.NewContextOptions().setScreenSize(width, height).
//				                       setRecordVideoDir(Paths.get("./video-output")));
//		page = browserContext.newPage();

		/**
		 * Using ThreadLocal get() & set() Concept......
		 */

		tlocalBrowserContext.set(getBrowser().newContext(
				new NewContextOptions().setScreenSize(width, height).setRecordVideoDir(Paths.get("./video-output"))));
		tlocalPage.set(getBrowserContext().newPage());

		navigate(properties.getProperty("Url").trim());

		return getPage();
	}

	public void navigate(String url) {
		tlocalPage.get().navigate(url);
		tlocalPage.get().waitForLoadState();
	}

	/**
	 * This method is used to initialize the properties from config file
	 */
	public Properties initProperties() {
		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
			properties = new Properties();
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
//C:/PlayJava/Playwright_WebAutomation/src/test/resources/config/config.properties
	}

	public static int setViewPortWidthSize() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) dimension.getWidth();
		return width;

	}

	public static int setViewPortHeightSize() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		return (int) dimension.getHeight();

	}

	public static String takeScreenShot() {

		String path= System.getProperty("user.dir")+ "/screenshots/"+ 
		                                         System.currentTimeMillis()+".png";
		getPage().screenshot(new ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return path;

	}

}
