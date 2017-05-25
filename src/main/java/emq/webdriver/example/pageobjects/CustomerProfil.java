package emq.webdriver.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerProfil {

	private WebDriver driver;
	
	@FindBy(name="email")
	WebElement emailAdresse;
	
	public CustomerProfil(WebDriver driver) {
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


