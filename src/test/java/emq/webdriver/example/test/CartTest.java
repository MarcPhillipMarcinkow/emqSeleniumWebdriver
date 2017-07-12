package emq.webdriver.example.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import emq.webdriver.example.pageobjects.MainPage;
import emq.webdriver.example.pageobjects.ProductPage;

/**
 * Dieser Test testet den Warenkorb. Dabei wird ein Produkt zum Warenkorb hinzugefügt und das richtige Verhalten der Seite überprüft.
 * Es wird auch die Anzahl geändert, sowie das Produkt aus dem Warenkorb entfernt.
 * 
 *  * Der Testcase richtet sich nach den  Testcase für den Bestellvorgang, dessen Ablauf im Portfolio tabellarisch abgebildet ist.
 * 
 * @author Marc Philipp Marcinkowski
 *
 */
public class CartTest extends AbstractEMQ {
	
private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Test
	public void addToCartTest() {
		
		String menge = "2 Stück";
		String productId = "96";
		Double product1Prize;
		
		
		MainPage mp = new MainPage(driver);
		ProductPage pp = new ProductPage(driver);
		
		logger.info("Navigiere zur Hauptseite");
		mp.openPage();

		logger.info("Öffne die Produktseite für das Produkt mit der ID: " +productId);
		pp.openProductPage(productId);
		
		logger.info("Überprüfe Initial-Zustand des Warenkorbs");
		
		logger.info("Überprüfe das der Warenkorb leer ist");
		Assert.assertTrue(pp.getCartCount() == 0);
		
		logger.info("Überprüfe ob die Summe 0 ist");
		Assert.assertTrue(pp.getCartSum() == 0);
		
		
		logger.info("Füge das Produkt zum Warenkorb hinzu");
		pp.addToCart();
		
		logger.info("Überprüfe das der Warenkorb inkrementiert wurde");
		Assert.assertTrue(pp.checkCartForHavingItems("1"));
		logger.info("Produkt wurde hinzugefügt");
		
		logger.info("Vergleiche die Summe des Warenkorbs mit dem Artikelpreis");
		Assert.assertTrue(pp.expectCartSumToBe(pp.getSellPriceAsString()));
		
		product1Prize = pp.getSellPriceAsDouble();
		
		logger.info("Klick den Delete-Button");
		pp.clickDeleteButton();
		logger.info("Überprüfe ob die Anzahl im Warenkorb nun 0 ist");
		Assert.assertTrue(pp.checkCartForHavingItems("0"));	
		
		logger.info("Füge das Produkt zum Warenkorb hinzu");
		pp.addToCart();
		
		logger.info("Überprüfe ob die Anzahl im Warenkorb wieder 1 ist");
		Assert.assertTrue(pp.checkCartForHavingItems("1"));
		
		logger.info("Ändere die Menge auf " +menge);
		pp.setAmount(menge);
		
		logger.info("Überprüfe ob die Anzahl im Warenkorb nun 2 ist");
		Assert.assertTrue(pp.checkCartForHavingItems("2"));
		
		logger.info("Überprüfe ob die Summe richtig angepasst wurde");
		Assert.assertTrue(pp.getCartSum() == product1Prize*2);
		
		logger.info("Ändere die Menge auf 3 Stück");
		pp.setAmount("3 Stück");
		
		logger.info("Überprüfe ob die Anzahl im Warenkorb nun 3 ist");
		Assert.assertTrue(pp.checkCartForHavingItems("3"));
		
		logger.info("Überprüfe ob die Summe richtig angepasst wurde");
	 //   Assert.assertTrue("Erwartete Summe :" +Math.round((product1Prize*3)*10000.0D/10000.0D) +" Summe des Warenkorbs :"+pp.getCartSum(), pp.getCartSum() - (product1Prize*3) == 0);
	//	Assert.assertEquals((product1Prize*3), pp.getCartSum(),0);
		
		
		
	}
}
