package emq.webdriver.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Pageobject für die Registrierung
 * 
 * @author Marc Philipp Marcinkowski
 *
 */
public class Login {
	private WebDriver driver;

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
		driver.navigate().to("https://onlinechilishop.de/login/");
	}

	/**
	 * Füllt das Loginformular 
	 * @param loginEmail Email des Benutzers
	 * @param loginPassword Password des Benutzers
	 */
	public void login(String loginEmail, String loginPassword) {
		email.sendKeys(loginEmail);
		password.sendKeys(loginPassword);
		submit.submit();
	}

	/**
	 * Navigiert zum Logout 
	 */
	public void logout() {
		driver.navigate().to("https://onlinechilishop.de/login/?logout");
	}

	/**
	 * Bekomme die erfolgreiche Nachricht im Loginformular, welche mit grüner Schrift dargestellt wird
	 * @return Gibt die erfolgreiche Nachricht des Logins zurück
	 */
	public String getSuccessAlertMessage() {
		return driver.findElement(By.className("alert-success")).getText();
	}
	
	/**
	 * Bekomme die nicht erfolgreiche Nachricht im Loginformular, welche mit roter Schrift dargestellt wird
	 * @return Gibt die nicht erfolgreiche Nachricht des Logins zurück
	 */
	public String getDangerAlertMessage() {
		return driver.findElement(By.className("alert-danger")).getText();
	}
}
