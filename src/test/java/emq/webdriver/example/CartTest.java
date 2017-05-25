package emq.webdriver.example;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import emq.webdriver.example.pageobjects.MainPage;
import emq.webdriver.example.pageobjects.ProductPage;

/**
 * Dieser Test testet den Warenkorb. Dabei wird ein Produkt zum Warenkorb hinzugefügt und das richtige Verhalten der Seite überprüft.
 * Es wird auch die Anzahl geändert, sowie das Produkt aus dem Warenkorb entfernt.
 * @author Marc Philipp Marcinkowski
 *
 */
public class CartTest extends AbstractEMQ {
	
private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Test
	public void addToCart() {
		
		String menge = "2 Stück";
		String productId = "96";
		
		MainPage mp = new MainPage(driver);
		ProductPage pp = new ProductPage(driver);
		
		logger.info("Navigiere zur Hauptseite");
		mp.openPage();
		
		logger.info("Öffne die Produktseite");
		pp.openProductPage(productId);
		
		logger.info("Überprüfe das der Warenkorb leer ist");
		Assert.assertTrue(pp.getCartCount() == 0);
		
		logger.info("Füge das Produkt zum Warenkorb hinzu");
		pp.addToCart();
		
		logger.info("Überprüfe das der Warenkorb inkrementiert wurde");
		Assert.assertTrue(pp.checkCartForHavingItems("1"));
		logger.info("Produkt wurde hinzugefügt");
		
		logger.info("Vergleiche die Summe des Warenkorbs mit dem Artikelpreis");
		Assert.assertTrue(pp.expectCartSumToBe(pp.getSellPrice()));
		
		logger.info("Ändere die Menge auf " +menge);
		pp.setAmount(menge);
		pp.clickChangeAmountButton();
		logger.info("Überprüfe ob die Anzahl im Warenkorb nun 2 ist");
		Assert.assertTrue(pp.checkCartForHavingItems("2"));
		
		pp.clickDeleteButton();
		logger.info("Überprüfe ob die Anzahl im Warenkorb nun 0 ist");
		Assert.assertTrue(pp.checkCartForHavingItems("0"));
	
	
		
		
		
		
	}

}
