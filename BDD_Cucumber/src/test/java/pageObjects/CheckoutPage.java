package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

	CheckoutPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//img[@alt='Cart']")WebElement Cart;
	
	@FindBy(xpath="//button[text()='PROCEED TO CHECKOUT']")WebElement PROCEEDTOCHECKOUT;
	
	@FindBy(xpath="//button[text()='Apply']")WebElement Apply;
	
	@FindBy(xpath="//button[text()='Place Order']")WebElement PlaceOrder;
	
	public void checkOutItems() {
		Cart.click();
		PROCEEDTOCHECKOUT.click();
	}
	
	public boolean VerifypromoBtn() {
		return Apply.isDisplayed();
		
	}
	
	public boolean VerifyPlaceOrder() {
		return PlaceOrder.isDisplayed();
		
	}
	

}
