package emq.webdriver.example;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import emq.webdriver.example.pageobjects.CustomerProfil;
import emq.webdriver.example.pageobjects.Login;
import emq.webdriver.example.pageobjects.MainPage;
import emq.webdriver.example.pageobjects.Registration;

/**
 * Diese Klasse beinhaltet die Testfälle für die Authentifizierung, da die Registrierung nur einmalig erfolgt,
 * wurde dieser Testcase nicht weiter bearbeitet und ist deaktiviert. Für das Login wird erfolgreiches und unerfolgreiches Einloggen getestet.
 * Desweiteren wird überprüft, ob im Profil einige Daten stimmen und ob das Verhalten im eingelogten zustand richtig ist.
 * Am Ende wird sich ausgeloggt.
 * 
 * @author Marc
 *
 */
public class AuthentifierungTest extends AbstractEMQ {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	// Pageobject for the main page
	MainPage mp = new MainPage(driver);

	/*
	 * Temporär deaktiviert, da schon viele Test-Accounts erstellt wurden
	 */
	//@Test
	public void registration() {
		mp.openPage();

		Registration reg = new Registration(driver);
		reg.openPage();
		reg.fillRegistrationForm();
	}

	@Test
	public void login() {
		
		String emailAdresse = "MaxMustermann@discardmail.com";
		String password = "Max";
		
		//öffnet die Startseite
		mp.openPage();
		mp.clickLoginButton();

		Login login = new Login(driver);
		
		//prüft ob der Redirekt zur Loginmaske funktionierte
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));

		/**
		 * Login mit falschen Daten
		 */

		logger.info("Login mit ungültigen Daten");
		login.login("asdf", "aas2fds");
		logger.info("Überprüfe ob in der Loginmaske der Error ein Error erscheint");
		Assert.assertTrue(login.getDangerAlertMessage().contains("Ungültige Zugangsdaten."));

		/**
		 * Login mit richtigen Daten
		 */

		logger.info("Login mit ungültigen Daten");
		login.login(emailAdresse, password);

		
		logger.info("Überprüfe ob der redirect und somit der Login erfolgreich war");
		Assert.assertTrue(driver.getCurrentUrl().equals("https://onlinechilishop.de/customer/"));
		
		logger.info("Überprüft ob der Benutzerprofil-Button nun sichtbar ist");
		Assert.assertTrue(mp.isLoggedIn());
		
		logger.info("Prüft ob die richtigen Kundendaten angezeigt werden");
		CustomerProfil cp = new CustomerProfil(driver);
		Assert.assertEquals(cp.getEmailAdresse(), emailAdresse);
		
		
		mp.clickLogoutButton();

		/*
		 * Überprüft ob der Logout erfolgreich war
		 */
		logger.info("Überprüfe ob der redirect und somit der Logout erfolgreich war");
		Assert.assertTrue(driver.getCurrentUrl().equals("https://onlinechilishop.de/login/?logout"));
		logger.info("Überprüfe ob die success Nachricht richtig ist");
		Assert.assertTrue(login.getSuccessAlertMessage().contains("Erfolgreich ausgeloggt"));
		logger.info("überprüfe ob der Loginbutton wieder angezeigt wird");
		Assert.assertTrue(mp.loginButtonIsDisplayed());
	}
}
