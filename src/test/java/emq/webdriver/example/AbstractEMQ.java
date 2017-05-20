package emq.webdriver.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import emq.webdriver.example.config.ConfigDrivers;

/**
 * Diese abstrakte Klasse ist eine Hilfsklasse für die einzelnen Testfälle. Es
 * wird dort anfangs initiall der Webdriver definiert und instanziert. Auch wird der WebDriver am ende wieder geschlossen
 * 
 * @author Marc Philipp Marcinkowski
 *
 */
public abstract class AbstractEMQ {

	public static WebDriver driver;

	/**
	 * Definiert und initial instanziert den zu verwendenen Webdriver
	 */
	@BeforeClass
	public static void setDriver() {
		ConfigDrivers conf = new ConfigDrivers();
		driver = conf.getChrome();
		driver.manage().window().maximize();
	}

	/**
	 * Gibt den Webdriver zurück
	 * @return den Webdriver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Schließt den Webdriver am Ende
	 */
	@AfterClass
	public static void shutdownDriver() {
		driver.quit();
	}
}
