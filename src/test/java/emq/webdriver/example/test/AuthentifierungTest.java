package emq.webdriver.example.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import emq.webdriver.example.pageobjects.CustomerProfilPage;
import emq.webdriver.example.pageobjects.LoginPage;
import emq.webdriver.example.pageobjects.MainPage;
import emq.webdriver.example.pageobjects.RegistrationPage;

/**
 * Diese Klasse beinhaltet die Testfälle für die Authentifizierung, da die Registrierung nur einmalig erfolgt,
 * wurde dieser Testcase nicht weiter bearbeitet und ist deaktiviert. Für das Login wird erfolgreiches und unerfolgreiches Einloggen getestet.
 * Desweiteren wird überprüft, ob im Profil einige Daten stimmen und ob das Verhalten im eingelogten zustand richtig ist.
 * Am Ende wird sich ausgeloggt.
 * 
 * Der Testcase richtet sich nach den beiden Testcases für die Registrierung und Login, dessen Ablauf im Portfolio tabellarisch abgebildet ist.
 * 
 * @author Marc Philipp Marcinkowski
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
	public void registrationTest() {
		mp.openPage();
		mp.clickRegistrationButton();
		RegistrationPage reg = new RegistrationPage(driver);
		
		reg.fillRegistrationForm();
	}

	@Test
	public void loginTest() {
		
		String emailAdresse = "hans_petar@web.de";
		String password = "123";
		
		//öffnet die Startseite
		mp.openPage();
		mp.clickLoginButton();

		LoginPage login = new LoginPage(driver);
		
		//prüft ob der Redirekt zur Loginmaske funktionierte
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));

		/**
		 * Login mit falschen Daten
		 */

		logger.info("Login mit ungültigen Daten");
		login.login("asdf", "aas2fds");
		logger.info("Überprüfe ob in der Loginmaske ein Error erscheint");
		Assert.assertTrue(login.getDangerAlertMessage().contains("Ungültige Zugangsdaten."));
		logger.info("Errornachricht ist: "+login.getDangerAlertMessage());
		/**
		 * Login mit richtigen Daten
		 */

		logger.info("Login mit gültigen Daten");
		login.login(emailAdresse, password);

		
		logger.info("Überprüfe ob der redirect und somit der Login erfolgreich war");
		Assert.assertTrue(driver.getCurrentUrl().equals("https://onlinechilishop.de/customer/"));
		
		logger.info("Überprüft ob der Benutzerprofil-Button nun sichtbar ist");
		Assert.assertTrue(mp.isLoggedIn());
		
		logger.info("Prüft ob die richtigen Kundendaten angezeigt werden");
		CustomerProfilPage cp = new CustomerProfilPage(driver);
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
