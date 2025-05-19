package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class OffersPage extends BasePage {
	public OffersPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='search-field']")WebElement search;
	@FindBy(linkText="Top Deals")WebElement OffersLink;
	@FindBy (xpath="//tbody//tr//td[text()='Tomato']") WebElement serachedProductATOffersPage;
	public void enterinputIntoSearchTextField(String sdss) {
		search.sendKeys(sdss);
	}
	
	public void clickOnTopDealsLink() {
		OffersLink.click();
	}
	
	public String getSerachedProductATOffersPage() {
		
		String serachedOuptput=serachedProductATOffersPage.getText();
		
		return serachedOuptput;
	}
}

