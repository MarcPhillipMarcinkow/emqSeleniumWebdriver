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

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Öffnet die Login Seite
	 */
	public void openPage() {
		logger.info("Navigiere zur Login-Makse");
		driver.navigate().to("https://onlinechilishop.de/login/");
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
	 * Navigiert zum Logout
	 */
	public void logout() {
		logger.info("Navigiere zum Logout");
		driver.navigate().to("https://onlinechilishop.de/login/?logout");
	}

	/**
	 * Bekomme die erfolgreiche Nachricht im Loginformular, welche mit grüner
	 * Schrift dargestellt wird
	 * 
	 * @return Gibt die erfolgreiche Nachricht des Logins zurück
	 */
	public String getSuccessAlertMessage() {
		WebElement sucess = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.className("alert-success")));
		logger.info("Erfolgreiche Nachricht: " + sucess.getText());
		return sucess.getText();
	}

	/**
	 * Bekomme die nicht erfolgreiche Nachricht (Error) im Loginformular, welche
	 * mit roter Schrift dargestellt wird
	 * 
	 * @return Gibt die nicht erfolgreiche Nachricht (Error) des Logins zurück
	 */
	public String getDangerAlertMessage() {
		WebElement error = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.className("alert-danger")));
		logger.info("Error Nachricht: " + error.getText());
		return error.getText();
	}
}
