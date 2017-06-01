package emq.webdriver.example.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Diese Klasse ist das Pageobject für die Hauptseite. Dieses beinhaltet
 * momentan die Buttons, wie Login oder Logout, sowie die Elemente und Methoden
 * zur Suche
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

	@FindBy(className = "fa-sign-out")
	WebElement logoutButton;

	@FindBy(className = "fa-user")
	WebElement profilButton;

	/**
	 * Initialisiert ein Pageobjekt der Hauptseite. Dieses beinhaltet momentan die Navigation zur Hauptseite, Buttons und die Suche
	 * @param driver WebDriver
	 */
	public MainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * öffnet die Hauptseite und Authentifiziert sich per HTTP
	 */
	public void openPage() {

		logger.info("Öffnet die Hauptseite");
		driver.get("https://daniel:%23chili@onlinechilishop.de/"); // Öffnet die Seite mit Authentifizierung
	}

	/**
	 * Prüft ob der Loginbutton angezeigt wird
	 * @return true, wenn der Button angezeigt wird, sonst false
	 */
	public Boolean loginButtonIsDisplayed() {
		return loginButton.isDisplayed(); 
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
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(searchResults)).isDisplayed();
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
	 * Überprüft ob Benutzerprofil Button sichtbar ist, dafür wird maximal 10
	 * Sekunden gewartet
	 * 
	 * @return true wenn ja, sonst false
	 */
	public boolean isLoggedIn() {
		return (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOf(profilButton)).isDisplayed();
	}

	/**
	 * Clickt den Logout button. Wegen verzögerungen wird auf den Logout-Button
	 * 10 Sekunden gewartet
	 */
	public void clickLogoutButton() {
		/*
		 * Wartet maximal 10 Sekunden bis der logoutButton klickbar ist. Wenn er klickbar ist wird er geklickt.
		 */
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
	}
}
