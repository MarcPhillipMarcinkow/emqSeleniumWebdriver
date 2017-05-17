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
	 * Opens the login page
	 */
	public void openPage() {
		driver.navigate().to("https://onlinechilishop.de/login/");
	}

	/**
	 * fills the fomular from the login
	 * @param loginEmail email
	 * @param loginPassword password
	 */
	public void login(String loginEmail, String loginPassword) {
		email.sendKeys(loginEmail);
		password.sendKeys(loginPassword);
		submit.submit();
	}

	/**
	 * logout
	 */
	public void logout() {
		driver.navigate().to("https://onlinechilishop.de/login/?logout");
	}

	/**
	 * get the successful alert message in the login formular
	 * @return
	 */
	public String getSuccessAlertMessage() {
		return driver.findElement(By.className("alert-success")).getText();
	}
	
	/**
	 * get the danger alert message in the login formular
	 * @return
	 */
	public String getDangerAlertMessage() {
		return driver.findElement(By.className("alert-danger")).getText();
	}
}
