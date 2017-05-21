package emq.webdriver.example;

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
 * die Vorteile von Webdriver zu verdeutlichen
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
	 * @return collection with the data
	 */
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "chili", 1 }, { "samen", 1 }, { "asdfasdf", 0 }, });

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
	 * Der Haupttestfall, welcher die definierten Parameter verwendet und in
	 * diesen Beispiel 3x Ausgeführt wird.
	 */
	@Test
	public void Search() {
		mp.enterKeywordInSearchfield(searchkeyword);
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
	}
}
