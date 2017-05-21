package emq.webdriver.example.config;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


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
		System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
		return driver = new FirefoxDriver();
	}
	
	/**
	 * Konfiguriert den Webdriver für Google Chrome
	 * @return Den konfigurierten Google Chrome Webdriver
	 */
	public WebDriver getChrome() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		LoggingPreferences prefs = new LoggingPreferences();
		prefs.enable(LogType.DRIVER, Level.INFO);
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability(CapabilityType.LOGGING_PREFS, prefs);
		return driver = new ChromeDriver(caps);
	}
	
	/**
	 * Konfiguriert den Webdriver für Internet Explorer
	 * @return Den konfigurierten Internet Explorer Webdriver
	 */
	public WebDriver getIE() {
		System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
		return driver = new InternetExplorerDriver();
	}
	
	/**
	 * Konfiguriert den Webdriver für Opera
	 * @return Den konfigurierten Opera Webdriver
	 */
	public WebDriver getOpera() {
		System.setProperty("webdriver.opera.driver", "src\\main\\resources\\operadriver.exe");
		return driver = new OperaDriver();
	}
	
	/**
	 * Konfiguriert den Webdriver für Edge
	 * @return Den konfigurierten Edge Webdriver
	 */
	public WebDriver getEdge() {
		System.setProperty("webdriver.edge.driver", "src\\main\\resources\\MicrosoftWebDriver.exe");
		return driver = new EdgeDriver();
	}
	
	/**
	 * Konfiguriert den Webdriver für Phantomjs
	 * @return Den konfigurierten Phantomjs Webdriver
	 */
	public WebDriver getPhantomJs() {
		System.setProperty("phantomjs.binary.path", "src\\main\\resources\\phantomjs.exe");
		return driver = new PhantomJSDriver();
	}

}
