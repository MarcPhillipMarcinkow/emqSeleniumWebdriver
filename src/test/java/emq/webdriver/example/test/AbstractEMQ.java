package emq.webdriver.example.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import emq.webdriver.example.config.ConfigDrivers;

/**
 * Diese abstrakte Klasse ist eine Hilfsklasse für die einzelnen Testfälle. Es
 * wird dort anfangs initiall der Webdriver definiert und instanziert. Auch wird
 * der WebDriver am Ende wieder geschlossen
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
		driver = conf.getChrome(); // Definiert GoogleChrome als Webdriver
		driver.manage().window().maximize(); // Maximiert das Fenster des Browsers
	}

	/**
	 * Gibt den Konfigurierten Webdriver zurück
	 * 
	 * @return Webdriver
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
