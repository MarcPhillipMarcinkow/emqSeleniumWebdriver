package emq.webdriver.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Diese Klasse ist das Pageobject für die Registrierung.
 * 
 * @author Marc Philipp Marcinkowski
 *
 */
public class RegistrationPage {
	private WebDriver driver;

	/**
	 * Definierung von Webelementen
	 */
	@FindBy(id = "inlineRadio1")
	WebElement anredeFrau;

	@FindBy(name = "firstname")
	WebElement firstName;

	@FindBy(name = "lastname")
	WebElement lastName;

	@FindBy(name = "email")
	WebElement email;

	@FindBy(name = "password_2")
	WebElement passwordConfirm;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "submit")
	WebElement submit;

	/**
	 * Initialisiert ein Pageobjekt für die Registrierung.
	 * @param driver WebDriver
	 */
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Öffnet die Seite der Registrierung
	 */
	public void openPage() {
		driver.navigate().to("https://onlinechilishop.de/register/");
	}

	/**
	 * Füllt das Formular der Registrierung aus und sendet es ab
	 */
	public void fillRegistrationForm() {
		firstName.sendKeys("Hans");
		lastName.sendKeys("Petar");
		anredeFrau.click();
		email.sendKeys("hans_petar@web.de");
		password.sendKeys("123");
		passwordConfirm.sendKeys("123");
		submit.click();
		submit.submit();
	}
}
