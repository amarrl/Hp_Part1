package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AmazonHome extends BasePage {

	public AmazonHome(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//a[@id='nav-logo-sprites']")WebElement logo;
	
	
	
	
	public void logoDisplay() {
		if(logo.isDisplayed()) {
		Assert.assertTrue(true);
		}
		else {
			Assert.assertFalse(false);
		}
	}
}
