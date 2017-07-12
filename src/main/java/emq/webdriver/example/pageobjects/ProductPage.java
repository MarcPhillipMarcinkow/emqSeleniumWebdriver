package emq.webdriver.example.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Pageobjekt für die Produktseite. Diese Extended als Beispiel die MainPage, welches die Elemente wie Warenkorb schon definiert hat,
 * da dieses dann auch in weiteren Methoden von anderen Pageobjects verwendet werden können
 * @author Marc Philipp Marcinkowski
 *
 */
public class ProductPage  {

	private WebDriver driver;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Definierung von Webelementen
	 */

	@FindBy(id = "addToCartButton")
	WebElement addtoCartButton;

	@FindBy(id = "salePrice")
	WebElement salePrice;

	@FindBy(id = "cartSum")
	WebElement cartSum;

	@FindBy(id = "amount")
	WebElement productAmount;

	@FindBy(className = "delete")
	WebElement deleteButton;
	
	@FindBy(id = "cartCount")
	WebElement cartCount;

	/**
	 * Initialisiert ein Pageobjekt einer Produktseite. Dieses beinhaltet momentan das Befüllen des Warenkorbs, so wie dessen Funktionalität
	 * @param driver WebDriver
	 */
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Öffnet die Productseite eines Produktes
	 * 
	 * @param productNumber
	 *            die ProduktId, welche in der URL mit ?productNr=
	 *            gekennzeichnet ist
	 */
	public void openProductPage(String productNumber) {
		logger.info("navigiere zu https://onlinechilishop.de/products/?productNr=" + productNumber);
		driver.navigate().to("https://onlinechilishop.de/products/?productNr=" + productNumber);
	}

	/**
	 * Klickt auf den Button zum Warenkorb hinzufügen
	 */
	public void addToCart() {
		logger.info("klicke zum Warenkorb hinzufügen");
		addtoCartButton.click();
	}

	/**
	 * Prüft ob der Warenkorb eine bestimmte Summe hat
	 * @param expectedSum Erwartete Summe des Warenkorbs
	 * @return true, falls ja, sonst false
	 */
	public Boolean expectCartSumToBe(String expectedSum) {
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElement(cartSum, expectedSum));
	}
	
	/**
	 * Gibt die Summe des Warenkorbs als float zurück
	 * @return Summe des Warenkorbs als float
	 */
	public Double getCartSum() {
		Double sum = Double.parseDouble(cartSum.getText().substring(2).replace(",", "."));
		logger.info("Aktuelle Summe im Warenkorb: "+ sum);
		return sum;
	}


	/**
	 * Überprüft, ob die Anzahl der Produkte im Warenkorb sich ändern
	 * 
	 * @param amount
	 *            die Amzahl der zubeinhaltenen Produkte
	 * @return true wenn die Anzahl sich geändert hat, sonst false
	 */
	public Boolean checkCartForHavingItems(String amount) {
		return (new WebDriverWait(driver, 20)).until(ExpectedConditions.textToBePresentInElement(cartCount, amount));
	}

	
	/**
	 * Bekomme den Preis des Produktes
	 * 
	 * @return Der Preis des Produkts
	 */
	public String getSellPriceAsString() {
		return salePrice.getText();
	}
	
	/**
	 * Bekomme den Preis des Produktes
	 * 
	 * @return Der Preis des Produkts
	 */
	public Double getSellPriceAsDouble() {
		return Double.parseDouble(salePrice.getText().substring(2).replace(",", "."));
	}

	/**
	 * Bekomme die Anzahl der Produkte, die im Warenkorb sind
	 * 
	 * @return Die Anzahl der Produkte im Warenkorb
	 */
	public int getCartCount() {
		return Integer.parseInt(cartCount.getText());
	}
	
	/**
	 * Setzt die Anzahl der zu Kaufenden Menge
	 * 
	 * @param value
	 *            Die Menge, wie sie im Dropdown zu finden ist
	 */
	public void setAmount(String value) {
		logger.info("Ändere die Menge im Dropdown");
		Select select = new Select(productAmount);
		select.selectByVisibleText(value);
	}


	/**
	 * Klickt auf den Delete-Button, um das Produkt aus dem Warenkorb zu nehmen
	 */
	public void clickDeleteButton() {
		logger.info("Klicke den delete Button");
		deleteButton.click();
	}
	
	/**
	 * Bekomme die Summe aus dem Warenkorb
	 */
}
