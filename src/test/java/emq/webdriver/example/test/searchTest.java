package emq.webdriver.example.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import emq.webdriver.example.pageobjects.MainPage;

/**
 * Diese Klasse ist ein Testcase für die Suche. Diese wird Parametrisiert, um
 * die Vorteile von Webdriver zu verdeutlichen. Bei einer PArametrisierung wird
 * der Testcase mit verschiedenen Inputdaten wiederholt, die vorher definiert
 * werden
 * 
 * Der Testcase richtet sich nach den Testcase für die Suche, dessen Ablauf im Portfolio tabellarisch abgebildet ist.
 * 
 * @author Marc Philipp Marcinkowski
 *
 */
@RunWith(Parameterized.class)
public class searchTest extends AbstractEMQ {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private String searchkeyword;
	private int resultcount;

	// Pageobject for the main page
	private MainPage mp = new MainPage(driver);

	/**
	 * Parameter, welche für die Suche verwendet werden. Die einzelnen Objekte
	 * beinhalten den Suchbegriff, sowie die minimale Anzahl zu findener
	 * Ergebnisse
	 * 
	 * @return collection mit den verschiedenen Input-Daten
	 */
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
			{ "chili", 1 }, // Vollständiger Suchbegriff
			{ "chi", 1 },  // Unvollständiger Suchbegriff
			{ "asdfasdf", 0 }, // Fehlerhafter Suchbegriff
			{ "96", 1 } //Artikelnummer (Produkt ID) als Suchbegriff
			});
	}

	/**
	 * Konstruktur des Testcases, welcher für die Parametrisierung benötigt
	 * wird.
	 * 
	 * @param keyword
	 *            Der zu verwendene Suchbegriff
	 * @param results
	 *            die minimale Anzahl zu findener Ergebnisse
	 */
	public searchTest(String keyword, int results) {
		searchkeyword = keyword;
		resultcount = results;
	}

	/**
	 * Öffnet die Startseite bevor der Testcase stattfindet, damit dies nicht
	 * für jeden Lauf in der Parametrisierung geschehen muss.
	 */
	@Before
	public void openPage() {
		mp.openPage();
	}

	/**
	 * Der Haupttestfall, welcher die definierten Parameter searchkeyword und resultcount verwendet und in
	 * diesen Beispiel 3x Ausgeführt wird.
	 * 
	 */
	@Test
	public void SearchTest() {
		logger.info("Suchtest für: " + searchkeyword);
		mp.enterKeywordInSearchfield(searchkeyword);
		logger.info("Warte auf Suchergebnisse");
		mp.waitForSearchResults();

		/*
		 * Falls die minimale Anzahl der Ergebnisse über 0 ist wurden Ergebnisse
		 * gefunden
		 */
		if (resultcount > 0) {
			Assert.assertTrue(mp.getResultsCount() > 0);
			logger.info("Suchtest für: " + searchkeyword + " erfolgreich");
		} else {
			/*
			 * Falls die minimale Anzahl der Ergebnisse gleich 0 ist, wurden
			 * keine Ergebnisse gefunden
			 */
			Assert.assertTrue(mp.getResultsCount() == 0);
			logger.info("Suchtest für: " + searchkeyword + " erfolgreich");
		}
		
		logger.info("Klicke den Resetbutton für die Suche");
		assertTrue(mp.deleteSearchButtonIsDisplayed());
		mp.clickDeleteSearchButton();
		logger.info("Überprüfe ob das Suchfeld leer ist");
		Assert.assertTrue(mp.SearchfieldIsEmpty());
		
	}
}
