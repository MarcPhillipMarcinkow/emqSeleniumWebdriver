package emq.webdriver.example.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Pageobject für das Login und Logout
 * 
 * @author Marc Philipp Marcinkowski
 *
 */
public class LoginPage {

	private WebDriver driver;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Definierung von Webelementen
	 */

	@FindBy(name = "e")
	WebElement emailField;

	@FindBy(name = "input")
	WebElement passwordField;

	@FindBy(tagName = "button")
	WebElement submitButton;
	
	@FindBy(className = "alert-success")
	WebElement sucessMessage;
	
	@FindBy(className = "alert-danger")
	WebElement errorMessage;

	/**
	 * Initialisiert ein Pageobjekt für das Einloggen und Ausloggen.
	 * @param driver WebDriver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Füllt das Loginformular
	 * 
	 * @param loginEmail
	 *            Email des Benutzers
	 * @param loginPassword
	 *            Password des Benutzers
	 */
	public void login(String loginEmail, String loginPassword) {

		logger.info("Authentifizieren mit Email: " + loginEmail + " und Passwort: " + loginPassword);
		public void login(String loginEmail, String loginPassword) {
			emailField.sendKeys(loginEmail);
			passwordField.sendKeys(loginPassword);
			submitButton.submit();
		}
	}

	/**
	 * Bekomme die erfolgreiche Nachricht im Loginformular, welche mit grüner
	 * Schrift dargestellt wird
	 * 
	 * @return Gibt die erfolgreiche Nachricht des Logins zurück
	 */
	public String getSuccessAlertMessage() {
		return (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.visibilityOf(sucessMessage)).getText();
	}

	/**
	 * Bekomme die nicht erfolgreiche Nachricht (Error) im Loginformular, welche
	 * mit roter Schrift dargestellt wird
	 * 
	 * @return Gibt die nicht erfolgreiche Nachricht (Error) des Logins zurück
	 */
	public String getDangerAlertMessage() {
		return (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
	}
}
