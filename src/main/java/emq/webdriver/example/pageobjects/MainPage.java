package emq.webdriver.example.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Diese Klasse ist das Pageobject für die Hauptseite
 * 
 * @author Marc Philipp Marcinkowski
 */
public class MainPage {

	private WebDriver driver;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@FindBy(id = "searchInput")
	WebElement searchField;

	@FindBy(id = "searchResult")
	WebElement searchResults;

	@FindBy(id = "resultCount")
	WebElement resultCount;
	
	@FindBy(className = "fa-sign-in")
	WebElement loginButton;

	public MainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * öffnet die Hauptseite
	 */
	public void openPage() {

		logger.info("Öffnet die Hauptseite");
		driver.get("https://daniel:%23chili@onlinechilishop.de/");
	}

	/**
	 * Klickt den Loginbutton auf der Startseite
	 */
	public void clickLoginButton() {
		loginButton.click();
	}
	/**
	 * Gibt ein Wort in das Suchfeld ein
	 * 
	 * @param keyword
	 *            das zusuchende Wort
	 */
	public void enterKeywordInSearchfield(String keyword) {
		logger.info("Gebe das Wort: " + keyword + " in das Suchfeld ein");
		searchField.sendKeys(keyword);
	}

	/**
	 * Wartet maximal 10 Sekunden bis die Suchergebnisse angezeigt werden
	 */
	public void waitForSearchResults() {
		logger.info("Warte auf die Suchergebnisse");
		WebElement searchResults2 = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.visibilityOf(searchResults));
		searchResults2.isDisplayed();
	}

	/**
	 * Gibt die Anzahl der gefundenen Suchergebnisse zurück
	 * 
	 * @return Anzahl der gefundenen Suchergebnisse
	 */
	public int getResultsCount() {
		logger.info("Anzahl gefundener Ergebnisse: " + resultCount.getText());
		return Integer.parseInt(resultCount.getText());
	}
	
	/**
	 * Überprüft ob Benutzerprofil Button sichtbar ist
	 * @return true wenn ja, sonst false
	 */
	public boolean isLoggedIn() {
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.className("fa-user"))).isDisplayed();	
	}
}
