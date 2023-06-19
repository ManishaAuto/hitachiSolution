package com.interview.hitachiSolutions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.interview.page.pageClass;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class TestClass extends BaseClass {
	public TestClass() throws Exception {
		super();
	}

	private WebDriver driver;
	private pageClass page = new pageClass();

	@BeforeTest
	public void setUp() {

		String browser = getPropertyValue("Browser").toLowerCase();

		switch (browser) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("-inprivate");
			driver = new EdgeDriver(options);
			break;
		}
		driver.get(getPropertyValue("BaseURL"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Long.valueOf(getPropertyValue("ImplicitTimeOut"))));
	}

	@Test
	public void VerifyAddressForIrvine() throws Exception {
		
		clickOnAElementUsingText("Contact us");

		Assert.assertEquals(getWebElementUsingXpath(page.findXpathOfParentOfText("Irvine, United States")).getText(),
				"Irvine, United States\n" + "100 Spectrum Center Drive, Suite 350, Irvine CA 92618, United States\n"
						+ "Corporate Phone: 949-242-1300");

	}

	private void clickOnAElementUsingText(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver,
				Duration.ofSeconds(Long.valueOf(getPropertyValue("ExplicitTimeOut"))));
		wait.until(
				ExpectedConditions.visibilityOf(getWebElementUsingXpath(page.findxPathElementWithText("Contact us"))));
		wait.until(ExpectedConditions
				.elementToBeClickable(getWebElementUsingXpath(page.findxPathElementWithText("Contact us"))));
		getWebElementUsingXpath(page.findxPathElementWithText(xpath)).click();
	}

	private WebElement getWebElementUsingXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}