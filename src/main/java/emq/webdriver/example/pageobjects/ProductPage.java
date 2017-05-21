package emq.webdriver.example.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

	private WebDriver driver;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Definierung von Webelementen
	 */

	@FindBy(id = "addToCartButton")
	WebElement addtoCartButton;

	@FindBy(id = "salePrice")
	WebElement salePrice;

	@FindBy(id = "cartCount")
	WebElement cartCount;

	@FindBy(id = "cartSum")
	WebElement cartSum;

	@FindBy(id = "amount")
	WebElement productAmount;

	@FindBy(id = "changeAmountButton")
	WebElement changeAmountButton;

	@FindBy(className = "delete")
	WebElement deleteButton;

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
	 * Bekomme die Anzahl der Produkte, die im Warenkorb sind
	 * 
	 * @return Die Anzahl der Produkte im Warenkorb
	 */
	public int getCartCount() {
		return Integer.parseInt(cartCount.getText());
	}

	/**
	 * Bekomme die Summe der Bestellung
	 * 
	 * @return Die Summe der Bestellung
	 */
	public String getCartSum() {
		return cartSum.getText();
	}

	/**
	 * Bekomme den Preis des Produktes
	 * 
	 * @return Der Preis des Produkts
	 */
	public String getSellPrice() {
		return salePrice.getText();
	}

	/**
	 * Überprüft, ob die Anzahl der Produkte im Warenkorb sich ändern
	 * 
	 * @param amount
	 *            die Amzahl der zubeinhaltenen Produkte
	 * @return true wenn die Anzahl sich geändert hat, sonst false
	 */
	public Boolean checkCartForHavingItems(String amount) {
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElement(cartCount, amount));
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
	 * Klickt auf den Refresh-Button, um die Änderung der Menge anzustoßen
	 */
	public void clickChangeAmountButton() {
		logger.info("Klicke den Button zum Ändern der Anzahl");
		changeAmountButton.click();
	}

	/**
	 * Klickt auf den Delete-Button, um das Produkt aus dem Warenkorb zu nehmen
	 */
	public void clickDeleteButton() {
		logger.info("Klicke den delete Button");
		deleteButton.click();
	}
}
