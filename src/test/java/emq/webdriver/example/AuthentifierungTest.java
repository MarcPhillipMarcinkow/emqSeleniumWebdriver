package emq.webdriver.example;

import org.junit.Assert;
import org.junit.Test;

import emq.webdriver.example.pageobjects.Login;
import emq.webdriver.example.pageobjects.MainPage;
import emq.webdriver.example.pageobjects.Registration;

public class AuthentifierungTest extends AbstractEMQ {

	//  Pageobject for the main page
	MainPage mp = new MainPage(driver);
	
	/*
	 * temporary disabled, we dont need more accounts... 
	 */
	// @Test
	public void registration() {
		mp.openPage();

		Registration reg = new Registration(driver);
		reg.openPage();
		reg.fillRegistrationForm();
	}

	@Test
	public void login() {
		mp.openPage();

		Login login = new Login(driver);
		login.openPage();
		
		/**
		 * login with wrong data
		 */
		
		login.login("asdf", "aas2fds");
		Assert.assertTrue(login.getDangerAlertMessage().contains("Ungültige Zugangsdaten."));
		
		/**
		 * login with right data
		 */
		
		login.login("MaxMustermann@discardmail.com", "Max");

		/*
		 * checks if the login was successful by checking the redirect
		 */
		Assert.assertTrue(driver.getCurrentUrl().equals("https://onlinechilishop.de/customer/"));
		
		login.logout();
		
		/*
		 * checks if the logout was successful
		 */
		Assert.assertTrue(driver.getCurrentUrl().equals("https://onlinechilishop.de/login/?logout"));
		Assert.assertTrue(login.getSuccessAlertMessage().contains("Erfolgreich ausgeloggt"));
	}
}
