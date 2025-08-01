package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.orangehrm.actiondriver.ActionDriver;


public class BaseClass {

	protected static Properties prop;
	protected static WebDriver driver;
	private static ActionDriver actiondriver;
	
	

	@BeforeSuite
	public void loadConfig() throws IOException {
		// Load the configuration file
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
	
	}
    
	@BeforeMethod
	public void setup() throws IOException {
		System.out.println("Setting up driver for :" + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticWait(5);

		// Initialize the action driver only once
		if (actiondriver == null) {
			actiondriver = new ActionDriver(driver);
			System.out.println("Action driver instance is created"+Thread.currentThread().getId());
		}
	}
    
	@BeforeMethod
	private void launchBrowser() {
		// Initialize the WebDriver from the properties file

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {

			System.out.println("Browser not supported");
		}
	}
    @BeforeMethod
	private void configureBrowser() {
		// Implicit wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// maximize the browser
		driver.manage().window().maximize();

		// navigate to url
		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Failed to navigate" + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null)
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Unable to quit the browser" + e.getMessage());
			}
		System.out.println("WebDriver instance is closed");
		driver = null;
		actiondriver = null;
	}

	/*
	 * // getter method for prop public static Properties getProp() { return prop; }
	 * //setting setter and getter methods to use driver outside of the package
	 * //setting getter method public WebDriver getDriver() { return driver; }
	 */

	// Getter method for WebDriver
	public static WebDriver getDriver() {
		if (driver == null) {
			System.out.println("WebDriver is not initialised");
			throw new IllegalStateException("WebDriver is not initialized");
		}
		return driver;
	}

	// Getter method for ActionDriver
	public static ActionDriver getActionDriver() {
		if (actiondriver == null) {
			System.out.println("ActionDriver is not initialised");
			throw new IllegalStateException("Actiondriver is not initialized");
		}
		return actiondriver;
	}

	//getter method for prop
	public static Properties getProp() {
		return prop;
	}

	// setting setter method
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// Static wait for pause
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
}
