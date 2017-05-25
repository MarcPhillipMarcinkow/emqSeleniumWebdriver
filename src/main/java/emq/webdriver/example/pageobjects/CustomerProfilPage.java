package emq.webdriver.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Pageobject für die Profilseite
 * 
 * @author Marc Philipp Marcinkowski
 *
 */
public class CustomerProfilPage {

	private WebDriver driver;
	
	@FindBy(name="email")
	WebElement emailAdresse;
	
	
	/**
	 * Initialisiert ein Pageobjekt für die Profilseite
	 * @param driver WebDriver
	 */
	public CustomerProfilPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Bekomme die Emailadresse des Kunden aus dem Kundenprofil
	 * @return Emailadresse des Kunden
	 */
	public String getEmailAdresse() {
		return emailAdresse.getText();
	}
	
	
}


