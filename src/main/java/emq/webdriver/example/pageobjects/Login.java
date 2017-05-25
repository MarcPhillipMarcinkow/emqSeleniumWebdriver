package emq.webdriver.example.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Pageobject für die Registrierung
 * 
 * @author Marc Philipp Marcinkowski
 *
 */
public class Login {

	private WebDriver driver;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Definierung von Webelementen
	 */

	@FindBy(name = "e")
	WebElement email;

	@FindBy(name = "input")
	WebElement password;

	@FindBy(tagName = "button")
	WebElement submit;
	
	@FindBy(className = "alert-success")
	WebElement sucessMessage;
	
	@FindBy(className = "alert-danger")
	WebElement errorMessage;

	public Login(WebDriver driver) {
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
		email.sendKeys(loginEmail);
		password.sendKeys(loginPassword);
		logger.info("Klicke auf Login");
		submit.submit();
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
