package emq.webdriver.example.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * This class contains the configuration for the different webdrivers, which can be used for the tests
 * 
 * @author Marc Philipp Marcinkowski
 */
public class ConfigDrivers {

	private WebDriver driver;

	/**
	 * configurating the firefox webdriver
	 * @return the configurated firefox webdriver
	 */
	public WebDriver getFirefox() {
		System.setProperty("webdriver.gecko.driver", "target\\resources\\geckodriver.exe");
		return driver = new FirefoxDriver();
	}
	
	/**
	 * configurating the chrome webdriver
	 * @return the configurated chrome webdriver
	 */
	public WebDriver getChrome() {
		System.setProperty("webdriver.chrome.driver", "target\\resources\\chromedriver.exe");
		return driver = new ChromeDriver();

	}

}
