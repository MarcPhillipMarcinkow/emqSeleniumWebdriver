package emq.webdriver.example.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Diese Klasse beinhaltet die Konfigurationen für die jeweiligen Webdriver
 * 
 * @author Marc Philipp Marcinkowski
 */
public class ConfigDrivers {

	private WebDriver driver;

	/**
	 * Konfiguriert den Webdriver für firefox
	 * @return Den konfigurierten Firefox Webdriver
	 */
	public WebDriver getFirefox() {
		System.setProperty("webdriver.gecko.driver", "target\\resources\\geckodriver.exe");
		return driver = new FirefoxDriver();
	}
	
	/**
	 * Konfiguriert den Webdriver für Google Chrome
	 * @return Den konfigurierten Google Chrome Webdriver
	 */
	public WebDriver getChrome() {
		System.setProperty("webdriver.chrome.driver", "target\\resources\\chromedriver.exe");
		return driver = new ChromeDriver();

	}

}
