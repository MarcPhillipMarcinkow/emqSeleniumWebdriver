package emq.webdriver.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import emq.webdriver.example.config.ConfigDrivers;

public abstract class AbstractEMQ {

	public static WebDriver driver;

	/**
	 * Defines the used WebDriver
	 */
	@BeforeClass
	public static void setDriver() {
		ConfigDrivers conf = new ConfigDrivers();
		driver = conf.getChrome();
		driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	@AfterClass
	public static void shutdownDriver() {
		driver.quit();
	}
}
