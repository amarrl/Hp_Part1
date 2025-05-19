package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

	public LandingPage(WebDriver driver) {
		super(driver);
	
	}
	
	@FindBy(xpath="//input[@type='search']")WebElement search;
	@FindBy(xpath="//h4[@class='product-name']")WebElement searchedProductText;
	@FindBy(xpath="//a[@class='increment']")WebElement increment;
	@FindBy(xpath="//button[text()='ADD TO CART']")WebElement Addtocart;
	
	
	
	 
	public void enterinputIntoSearchTextField(String sds) {
		search.sendKeys(sds); 
	}

	public String getSearchedProductName() {
		String searchedProductName= searchedProductText.getText();
		return searchedProductName;
		
	}
	
	public void incrementQnty(int qnty) {
		int i=qnty-1;
		while(i>0) {
			increment.click();
			i--;
		}
	}
	
	public void addTOCart() {
		Addtocart.click();
	}
	
	
}
