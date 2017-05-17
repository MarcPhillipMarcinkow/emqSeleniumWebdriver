package emq.webdriver.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author Marc Philipp Marcinkowski
 */
public class MainPage {

	private WebDriver driver;
	
	@FindBy(id="searchInput")
	WebElement searchField;
	
	@FindBy(id="searchResult")
	WebElement searchResults;
	
	@FindBy(id="resultCount")
	WebElement resultCount;
	
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public void openPage() {
		driver.get("https://daniel:%23chili@onlinechilishop.de/");
	}
	
	public void enterKeywordInSearchfield(String keyword) {
		searchField.sendKeys(keyword);
	}
	
	public void waitForSearchResults() {
		WebElement searchResults2 = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.visibilityOf(searchResults));
	}
	
	public int getResultsCount() {
		System.out.println(resultCount.getText());
		return Integer.parseInt(resultCount.getText());
	}
}
