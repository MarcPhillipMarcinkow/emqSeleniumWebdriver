package emq.webdriver.example;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import emq.webdriver.example.pageobjects.MainPage;

@RunWith(Parameterized.class)
public class searchTest extends AbstractEMQ {

	private String searchkeywoard;
	private int resultcount;
	
	// Pageobject for the main page
	private MainPage mp = new MainPage(driver);
	
	/**
	 * Parameters which are used to run the test multiple times with diffrent data
	 * @return collection with the data
	 */
	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { "chili", 1 },
                 { "samen", 1 }, 
                 { "asdfasdf", 0 }, 
           });
        
    }
    
    /**
     * Constructor which uses the collection for the parameterization 
     * @param keyword is the keywoard for the search
     * @param results are minimum amount of found items
     */
    public searchTest(String keyword, int results) {
    	searchkeywoard= keyword;
    	resultcount = results;
    }
	
    /**
     * Opens the page before the main test starts, so it just have to take place once
     */
    @Before
    public void openPage() {
		mp.openPage();
    }
    
    /**
     * The main test
     */
	@Test
	public void Search() {
		mp.enterKeywordInSearchfield(searchkeywoard);
		mp.waitForSearchResults();
		
		if (resultcount > 0) { 
		Assert.assertTrue(mp.getResultsCount() > 0);
		}
		else { // no searchresults
			Assert.assertTrue(mp.getResultsCount() == 0);
		}
	}
}
